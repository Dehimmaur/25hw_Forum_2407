package telran.forum.tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import telran.forum.dao.Forum;
import telran.forum.dao.ForumImpl;
import telran.forum.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        assertArrayEquals(expected1, forum.getPostsByAuthor(oldPost1.getAuthor()));
        assertArrayEquals(expected2, forum.getPostsByAuthor("Author2"));
    }

    @Test
    void getPostsByAuthorByDate() {
        Post newPost1 = new Post(10, "Title10", "Author10", "Hello hoverim!!!", LocalDateTime.parse("2025-07-15T23:20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        Post newPost2 = new Post(20, "Title20", "Author10", "Hello hoverim!!!", LocalDateTime.parse("2025-07-16T23:20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        Post newPost3 = new Post(30, "Title30", "Author10", "Hello hoverim!!!", LocalDateTime.parse("2025-07-17T23:20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        Post newPost4 = new Post(40, "Title40", "Author10", "Hello hoverim!!!", LocalDateTime.parse("2025-07-18T23:20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        Post newPost5 = new Post(50, "Title50", "Author10", "Hello hoverim!!!", LocalDateTime.parse("2025-07-19T23:20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        Post newPost6 = new Post(60, "Title60", "Author10", "Hello hoverim!!!", LocalDateTime.parse("2025-07-20T23:20:10", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));

        forum.addPost(newPost1);
        forum.addPost(newPost2);
        forum.addPost(newPost3);
        forum.addPost(newPost4);
        forum.addPost(newPost5);
        forum.addPost(newPost6);

        Post[] expected1 = {newPost2, newPost3, newPost4, newPost5};
        LocalDate dateFrom = LocalDate.of(2025, 7, 16);
        LocalDate dateTo = LocalDate.of(2025, 7, 19);


        assertArrayEquals(expected1, forum.getPostsByAuthor("Author10", dateFrom, dateTo));
    }


    @Test
    void getLikesByAuthor() {
        Post newPost1 = new Post(10, "Title10", "Author10", "Hello hoverim!!!", 10);
        forum.addPost(newPost1);
        assertEquals(10, forum.getLikesByAuthor(newPost1.getAuthor()));

        newPost1.addLike();
        newPost1.addLike();
        assertEquals(12, forum.getLikesByAuthor(newPost1.getAuthor()));

        Post newPost2 = new Post(20, "Title20", "Author10", "Hello hoverim!!!", 15);
        forum.addPost(newPost2);
        assertEquals(27, forum.getLikesByAuthor(newPost1.getAuthor()));

    }


}
