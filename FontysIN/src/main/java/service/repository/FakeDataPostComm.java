package service.repository;



import service.model.Posts;
import service.model.Comments;


import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeDataPostComm{


    private final List<Comments> commentsList = new ArrayList<>();
    private final List<Posts> postsList = new ArrayList<>();
    public static int lastPostId = 0;
    public static int lastCommId = 0;

    public FakeDataPostComm(){
        postsList.add(new Posts(1,1,"First post!!", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        postsList.add(new Posts(2,1,"Wow!!Second post!", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

        commentsList.add(new Comments(1,1,1,"Good post",new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        commentsList.add(new Comments(2,1,2,"Bad post",new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

        for(Posts post : postsList) {
            if(post.getId() >= lastPostId){
                lastPostId = post.getId();
            }
        }
        for(Comments comm : commentsList) {
            if(comm.getId() >= lastCommId){
                lastCommId = comm.getId();
            }
        }

    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }
    public List<Comments> getCommentsListByPost(int id) {
        List<Comments> commlist = new ArrayList<>();
        for(Comments comments : commentsList) {
            if(comments.getPostId() == id){
                commlist.add(comments);
            }
        }
        return commlist;
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
        comments.setId(lastCommId+1);
        for(Comments comm : commentsList) {
            if(comm.getId() >= lastCommId){
                lastCommId = comm.getId();
            }
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

    public List<Posts> getPostsListbyUserId(int id){
        List<Posts> newPosts = new ArrayList<>();
        for(Posts posts : postsList) {
            if(posts.getUserId() == id){
                newPosts.add(posts);
            }
        }
        return newPosts;
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
        post.setId(lastPostId+1);
        for(Posts p : postsList) {
            if(p.getId() >= lastPostId){
                lastPostId = p.getId();
            }
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
