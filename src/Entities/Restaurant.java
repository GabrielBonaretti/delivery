package src.Entities;

import src.Database.Database;

import java.util.ArrayList;

public class Restaurant {
    public int id;
    public String name;
    public Address address;

    public ArrayList<Food> listLanches = new ArrayList<Food>();

    public Restaurant(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void addFood(Food food) {
        Database database = new Database();
        database.addFood(id, food.name, food.price);
    }

    public void removeFood(int foodId) {
        Database database = new Database();
        database.deleteFood(foodId);
    }

    public void setListLanches() {
        Database database = new Database();
        ArrayList<Food> listFoods = database.getAllFoods(this.id);
        this.listLanches.clear();
        this.listLanches = listFoods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Food> getListLanches() {
        return listLanches;
    }
}
