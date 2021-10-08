package zatribune.spring.itextvelocity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Player {
    private final Long id;
    private final String name;
}
