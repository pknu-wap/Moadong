package moadong.club.payload.request;

import java.time.LocalDate;
import java.util.List;

public record ClubUpdateRequest(
    String clubId,
    String name,
    String classification,
    String division,
    List<String> tags,
    String thumbnail,
    String introduction,
    String description,
    String clubPresidentName,
    String telephoneNumber,
    LocalDate recruitmentStart,
    LocalDate recruitmentEnd
) {

}
