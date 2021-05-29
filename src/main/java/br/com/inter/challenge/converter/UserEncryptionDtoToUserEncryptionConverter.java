package br.com.inter.challenge.converter;

import br.com.inter.challenge.domain.UserEncryption;
import br.com.inter.challenge.dto.UserEncryptionDTO;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class UserEncryptionDtoToUserEncryptionConverter implements Converter<UserEncryptionDTO, UserEncryption> {
    @Override
    public UserEncryption convert(MappingContext<UserEncryptionDTO, UserEncryption> mappingContext) {
        UserEncryptionDTO userEncryptionDTO = mappingContext.getSource();
        return UserEncryption.builder()
                .publicKey(userEncryptionDTO.getPublicKey())
                .privateKey(userEncryptionDTO.getPrivateKey())
                .idUser(userEncryptionDTO.getIdUser() != null ? userEncryptionDTO.getIdUser() : null)
                .build();
    }
}
