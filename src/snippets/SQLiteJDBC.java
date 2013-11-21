package snippets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import snippets.model.Snippet;


public class SQLiteJDBC {
    public static void createDB() {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:snippets.db");
            //System.out.println("Opened database successfully");
        
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SNIPPET " + 
                            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
                            " NAME TEXT NOT NULL, " + 
                            " CODE TEXT NOT NULL)";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Table created successfully/table already created");
    }
    
    public static void insert(String name, String code) {
        code = code.replaceAll("'", "''");
        
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:snippets.db");
            connection.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            
            statement = connection.createStatement();
            String sql = "INSERT INTO SNIPPET (ID, NAME, CODE) " + 
                            "VALUES (NULL, '" + name + "', '" + code + "');";
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Records created successfully");
    }

    public static ObservableList<Snippet> getSnippetData() {
        ObservableList<Snippet> snippetData = FXCollections.observableArrayList();

        Connection connection;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:snippets.db");
            connection.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SNIPPET;");
            while (rs.next()) {
                Snippet temp = new Snippet(rs.getInt("id"), 
                        rs.getString("name"), rs.getString("code"));
                snippetData.add(temp);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
        
        return snippetData;
    }
    
    public static String selectCode(String name) {
        String code = "";
        
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:snippets.db");
            connection.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            
            statement = connection.createStatement();
            String sql = "SELECT CODE FROM SNIPPET where NAME = '" + name + "';";
            ResultSet rs = statement.executeQuery(sql);
            code = rs.getString("code");
            connection.commit();
            
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
        return code;
    }
    
    public static void update(int id, String name, String code) {
        code = code.replaceAll("'", "''");
        
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:snippets.db");
            connection.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            
            statement = connection.createStatement();
            String sql = "UPDATE SNIPPET set CODE = '" + code + "', NAME = '" + 
                    name + "' where ID = '" + id + "';";
            statement.executeUpdate(sql);
            connection.commit();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
    }
    
    public static void delete(int id) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:snippets.db");
            connection.setAutoCommit(false);
            //System.out.println("Opened database successfully");
            
            statement = connection.createStatement();
            String sql = "DELETE FROM SNIPPET where ID = '" + id + "';";
            statement.executeUpdate(sql);
            connection.commit();
            
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
    }
}
