package com.inha.dice_game.DTO;

import lombok.Getter;

import java.util.Objects;

@Getter
public class GameInfoVO {

    private final String roomCode;
    private final String title;
    private final boolean isLocked;
    private final String organizerName;
    private final boolean isStarted;
    private final int curPlayerCount;

    public GameInfoVO(GameDTO gameDTO) {
        this.roomCode = gameDTO.getRoomCode();
        this.title = gameDTO.getTitle();
        this.isLocked = gameDTO.isLocked();
        this.organizerName = gameDTO.getOrganizerName();
        this.isStarted = gameDTO.isStarted();
        this.curPlayerCount = gameDTO.getCurPlayerCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfoVO that = (GameInfoVO) o;
        return isLocked == that.isLocked && roomCode.equals(that.roomCode) && title.equals(that.title) && organizerName.equals(that.organizerName) && isStarted == that.isStarted && curPlayerCount == that.getCurPlayerCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomCode, title, isLocked, organizerName, isStarted);
    }
}
