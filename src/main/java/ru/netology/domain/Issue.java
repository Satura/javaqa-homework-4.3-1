package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Issue {

    private int id;
    private String title;
    private String author;
    private int commentCount;
    private Set<String> labels;
    private Set<String> assignee;
    private boolean status; // true - open, false - closed

    public boolean getStatus() {
        return this.status;
    }
}
