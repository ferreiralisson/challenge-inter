package br.com.inter.challenge.testutil;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;

public class UserCreator {

    public static User createUserToBeSaved(){
        return User.builder()
                .name("alisson")
                .email("email@example.com")
                .build();
    }

    public static User createUserValid(){
        return User.builder()
                .id(1L)
                .name("alisson")
                .email("email@example.com")
                .build();
    }

    public static UserDTO createUserDtoValid(){
        return UserDTO.builder()
                .id(1L)
                .name("alisson")
                .email("email@example.com")
                .build();
    }

    public static UserDTO createUserDtoToBeSaved(){
        return UserDTO.builder()
                .id(1L)
                .name("alisson")
                .email("email@example.com")
                .build();
    }

    public static UserDTO createUserDtoToBeUpdated(){
        return UserDTO.builder()
                .id(1L)
                .name("alisson2")
                .email("email2@example.com")
                .build();
    }
}
