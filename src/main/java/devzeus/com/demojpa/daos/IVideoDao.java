package devzeus.com.demojpa.daos;

import devzeus.com.demojpa.entities.Video;

import java.util.List;

public interface IVideoDao {
    void insert(Video video);

    void update(Video video);

    void delete(int videoId);

    Video findById(int videoId);

    List<Video> findAll();

    List<Video> findByVideoName(String videoName);

    List<Video> findAll(int page, int pageSize);
}
