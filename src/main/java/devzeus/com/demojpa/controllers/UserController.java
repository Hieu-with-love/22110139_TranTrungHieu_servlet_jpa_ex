package devzeus.com.demojpa.controllers;

import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.entities.User;
import devzeus.com.demojpa.services.ICategoryService;
import devzeus.com.demojpa.services.IUserService;
import devzeus.com.demojpa.services.impl.CategoryService;
import devzeus.com.demojpa.services.impl.UserService;
import devzeus.com.demojpa.ultils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(urlPatterns = {"/admin/register", "/admin/users", "/admin/user"})
public class UserController extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;
    public IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/users")) {
            // Show list categories
            showUsers(req, resp);
        } else if (url.contains("/admin/register")) {
            // Show form add category
            req.getRequestDispatcher("/views/admin/user/user-register.jsp").forward(req, resp);
        } else if (url.contains("/admin/user")) {
            // Get username from url
            String username = req.getParameter("username");
            // Get data from database
            User user = userService.findByUsername(username);
            // Set data to form
            req.setAttribute("user", user);
            // Show form edit category
            req.getRequestDispatcher("/views/admin/user/user-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            // Get id from url
            int id = Integer.parseInt(req.getParameter("id"));
            // Delete data from database
            // Suggest delete image file on device !!!
            try {
                userService.delete(req.getParameter("username"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Show list categories
            resp.sendRedirect(req.getContextPath() + "/admin/users");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("register")) {
            registerUser(req, resp);
        } else if (url.contains("user/edit")) {
            //editUser(req, resp);
        } else if (url.contains("user/delete")) {
            try {
                //deleteUser(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Get user from url
        String username = req.getParameter("username");
        // Delete data from database
        userService.delete(username);

        showUsers(req, resp);
    }

    private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get old data from database
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        LocalDate dayOfBirth = LocalDate.parse(req.getParameter("dayOfBirth"));
        int active = Integer.parseInt(req.getParameter("active"));
        // Insert data to database
        User user = userService.findByUsername(username);
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDayOfBird(dayOfBirth);
        user.setActive(active);
        userService.update(user);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/views/admin/categories.jsp");
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        LocalDate dayOfBirth = LocalDate.parse(req.getParameter("dayOfBirth"));
        int active = Integer.parseInt(req.getParameter("active"));
        // Insert data to database
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDayOfBird(dayOfBirth);
        user.setActive(active);
        userService.register(user);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }

    private void showUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/admin/user/users-list.jsp").forward(req, resp);
    }
}
