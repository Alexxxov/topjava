package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    {
        Arrays.asList(new User(1,"usear1","emailuser1","password1", Role.ROLE_USER),
                      new User(2,"user2","emailuser2","password2", Role.ROLE_USER),
                      new User(3,"Alex","emailuser3","password3", Role.ROLE_USER))
                      .forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.entrySet().removeIf(user -> user.getValue().getId().equals(id));
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (!repository.containsKey(user.getId()))
        {
            repository.put(user.getId(),user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), ((integer, user1) -> user));
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream().sorted(User::compareTo).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
    }
}
