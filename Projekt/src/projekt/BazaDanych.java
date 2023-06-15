/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekt;

/**
 *
 * @author Jola
 */
import java.sql.*;

public class BazaDanych {
    private static final String DB_URL = "jdbc:sqlserver://JOLA\\SQLEXPRESS:49807;databaseName=Projekt;user=sa;password=student;";

    private static final BazaDanych instance = new BazaDanych();
    
    public static BazaDanych getInstance() {
        
        return instance;
    }
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    public void dodajNotatke(String tytuł, String treść) {
        try (Connection conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO dbo.notatnik (tytuł, treść) VALUES (?,?)"); 
            stmt.setString(1, tytuł);
            stmt.setString(2, treść);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.getMessage();
        }
    }



    public ResultSet pobierzNotatki() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery("SELECT id, tytuł FROM dbo.notatnik");
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }
    
    public ResultSet pobierzNotatke(int id) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT tytuł, data, treść FROM dbo.notatnik WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    
  public void usunNotatke(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM notatnik WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

   


}
