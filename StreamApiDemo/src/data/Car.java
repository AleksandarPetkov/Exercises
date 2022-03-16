package data;

import java.util.ArrayList;
import java.util.List;


public class Car {

    private String color;
    private List<Engine> engine;
    private List<String> parts;

    public Car (String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        this.engine = new ArrayList<>();
    }

    public List<Engine> getEngine() {
        return engine;
    }

    public void setEngine(List<Engine> engine) {
        this.engine = engine;
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }
}
