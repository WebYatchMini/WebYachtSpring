package com.inha.dice_game.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @Column(name = "uid", nullable = false)
    private String uid;

    private String pw_hash;
    private int MMR;
    private String pw_hint;
    private String hint_value_hash;
    private String nickname;
    private int Win;
    private int Lose;

    @Override
    public String toString() {
        return "Member{" +
                "uid='" + uid + '\'' +
                ", pw_hash='" + pw_hash + '\'' +
                ", MMR=" + MMR +
                ", pw_hint='" + pw_hint + '\'' +
                ", hint_value_hash='" + hint_value_hash + '\'' +
                ", nickname='" + nickname + '\'' +
                ", Win=" + Win +
                ", Lose=" + Lose +
                '}';
    }

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
