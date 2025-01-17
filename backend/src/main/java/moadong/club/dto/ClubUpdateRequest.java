package moadong.club.dto;

import java.time.LocalDate;
import org.springframework.cglib.core.Local;

public record ClubUpdateRequest(
    String clubId,
    String name,
    String classification,
    String division,
    String thumbnail,
    String introduction,
    String description,
    String clubPresidentName,
    String telephoneNumber,
    LocalDate recruitmentStart,
    LocalDate recruitmentEnd
) {

}
