package ru.netology.domain;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparatorAsc implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getCommentCount() - o1.getCommentCount();
    }
}