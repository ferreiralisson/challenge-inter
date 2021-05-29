package br.com.inter.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class UserEncryptionDTO {
    private Long id;
    @Size(min = 1, max = 3000, message = "Your private key must have a minimum of 1 and a maximum of 3000")
    private String privateKey;
    @Size(min = 1, max = 3000, message = "Your public key must have a minimum of 1 and a maximum of 3000")
    private String publicKey;
    private Long idUser;
}
