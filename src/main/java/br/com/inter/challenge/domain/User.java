package br.com.inter.challenge.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Invalid Name!")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Column(name = "email")
    @Email(message = "Email must be valid in the following format: email@example.com")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<SingleDigit> singleDigitList;

}
