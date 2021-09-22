package ru.job4j.cars.servlet;

import ru.job4j.cars.dao.AdRepository;
import ru.job4j.cars.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AdRepository store = AdRepository.getInstance();
        String name = req.getParameter("name");
        if (Objects.nonNull(store.findUserByName(name))) {
            String errorMessage = "Пользователь с таким name уже зарегистрирован в системе, используйте другой name";
            req.setAttribute("error", errorMessage);
            doGet(req, resp);
        } else {
            Author user = Author.of(name);
            store.save(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/ad.do");
        }
    }
}
