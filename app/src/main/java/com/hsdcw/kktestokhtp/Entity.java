package com.hsdcw.kktestokhtp;

/**
 * Created by Administrator on 2018/3/11.
 */

public class Entity {
    private String id;
    private String name;
    private String description;
    private String imgUrl;
    private String type;
    public Entity(){

    }
    public Entity(String id, String name, String description, String imgUrl, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

