package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.City;
import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;

@WebServlet(name = "ManageCity", urlPatterns = {"/ManageCity"})
public class ManageCity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Criteria cityList = s.createCriteria(City.class);
        Gson gson = new Gson();
        List<City> cities = cityList.list();
        JsonObject response = new JsonObject();
        
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(gson.toJsonTree(cities)));
    }

}
