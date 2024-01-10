package org.example.hexlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MainPage {
    private boolean visited;
    private String currentUserName;
    public boolean isVisited() {
        return visited;
    }
}
