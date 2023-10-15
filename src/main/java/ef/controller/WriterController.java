package ef.controller;

import ef.model.Post;
import ef.model.Region;
import ef.model.Writer;
import ef.service.PostService;
import ef.service.RegionService;
import ef.service.WriterService;

import java.util.ArrayList;
import java.util.List;

public class WriterController {

    private WriterService writerService = new WriterService();
    private RegionService regionService = new RegionService();
    private PostService postService = new PostService();

    public Writer getWriterByID(Long id) {
        return writerService.getWriterByID(id);
    }

    public void deleteWriterByID(Long id) {
        writerService.deleteWriterById(id);
    }

    public Writer saveWriter(String firstName, String lastName, String name, String content) {
        List<Post> posts = new ArrayList<>();
        posts.add(postService.savePost(new Post(content)));
        return writerService.saveWriter(new Writer(firstName, lastName,posts,regionService.saveRegion(new Region(name))));
    }

    public Writer updateWriter(Long id,String firsName, String lastName) {
        return writerService.updateWriter(new Writer(id,firsName, lastName));
    }

    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }
}
