package br.com.inter.challenge.converter;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;

public class UserDtoToUserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(MappingContext<UserDTO, User> mappingContext) {
        UserDTO userDTO = mappingContext.getSource();
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .singleDigitList(userDTO.getSingleDigitList() != null ? userDTO.getSingleDigitList() : new ArrayList<>())
                .build();
    }
}
