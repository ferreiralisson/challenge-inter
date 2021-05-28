package br.com.inter.challenge.repository;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.testutil.SingleDigitCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("Tests for single digit Repository")
class SingleDigitRepositoryTest {

    @Autowired
    private SingleDigitRepository singleDigitRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Calculate persists single digit when successful")
    void calculate_PersistSingleDigit_WhenSuccessful(){
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        User userSaved = userRepository.save(singleDigit.getUser());
        singleDigit.setUser(userSaved);
        SingleDigit singleDigitSaved = this.singleDigitRepository.save(singleDigit);

        assertThat(singleDigitSaved).isNotNull();
        assertThat(singleDigitSaved.getId()).isNotNull();
        assertThat(singleDigitSaved.getNumberRepresentation()).isEqualTo(singleDigit.getNumberRepresentation());
    }

    @Test
    @DisplayName("Find by id return single digit empty when no single digit is found")
    void findById_ReturnsSingleDigitEmpty_WhenNoSingleDigitIsFound(){
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        User userSaved = userRepository.save(singleDigit.getUser());
        singleDigit.setUser(userSaved);
        SingleDigit singleDigitSaved = this.singleDigitRepository.save(singleDigit);

        List<SingleDigit> singleDigitList = this.singleDigitRepository.findByUser(singleDigit.getUser());

        assertThat(singleDigitList).isNotNull();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when single digit is not number representation")
    void save_ThrowsConstraintViolationException_WhenSingleDigitIsNotNumberRepresentation() {
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        singleDigit.setNumberRepresentation("abc");
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.singleDigitRepository.save(singleDigit))
                .withMessageContaining("The informed representation must contain only integers between 1 and 10,000.00");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when single digit is null number representation")
    void save_ThrowsConstraintViolationException_WhenSingleDigitIsNullNumberRepresentation() {
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        singleDigit.setNumberRepresentation(null);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.singleDigitRepository.save(singleDigit))
                .withMessageContaining("number of representation cannot be null");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when single digit is min number repetition")
    void save_ThrowsConstraintViolationException_WhenSingleDigitIsMinNumberRepetition() {
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        singleDigit.setNumberOfRepetitions(0);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.singleDigitRepository.save(singleDigit))
                .withMessageContaining("number of repetitions cannot be less than 1");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when single digit is max number repetition")
    void save_ThrowsConstraintViolationException_WhenSingleDigitIsMaxNumberRepetition() {
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        singleDigit.setNumberOfRepetitions(10000000);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.singleDigitRepository.save(singleDigit))
                .withMessageContaining("number of repetitions cannot exceed 1000000");
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when single digit is null number repetition")
    void save_ThrowsConstraintViolationException_WhenSingleDigitIsNullNumberRepetition() {
        SingleDigit singleDigit = SingleDigitCreator.singleDigitCreateValidWithUser();
        singleDigit.setNumberOfRepetitions(null);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.singleDigitRepository.save(singleDigit))
                .withMessageContaining("number of repetitions cannot be null");
    }

}