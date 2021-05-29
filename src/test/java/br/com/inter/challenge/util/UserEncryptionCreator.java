package br.com.inter.challenge.util;

import br.com.inter.challenge.domain.UserEncryption;
import br.com.inter.challenge.dto.UserEncryptionDTO;

public class UserEncryptionCreator {


    public static UserEncryptionDTO userEncryptionDTOToBeSaved(){
        return UserEncryptionDTO.builder()
                .publicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIr8hR0wZcBCu7")
                .build();
    }

    public static UserEncryptionDTO userEncryptionDTOToValid(){
        return UserEncryptionDTO.builder()
                .id(1L)
                .publicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIr8hR0wZcBCu7")
                .idUser(1L)
                .build();
    }

    public static UserEncryption userEncryptionToValid(){
        return UserEncryption.builder()
                .id(1L)
                .privateKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIr8hR0wZcBCu7")
                .publicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIr8hR0wZcBCu7")
                .idUser(1L)
                .build();
    }

    public static UserEncryption userEncryptionTobeSave(){
        return UserEncryption.builder()
                .privateKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIr8hR0wZcBCu7")
                .publicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIr8hR0wZcBCu7")
                .idUser(1L)
                .build();
    }
}
