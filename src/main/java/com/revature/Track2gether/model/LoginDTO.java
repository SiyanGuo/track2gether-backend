package com.revature.Track2gether.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginDTO {

    private String email;
    private String password;

}