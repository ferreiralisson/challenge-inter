package br.com.inter.challenge.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "single_digit")
public class SingleDigit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_representation")
    private String numberRepresentation;

    @NotNull
    @Column(name = "number_repetitions")
    private String numberOfRepetitions;

    @Column(name = "result")
    private Integer result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

}
