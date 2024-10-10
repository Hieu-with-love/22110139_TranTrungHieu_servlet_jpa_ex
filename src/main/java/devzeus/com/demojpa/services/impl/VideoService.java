package devzeus.com.demojpa.services.impl;

import devzeus.com.demojpa.configs.JpaConfig;
import devzeus.com.demojpa.daos.ICategoryDao;
import devzeus.com.demojpa.daos.IVideoDao;
import devzeus.com.demojpa.daos.ipml.CategoryDao;
import devzeus.com.demojpa.daos.ipml.VideoDao;
import devzeus.com.demojpa.entities.Video;
import devzeus.com.demojpa.services.IVideoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class VideoService implements IVideoService {
    IVideoDao videoDao = new VideoDao();

    @Override
    public void insert(Video video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void delete(int videoId) {
        videoDao.delete(videoId);
    }

    @Override
    public Video findById(int videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> findByVideoName(String videoName) {
        return videoDao.findByVideoName(videoName);
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        return videoDao.findAll(page, pageSize);
    }
}
