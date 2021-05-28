package br.com.inter.challenge.service.impl;

import br.com.inter.challenge.domain.SingleDigit;
import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.SingleDigitDTO;
import br.com.inter.challenge.dto.UserDTO;
import br.com.inter.challenge.repository.SingleDigitRepository;
import br.com.inter.challenge.repository.UserRepository;
import br.com.inter.challenge.service.SingleDigitService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SingleDigitServiceImpl implements SingleDigitService {

    private final SingleDigitRepository singleDigitRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public SingleDigitDTO create(SingleDigitDTO singleDigitDTO) {

        Integer calculatedRepresentation = calculateSingleDigit(singleDigitDTO.getNumberRepresentation(), singleDigitDTO.getNumberOfRepetitions());
        singleDigitDTO.setResult(calculatedRepresentation);

        if (singleDigitDTO.getIdUser() != null) {
            Optional<User> user = userRepository.findById(singleDigitDTO.getIdUser());
            if (user.isPresent()) {
                singleDigitDTO.setUser(modelMapper.map(user.get(), UserDTO.class));
                List<SingleDigit> singleDigitList = new ArrayList<>();
                singleDigitList.add(modelMapper.map(singleDigitDTO, SingleDigit.class));
                user.get().setSingleDigitList(singleDigitList);
                userRepository.save(user.get());
            }
        }

        SingleDigit singleDigit = modelMapper.map(singleDigitDTO, SingleDigit.class);

        SingleDigit singleDigitSaved = singleDigitRepository.save(singleDigit);

        return modelMapper.map(singleDigitSaved, SingleDigitDTO.class);
    }

    private Integer calculateSingleDigit(String n, Integer k) {
        String digit = n;
        Integer sum = 0;

        if (digit.length() == 1) {
            return Integer.parseInt(digit);
        }

        digit = concatSingleDigit(n, k, digit);
        sum = calculate(digit, sum);

        while (sum.toString().length() > 1) {
            sum = calculateSingleDigit(sum.toString(), 1);
        }

        return sum;
    }

    private Integer calculate(String digit, Integer sum) {
        for (char charDigit : digit.toCharArray()) {
            sum += Integer.parseInt(Character.toString(charDigit));
        }
        return sum;
    }

    private String concatSingleDigit(String n, Integer k, String digit) {
        for (int i = 0; i < k - 1; i++) {
            digit += n;
        }
        return digit;
    }

    @Override
    public List<SingleDigit> findAll(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            List<SingleDigit> singleDigitList = singleDigitRepository.findByUser(userOptional.get());
            return singleDigitList.stream().filter(singleDigit -> singleDigit != null).collect(Collectors.toUnmodifiableList());
        }
        return new ArrayList<>();
    }
}
