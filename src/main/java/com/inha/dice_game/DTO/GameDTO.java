package com.inha.dice_game.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

    private String roomCode;
    private String title;
    private boolean isLocked;
    private String roomPwd = null;
    private String organizerName;
    private boolean isStarted;
    private boolean isFull;
    private int curPlayerCount;

    public GameDTO(String title, String roomPwd, String organizerName) {
        this.title = title;
        if(roomPwd != null) {
            this.roomPwd = roomPwd;
            this.isLocked = true;
        }
        this.organizerName = organizerName;
        this.isStarted = false;
        this.isFull = false;
        this.curPlayerCount = 1;
    }

    public GameInfoVO extractInfo()
    {
        return new GameInfoVO(this);
    }
}
