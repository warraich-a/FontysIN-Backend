package service.repository;



import service.model.Comments;
import service.model.Posts;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeDateBase {


    private final List<Comments> commentsList = new ArrayList<>();
    private final List<Posts> postsList = new ArrayList<>();

    public FakeDateBase(){
        postsList.add(new Posts(1,1,"First post!!", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        postsList.add(new Posts(2,1,"Wow!!Second post!", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

        commentsList.add(new Comments(1,1,1,"Good post",new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        commentsList.add(new Comments(2,1,2,"Bad post",new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }
    public Comments getComment(int id){
        for(Comments comments : commentsList) {
            if(comments.getId() == id){
                return comments;
            }
        }
        return null;
    }

    public boolean deleteComment(int stNr) {
        Comments comments = getComment(stNr);
        if(comments == null){
            return false;
        }
        return commentsList.remove(comments);
    }

    public boolean addComment(Comments comments) {
        if (this.getComment(comments.getId()) != null){
            return false;
        }
        commentsList.add(comments);
        return true;
    }

    public boolean updateComment(Comments comments) {
        Comments old = this.getComment(comments.getId());
        if (old == null) {
            return false;
        }
        old.setContent(comments.getContent());
        return true;
    }



    public List<Posts> getPostsList() {
        return postsList;
    }

    public Posts getPost(int id){
        for(Posts posts : postsList) {
            if(posts.getId() == id){
                return posts;
            }
        }
        return null;
    }

    public boolean deletePost(int stNr) {
        Posts post = getPost(stNr);
        if(post == null){
            return false;
        }
        return postsList.remove(post);
    }

    public boolean addPost(Posts post) {
        if (this.getPost(post.getId()) != null){
            return false;
        }
        postsList.add(post);
        return true;
    }

    public boolean updatePost(Posts post) {
        Posts old = this.getPost(post.getId());
        if (old == null) {
            return false;
        }
        old.setContent(post.getContent());
        return true;
    }



}
