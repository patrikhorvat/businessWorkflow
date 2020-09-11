package com.example.poslovnihodogram.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkFlow {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Group")
    @Expose
    private Group group;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("Description")
    @Expose
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
