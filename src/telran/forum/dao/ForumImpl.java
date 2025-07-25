package telran.forum.dao;

import telran.forum.model.Post;
import java.time.LocalDate;
import java.util.Arrays;

public class ForumImpl implements Forum {
    private Post[] posts;
    private int size;


    public ForumImpl() {
        posts = new Post[0];
    }

    private void forumСhangeSize() {
        if(size > posts.length - 10) {
            int addLength = posts.length == 0 ? 10 : posts.length << 1;
            posts = Arrays.copyOf(posts, addLength);
        }

        if(size < posts.length >> 2) {
            int removeLength = posts.length < 10 ? 10 : posts.length >> 1;
            posts = Arrays.copyOf(posts, removeLength);
        }
    }

    @Override
    public boolean addPost(Post post) {
        forumСhangeSize();
        if (size == posts.length || post == null || getPostById(post.getPostId()) != null) {
            return false;
        }
        int index = Arrays.binarySearch(posts, 0, size, post);
        index = index < 0 ? -index - 1 : index;
        System.arraycopy(posts, index, posts, index + 1, size - index);
        posts[index] = post;
        size++;
        return true;
    }

    @Override
    public Post removePost(int postId) {
        Post pattern = new Post(postId, null, null, null);
        for (int i = 0; i < size; i++) {
            if (posts[i].equals(pattern)) {
                Post removedPost = posts[i];
                System.arraycopy(posts, i + 1, posts, i, size - i - 1);
                posts[--size] = null;
                forumСhangeSize();
                return removedPost;
            }
        }
        return null;
    }

    @Override
    public boolean updatePost(int postId, String content) {
        Post post = getPostById(postId);
        if (content == null) {
            return false;
        }
        post.setContent(content);
        return true;
    }

    @Override
    public Post getPostById(int postId) {
        Post pattern = new Post(postId, null,null,null);
        for (int i = 0; i < size; i++) {
            if (posts[i].equals(pattern)) {
                return posts[i];
            }
        }
        return null;
    }

    @Override
    public Post[] getPostsByAuthor(String author) {
        Post[] result = new Post[size];
        int l = 0;
        for (int i = 0; i < size; i++) {
            if (author != null && author.equals(posts[i].getAuthor())) {
                result[l++] = posts[i];
            }
        }
        return Arrays.copyOf(result, l);
    }


    @Override
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        return new Post[0];
    }

    @Override
    public int size() {
        return size;
    }
}
