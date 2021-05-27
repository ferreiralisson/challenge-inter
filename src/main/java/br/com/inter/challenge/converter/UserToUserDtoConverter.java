package br.com.inter.challenge.converter;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;

public class UserToUserDtoConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(MappingContext<User, UserDTO> mappingContext) {
        User user = mappingContext.getSource();
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .singleDigitList(user.getSingleDigitList())
                .build();
    }
}
