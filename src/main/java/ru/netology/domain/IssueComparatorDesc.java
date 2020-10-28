package ru.netology.domain;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparatorDesc implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getCommentCount() - o2.getCommentCount();
    }
}
