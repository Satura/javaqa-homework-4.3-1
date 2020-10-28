package ru.netology.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    IssueRepository repository = new IssueRepository();
    Issue issue1 = new Issue(1, "title1", "author1", 15,
            new HashSet<String>(Arrays.asList("Bug", "UI")), new HashSet<String>(Arrays.asList("assignee1")), true);
    Issue issue2 = new Issue(2, "title2", "author2", 5,
            new HashSet<String>(Arrays.asList("UI")), new HashSet<String>(Arrays.asList("assignee2", "assignee1")), false);
    Issue issue3 = new Issue(3, "title3", "author3", 45,
            new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("assignee3")), false);

    @BeforeEach
    void setUp() {
        repository.saveAll(List.of(issue1, issue2, issue3));
    }

    @Test
    void shouldFindByID() {
        Issue expected = issue2;
        Issue actual = repository.findByID(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByID() {
        Issue expected = null;
        Issue actual = repository.findByID(8);
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenIssue() {
        issue2.setStatus(true);
        assertEquals(true, issue2.getStatus());
    }

    @Test
    void shouldNotOpenIssue() {
        assertThrows(NotFoundException.class, () -> repository.openIssue(7));
    }

    @Test
    void shouldCloseIssue() {
        issue1.setStatus(false);
        assertEquals(false, issue1.getStatus());
    }

    @Test
    void shouldNotCloseIssue() {
        assertThrows(NotFoundException.class, () -> repository.closeIssue(7));
    }
}