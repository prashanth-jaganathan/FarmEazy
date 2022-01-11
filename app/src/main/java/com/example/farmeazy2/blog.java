package com.example.farmeazy2;

import java.util.Date;
import java.util.HashMap;

public class blog {
    private String Title;
    private String Author;
    private String Content;
    private HashMap<String,Object> Latlng;
    public blog(){}

    public String getAuthor() {
        return Author;
    }

    public String getContent() {
        return Content;
    }

    public String getTitle() {
        return Title;
    }

    public HashMap<String, Object> getLatlng() {
        return Latlng;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setLatlng(HashMap<String, Object> latlng) {
        Latlng = latlng;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
}
