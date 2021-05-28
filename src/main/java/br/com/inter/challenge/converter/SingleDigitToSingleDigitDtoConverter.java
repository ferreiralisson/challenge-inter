package br.com.inter.challenge.converter;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.SingleDigitDTO;
import br.com.inter.challenge.dto.UserDTO;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class SingleDigitToSingleDigitDtoConverter implements Converter<SingleDigit, SingleDigitDTO> {

    @Override
    public SingleDigitDTO convert(MappingContext<SingleDigit, SingleDigitDTO> mappingContext) {
        SingleDigit singleDigit = mappingContext.getSource();

        return SingleDigitDTO.builder()
                .id(singleDigit.getId())
                .numberRepresentation(singleDigit.getNumberRepresentation())
                .numberOfRepetitions(singleDigit.getNumberOfRepetitions())
                .result(singleDigit.getResult())
                .user(singleDigit.getUser() != null ? getUser(singleDigit.getUser()) : null)
                .build();
    }

    private UserDTO getUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
