package devzeus.com.demojpa.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.services.ICategoryService;
import devzeus.com.demojpa.services.impl.CategoryService;
import devzeus.com.demojpa.ultils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/edit",
        "/admin/category/update", "/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/categories")) {
            // Show list categories
            showCategories(req, resp);
        } else if (url.contains("/admin/category/add")) {
            // Show form add category
            req.getRequestDispatcher("/views/admin/category-add").forward(req, resp);
        } else if (url.contains("/admin/category/edit")) {
            // Get id from url
            int id = Integer.parseInt(req.getParameter("id"));
            // Get data from database
            Category category = categoryService.findById(id);
            // Set data to form
            req.setAttribute("category", category);
            // Show form edit category
            req.getRequestDispatcher("/views/admin/category-edit").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            // Get id from url
            int id = Integer.parseInt(req.getParameter("id"));
            // Delete data from database
            // Suggest delete image file on device !!!
            try {
                categoryService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Show list categories
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("add")) {
            addCategory(req, resp);
        } else if (url.contains("category/edit")) {
            editCategory(req, resp);
        } else if (url.contains("category/delete")) {
            try {
                deleteCategory(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Get id from url
        int id = Integer.parseInt(req.getParameter("id"));
        // Delete data from database
        categoryService.delete(id);

        showCategories(req, resp);
    }

    private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get old data from database
        Category category = categoryService.findById(Integer.parseInt(req.getParameter("id")));
        String oldImage = category.getImage();
        // Get data from form
        String name = req.getParameter("name");
        int status = Integer.parseInt(req.getParameter("status"));
        // Get file from form
        String fname = "";
        String uploadPath = Constant.UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir(); // Create folder if not exist
        try {
            Part filePart = req.getPart("imageUpload");
            if (filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Change file name
                int index = fileName.lastIndexOf("."); // index of extend file .jpg, .png, .jpeg
                String ext = fileName.substring(index + 1); // extend file .jpg, .png, .jpeg
                fname = System.currentTimeMillis() + "." + ext;
                // Write file path
                filePart.write(uploadPath + "/" + fname);
            } else if (!req.getParameter("image").isEmpty()) {
                // Default image
                fname = req.getParameter("image");
            } else {
                fname = oldImage;
            }
            // Delete old image file on device
            File file = new File(uploadPath + "/" + oldImage);
            if (file.exists()) file.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert data to database
        category.setName(name);
        category.setImage(fname);
        category.setStatus(status);
        categoryService.update(category);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }

    private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from form
        String name = req.getParameter("name");
        int status = Integer.parseInt(req.getParameter("status"));
        // Get file from form
        String fname = "";
        String uploadPath = Constant.UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir(); // Create folder if not exist
        try {
            Part filePart = req.getPart("imageUpload");
            if (filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Change file name
                int index = fileName.lastIndexOf("."); // index of extend file .jpg, .png, .jpeg
                String ext = fileName.substring(index + 1); // extend file .jpg, .png, .jpeg
                fname = System.currentTimeMillis() + "." + ext;
                // Write file path
                filePart.write(uploadPath + "/" + fname);
            } else {
                // Default image
                fname = req.getParameter("image");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert data to database
        Category category = new Category();
        category.setName(name);
        category.setImage(fname);
        category.setStatus(status);
        categoryService.insert(category);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/admin/categories.jsp");
    }

    private void showCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/categories.jsp").forward(req, resp);
    }
}
