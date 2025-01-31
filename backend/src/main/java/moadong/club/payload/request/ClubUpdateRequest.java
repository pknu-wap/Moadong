package moadong.club.payload.request;

import java.time.LocalDate;

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
