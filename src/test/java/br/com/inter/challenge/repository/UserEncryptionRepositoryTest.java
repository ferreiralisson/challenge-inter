package br.com.inter.challenge.repository;

import br.com.inter.challenge.domain.UserEncryption;
import br.com.inter.challenge.util.UserEncryptionCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Tests for user encryption Repository")
class UserEncryptionRepositoryTest {

    @Autowired
    private UserEncryptionRepository userEncryptionRepository;

    @Test
    @DisplayName("Save user encryption when successful")
    void save_UserEncryption_WhenSuccessful(){

        UserEncryption userEncryption = UserEncryptionCreator.userEncryptionTobeSave();
        UserEncryption save = this.userEncryptionRepository.save(userEncryption);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
        assertThat(save.getPublicKey()).isEqualTo(userEncryption.getPublicKey());
    }

    @Test
    @DisplayName("Find by publicKey when successful")
    void FindByPublicKey_WhenSuccessful(){

        UserEncryption userEncryption = UserEncryptionCreator.userEncryptionTobeSave();
        UserEncryption userEncryptionSaved = this.userEncryptionRepository.save(userEncryption);
        UserEncryption userEncryptionFound = this.userEncryptionRepository.findByPublicKey(userEncryptionSaved.getPublicKey());

        assertThat(userEncryptionFound).isNotNull();
        assertThat(userEncryptionFound.getId()).isNotNull();
        assertThat(userEncryptionFound.getPublicKey()).isEqualTo(userEncryptionSaved.getPublicKey());
    }

    @Test
    @DisplayName("Find by user encrypt when successful")
    void FindByUserEncrypt_WhenSuccessful(){

        UserEncryption userEncryption = UserEncryptionCreator.userEncryptionTobeSave();
        UserEncryption userEncryptionSaved = this.userEncryptionRepository.save(userEncryption);
        UserEncryption userEncryptionFound = this.userEncryptionRepository.findByIdUser(userEncryptionSaved.getIdUser());

        assertThat(userEncryptionFound).isNotNull();
        assertThat(userEncryptionFound.getId()).isNotNull();
        assertThat(userEncryptionFound.getIdUser()).isEqualTo(userEncryptionSaved.getIdUser());
    }
}