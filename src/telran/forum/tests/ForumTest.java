package telran.forum.tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import telran.forum.dao.Forum;
import telran.forum.dao.ForumImpl;
import telran.forum.model.Post;

import static org.junit.jupiter.api.Assertions.*;

class ForumTest {

    Forum forum = new ForumImpl();


    @BeforeEach
    void setUp(){


        Post[] posts = new Post[4];

        posts[0] = new Post(1, "Title1", "Author1", "Hello hoverim");
        posts[1] = new Post(2, "Title2", "Author2", "Hello hoverim!!!");
        posts[2] = new Post(3, "Title3", "Author3", "Hello friends!");
        posts[3] = new Post(4, "Title4", "Author4", "Hello druzia");

        for (int i = 0; i < posts.length; i++) {
            forum.addPost(posts[i]);
        }

    }


    @Test
    void addPost(){
        Post newPost = new Post(5, "Title5", "Author5", "Hello world");
        assertTrue(forum.addPost(newPost));
    }

    @Test
    void removePost() {
        Post removedPost = new Post(2, "Title2", "Author2", "Hello hoverim!!!");
        assertEquals(removedPost, forum.removePost(removedPost.getPostId()));
        assertNull(forum.removePost(removedPost.getPostId()));
    }

    @Test
    void updatePost() {
        Post updatedPost = new Post(2, "Title2", "Author2", "Hello hoverim!!!");
        String newContent = "Hello, peoples";
        assertTrue(forum.updatePost(updatedPost.getPostId(), newContent));
        assertEquals("Hello, peoples", forum.getPostById(updatedPost.getPostId()).getContent());
    }

    @Test
    void getPostById() {
        Post post = new Post(2, "Title2", "Author2", "Hello hoverim!!!");
        assertEquals(post, forum.getPostById(post.getPostId()));
    }

    @Test
    void getPostsByAuthor() {
        Post oldPost1 = new Post(1, "Title1", "Author1", "Hello hoverim");
        Post oldPost2 = new Post(2, "Title2", "Author2", "Hello hoverim!!!");
        Post newPost1 = new Post(5, "Title5", "Author1", "Hello 5 times");
        Post newPost2 = new Post(6, "Title6", "Author2", "Hello 6 times");
        Post[] expected1 = {oldPost1, newPost1};
        Post[] expected2 = {oldPost2, newPost2};
        forum.addPost(newPost1);
        forum.addPost(newPost2);

        assertEquals(expected1, forum.getPostsByAuthor(oldPost1.getAuthor()));
        assertEquals(expected2, forum.getPostsByAuthor("Author2"));
    }


}
