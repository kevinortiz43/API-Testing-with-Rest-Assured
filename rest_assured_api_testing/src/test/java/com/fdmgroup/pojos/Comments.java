package com.fdmgroup.pojos;

public class Comments {

    private String id;
    private String body;
    private String postId;

    public Comments() {
        super();
    }

    public Comments(String id, String body, String postId) {
        this.id = id;
        this.body = body;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

}
