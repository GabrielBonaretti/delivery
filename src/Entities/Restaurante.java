package src.Entities;

import src.Database.Database;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    public int id;
    public String nome;
    public Endereco localizacao;
    public ArrayList<Lanche> listaLanches = new ArrayList<Lanche>();

    public Restaurante(String nome, Endereco localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void adicionarLanche(Lanche lanche) {
        Database database = new Database();
        database.addFood(id, lanche.nome, lanche.preco);
    }

    public void removerLanche(int lancheId) {
        Database database = new Database();
        database.deleteFood(lancheId);
    }

    public void setListaLanches() {
        Database database = new Database();
        ArrayList<Lanche> listFoods = database.getAllFoods(this.id);
        this.listaLanches.clear();
        this.listaLanches = listFoods;
    }

    public void setId(int id) {
        this.id = id;
    }

}
