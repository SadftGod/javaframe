package codes;

public class Project {
    private String name;
    private String category;
    private String location;
    private String description;
    private String prod_type;
    private String path_to_img;

    // Геттеры и сеттеры для всех полей
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    public String getPath_to_img() {
        return path_to_img;
    }

    public void setPath_to_img(String path_to_img) {
        this.path_to_img = path_to_img;
    }
}
