package com.inha.dice_game.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    public String getUid() {
        return uid;
    }

    public String getPw_hash() {
        return pw_hash;
    }

    public int getMMR() {
        return MMR;
    }

    public String getPw_hint() {
        return pw_hint;
    }

    public String getHint_value_hash() {
        return hint_value_hash;
    }

    public String getNickname() {
        return nickname;
    }

    public int getWin() {
        return Win;
    }

    public int getLose() {
        return Lose;
    }

    @Id
    @Column(name = "u_id", nullable = false)
    private String uid;

    private String pw_hash;
    private int MMR;
    private String pw_hint;
    private String hint_value_hash;
    private String nickname;
    private int Win;
    private int Lose;

    @Builder
    public Member(String u_id,String pw_hint,String hint_value_hash,String pw_hash,String nickname)
    {
        this.uid = u_id;
        this.pw_hash = pw_hash;
        this.pw_hint = pw_hint;
        this.hint_value_hash = hint_value_hash;
        this.nickname = nickname;
        this.MMR = 1000;
        this.Win = 0;
        this.Lose = 0;
    }
}
