package br.com.inter.challenge.service;


import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.dto.SingleDigitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SingleDigitService {
    SingleDigitDTO create(SingleDigitDTO singleDigitDTO);
    List<SingleDigit> findAll(long id);
}
