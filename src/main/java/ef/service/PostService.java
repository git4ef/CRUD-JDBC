package ef.service;

import ef.model.Post;
import ef.repository.DatabaseRepoImpl.PostRepoImpl;
import ef.repository.PostRepository;

import java.util.List;

public class PostService {
    private PostRepository postRepository = new PostRepoImpl();

    public Post getPostByID(Long id) {
        return postRepository.getById(id);
    }

    public void deletePostByID(Long id) {
        postRepository.deleteById(id);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        return postRepository.update(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }
}
