package com.example.fullstacknewsapp_android;

public class CommentModel {
    private String id;
    private String content;
    private String commenter;
    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }



    public CommentModel(String id, String content, String commenter) {
        this.id = id;
        this.content = content;
        this.commenter = commenter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
