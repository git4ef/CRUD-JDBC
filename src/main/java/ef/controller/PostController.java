package ef.controller;

import ef.model.Post;
import ef.service.PostService;

import java.util.List;

public class PostController {
    private PostService postService = new PostService();

    public Post getPostByID(Long id) {
        return postService.getPostByID(id);
    }

    public void deletePostByID(Long id) {
        postService.deletePostByID(id);
    }

    public Post savePost(String content) {
        return postService.savePost(new Post(content));
    }

    public Post updatePost(Long id,String content) {
        return postService.updatePost(new Post(id,content));
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
