package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.IssueComparatorAsc;
import ru.netology.domain.IssueComparatorDesc;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    IssueComparatorAsc comparatorAsc = new IssueComparatorAsc();
    IssueComparatorDesc comparatorDesc = new IssueComparatorDesc();

    Issue issue1 = new Issue(1, "title1", "author1", 15,
            new HashSet<String>(Arrays.asList("Bug", "UI")), new HashSet<String>(Arrays.asList("assignee1")), true);
    Issue issue2 = new Issue(2, "title2", "author2", 5,
            new HashSet<String>(Arrays.asList("UI")), new HashSet<String>(Arrays.asList("assignee2", "assignee1")), false);
    Issue issue3 = new Issue(3, "title3", "author1", 45,
            new HashSet<String>(Arrays.asList("Money")), new HashSet<String>(Arrays.asList("assignee3")), false);


    @BeforeEach
    void setUp() {
        repository.saveAll(List.of(issue1, issue2, issue3));
    }

    @Test
    void shouldAddIssue() {
        Issue issue4 = new Issue(4, "title4", "author4", 15,
                new HashSet<String>(Arrays.asList("UI", "money")), new HashSet<String>(Arrays.asList("assignee4")), true);
        manager.add(issue4);
        Collection<Issue> expected = List.of(issue1, issue2, issue3, issue4);
        Collection<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpenIssues() {
        Collection<Issue> expected = List.of(issue1);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    void shouldShowCloseIssues() {
        Collection<Issue> expected = List.of(issue2, issue3);
        Collection<Issue> actual = manager.showCloseIssues();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        String author = "author1";
        Predicate<String> equalAuthor = t -> t.equals(author);
        Collection<Issue> expected = List.of(issue1, issue3);
        Collection<Issue> actual = manager.filterByAuthor(equalAuthor);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        String label = "UI";
        Predicate<Set> equalLabel = t -> t.contains(label);
        Collection<Issue> expected = List.of(issue1, issue2);
        Collection<Issue> actual = manager.filterByLabel(equalLabel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        String assignee = "assignee1";
        Predicate<Set> equalAssignee = t -> t.contains(assignee);
        Collection<Issue> expected = List.of(issue1, issue2);
        Collection<Issue> actual = manager.filterByAssignee(equalAssignee);
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByCommentsCountAsc() {
        Collection<Issue> expected = List.of(issue3, issue1, issue2);
        Collection<Issue> actual = manager.sortByCommentsCountAsc(comparatorAsc);
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByCommentsCountDesc() {
        Collection<Issue> expected = List.of(issue2, issue1, issue3);
        Collection<Issue> actual = manager.sortByCommentsCountDesc(comparatorDesc);
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenIssue() {
        manager.openIssue(2);
        Collection<Issue> expected = List.of(issue1, issue2);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssue() {
        manager.closeIssue(1);
        Collection<Issue> expected = List.of(issue1, issue2, issue3);
        Collection<Issue> actual = manager.showCloseIssues();
        assertEquals(expected, actual);
    }
}