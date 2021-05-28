package br.com.inter.challenge.service.impl;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import br.com.inter.challenge.exception.BadRequestException;
import br.com.inter.challenge.repository.UserRepository;
import br.com.inter.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static final String USER_NOT_FOUND = "User not found";

    @Override
    public UserDTO create(UserDTO userDTO) {
        User userMapper = modelMapper.map(userDTO, User.class);
        User userSaved = userRepository.save(userMapper);
        return modelMapper.map(userSaved, UserDTO.class);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return new PageImpl<>(userPage.stream().collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public UserDTO findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void update(UserDTO userDTO) {
        User userFound = userRepository.findById(userDTO.getId()).orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));
        userFound.setName(userDTO.getName());
        userFound.setEmail(userFound.getEmail());
        userRepository.save(userFound);
    }

    @Override
    public void delete(long id) {
        UserDTO userDtoFound = findById(id);
        userRepository.deleteById(userDtoFound.getId());
    }
}
