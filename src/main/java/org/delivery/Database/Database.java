package org.delivery.Database;

import org.delivery.Entities.*;

import java.sql.*;
import java.util.ArrayList;


/**
 * The Database class provides methods to interact with a MySQL database
 * for managing users, restaurants, orders, and food items.
 */
public class Database {
    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A Connection object representing the database connection.
     */
    public Connection connect(boolean connectDatabase) {
        // Database connection parameters
        String CLASS_DRIVER = "com.mysql.cj.jdbc.Driver";
        String USER = "root";
        String PASSWORD = "";
//        String URL_SERVER = "jdbc:mysql://localhost:3306";
        String BASE_URL = "jdbc:mysql://localhost:3306";
        String URL_SERVER = connectDatabase ? BASE_URL + "/delivery?useSSL=false" : BASE_URL;;


        try {
            // Load the MySQL JDBC driver and establish a connection
            Class.forName(CLASS_DRIVER);
            return DriverManager.getConnection(URL_SERVER, USER, PASSWORD);
        } catch (Exception e) {
            // Handle exceptions related to database connection
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

    /**
     * Closes the database connection.
     *
     * @param conn The Connection object to be closed.
     */
    public void disconnect(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {
                System.err.println("Não foi possível fechar a conexão.");
            }
        }
    }

    /**
     * Create database an tables if not exists
     * */
    public void createDatabase() {
        String CREATE_DATABASE_QUERY = """
               create database if not exists delivery;
               """;

        String SELECT_DATABASE_QUERY = """
               use delivery;
               """;

        String CREATE_TABLE_USERS_QUERY = """
                create table if NOT exists users (
                    id int auto_increment primary key,
                    name varchar(100) not null unique,
                    cpf varchar(255) not null,
                    positionX int not null,
                    positionY int not null,
                    password varchar(255) not null
                );
                """;

        String CREATE_TABLE_RESTAURANTS_QUERY = """
                create table if NOT exists restaurants (
                    id int auto_increment primary key,
                    name varchar(100) not null unique,
                    cnpj varchar(25) not null,
                    positionX int not null,
                    positionY int not null,
                    password varchar(255) not null
                );
                """;

        String CREATE_TABLE_ORDERS_QUERY = """
                create table if NOT exists orders (
                    id int auto_increment primary key,
                    idUser int not null,
                    dateOrder datetime not null,
                    priceTotal DOUBLE NOT NULL,
                	constraint fkUsers foreign key (idUser) references users(id)
                );
                """;

        String CREATE_TABLE_FOODS_QUERY = """
                create table if NOT exists foods (
                	id int auto_increment primary key,
                    idRestaurant int not null,
                    name varchar(100) not null,
                    preco double not null,
                    active boolean not null,
                	constraint fkRestaurant foreign key (idRestaurant) references restaurants(id)
                );
                """;

        String CREATE_TABLE_ORDER_FOODS_QUERY = """
                create table if NOT exists OrderFoods (
                    id int auto_increment primary key,
                    idOrder int not null,
                    idFood int not null,
                    quantity INT NOT NULL,
                	constraint fkOrder foreign key (idOrder) references orders(id),
                	constraint fkFood foreign key (idFood) references foods(id)
                );
                """;

        try {
            Connection conn = connect(false);
            Statement statement = conn.createStatement();

            statement.executeUpdate(CREATE_DATABASE_QUERY);
            statement.executeUpdate(SELECT_DATABASE_QUERY);
            statement.executeUpdate(CREATE_TABLE_USERS_QUERY);
            statement.executeUpdate(CREATE_TABLE_RESTAURANTS_QUERY);
            statement.executeUpdate(CREATE_TABLE_ORDERS_QUERY);
            statement.executeUpdate(CREATE_TABLE_FOODS_QUERY);
            statement.executeUpdate(CREATE_TABLE_ORDER_FOODS_QUERY);

            statement.close();
            disconnect(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }

    /**
     * Creates a new user in the database.
     *
     * @param name     The name of the user.
     * @param cpf      The CPF (Brazilian ID) of the user.
     * @param positionX The X coordinate of the user's position.
     * @param positionY The Y coordinate of the user's position.
     * @param password The user's password.
     */
    public void createUser(String name, String cpf, int positionX, int positionY, String password) {
        String hexPassword = HashPassword.hexPassword(password);
        String INSERT_QUERY = "INSERT INTO users (name, cpf, positionX, positionY, password) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = connect(true);
            PreparedStatement save = conn.prepareStatement(INSERT_QUERY);

            save.setString(1, name);
            save.setString(2, cpf);
            save.setInt(3, positionX);
            save.setInt(4, positionY);
            save.setString(5, hexPassword);

            save.executeUpdate();
            save.close();
            disconnect(conn);

            System.out.println("O usuario " + name + " foi inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }


    /**
     * Creates a new restaurant in the database.
     *
     * @param name     The name of the restaurant.
     * @param cnpj     The CNPJ (Brazilian business ID) of the restaurant.
     * @param positionX The X coordinate of the restaurant's position.
     * @param positionY The Y coordinate of the restaurant's position.
     * @param password The restaurant's password.
     */
    public void createRestaurant(String name, String cnpj, int positionX, int positionY, String password) {
        String hexPassword = HashPassword.hexPassword(password);
        String INSERT_QUERY = "INSERT INTO restaurants (name, cnpj, positionX, positionY, password) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = connect(true);
            PreparedStatement save = conn.prepareStatement(INSERT_QUERY);

            save.setString(1, name);
            save.setString(2, cnpj);
            save.setInt(3, positionX);
            save.setInt(4, positionY);
            save.setString(5, hexPassword);

            save.executeUpdate();
            save.close();
            disconnect(conn);

            System.out.println("O usuario " + name + " foi inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }


    /**
     * Verifies the user's account in the database.
     *
     * @param name     The name of the user.
     * @param password The user's password.
     * @return A User object if the account is verified, otherwise null.
     */
    public User verifyAcountUser(String name, String password) {
        String hexPassword = HashPassword.hexPassword(password);
        String VERIFY_QUERY = "SELECT * FROM users WHERE name=? AND password=?";

        try {
            Connection conn = connect(true);
            PreparedStatement user = conn.prepareStatement(VERIFY_QUERY);

            user.setString(1, name);
            user.setString(2, hexPassword);

            ResultSet resultado = user.executeQuery();

            if (resultado.isBeforeFirst() && resultado.next()) {
                Address address = new Address(resultado.getInt(4), resultado.getInt(5));

                User userObject = new User(
                    resultado.getInt(1),
                    resultado.getString(2),
                    address,
                    resultado.getString(3)
                );

                user.close();
                disconnect(conn);

                return userObject;
            } else {
                user.close();
                disconnect(conn);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }
        return null;
    }


    /**
     * Verifies the restaurant's account in the database.
     *
     * @param name     The name of the restaurant.
     * @param password The restaurant's password.
     * @return A User object if the account is verified, otherwise null.
     */
    public User verifyAcountRestaurant(String name, String password) {
        String hexPassword = HashPassword.hexPassword(password);
        String VERIFY_QUERY = "SELECT * FROM restaurants WHERE name=? AND password=?";

        try {
            Connection conn = connect(true);
            PreparedStatement user = conn.prepareStatement(VERIFY_QUERY);

            user.setString(1, name);
            user.setString(2, hexPassword);

            ResultSet resultado = user.executeQuery();

            if (resultado.isBeforeFirst() && resultado.next()) {
                Address address = new Address(resultado.getInt(4), resultado.getInt(5));

                User userObject = new User(
                        resultado.getInt(1),
                        resultado.getString(2),
                        address,
                        resultado.getString(3)
                );

                user.close();
                disconnect(conn);
                return userObject;
            } else {
                user.close();
                disconnect(conn);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }
        return null;
    }


    /**
     * Retrieves a list of all restaurants from the database.
     *
     * @return An ArrayList of Restaurant objects.
     */
    public ArrayList<Restaurant> getAllRestaurants() {
        String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM restaurants";
        ArrayList<Restaurant> listRestaurants = new ArrayList<>();

        try {
            Connection conn = connect(true);
            PreparedStatement restaurantQuery = conn.prepareStatement(GET_ALL_RESTAURANTS_QUERY);

            ResultSet result = restaurantQuery.executeQuery();

            if (result.isBeforeFirst()) {
                while (result.next()) {
                    Address address = new Address(result.getInt(4),result.getInt(5));
                    Restaurant restaurant = new Restaurant(result.getString(2), address);
                    restaurant.setId(result.getInt(1));
                    listRestaurants.add(restaurant);
                }
            } else {
                System.out.println("Não existem restaurantes cadastrados.");
            }

            restaurantQuery.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listRestaurants;
    }


    /**
     * Retrieves a specific restaurant from the database based on its ID.
     *
     * @param id The ID of the restaurant.
     * @return A Restaurant object if found, otherwise null.
     */
    public Restaurant getRestaurant(int id) {
        String GET_RESTAURANT_QUERY = "SELECT * FROM restaurants WHERE id=?";
        Restaurant restaurant = null;

        try {
            Connection conn = connect(true);
            PreparedStatement preparedStatement = conn.prepareStatement(GET_RESTAURANT_QUERY);

            preparedStatement.setInt(1, id);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst() && resultSet.next()) {
                Address address = new Address(resultSet.getInt(4),resultSet.getInt(5));
                restaurant = new Restaurant(resultSet.getString(2), address);
            } else {
                System.out.println("Não existe esse restaurante.");
            }

            preparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return restaurant;
    }


    /**
     * Retrieves a list of all active foods associated with a restaurant from the database.
     *
     * @param id The ID of the restaurant.
     * @return An ArrayList of Food objects.
     */
    public ArrayList<Food> getAllFoods(int id) {
        String GET_ALL_FOODS_QUERY = "SELECT * FROM foods WHERE idRestaurant=? AND active=?";
        ArrayList<Food> listFoods = new ArrayList<>();

        try {
            Connection conn = connect(true);
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_FOODS_QUERY);

            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, true);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Food newFood = new Food(resultSet.getString(3), resultSet.getDouble(4));
                    newFood.setId(resultSet.getInt(1));
                    listFoods.add(newFood);
                }
            } else {
                System.out.println("Não existem foods na loja.");
            }

            preparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listFoods;
    }


    /**
     * Adds a new food item to the database for a specific restaurant.
     *
     * @param idRestaurant The ID of the restaurant.
     * @param name         The name of the food item.
     * @param preco        The price of the food item.
     */
    public void addFood(int idRestaurant, String name, Double preco) {
        String ADD_FOOD_QUERY = "INSERT INTO foods (idRestaurant, name, preco, active) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = connect(true);
            PreparedStatement insertFood = conn.prepareStatement(ADD_FOOD_QUERY);

            insertFood.setInt(1, idRestaurant);
            insertFood.setString(2, name);
            insertFood.setDouble(3, preco);
            insertFood.setBoolean(4, true);

            insertFood.executeUpdate();
            insertFood.close();
            disconnect(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }


    /**
     * Deactivates (soft deletes) a food item in the database.
     *
     * @param idFood The ID of the food item.
     */
    public void deleteFood(int idFood) {
        String SEARCH_FOR_ID_QUERY = "SELECT * FROM foods WHERE id=?";
        String DEACTIVATE_FOOD_QUERY = "UPDATE foods SET active=? WHERE id=?";

        try {
            Connection conn = connect(true);
            PreparedStatement selectPreparedStatement = conn.prepareStatement(SEARCH_FOR_ID_QUERY);

            selectPreparedStatement.setInt(1, idFood);

            ResultSet resultSet = selectPreparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                PreparedStatement updatePreparedStatement = conn.prepareStatement(DEACTIVATE_FOOD_QUERY);

                updatePreparedStatement.setBoolean(1, false);
                updatePreparedStatement.setInt(2, idFood);

                updatePreparedStatement.executeUpdate();
            } else {
                System.out.println("Não existe produto com o ID informado.");
            }

            selectPreparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }


    /**
     * Adds a new order to the database.
     *
     * @param idUser      The ID of the user placing the order.
     * @param date        The date of the order.
     * @param totalPrice  The total price of the order.
     */
    public void addOrder(int idUser, String date, double totalPrice) {
        String ADD_ORDER_QUERY = "INSERT INTO orders (idUser, dateOrder, priceTotal) VALUES (?, ?, ?)";

        try {
            Connection conn = connect(true);
            PreparedStatement addOrderPreparedStatement = conn.prepareStatement(ADD_ORDER_QUERY);

            addOrderPreparedStatement.setInt(1, idUser);
            addOrderPreparedStatement.setString(2, date);
            addOrderPreparedStatement.setDouble(3, totalPrice);

            addOrderPreparedStatement.executeUpdate();

            addOrderPreparedStatement.close();
            disconnect(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }


    /**
     * Retrieves the ID of the last order placed in the database.
     *
     * @return The ID of the last order.
     */
    public int getLastOrder() {
        String GET_LAST_ORDER_QUERY = "SELECT * FROM orders ORDER BY id DESC LIMIT 1;";
        int idLastOrder = 0;

        try {
            Connection conn = connect(true);
            PreparedStatement orderPreparedStatement = conn.prepareStatement(GET_LAST_ORDER_QUERY);

            ResultSet resultSet = orderPreparedStatement.executeQuery();

            if (resultSet.isBeforeFirst() && resultSet.next()) {
                idLastOrder = resultSet.getInt(1);
            } else {
                System.out.println("Não existem restaurantes cadastrados.");
            }

            orderPreparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return idLastOrder;
    }


    /**
     * Retrieves a list of all orders for a specific user from the database.
     *
     * @param idUser The ID of the user.
     * @return An ArrayList of OrderBank objects.
     */
    public ArrayList<OrderBank> getAllOrders(int idUser) {
        String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders WHERE idUser=? ORDER BY id DESC";
        ArrayList<OrderBank> listOrderBanks = new ArrayList<>();
        try {
            Connection conn = connect(true);
            PreparedStatement ordersPreparedStatement = conn.prepareStatement(GET_ALL_ORDERS_QUERY);

            ordersPreparedStatement.setInt(1, idUser);

            ResultSet resultSet = ordersPreparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    OrderBank orderBank = new OrderBank(resultSet.getInt(1), resultSet.getString(3), resultSet.getDouble(4));
                    listOrderBanks.add(orderBank);
                }
            } else {
                System.out.println("Não existem foods na loja.");
            }

            ordersPreparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listOrderBanks;
    }


    /**
     * Associates a food item with a specific order in the database.
     *
     * @param idOrder  The ID of the order.
     * @param idFood   The ID of the food item.
     * @param quantity The quantity of the food item in the order.
     */
    public void setOrderFood(int idOrder, int idFood, int quantity) {
        String ADD_ORDER_FOOD_QUERY = "INSERT INTO orderFoods (idOrder, idFood, quantity) VALUES (?, ?, ?)";

        try {
            Connection conn = connect(true);
            PreparedStatement addOrderPreparedStatement = conn.prepareStatement(ADD_ORDER_FOOD_QUERY);

            addOrderPreparedStatement.setInt(1, idOrder);
            addOrderPreparedStatement.setInt(2, idFood);
            addOrderPreparedStatement.setInt(3, quantity);

            addOrderPreparedStatement.executeUpdate();

            addOrderPreparedStatement.close();
            disconnect(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-42);
        }
    }


    /**
     * Retrieves a list of food items associated with a specific order from the database.
     *
     * @param idOrder The ID of the order.
     * @return An ArrayList containing ArrayLists of Objects representing the food items in the order.
     */
    public ArrayList<ArrayList<Object>> getSpecificOrder(int idOrder) {
        String ALL_FOODS_ORDER_QUERY = """
                SELECT foods.*, OrderFoods.quantity
                FROM foods
                JOIN OrderFoods ON OrderFoods.idFood = foods.id
                WHERE OrderFoods.idOrder = ?;
                """;
        ArrayList<ArrayList<Object>> listItemsOrder = new ArrayList<>();

        try {
            Connection conn = connect(true);
            PreparedStatement foodOrderPreparedStatement = conn.prepareStatement(ALL_FOODS_ORDER_QUERY);

            foodOrderPreparedStatement.setInt(1, idOrder);

            ResultSet resultSet = foodOrderPreparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    ArrayList<Object> itemOrder = new ArrayList<>();
                    Food food = new Food(resultSet.getString(3), resultSet.getDouble(4));

                    itemOrder.add(food);
                    itemOrder.add(resultSet.getInt(6));

                    listItemsOrder.add(itemOrder);
                }
            }

            foodOrderPreparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return listItemsOrder;
    }


    /**
     * Retrieves the name of the restaurant associated with a specific order from the database.
     *
     * @param idOrder The ID of the order.
     * @return The name of the restaurant associated with the order.
     */
    public String getRestaurantOrder(int idOrder) {
        String GET_RESTAURANT_NAME = """
                SELECT restaurants.name
                FROM restaurants
                INNER JOIN foods ON foods.idRestaurant = restaurants.id
                INNER JOIN orderFoods ON orderfoods.idFood = foods.id
                WHERE orderfoods.idOrder = ?;""";

        String nameRestaurantString = "";
        try {
            Connection conn = connect(true);
            PreparedStatement nameRestaurant = conn.prepareStatement(GET_RESTAURANT_NAME);

            nameRestaurant.setInt(1, idOrder);

            ResultSet result = nameRestaurant.executeQuery();

            if (result.isBeforeFirst() && result.next()) {
                nameRestaurantString = result.getString(1);
            } else {
                System.out.println("Erro.");
            }

            nameRestaurant.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-42);
        }

        return nameRestaurantString;
    }
}
