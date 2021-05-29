package br.com.inter.challenge.controller;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import br.com.inter.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.inter.challenge.util.Constants.API_URL_PREFIX;
import static br.com.inter.challenge.util.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = API_URL_PREFIX + "/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private static final String RESPONSE_USER = "response user, finish - {}";

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO userDTO){
        log.info("create user in userService.create, start - {}", localDateTimeToString(now()));
        UserDTO user = userService.create(userDTO);
        log.info(RESPONSE_USER, localDateTimeToString(now()));
        return new ResponseEntity<>(user, CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<User>> listAll(Pageable pageable){
        log.info("complete list of users in userService.findAll, start -  {}", localDateTimeToString(now()));
        Page<User> user = userService.findAll(pageable);
        log.info(RESPONSE_USER, localDateTimeToString(now()));
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id){
        log.info("complete list of users by id in userService.findById, start -  {}", localDateTimeToString(now()));
        UserDTO user = userService.findById(id);
        log.info(RESPONSE_USER, localDateTimeToString(now()));
        return new ResponseEntity<>(user, OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO){
        log.info("update user in userService.create, start - {}", localDateTimeToString(now()));
        userService.update(userDTO);
        log.info("update user in userService.create, finish - {}", localDateTimeToString(now()));
        return new ResponseEntity<>(NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        log.info("delete user in userService.delete, start -  {}", localDateTimeToString(now()));
        userService.delete(id);
        log.info("delete user, finish -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(NO_CONTENT);
    }

}
