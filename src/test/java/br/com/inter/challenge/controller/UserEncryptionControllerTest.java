package br.com.inter.challenge.controller;

import br.com.inter.challenge.domain.UserEncryption;
import br.com.inter.challenge.dto.UserDTO;
import br.com.inter.challenge.dto.UserEncryptionDTO;
import br.com.inter.challenge.service.UserEncryptionService;
import br.com.inter.challenge.util.UserCreator;
import br.com.inter.challenge.util.UserEncryptionCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for user encryption controller")
class UserEncryptionControllerTest {

    @InjectMocks
    private UserEncryptionController userEncryptionController;

    @Mock
    private UserEncryptionService userEncryptionServiceMock;

    @BeforeEach
    void setUp() {
        when(userEncryptionServiceMock.generateKey(anyLong())).thenReturn(UserEncryptionCreator.userEncryptionDTOToValid());
        doNothing().when(userEncryptionServiceMock).encryptUser(any());
        doNothing().when(userEncryptionServiceMock).decryptUser(any());
    }

    @Test
    @DisplayName("Generate key when successful")
    void generateKey_WhenSuccessful() {
        UserEncryptionDTO userEncryptionDTO = UserEncryptionCreator.userEncryptionDTOToBeSaved();
        String expected = userEncryptionDTO.getPublicKey();
        UserEncryptionDTO generateKey = userEncryptionController.generateKey(1L).getBody();
        assertThat(generateKey).isNotNull();
        assertThat(generateKey.getPublicKey()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Encrypt user when successful")
    void EncryptUser_WhenSuccessful() {
        assertThatCode(() -> userEncryptionController.encryptUser(UserEncryptionCreator.userEncryptionDTOToBeSaved()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = userEncryptionController.encryptUser(UserEncryptionCreator.userEncryptionDTOToBeSaved());
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    @DisplayName("Decrypt user when successful")
    void DecryptUser_WhenSuccessful() {
        assertThatCode(() -> userEncryptionController.decryptUser(UserEncryptionCreator.userEncryptionDTOToBeSaved()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = userEncryptionController.decryptUser(UserEncryptionCreator.userEncryptionDTOToBeSaved());
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(NO_CONTENT);
    }

}