package com.inha.dice_game.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String u_id;
    private String pw;
    private boolean useCookie;
}
