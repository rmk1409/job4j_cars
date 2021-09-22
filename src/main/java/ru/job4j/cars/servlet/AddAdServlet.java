package ru.job4j.cars.servlet;

import ru.job4j.cars.dao.AdRepository;
import ru.job4j.cars.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-ad.do")
public class AddAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdRepository store = AdRepository.getInstance();
        req.setAttribute("brands", store.getAllBrands());
        req.setAttribute("bodyTypes", store.getAllBodyTypes());
        req.getRequestDispatcher("/add-ad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        int brandId = Integer.parseInt(req.getParameter("brand"));
        int bodyTypeId = Integer.parseInt(req.getParameter("bodyType"));
        Author author = (Author) req.getSession().getAttribute("user");
        AdRepository.getInstance().saveAdvertisement(description, brandId, bodyTypeId, author.getId());
        resp.sendRedirect(req.getContextPath() + "/ad.do");
    }
}
