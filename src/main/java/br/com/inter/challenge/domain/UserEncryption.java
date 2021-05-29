package br.com.inter.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_encryption")
public class UserEncryption {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "private_key")
    @Size(min = 1, max = 3000, message = "Your private key must have a minimum of 1 and a maximum of 3000")
    private String privateKey;

    @Column(name = "public_key")
    @Size(min = 1, max = 3000, message = "Your public key must have a minimum of 1 and a maximum of 3000")
    private String publicKey;

    @Column(name = "id_user")
    private Long idUser;
}
