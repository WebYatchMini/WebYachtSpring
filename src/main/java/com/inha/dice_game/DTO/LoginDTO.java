package com.inha.dice_game.DTO;

import com.inha.dice_game.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String u_id;

    public LoginDTO(String u_id) {
        this.u_id = u_id;
    }

    public LoginDTO(Member member)
    {
        this.u_id = member.getUid();
    }
}
