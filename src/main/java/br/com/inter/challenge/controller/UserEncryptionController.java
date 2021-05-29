package br.com.inter.challenge.controller;

import br.com.inter.challenge.dto.UserEncryptionDTO;
import br.com.inter.challenge.service.UserEncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.inter.challenge.util.Constants.API_URL_PREFIX;
import static br.com.inter.challenge.util.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(path = API_URL_PREFIX + "/encryption")
@RequiredArgsConstructor
@Slf4j
public class UserEncryptionController {

    private final UserEncryptionService userEncryptionService;

    @PostMapping(path = "/generate-key")
    public ResponseEntity<UserEncryptionDTO> generateKey(@RequestParam long id){
        log.info("generate key in userEncryptionService.generateKey, start -  {}", localDateTimeToString(now()));
        UserEncryptionDTO userEncryptionDTO = userEncryptionService.generateKey(id);
        log.info("response generateKey, finish", localDateTimeToString(now()));
        return new ResponseEntity<>(userEncryptionDTO, CREATED);
    }

    @PatchMapping(path = "/encrypt-user")
    public ResponseEntity<Void> encryptUser(@RequestBody @Valid UserEncryptionDTO userEncryptionDTO){
        log.info("Encrypt user in userEncryptionService.encryptUser, start -  {}", localDateTimeToString(now()));
        userEncryptionService.encryptUser(userEncryptionDTO);
        log.info("response Encrypt user, finish", localDateTimeToString(now()));
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PatchMapping(path = "/decrypt-user")
    public ResponseEntity<Void> decryptUser(@RequestBody @Valid UserEncryptionDTO userEncryptionDTO){
        log.info("Decrypt user in userEncryptionService.encryptUser, start -  {}", localDateTimeToString(now()));
        userEncryptionService.decryptUser(userEncryptionDTO);
        log.info("response decrypt user, finish", localDateTimeToString(now()));
        return new ResponseEntity<>(NO_CONTENT);
    }
}
