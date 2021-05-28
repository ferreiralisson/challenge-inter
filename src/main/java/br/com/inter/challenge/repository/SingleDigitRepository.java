package br.com.inter.challenge.repository;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleDigitRepository extends JpaRepository<SingleDigit, Long> {
    List<SingleDigit> findByUser(User user);
}
