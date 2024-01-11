package org.example.hexlet.dto.sessions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoginPage {
    private String nickname;
    private String errorMessage;
}
