package com.inha.dice_game.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class GameActionResultDTO {
    private boolean result;
    private String roomCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameActionResultDTO that = (GameActionResultDTO) o;
        return result == that.result && Objects.equals(roomCode, that.roomCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, roomCode);
    }
}
