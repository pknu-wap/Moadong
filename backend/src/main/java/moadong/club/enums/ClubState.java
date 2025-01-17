package moadong.club.enums;

import lombok.Getter;

@Getter
public enum ClubState {
    UNAVAILABLE("unavailable","모집 끝"),
    AVAILABLE("available","모집 중");
    private final String name;
    private final String desc;

    ClubState(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static ClubState fromString(String value) {
        for (ClubState state : ClubState.values()) {
            if (state.getName().equals(value)) {
                return state;
            }
        }
        return AVAILABLE;
    }
}
