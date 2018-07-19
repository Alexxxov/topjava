package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init(){

    }

    @Override
    public Meal save(Meal meal, int userId) {
        if(jdbcTemplate.queryForObject("select * FROM meals where id=:meal.id and user_id=:userId", Meal.class) != null) {
            return jdbcTemplate.queryForObject("Update meals SET id=:meal.id",Meal.class);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE from meals where id=:id and user_id=:userId") != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return jdbcTemplate.queryForObject("select * FROM meals where id=:id and user_id=:userId", Meal.class);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.queryForList("SELECT * from meals where user_id=:userId",Meal.class);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
