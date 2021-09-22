package ru.job4j.cars.servlet;

import ru.job4j.cars.dao.AdRepository;
import ru.job4j.cars.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/auth.do")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Author user = AdRepository.getInstance().findUserByName(name);
        if (Objects.nonNull(user)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/ad.do");
        } else {
            req.setAttribute("error", "Не верные данные");
            req.getRequestDispatcher("auth.jsp").forward(req, resp);
        }
    }
}
