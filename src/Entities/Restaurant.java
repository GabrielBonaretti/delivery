package src.Entities;

import src.Database.Database;

import java.util.ArrayList;

public class Restaurant {
    public int id;
    public String nome;
    public Address localizacao;
    public ArrayList<Food> listaLanches = new ArrayList<Food>();

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

    public void setListaLanches() {
        Database database = new Database();
        ArrayList<Food> listFoods = database.getAllFoods(this.id);
        this.listaLanches.clear();
        this.listaLanches = listFoods;
    }

    public void setId(int id) {
        this.id = id;
    }

}
