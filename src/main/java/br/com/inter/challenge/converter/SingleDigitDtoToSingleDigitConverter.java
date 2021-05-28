package br.com.inter.challenge.converter;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.SingleDigitDTO;
import br.com.inter.challenge.dto.UserDTO;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class SingleDigitDtoToSingleDigitConverter implements Converter<SingleDigitDTO, SingleDigit> {

    @Override
    public SingleDigit convert(MappingContext<SingleDigitDTO, SingleDigit> mappingContext) {
        SingleDigitDTO singleDigitDTO = mappingContext.getSource();

        return SingleDigit.builder()
                .numberRepresentation(singleDigitDTO.getNumberRepresentation())
                .numberOfRepetitions(singleDigitDTO.getNumberOfRepetitions())
                .result(singleDigitDTO.getResult())
                .user(singleDigitDTO.getUser() != null ? getUser(singleDigitDTO.getUser()): null)
                .build();
    }

    private User getUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }
}
