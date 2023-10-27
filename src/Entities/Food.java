package src.Entities;

public class Food {
    public int id;
    public String name;
    public double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
