package src.Entities;

import src.Database.Database;

import java.util.ArrayList;

public class Restaurant {
    public int id;
    public String nome;
    public Address localizacao;


    public ArrayList<Food> listLanches = new ArrayList<Food>();

    public Restaurant(String nome, Address localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void addFood(Food food) {
        Database database = new Database();
        database.addFood(id, food.nome, food.preco);
    }

    public void removeFood(int lancheId) {
        Database database = new Database();
        database.deleteFood(lancheId);
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
