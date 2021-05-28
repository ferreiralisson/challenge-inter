package br.com.inter.challenge.controller;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.dto.SingleDigitDTO;
import br.com.inter.challenge.service.SingleDigitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static br.com.inter.challenge.util.Constants.API_URL_PREFIX;
import static br.com.inter.challenge.util.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = API_URL_PREFIX + "/single-digit")
@RequiredArgsConstructor
@Slf4j
public class SingleDigitController {

    private final SingleDigitService singleDigitService;
    private static final String RESPONSE_SINGLE_DIGIT = "response single digit, finish - {}";

    @PostMapping
    public ResponseEntity<SingleDigitDTO> calculate(@RequestBody @Valid SingleDigitDTO singleDigitDTO){
        log.info("create single digit in singleDigitService.create, start - {}", localDateTimeToString(now()));
        SingleDigitDTO singleDigit = singleDigitService.create(singleDigitDTO);
        log.info(RESPONSE_SINGLE_DIGIT, localDateTimeToString(now()));
        return new ResponseEntity<>(singleDigit, CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<List<SingleDigit>> listAll(@PathVariable long id){
        log.info("list all single digit in singleDigitService.listAll, start -  {}", localDateTimeToString(now()));
        List<SingleDigit> singleDigits = singleDigitService.findAll(id);
        log.info(RESPONSE_SINGLE_DIGIT, localDateTimeToString(now()));
        return new ResponseEntity<>(singleDigits, OK);
    }

}
