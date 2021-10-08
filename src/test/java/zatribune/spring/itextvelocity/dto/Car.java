package zatribune.spring.itextvelocity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Builder
@AllArgsConstructor
public class Car {
    private final Long id;
    private final String model;
    private final Date date;

}
