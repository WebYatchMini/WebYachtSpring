package com.inha.dice_game.member;

public class Member {
    private String id;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    private String pw;
    private String name;
    private int MMR;
    private int unique;
    private String nickname;
    private int win;
    private int lose;


    public Member(String id,String pw)
    {
        this.id = id;
        this.pw = pw;
        this.name = "0";
        this.unique = 0;
        this.nickname = "0";
        this.MMR = 1000;
        this.win = 0;
        this.lose = 0;

    }

    public Member(String id,String pw,String name,int unique,String nickname)
    {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.unique = unique;
        this.nickname = nickname;
        this.MMR = 1000;
        this.win = 0;
        this.lose = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMMR() {
        return MMR;
    }

    public void setMMR(int MMR) {
        this.MMR = MMR;
    }

    public int getUnique() {
        return unique;
    }

    public void setUnique(int unique) {
        this.unique = unique;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }
}
