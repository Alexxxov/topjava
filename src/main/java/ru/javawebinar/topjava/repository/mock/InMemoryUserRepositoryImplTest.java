package ru.javawebinar.topjava.repository.mock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Admin on 13.07.2018.
 */
public class InMemoryUserRepositoryImplTest {
    @Test
    public void get() throws Exception {
        User user = new User(1,"usear1","emailuser1","password1", Role.ROLE_USER);
        assertEquals(user,userRepository.get(1));
    }

    @Test
    public void getAll() throws Exception {
        List<User> users = userRepository.getAll();
        assertArrayEquals(users.toArray(), new User[]{
                new User(3,"Alex","emailuser3","password3", Role.ROLE_USER),
                new User(1,"usear1","emailuser1","password1", Role.ROLE_USER),
                new User(2,"user2","emailuser2","password2", Role.ROLE_USER)});
    }

    @Test
    public void getByEmail() throws Exception {
        User user = new User(1,"usear1","emailuser1","password1",Role.ROLE_USER);
        assertEquals(user,userRepository.getByEmail("emailuser1"));
    }

    private InMemoryUserRepositoryImpl userRepository = new InMemoryUserRepositoryImpl();

    @Test
    public void delete() throws Exception {
        assertTrue(userRepository.delete(1));
    }

    @Test
    public void save() throws Exception {
        User user = new User(5,"name","email", "pass",Role.ROLE_USER);
        userRepository.save(user);
        assertEquals(user, userRepository.get(5));
    }




}