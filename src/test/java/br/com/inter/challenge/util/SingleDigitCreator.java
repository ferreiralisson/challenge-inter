package br.com.inter.challenge.util;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.SingleDigitDTO;

public class SingleDigitCreator {

    public static SingleDigit singleDigitCreateValidWithUser(){
        return SingleDigit.builder()
                .id(1L)
                .numberRepresentation("9875")
                .numberOfRepetitions(1)
                .result(29)
                .user(createUserValid())
                .build();
    }

    public static SingleDigit singleDigitCreateValidWithoutIdUser(){
        return SingleDigit.builder()
                .id(1L)
                .numberRepresentation("9875")
                .numberOfRepetitions(1)
                .result(29)
                .build();
    }

    public static SingleDigitDTO singleDigitDtoCreateValidWithoutIdUser(){
        return SingleDigitDTO.builder()
                .id(1L)
                .numberRepresentation("9875")
                .numberOfRepetitions(1)
                .result(29)
                .build();
    }

    public static SingleDigitDTO singleDigitDtoTobeSaveWithoutIdUser(){
        return SingleDigitDTO.builder()
                .numberRepresentation("9875")
                .numberOfRepetitions(1)
                .build();
    }

    public static SingleDigitDTO singleDigitDtoTobeSaveWithIdUser(){
        return SingleDigitDTO.builder()
                .numberRepresentation("9875")
                .numberOfRepetitions(1)
                .idUser(1L)
                .build();
    }

    public static User createUserValid(){
        return User.builder()
                .id(1L)
                .name("alisson")
                .email("email@example.com")
                .build();
    }
}
