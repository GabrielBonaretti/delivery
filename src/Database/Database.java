package src.Database;

import src.Entities.*;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public Connection conectar() {
        String CLASSE_DRIVER = "com.mysql.cj.jdbc.Driver";
        String USUARIO = "root";
        String SENHA = "";
        String URL_SERVIDOR = "jdbc:mysql://localhost:3306/delivery?useSSL=false";

        try {
            Class.forName(CLASSE_DRIVER);
            return DriverManager.getConnection(URL_SERVIDOR, USUARIO, SENHA);
        } catch (Exception e) {
            if (e instanceof ClassNotFoundException) {
                e.printStackTrace();
                System.err.println("Verifique o driver de conexão");
            } else {
                System.err.println("Verifique se o servidor está ativo");
            }
            System.exit(-42);
            return null;
        }
    }

    public void desconectar(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {
                System.err.println("Não foi possível fechar a conexão.");
            }
        }
    }

    public void createUser(String name, String cpf, int positionX, int positionY, String password) {
        String hexPassword = HashPassword.hexPassword(password);

        String INSERIR = "INSERT INTO users (name, cpf, positionX, positionY, password) VALUES (?, ?, ?, ?, ?)";


        try {
            Connection conn = conectar();
            PreparedStatement salvar = conn.prepareStatement(INSERIR);

            salvar.setString(1, name);
            salvar.setString(2, cpf);
            salvar.setInt(3, positionX);
            salvar.setInt(4, positionY);
            salvar.setString(5, hexPassword);

            salvar.executeUpdate();
            salvar.close();
            desconectar(conn);

            System.out.println("O usuario " + name + " foi inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro salvando o produto");
            System.exit(-42);
        }
    }

    public void createRestaurant(String name, String cnpj, int positionX, int positionY, String password) {
        String hexPassword = HashPassword.hexPassword(password);

        String INSERIR = "INSERT INTO restaurants (name, cnpj, positionX, positionY, password) VALUES (?, ?, ?, ?, ?)";


        try {
            Connection conn = conectar();
            PreparedStatement salvar = conn.prepareStatement(INSERIR);

            salvar.setString(1, name);
            salvar.setString(2, cnpj);
            salvar.setInt(3, positionX);
            salvar.setInt(4, positionY);
            salvar.setString(5, hexPassword);

            salvar.executeUpdate();
            salvar.close();
            desconectar(conn);

            System.out.println("O usuario " + name + " foi inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }

    public int verifyAcountUser(String name, String password) {
        String hexPassword = HashPassword.hexPassword(password);

        String VERIFY = "SELECT * FROM users WHERE name=? AND password=?";

        try {
            Connection conn = conectar();
            PreparedStatement user = conn.prepareStatement(VERIFY);

            user.setString(1, name);
            user.setString(2, hexPassword);

            ResultSet resultado = user.executeQuery();

            if (resultado.isBeforeFirst() && resultado.next()) {
                int idUser = resultado.getInt(1);
                user.close();
                desconectar(conn);
                return idUser;
            } else {
                user.close();
                desconectar(conn);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }
        return 0;
    }

    public int verifyAcountRestaurant(String name, String password) {
        String hexPassword = HashPassword.hexPassword(password);

        String VERIFY = "SELECT * FROM restaurants WHERE name=? AND password=?";

        try {
            Connection conn = conectar();
            PreparedStatement user = conn.prepareStatement(VERIFY);

            user.setString(1, name);
            user.setString(2, hexPassword);

            ResultSet resultado = user.executeQuery();

            if (resultado.isBeforeFirst() && resultado.next()) {
                int idUser = resultado.getInt(1);
                user.close();
                desconectar(conn);
                return idUser;
            } else {
                user.close();
                desconectar(conn);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }
        return 0;
    }

    public ArrayList<Restaurante> getAllRestaurants() {
        String getAllRestaurants = "SELECT * FROM restaurants";
        ArrayList<Restaurante> listaRestaurants = new ArrayList<>();
        try {
            Connection conn = conectar();
            PreparedStatement restaurant = conn.prepareStatement(getAllRestaurants);

            ResultSet resultado = restaurant.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    Endereco endereco = new Endereco(resultado.getInt(4),resultado.getInt(5));
                    Restaurante restaurante = new Restaurante(resultado.getString(2), endereco);
                    restaurante.setId(resultado.getInt(1));
                    listaRestaurants.add(restaurante);
                }
            } else {
                System.out.println("Não existem restaurantes cadastrados.");
            }

            restaurant.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listaRestaurants;
    }

    public Restaurante getRestaurant(int id) {
        String getRestaurant = "SELECT * FROM restaurants WHERE id=?";
        Restaurante restaurante = null;
        try {
            Connection conn = conectar();
            PreparedStatement restaurant = conn.prepareStatement(getRestaurant);

            restaurant.setInt(1, id);


            ResultSet resultado = restaurant.executeQuery();

            if (resultado.isBeforeFirst() && resultado.next()) {
                Endereco endereco = new Endereco(resultado.getInt(4),resultado.getInt(5));
                restaurante = new Restaurante(resultado.getString(2), endereco);
            } else {
                System.out.println("Não existe esse restaurante.");
            }

            restaurant.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return restaurante;
    }

    public ArrayList<Lanche> getAllFoods(int id) {
        String getAllFoods = "SELECT * FROM foods WHERE idRestaurant=? AND active=?";
        ArrayList<Lanche> listFoods = new ArrayList<>();
        try {
            Connection conn = conectar();
            PreparedStatement food = conn.prepareStatement(getAllFoods);

            food.setInt(1, id);
            food.setBoolean(2, true);

            ResultSet resultado = food.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    Lanche newFood = new Lanche(resultado.getString(3), resultado.getDouble(4));
                    newFood.setId(resultado.getInt(1));
                    listFoods.add(newFood);
                }
            } else {
                System.out.println("Não existem foods na loja.");
            }

            food.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listFoods;
    }

    public void addFood(int idRestaurant, String name, Double preco) {
        String ADDFOOD = "INSERT INTO foods (idRestaurant, name, preco, active) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = conectar();
            PreparedStatement insertFood = conn.prepareStatement(ADDFOOD);

            insertFood.setInt(1, idRestaurant);
            insertFood.setString(2, name);
            insertFood.setDouble(3, preco);
            insertFood.setBoolean(4, true);

            insertFood.executeUpdate();
            insertFood.close();
            desconectar(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }

    public void deleteFood(int idFood) {
        String SEARCH_FOR_ID = "SELECT * FROM foods WHERE id=?";
        String DEACTIVATE_FOOD = "UPDATE foods SET active=? WHERE id=?";

        try {
            Connection conn = conectar();
            PreparedStatement produto = conn.prepareStatement(SEARCH_FOR_ID);

            produto.setInt(1, idFood);

            ResultSet resultado = produto.executeQuery();

            if (resultado.isBeforeFirst()) {
                PreparedStatement update = conn.prepareStatement(DEACTIVATE_FOOD);

                update.setBoolean(1, false);
                update.setInt(2, idFood);

                update.executeUpdate();
            } else {
                System.out.println("Não existe produto com o ID informado.");
            }

            produto.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao atualizar o produto");
            System.exit(-42);
        }
    }

    public void addOrder(int idUser, String date, double totalPrice) {
        String ADD_ORDER = "INSERT INTO orders (idUser, dateOrder, priceTotal) VALUES (?, ?, ?)";

        try {
            Connection conn = conectar();
            PreparedStatement addOrder = conn.prepareStatement(ADD_ORDER);

            addOrder.setInt(1, idUser);
            addOrder.setString(2, date);
            addOrder.setDouble(3, totalPrice);

            addOrder.executeUpdate();

            addOrder.close();
            desconectar(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }

    public int getLastOrder() {
        String GET_LAST_ORDER = "SELECT * FROM orders ORDER BY id DESC LIMIT 1;";
        int idLastOrder = 0;
        try {
            Connection conn = conectar();
            PreparedStatement order = conn.prepareStatement(GET_LAST_ORDER);

            ResultSet resultado = order.executeQuery();

            if (resultado.isBeforeFirst() && resultado.next()) {
                idLastOrder = resultado.getInt(1);
            } else {
                System.out.println("Não existem restaurantes cadastrados.");
            }

            order.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return idLastOrder;
    }

    public ArrayList<Order> getAllOrders(int idUser) {
        String getAllOrders = "SELECT * FROM orders WHERE idUser=? ORDER BY id DESC";
        ArrayList<Order> listOrders = new ArrayList<>();
        try {
            Connection conn = conectar();
            PreparedStatement orders = conn.prepareStatement(getAllOrders);

            orders.setInt(1, idUser);

            ResultSet resultado = orders.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    Order order = new Order(resultado.getInt(1), resultado.getString(3), resultado.getDouble(4));
                    listOrders.add(order);
                }
            } else {
                System.out.println("Não existem foods na loja.");
            }

            orders.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listOrders;
    }

    public void setOrderFood(int idOrder, int idFood, int quantity) {
        String ADD_ORDER_FOOD = "INSERT INTO orderFoods (idOrder, idFood, quantity) VALUES (?, ?, ?)";

        try {
            Connection conn = conectar();
            PreparedStatement addOrder = conn.prepareStatement(ADD_ORDER_FOOD);

            addOrder.setInt(1, idOrder);
            addOrder.setInt(2, idFood);
            addOrder.setInt(3, quantity);

            addOrder.executeUpdate();

            addOrder.close();
            desconectar(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }

    public ArrayList<ArrayList<Object>> getSpecificOrder(int idOrder) {
        String SPECIFIC_ORDER = "SELECT * FROM orderFoods WHERE idOrder=?";

        ArrayList<ArrayList<Object>> listItemsOrder = new ArrayList<>();
        try {
            Connection conn = conectar();
            PreparedStatement foodOrder = conn.prepareStatement(SPECIFIC_ORDER);

            foodOrder.setInt(1, idOrder);

            ResultSet resultado = foodOrder.executeQuery();

            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    ArrayList<Object> itemOrder = new ArrayList<>();

                    int idFood = resultado.getInt(3);

                    String GET_FOOD = "SELECT * FROM foods WHERE id=?";

                    PreparedStatement food = conn.prepareStatement(GET_FOOD);

                    food.setInt(1, idFood);

                    ResultSet resultadoFood = food.executeQuery();

                    if (resultadoFood.next()) {
                        Lanche lanche = new Lanche(resultadoFood.getString(3), resultadoFood.getDouble(4));

                        itemOrder.add(lanche);
                        itemOrder.add(resultado.getInt(4));

                        listItemsOrder.add(itemOrder);

                    }
                    resultadoFood.close();
                }
            } else {
                System.out.println("Não existem foods na loja.");
            }

            foodOrder.close();
            desconectar(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listItemsOrder;
    }
}
