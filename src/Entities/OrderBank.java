package src.Entities;

public class OrderBank {
    public int id;
    public String date;
    public double totalPrice;

    public OrderBank(int id, String date, double totalPrice) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
    }
}
