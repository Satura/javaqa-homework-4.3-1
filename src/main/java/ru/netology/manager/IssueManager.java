package ru.netology.manager;

import ru.netology.domain.*;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    // Добавление Issue

    public void add(Issue item) {
        repository.save(item);
    }

    // Списки открытых и закрытых Issue

    public Collection<Issue> showOpenIssues() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if ((item.getStatus())) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> showCloseIssues() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (!item.getStatus()) {
                result.add(item);
            }
        }
        return result;
    }

    // Фильтрация:

    // по автору
    public Collection<Issue> filterByAuthor(Predicate<String> predicate) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAuthor())) {
                result.add(item);
            }
        }
        return result;
    }

    // по тегам
    public Collection<Issue> filterByLabel(Predicate<Set> predicate) {
        Collection<Issue> result = new ArrayList<>();

        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getLabels())) {
                result.add(item);
            }
        }
        return result;
    }

    // по исполнителям
    public Collection<Issue> filterByAssignee(Predicate<Set> predicate) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAssignee())) {
                result.add(item);
            }
        }
        return result;
    }

    // Сортировка

    public Collection<Issue> sortByCommentsCountAsc(Comparator<Issue> asc) {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            result.add(item);
        }
        Collections.sort(result, asc);
        return result;
    }

    public Collection<Issue> sortByCommentsCountDesc(Comparator<Issue> desc) {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            result.add(item);
        }
        Collections.sort(result, desc);
        return result;
    }

    // Открыть/закрыть Issue

    public void openIssue(int id) {
        try {
            repository.openIssue(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeIssue(int id) {
        try {
            repository.closeIssue(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addAll(Collection<Issue> items) {
        repository.saveAll(items);
    }

    public Collection<Issue> getAll() {
        return repository.findAll();
    }
}
