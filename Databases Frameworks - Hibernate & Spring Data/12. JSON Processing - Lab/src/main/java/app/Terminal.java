package app;

import app.domain.dto.PersonDto;
import app.services.PersonService;
import app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final PersonService personService;
    private final JsonParser jsonParser;

    @Autowired
    public Terminal(PersonService personService, JsonParser jsonParser) {
        this.personService = personService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... strings) {
        PersonDto[] personDtos = this.jsonParser.getFromFile(PersonDto[].class, "json/input/persons.json");
        for (PersonDto personDto : personDtos) {
            this.personService.create(personDto);
        }

        PersonDto personDto = this.personService.getPersonDtoFromId(1);
        this.jsonParser.writeToFile(personDto, "json/output/person.json"); // target/classes/json/output/person.json
        System.out.println(this.jsonParser.toJson(personDto));
    }
}
