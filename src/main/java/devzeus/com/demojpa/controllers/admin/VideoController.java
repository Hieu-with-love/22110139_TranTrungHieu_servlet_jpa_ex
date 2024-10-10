package devzeus.com.demojpa.controllers.admin;

import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.entities.Video;
import devzeus.com.demojpa.services.IVideoService;
import devzeus.com.demojpa.services.impl.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import devzeus.com.demojpa.ultils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/add", "/admin/video/edit",
                            "/admin/video/delete"})
public class VideoController extends HttpServlet {
    IVideoService videoService = new VideoService();

    private void showCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> videos = videoService.findAll();
        req.setAttribute("video", videos);
        req.getRequestDispatcher("/views/admin/video/video-list").forward(req, resp);
    }

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
            req.getRequestDispatcher("/views/admin/video/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/edit")) {
            // Get id from url
            int id = Integer.parseInt(req.getParameter("id"));
            // Get data from database
            Video video = videoService.findById(id);
            // Set data to form
            req.setAttribute("video", video);
            // Show form edit category
            req.getRequestDispatcher("/views/admin/video/video-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            // Get id from url
            int id = Integer.parseInt(req.getParameter("id"));
            // Delete data from database
            // Suggest delete image file on device !!!
            try {
                videoService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Show list categories
            resp.sendRedirect(req.getContextPath() + "/admin/video/video-list");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/video/add")){
            createVideo(req, resp);
        }else if (url.contains("/video/edit")){
            updateVideo(req, resp);
        }else if (url.contains("/video/delete")){
            deleteVideo(req, resp);
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
        String description = req.getParameter("description");
        String title = req.getParameter("title");
        String poster = req.getParameter("poster");
        int views = Integer.parseInt(req.getParameter("views"));
        int status = Integer.parseInt(req.getParameter("active"));
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
        Video video = new Video();
        video.setDescription(description);
        video.setTitle(title);
        video.setPoster(poster);
        video.setViews(views);
        video.setActive(status);
        videoService.insert(video);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/admin/video/video-list");
    }

}
