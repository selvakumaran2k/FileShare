package com.example.fileshare.processingPack;

import java.util.List;

public class Group {
    private String groupName;
    private String group_id;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private String timeCreated;
    private String description;
    private List<Resource> groupResources;
    private List<String> groupMembers;

    public List<Resource> getGroupResources() {
        return groupResources;
    }

    public void setGroupResources(List<Resource> groupResources) {
        this.groupResources = groupResources;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<String> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Group(String owner,String groupName, String group_id, String timeCreated, String description, List<Resource> groupResources, List<String> groupMembers) {
        this.groupName = groupName;
        this.owner = owner;
        this.group_id = group_id;
        this.timeCreated = timeCreated;
        this.description = description;
        this.groupResources = groupResources;
        this.groupMembers = groupMembers;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", group_id='" + group_id + '\'' +
                ", timeCreated='" + timeCreated + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
