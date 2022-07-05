package com.inha.dice_game.DTO;

import lombok.Getter;

import java.util.Objects;

@Getter
public class MemberVO {
    private String id;
    private String pw;

    @Override
    public String toString() {
        return "MemberVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", hint='" + hint + '\'' +
                ", hint_answer='" + hint_answer + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    private String hint;
    private String hint_answer;
    private String nickname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberVO memberVO = (MemberVO) o;
        return id.equals(memberVO.id) && pw.equals(memberVO.pw) && hint.equals(memberVO.hint) && hint_answer.equals(memberVO.hint_answer) && nickname.equals(memberVO.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw, hint, hint_answer, nickname);
    }
}
