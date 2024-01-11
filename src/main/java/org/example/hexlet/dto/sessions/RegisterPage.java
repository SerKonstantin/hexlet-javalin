package org.example.hexlet.dto.sessions;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RegisterPage {
    private String nickname;
    private String firstName;
    private String secondName;
    private String email;
    private Map<String, List<ValidationError<Object>>> errors;
}
