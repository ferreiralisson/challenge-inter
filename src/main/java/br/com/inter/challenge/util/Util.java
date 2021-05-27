package br.com.inter.challenge.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;

@Component
@NoArgsConstructor(access = PRIVATE)
public class Util {

    public static String localDateTimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }
}
