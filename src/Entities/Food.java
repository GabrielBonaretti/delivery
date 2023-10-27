package src.Entities;

public class Food {
    public int id;
    public String nome;
    public double preco;

    public Food(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public void setId(int id) {
        this.id = id;
    }
}
