package app.services.user;

import app.models.dtos.binding.UserDto;
import app.models.dtos.view.SuccessfulUserDto;
import app.models.dtos.view.query4.UsersCountAndUsersDto;
import app.models.entities.User;
import app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void saveAll(UserDto[] usersDto) {
		User[] users = this.modelMapper.map(usersDto, User[].class);
		this.userRepository.saveAll(Arrays.asList(users));

		for (int i = 0; i < users.length * 2; i++) {
			User user1 = this.userRepository.getRandomUser();
			User user2 = this.userRepository.getRandomUser();
			if (user1 != null && user2 != null && user1 != user2) {
				user1.getFriends().add(user2);
				user2.getFriends().add(user1);
			}
		}
	}

	@Override
	public List<SuccessfulUserDto> usersWithAtleastOneProductSold() {
		List<User> users = this.userRepository.usersWithAtleastOneProductSold();
		return users.stream()
				.map(u -> this.modelMapper.map(u, SuccessfulUserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UsersCountAndUsersDto userCountWithUsersAndProductCountWithProducts() {

		return null;
	}

}