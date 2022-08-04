package com.inha.dice_game.DTO.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

public class MemberDTOCollection {

    @Getter
    @Setter
    public static class LoginDTO {
        private String u_id;

        public LoginDTO(String u_id) {
            this.u_id = u_id;
        }

        public LoginDTO(com.inha.dice_game.entity.Member member) {
            this.u_id = member.getUid();
        }
    }

    @Getter
    public static class LoginVO {

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

    @Getter
    public static class MemberVO {
        private String id;
        private String pw;
        private String hint;
        private String hint_answer;
        private String nickname;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MemberVO memberVO = (MemberVO) o;
            return Objects.equals(id, memberVO.id) && Objects.equals(pw, memberVO.pw) && Objects.equals(hint, memberVO.hint) && Objects.equals(hint_answer, memberVO.hint_answer) && Objects.equals(nickname, memberVO.nickname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, pw, hint, hint_answer, nickname);
        }
    }


    @Getter
    @Setter
    public static class Profile {

        private String uid;
        private String nickname;
        private int Win;
        private int Lose;
        private int MMR;
        private boolean status;

        public Profile(com.inha.dice_game.entity.Member member, boolean status)
        {
            this.uid = member.getUid();
            this.nickname = member.getNickname();
            this.Win = member.getWin();
            this.Lose = member.getLose();
            this.MMR = member.getMMR();
            this.status = status;
        }

        public void setWithMember(com.inha.dice_game.entity.Member member)
        {
            this.uid = member.getUid();
            this.nickname = member.getNickname();
            this.Win = member.getWin();
            this.Lose = member.getLose();
            this.MMR = member.getMMR();
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

    @Getter
    @Setter
    public static class ProfileExchange {
        private int type;
        private ArrayList<MemberDTOCollection.Profile> userProfileData;
    }

}
