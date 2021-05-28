package br.com.inter.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class SingleDigitDTO {
    private Long id;
    @NotNull
    @Pattern(regexp="[0-9]+",message="The informed representation must contain only integers between 1 and 10,000.00")
    private String numberRepresentation;
    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer numberOfRepetitions;
    private Integer result;
    private UserDTO user;
    private Long idUser;
}
