package src.Entities;

public class Order {
    public int id;
    public String date;
    public double totalPrice;

    public Order(int id, String date, double totalPrice) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
    }
}
