package devzeus.com.demojpa.controllers;

import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.entities.User;
import devzeus.com.demojpa.services.ICategoryService;
import devzeus.com.demojpa.services.IUserService;
import devzeus.com.demojpa.services.impl.CategoryService;
import devzeus.com.demojpa.services.impl.UserService;
import devzeus.com.demojpa.ultils.Constant;
import jakarta.servlet.ServletException;
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
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
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
            resp.sendRedirect(req.getContextPath() + "/views/admin/categories.jsp");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("add")) {
            registerUser(req, resp);
        } else if (url.contains("category/edit")) {
            //editUser(req, resp);
        } else if (url.contains("category/delete")) {
            try {
                //deleteUser(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

//    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        // Get user from url
//        String username = req.getParameter("username");
//        // Delete data from database
//        userService.delete(username);
//
//        showUsers(req, resp);
//    }
//
//    private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Get old data from database
//        User user = userService.findByUsername(req.getParameter("username"));
//        String oldImage = category.getImage();
//        // Get data from form
//        String name = req.getParameter("name");
//        int status = Integer.parseInt(req.getParameter("status"));
//        // Get file from form
//        String fname = "";
//        String uploadPath = Constant.UPLOAD_DIR;
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) uploadDir.mkdir(); // Create folder if not exist
//        try {
//            Part filePart = req.getPart("imageUpload");
//            if (filePart.getSize() > 0) {
//                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//                // Change file name
//                int index = fileName.lastIndexOf("."); // index of extend file .jpg, .png, .jpeg
//                String ext = fileName.substring(index + 1); // extend file .jpg, .png, .jpeg
//                fname = System.currentTimeMillis() + "." + ext;
//                // Write file path
//                filePart.write(uploadPath + "/" + fname);
//            } else if (!req.getParameter("image").isEmpty()) {
//                // Default image
//                fname = req.getParameter("image");
//            } else {
//                fname = oldImage;
//            }
//            // Delete old image file on device
//            File file = new File(uploadPath + "/" + oldImage);
//            if (file.exists()) file.deleteOnExit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Insert data to database
//        category.setName(name);
//        category.setImage(fname);
//        category.setStatus(status);
//        categoryService.update(category);
//        // Show list categories
//        resp.sendRedirect(req.getContextPath() + "/views/admin/categories.jsp");
//    }

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
        resp.sendRedirect(req.getContextPath() + "/views/admin/user/users-list.jsp");
    }

    private void showUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/admin/user/users-list.jsp").forward(req, resp);
    }
}
