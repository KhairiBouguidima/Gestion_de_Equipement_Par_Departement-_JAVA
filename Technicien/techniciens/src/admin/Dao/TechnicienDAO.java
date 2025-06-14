/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.Dao;

/**
 *
 * @author Admin
 */
import java.sql.*;
import java.util.*;

public class TechnicienDAO {
    
    public static void createTechnicien(String username, String email, String password, 
                                      String specialite, boolean disponibilite, Integer idAdmin) throws SQLException {
        String sql = "INSERT INTO technicien (username, email, password, specialite, disponibilite, idAdmin) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, specialite);
            stmt.setBoolean(5, disponibilite);
            if (idAdmin != null) {
                stmt.setInt(6, idAdmin);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }
    
    public static Map<String, Object> getTechnicienById(int idTech) throws SQLException {
        String sql = "SELECT * FROM technicien WHERE id_tech = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTech);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> tech = new HashMap<>();
                    tech.put("id_tech", rs.getInt("id_tech"));
                    tech.put("username", rs.getString("username"));
                    tech.put("email", rs.getString("email"));
                    tech.put("password", rs.getString("password"));
                    tech.put("specialite", rs.getString("specialite"));
                    tech.put("disponibilite", rs.getBoolean("disponibilite"));
                    tech.put("idAdmin", rs.getObject("idAdmin"));
                    return tech;
                }
            }
        }
        return null;
    }
    
    public static void updateTechnicien(int idTech, String username, String email, String password, 
                                      String specialite, boolean disponibilite, Integer idAdmin) throws SQLException {
        String sql = "UPDATE technicien SET username = ?, email = ?, password = ?, specialite = ?, disponibilite = ?, idAdmin = ? WHERE id_tech = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, specialite);
            stmt.setBoolean(5, disponibilite);
            if (idAdmin != null) {
                stmt.setInt(6, idAdmin);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.setInt(7, idTech);
            stmt.executeUpdate();
        }
    }
    
    public static void deleteTechnicien(int idTech) throws SQLException {
        String sql = "DELETE FROM technicien WHERE id_tech = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTech);
            stmt.executeUpdate();
        }
    }
    
    public static List<Map<String, Object>> getAllTechniciens() throws SQLException {
        String sql = "SELECT * FROM technicien";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            List<Map<String, Object>> techs = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> tech = new HashMap<>();
                tech.put("id_tech", rs.getInt("id_tech"));
                tech.put("username", rs.getString("username"));
                tech.put("email", rs.getString("email"));
                tech.put("password", rs.getString("password"));
                tech.put("specialite", rs.getString("specialite"));
                tech.put("disponibilite", rs.getBoolean("disponibilite"));
                tech.put("idAdmin", rs.getObject("idAdmin"));
                techs.add(tech);
            }
            return techs;
        }
    }
}