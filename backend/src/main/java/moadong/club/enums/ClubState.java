package moadong.club.enums;

import lombok.Getter;

@Getter
public enum ClubState {
    UNAVAILABLE("모집 끝"),
    AVAILABLE("모집 중");

    private final String desc;

    ClubState(String desc) {
        this.desc = desc;
    }
}
