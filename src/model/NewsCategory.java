package model;

public class NewsCategory {
    private int id;
    private String name;

    public NewsCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public NewsCategory() {}

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}