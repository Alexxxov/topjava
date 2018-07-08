package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

/**
 * Created by Admin on 03.07.2018.
 */
@WebServlet(name = "mealServlet", urlPatterns = "/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("in MealServlet getting All Meals");

        request.setAttribute("meals",MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000));

        request.getRequestDispatcher("/mealList.jsp").forward(request,response);
    }
}
