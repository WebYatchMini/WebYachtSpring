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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "unique_id", nullable = false)
    private Long uniqueID;

    private String Id;
    private String Pw;
    private String name;
    private int MMR;
    private String nickname;
    private int Win;
    private int Lose;

    public Member(String id,String pw)
    {
        this.Id = id;
        this.Pw = pw;
        this.name = "0";
        this.nickname = "0";
        this.MMR = 1000;
        this.Win = 0;
        this.Lose = 0;

    }

    @Builder
    public Member(String id,String pw,String name,int unique,String nickname)
    {
        this.Id = id;
        this.Pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.MMR = 1000;
        this.Win = 0;
        this.Lose = 0;
    }
}
