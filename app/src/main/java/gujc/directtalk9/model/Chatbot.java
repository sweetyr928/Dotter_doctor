package gujc.directtalk9.model;

public class Chatbot {
    private int photo;
    private String name;
    private String current;

    public Chatbot(int photo, String name, String current) {
        this.photo = photo;
        this.name = name;
        this.current = current;
    }


    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
