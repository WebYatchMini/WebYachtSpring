package com.inha.dice_game.DTO;

import com.inha.dice_game.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {

    private String uid = "";
    private String nickname;
    private int Win;
    private int Lose;
    private int MMR;
    private boolean status;

    public ProfileDTO(Member member,boolean status)
    {
        this.uid = member.getUid();
        this.nickname = member.getNickname();
        this.Win = member.getWin();
        this.Lose = member.getLose();
        this.MMR = member.getMMR();
        this.status = status;
    }

    public void nullify()
    {
        this.uid = null;
        this.nickname = null;
        this.Win = -1;
        this.Lose = -1;
        this.MMR = -1;
        this.status = false;
    }

}
