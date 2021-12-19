package com.example.fileshare.processingPack;

public class Resource {
    private String owner;
    private String fileName;
    private int version;
    private long size;
    private String lables;
    private String location;
    private String description;
    private String chats;
    private String id;
    private String timestamp;

    public Resource(String owner, String fileName, int version, long size, String lables, String location, String description, String chats, String id,String timestamp) {
        this.owner = owner;
        this.fileName = fileName;
        this.version = version;
        this.size = size;
        this.lables = lables;
        this.location = location;
        this.description = description;
        this.chats = chats;
        this.id = id;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Resource{" +
                "owner='" + owner + '\'' +
                ", fileName='" + fileName + '\'' +
                ", version=" + version +
                ", size=" + size +
                ", lables='" + lables + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", chats='" + chats + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLables() {
        return lables;
    }

    public void setLables(String lables) {
        this.lables = lables;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChats() {
        return chats;
    }

    public void setChats(String chats) {
        this.chats = chats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
