package br.com.inter.challenge.service.impl;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.domain.UserEncryption;
import br.com.inter.challenge.dto.UserEncryptionDTO;
import br.com.inter.challenge.exception.BadRequestException;
import br.com.inter.challenge.repository.UserEncryptionRepository;
import br.com.inter.challenge.repository.UserRepository;
import br.com.inter.challenge.service.UserEncryptionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Optional;

import static br.com.inter.challenge.util.Constants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserEncryptionServiceImpl implements UserEncryptionService {

    private final UserEncryptionRepository userEncryptionRepository;
    private static final String RSA = "RSA";
    private static final Integer SIZE = 2048;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserEncryptionDTO generateKey(long id) {
        UserEncryptionDTO userEncryptionDTO = new UserEncryptionDTO();
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));

        if (user != null) {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
                keyPairGenerator.initialize(SIZE);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                userEncryptionDTO = UserEncryptionDTO.builder()
                        .publicKey(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()))
                        .privateKey(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()))
                        .idUser(user.getId())
                        .build();
                UserEncryption userEncryption = modelMapper.map(userEncryptionDTO, UserEncryption.class);
                userEncryptionRepository.save(userEncryption);
            } catch (Exception e) {
                throw new RuntimeException("Error generating key - {}", e);
            }
        }

        if (StringUtils.hasText(userEncryptionDTO.getPrivateKey())) {
            userEncryptionDTO.setPrivateKey(null);
        }

        return userEncryptionDTO;
    }

    @Override
    public void encryptUser(UserEncryptionDTO userEncryptionDTO) {

        if(userEncryptionDTO.getPublicKey() == null && userEncryptionDTO.getIdUser() == null){
            throw new BadRequestException("Public key or id User Invalid");
        }

        UserEncryption userEncryption = new UserEncryption();
        if (StringUtils.hasText(userEncryptionDTO.getPublicKey())) {
            userEncryption = userEncryptionRepository.findByPublicKey(userEncryptionDTO.getPublicKey());
        } else {
            userEncryption = userEncryptionRepository.findByIdUser(userEncryptionDTO.getIdUser());
        }

        Optional<User> user = userRepository.findById(userEncryption.getIdUser());
        if (user.isPresent()) {
            User userSave = User.builder()
                    .id(user.get().getId())
                    .name(Base64.getEncoder().encodeToString(encrypt(user.get().getName(), userEncryption.getPublicKey())))
                    .email(Base64.getEncoder().encodeToString(encrypt(user.get().getEmail(), userEncryption.getPublicKey())))
                    .build();
            userRepository.save(userSave);
        }
    }

    @Override
    public void decryptUser(UserEncryptionDTO userEncryptionDTO) {

        if(userEncryptionDTO.getPublicKey() == null && userEncryptionDTO.getIdUser() == null){
            throw new BadRequestException("Public key or id User Invalid");
        }

        UserEncryption userEncryption = new UserEncryption();

        if (StringUtils.hasText(userEncryptionDTO.getPublicKey())) {
            userEncryption = userEncryptionRepository.findByPublicKey(userEncryptionDTO.getPublicKey());
        } else {
            userEncryption = userEncryptionRepository.findByIdUser(userEncryptionDTO.getIdUser());
        }

        Optional<User> user = userRepository.findById(userEncryption.getIdUser());
        if (user.isPresent()) {
            User userSave = User.builder()
                    .id(user.get().getId())
                    .name(decrypt(Base64.getDecoder().decode(user.get().getName()), userEncryption.getPrivateKey()))
                    .email(decrypt(Base64.getDecoder().decode(user.get().getEmail()), userEncryption.getPrivateKey()))
                    .build();
            userRepository.save(userSave);
        }
    }

    private byte[] encrypt(String text, String userPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            PublicKey publicKey = decodePublicKey(userPublicKey);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Encrypt Error");
        }
    }

    private static PublicKey decodePublicKey(String publicKeyBase64) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Error Public key is not valid");
        }
    }

    private String decrypt(byte[] text, String userPrivateKey) {
        try {
            final Cipher cipher = Cipher.getInstance(RSA);
            PrivateKey privateKey = decodePrivateKey(userPrivateKey);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(text));
        } catch (Exception e) {
            throw new RuntimeException("Decrypting Error");
        }

    }

    private PrivateKey decodePrivateKey(String privateKeyBase64) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                    Base64.getDecoder().decode(privateKeyBase64.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Error Private key is not valid");
        }

    }

}
