package moadong.club.payload.dto;

public interface ClubInformationSearchProjection {
    String getLogo();
    String getDescription();

    static ClubInformationSearchProjection empty() {
        return new ClubInformationSearchProjection() {
            @Override
            public String getLogo() {
                return "";
            }

            @Override
            public String getDescription() {
                return "";
            }
        };
    }
}
