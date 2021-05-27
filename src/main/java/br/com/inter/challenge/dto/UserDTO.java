package br.com.inter.challenge.dto;

import br.com.inter.challenge.domain.SingleDigit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class UserDTO {

    private Long id;

    @NotEmpty(message = "Invalid Name!")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Email(message = "Email must be valid in the following format: email@example.com")
    private String email;

    private List<SingleDigit> singleDigitList;
}
