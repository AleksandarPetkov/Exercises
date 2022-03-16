package data;

public class Wheel {

    private String color;
    private Boolean isWorking;

    public Wheel(String color, Boolean isWorking) {
        this.color = color;
        this.isWorking = isWorking;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }
}
