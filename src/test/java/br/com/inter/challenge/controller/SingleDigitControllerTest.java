package br.com.inter.challenge.controller;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.dto.SingleDigitDTO;
import br.com.inter.challenge.service.SingleDigitService;
import br.com.inter.challenge.util.SingleDigitCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for single digit controller")
class SingleDigitControllerTest {

    @InjectMocks
    private SingleDigitController singleDigitController;

    @Mock
    private SingleDigitService singleDigitServiceMock;

    @BeforeEach
    void setUp() {
        when(singleDigitServiceMock.create(any(SingleDigitDTO.class))).thenReturn(SingleDigitCreator.singleDigitDtoCreateValidWithoutIdUser());
        when(singleDigitServiceMock.findAll(anyLong())).thenReturn(List.of(SingleDigitCreator.singleDigitCreateValidWithUser()));
    }

    @Test
    @DisplayName("Create single digit when successful")
    void calculateAndCreateSingleDigit_WhenSuccessful() {

        SingleDigitDTO singleDigitDTO = SingleDigitCreator.singleDigitDtoTobeSaveWithIdUser();
        String expectedResult = singleDigitDTO.getNumberRepresentation();
        Long idCreatedSingleDigit = 1L;
        SingleDigitDTO singleDigitDtoSaved = singleDigitController.calculate(singleDigitDTO).getBody();

        assertThat(singleDigitDtoSaved).isNotNull();
        assertThat(singleDigitDtoSaved.getNumberRepresentation()).isEqualTo(expectedResult);
        assertThat(singleDigitDtoSaved.getId()).isEqualTo(idCreatedSingleDigit);
    }

    @Test
    @DisplayName("List return list of single digit by user inside List object when successful")
    void list_ReturnsListOfSingleDigitInsideListObject_WhenSuccessful() {
        String expectedNumberRepresentation = SingleDigitCreator.singleDigitDtoTobeSaveWithIdUser().getNumberRepresentation();
        List<SingleDigit> singleDigitList = singleDigitController.listAll(1L).getBody();

        assertThat(singleDigitList).isNotEmpty().hasSize(1);
        assertThat(singleDigitList).isNotEmpty();
        assertThat(singleDigitList.get(0).getNumberRepresentation()).isEqualTo(expectedNumberRepresentation);
    }

}