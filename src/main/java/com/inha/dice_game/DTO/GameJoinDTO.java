package com.inha.dice_game.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class GameJoinDTO {
    private String roomCode;
    private String roomPwd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameJoinDTO that = (GameJoinDTO) o;
        return Objects.equals(roomCode, that.roomCode) && Objects.equals(roomPwd, that.roomPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomCode, roomPwd);
    }
}
