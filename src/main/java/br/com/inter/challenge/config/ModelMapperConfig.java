package br.com.inter.challenge.config;

import br.com.inter.challenge.converter.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new UserDtoToUserConverter());
        modelMapper.addConverter(new UserToUserDtoConverter());
        modelMapper.addConverter(new SingleDigitDtoToSingleDigitConverter());
        modelMapper.addConverter(new SingleDigitToSingleDigitDtoConverter());
        modelMapper.addConverter(new UserEncryptionDtoToUserEncryptionConverter());
        return modelMapper;
    }
}
