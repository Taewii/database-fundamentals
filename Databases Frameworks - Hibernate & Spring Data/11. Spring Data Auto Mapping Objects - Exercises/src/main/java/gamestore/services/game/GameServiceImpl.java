package gamestore.services.game;

import gamestore.models.dtos.AddGameDto;
import gamestore.models.entities.Game;
import gamestore.models.utils.ObjectValidator;
import gamestore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String add(AddGameDto addGame) {
        try {
            Game game = this.modelMapper.map(addGame, Game.class);
            ObjectValidator.validate(game);
            this.gameRepository.saveAndFlush(game);
            return String.format("Added %s", game.getTitle());
        } catch (RuntimeException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public Game getById(long id) {
        return this.gameRepository.findGameById(id);
    }

    @Override
    public String update(Game game, String... tokens) {
        Map<String, String> values = new HashMap<>();
        for (String token : tokens) {
            String[] valueSet = token.split("=");
            values.put(valueSet[0], valueSet[1]);
        }

        for (Map.Entry<String, String> entry : values.entrySet()) {
            switch (entry.getKey()) {
                case "size":
                    game.setSize(Double.parseDouble(entry.getValue()));
                    break;
                case "title":
                    game.setTitle(entry.getValue());
                    break;
                case "price":
                    game.setPrice(new BigDecimal(entry.getValue()));
                    break;
                case "trailer":
                    game.setYoutubeTrailer(entry.getValue());
                    break;
                case "thumbnail":
                    game.setImageURL(entry.getValue());
                    break;
                case "description":
                    game.setDescription(entry.getValue());
                    break;
                case "releaseDate":
                    try {
                        game.setReleaseDate(new SimpleDateFormat("dd-MM-yyyy").parse(entry.getValue()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        this.gameRepository.saveAndFlush(game);
        return String.format("Edited %s", game.getTitle());
    }

    @Override
    public String delete(Game game) {
        String tile = game.getTitle();
        this.gameRepository.delete(game);
        return String.format("Deleted %s", tile);
    }
}
