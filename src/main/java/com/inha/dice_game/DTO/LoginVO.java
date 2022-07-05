package com.inha.dice_game.DTO;

import lombok.Getter;

import java.util.Objects;

@Getter
public class LoginVO {

    private String id;
    private String pw;

    @Override
    public String toString() {
        return "LoginVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginVO loginVO = (LoginVO) o;
        return Objects.equals(id, loginVO.id) && Objects.equals(pw, loginVO.pw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw);
    }
}
