package devzeus.com.demojpa.controllers.admin;

import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.entities.Video;
import devzeus.com.demojpa.services.ICategoryService;
import devzeus.com.demojpa.services.IVideoService;
import devzeus.com.demojpa.services.impl.CategoryService;
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
    ICategoryService categoryService = new CategoryService();

    private void showVideos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> videos = videoService.findAll();
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/videos")) {
            // Show list categories
            showVideos(req, resp);
        } else if (url.contains("/admin/video/add")) {
            // Show form add category
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/edit")) {
            // Get id from url
            int id = Integer.parseInt(req.getParameter("id"));
            // Get data from database
            Video video = videoService.findById(id);
            // Set data to form
            req.setAttribute("video", video);
            // Show form edit category
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
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
            resp.sendRedirect(req.getContextPath() + "/views/admin/video-list.jsp");

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
            try {
                deleteVideo(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Get id from url
        int id = Integer.parseInt(req.getParameter("id"));
        // Delete data from database
        videoService.delete(id);

        showVideos(req, resp);
    }

    private void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get old data from database
        Video video = videoService.findById(Integer.parseInt(req.getParameter("id")));
        String oldImage = video.getPoster();
        // Get data from form
        String description = req.getParameter("description");
        String title = req.getParameter("title");
        if (req.getParameter("poster") != null){
            String poster = req.getParameter("poster");
        }
        int status = Integer.parseInt(req.getParameter("Active"));
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
        video.setTitle(title);
        video.setPoster(fname);
        video.setActive(status);
        video.setDescription(description);
        videoService.update(video);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/views/admin/video-list");
    }

    private void createVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from form
        int act = Integer.parseInt(req.getParameter("active"));
        String description = req.getParameter("description");
        String poster = req.getParameter("poster");
        String title = req.getParameter("title");
        int views = Integer.parseInt(req.getParameter("views"));
        int cate = Integer.parseInt(req.getParameter("cate"));
        Category category = categoryService.findById(cate);
        Video video = new Video();
        video.setActive(act);
        video.setDescription(description);
        video.setPoster(poster);
        video.setTitle(title);
        video.setViews(views);
        video.setCategory(category);
        videoService.insert(video);
        // Show list categories
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }

}
