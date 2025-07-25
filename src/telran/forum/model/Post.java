package telran.forum.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post implements Comparable<Post>{
    int postId;
    String title;
    String author;
    String content;
    LocalDateTime date;
    int likes;
    int disLikes;

    @Override
    public int compareTo(Post post) {
        return Integer.compare(this.postId, post.postId);
    }

    public Post(int postId, String title, String author, String content) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(int postId, String title, String author, String content, LocalDateTime date) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
    }


    public int addLike(){
        return ++this.likes;
    }

    public int disLike() {
        return ++this.disLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(postId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }
}
