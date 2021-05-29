package br.com.inter.challenge.repository;

import br.com.inter.challenge.domain.UserEncryption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEncryptionRepository extends JpaRepository<UserEncryption, Long> {
    UserEncryption findByPublicKey(String publicKey);
    UserEncryption findByIdUser(Long idUser);
}
