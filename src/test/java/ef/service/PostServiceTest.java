package ef.service;

import ef.model.Post;
import ef.model.Region;
import ef.repository.DatabaseRepoImpl.PostRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @InjectMocks
    PostService postService;
    @Mock
    PostRepoImpl postRepo;
    @Captor
    ArgumentCaptor<Post> requestCaptorPost = ArgumentCaptor.forClass(Post.class);
    Post post;
    @BeforeEach
    void setup() {
        post = new Post(1L, "content");
    }

    @Test
    public void shouldGetPostObject_whenSavePost() {
        when(postService.savePost(post)).thenReturn(post);
        Post resultPost = postService.savePost(post);
        verify(postRepo, times(1)).save(requestCaptorPost.capture());
        assertEquals(post,resultPost);
    }

    @Test
    public void shouldGetExistPostObject_whenFindPostByID() {
        postService.savePost(post);
        when(postService.getPostByID(1L)).thenReturn(post);
        Post expectedPost = postService.getPostByID(1L);
        verify(postRepo, times(1)).getById(anyLong());
        assertEquals(post,expectedPost);
    }

    @Test
    public void shouldDoNothing_whenDeletePostByID() {
        doNothing().when(postRepo).deleteById(1L);
        postRepo.save(post);
        postRepo.deleteById(1L);
        assertTrue(postRepo.getAll().isEmpty());
        verify(postRepo,times(1)).deleteById(1L);
    }

    @Test
    public void shouldGetUpdatedPostObject_whenUpdatePostByID() {
        when(postService.updatePost(any())).thenReturn(post);
        post.setContent("update");
        Post expectedPost = postService.updatePost(post);
        verify(postRepo, times(1)).update(any());
        assertEquals(expectedPost.getContent(), "update");
    }

    @Test
    public void shouldGetAllPosts(){
        postService.savePost(post);
        when(postService.getAllPosts()).thenReturn(List.of(post));
        List<Post> posts = postService.getAllPosts();
        verify(postRepo,times(1)).getAll();
        assertEquals(posts.size(),1);
    }
}
