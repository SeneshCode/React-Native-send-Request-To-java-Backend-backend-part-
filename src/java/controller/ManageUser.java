package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.City;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@MultipartConfig
@WebServlet(name = "ManageUser", urlPatterns = {"/ManageUser"})
public class ManageUser extends HttpServlet {

    private static final String UPLOAD_PATH = "profile_image";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String name = req.getParameter("fullName");
        String username = req.getParameter("username");
        String gmail = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword1 = req.getParameter("confirmPassword");
        String city = req.getParameter("city");
        Part filePart = req.getPart("profileImage");

        String thisPath = getServletContext().getRealPath("");
        String newPath = thisPath.replace("build" + File.separator + "web", "web" + File.separator + UPLOAD_PATH);
        File uploadDir = new File(newPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + "_profile.png";
        File profile = new File(uploadDir,fileName);
        Files.copy(filePart.getInputStream(), profile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        City city1 = (City) s.get(City.class,Integer.parseInt(city));
        
        User user = new User(name,username,gmail,password,fileName,new Date(),city1);
        
        s.save(user);
        s.beginTransaction().commit();
        
        resp.getWriter().write("Done");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
