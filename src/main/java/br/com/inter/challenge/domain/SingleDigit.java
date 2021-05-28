package br.com.inter.challenge.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotNull(message = "number of representation cannot be null")
    @Column(name = "number_representation")
    @Pattern(regexp="[0-9]+",message="The informed representation must contain only integers between 1 and 10,000.00")
    private String numberRepresentation;

    @NotNull(message = "number of repetitions cannot be null")
    @Column(name = "number_repetitions")
    @Min(value = 1, message = "number of repetitions cannot be less than 1")
    @Max(value = 1000000, message = "number of repetitions cannot exceed 1000000")
    private Integer numberOfRepetitions;

    @Column(name = "result")
    private Integer result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

}
