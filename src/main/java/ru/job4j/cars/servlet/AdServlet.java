package ru.job4j.cars.servlet;

import ru.job4j.cars.dao.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ad.do")
public class AdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("advs", AdRepository.getInstance().getAllAdvertisements());
        req.getRequestDispatcher("/ad.jsp").forward(req, resp);
    }
}
