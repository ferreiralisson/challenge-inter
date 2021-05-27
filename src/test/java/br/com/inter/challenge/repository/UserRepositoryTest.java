package br.com.inter.challenge.repository;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.testutil.UserCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("Tests for user Repository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save persists user when successful")
    void save_PersistClient_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        assertThat(userSaved).isNotNull();
        assertThat(userSaved.getId()).isNotNull();
        assertThat(userSaved.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Update client when successful")
    void updatesUser_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        userSaved.setName("userTest");

        User userUpdated = this.userRepository.save(userSaved);

        assertThat(userUpdated).isNotNull();
        assertThat(userUpdated.getId()).isNotNull();
        assertThat(userUpdated.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Delete client when successful")
    void deleteUser_WhenSuccessful(){
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);
        this.userRepository.delete(userSaved);
        Optional<User> userOptional = this.userRepository.findById(userSaved.getId());

        assertThat(userOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by id return user empty when no user is found")
    void findById_ReturnsUserEmpty_WhenNoUserIsFound(){
        Optional<User> userOptional = this.userRepository.findById(0L);
        assertThat(userOptional).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when userDTO is empty")
    void save_ThrowsConstraintViolationException_WhenUserIsEmpty() {
        User user = new User();
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user))
                .withMessageContaining("Invalid Name!");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when email is invalid")
    void save_ThrowsConstraintViolationException_WhenEmailIsInvalid() {
        User user = UserCreator.createUserToBeSaved();
        user.setEmail("email.com");
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user))
                .withMessageContaining("Email must be valid in the following format: email@example.com");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name less than three characters")
    void save_ThrowsConstraintViolationException_WhenNamelessThanThreeCharacters() {
        User user = UserCreator.createUserToBeSaved();
        user.setName("al");
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user))
                .withMessageContaining("Name must be at least 3 characters");
    }

}