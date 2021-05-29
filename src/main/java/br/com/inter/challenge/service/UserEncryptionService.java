package br.com.inter.challenge.service;

import br.com.inter.challenge.dto.UserEncryptionDTO;

public interface UserEncryptionService {
    UserEncryptionDTO generateKey(long id);
    void encryptUser(UserEncryptionDTO userEncryptionDTO);
    void decryptUser(UserEncryptionDTO userEncryptionDTO);
}
