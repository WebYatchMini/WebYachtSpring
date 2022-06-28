package com.inha.dice_game.member.controller;

public class MemberVO {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }

    private String nickname;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    private String pw;
}
