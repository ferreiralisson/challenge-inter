package br.com.inter.challenge.service.impl;

import br.com.inter.challenge.cache.GenericCache;
import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.SingleDigitDTO;
import br.com.inter.challenge.repository.SingleDigitRepository;
import br.com.inter.challenge.repository.UserRepository;
import br.com.inter.challenge.testutil.SingleDigitCreator;
import br.com.inter.challenge.testutil.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Tests for single digit service")
class SingleDigitServiceImplTest {

    @Autowired
    private SingleDigitServiceImpl singleDigitServiceImpl;

    @MockBean
    private SingleDigitRepository singleDigitRepositoryMock;

    @MockBean
    private UserRepository userRepositoryMock;

    private GenericCache<String, SingleDigit> cache;

    @BeforeEach
    void setUp() {
        when(singleDigitRepositoryMock.save(any(SingleDigit.class))).thenReturn(SingleDigitCreator.singleDigitCreateValidWithoutIdUser());
        when(singleDigitRepositoryMock.findAll()).thenReturn(List.of(SingleDigitCreator.singleDigitCreateValidWithoutIdUser()));
        when(userRepositoryMock.save(any(User.class))).thenReturn(UserCreator.createUserValid());
        when(singleDigitRepositoryMock.findByUser(any(User.class))).thenReturn(List.of(SingleDigitCreator.singleDigitCreateValidWithUser()));
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(UserCreator.createUserValid()));
    }

    @Test
    @DisplayName("Create single digit without user when successful")
    void createSingleDigitWithoutUser_WhenSuccessful() {
        SingleDigitDTO singleDigitDTO = singleDigitServiceImpl.create(SingleDigitCreator.singleDigitDtoTobeSaveWithoutIdUser());
        assertThat(singleDigitDTO.getNumberRepresentation()).isNotNull().isEqualTo(SingleDigitCreator.singleDigitDtoTobeSaveWithoutIdUser().getNumberRepresentation());
    }

    @Test
    @DisplayName("Create single digit with user when successful")
    void createSingleDigitWithUser_WhenSuccessful() {
        SingleDigitDTO singleDigitDTO = singleDigitServiceImpl.create(SingleDigitCreator.singleDigitDtoTobeSaveWithIdUser());
        assertThat(singleDigitDTO.getNumberRepresentation()).isNotNull().isEqualTo(SingleDigitCreator.singleDigitDtoTobeSaveWithIdUser().getNumberRepresentation());
    }

    @Test
    @DisplayName("calculate single digit when successful")
    void calculateSingleDigit_WhenSuccessful() {
        SingleDigitDTO singleDigitDTO = singleDigitServiceImpl.create(SingleDigitCreator.singleDigitDtoTobeSaveWithIdUser());
        assertThat(singleDigitDTO.getResult()).isNotNull().isEqualTo(SingleDigitCreator.singleDigitCreateValidWithUser().getResult());
        assertThat(singleDigitDTO.getNumberRepresentation()).isNotNull().isEqualTo(SingleDigitCreator.singleDigitDtoTobeSaveWithIdUser().getNumberRepresentation());
    }

    @Test
    @DisplayName("List return list of single digit by user inside list object when successful")
    void list_ReturnsListOfSingleDigitByUserInsideListObject_WhenSuccessful() {
        Integer expectedResult = SingleDigitCreator.singleDigitCreateValidWithUser().getResult();
        List<SingleDigit> singleDigitList = singleDigitServiceImpl.findAll(SingleDigitCreator.singleDigitCreateValidWithUser().getUser().getId());

        assertThat(singleDigitList).isNotEmpty().hasSize(1);
        assertThat(singleDigitList).isNotEmpty();
        assertThat(singleDigitList.get(0).getResult()).isEqualTo(expectedResult);
    }

}