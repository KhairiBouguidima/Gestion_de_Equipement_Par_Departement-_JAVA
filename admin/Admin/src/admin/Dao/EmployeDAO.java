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

public class EmployeDAO {
    
    public static void createEmploye(String username, int idLabo, Integer idAdmin, String email) throws SQLException {
        String sql = "INSERT INTO employe (username, idLabo, idAdmin, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, idLabo);
            if (idAdmin != null) {
                stmt.setInt(3, idAdmin);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setString(4, email);
            stmt.executeUpdate();
        }
    }
    
    public static Map<String, Object> getEmployeById(int id) throws SQLException {
        String sql = "SELECT * FROM employe WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> employe = new HashMap<>();
                    employe.put("id", rs.getInt("id"));
                    employe.put("username", rs.getString("username"));
                    employe.put("idLabo", rs.getInt("idLabo"));
                    employe.put("idAdmin", rs.getObject("idAdmin"));
                    employe.put("email", rs.getString("email"));
                    return employe;
                }
            }
        }
        return null;
    }
    
    public static String EmployeDAONomById(int id) throws SQLException {
    String sql = "SELECT * FROM employe WHERE id = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("username");
                return name ;
            }
        }
    }
    return null;
}

    
    public static void updateEmploye(int id, String username, int idLabo, Integer idAdmin, String email) throws SQLException {
        String sql = "UPDATE employe SET username = ?, idLabo = ?, idAdmin = ?, email = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, idLabo);
            if (idAdmin != null) {
                stmt.setInt(3, idAdmin);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            stmt.setString(4, email);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }
    
    public static void deleteEmploye(int id) throws SQLException {
        String sql = "DELETE FROM employe WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public static List<Map<String, Object>> getAllEmployes() throws SQLException {
        String sql = "SELECT * FROM employe";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            List<Map<String, Object>> employes = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> employe = new HashMap<>();
                employe.put("id", rs.getInt("id"));
                employe.put("username", rs.getString("username"));
                employe.put("idLabo", rs.getInt("idLabo"));
                employe.put("idAdmin", rs.getObject("idAdmin"));
                employe.put("email", rs.getString("email"));
                employes.add(employe);
            }
            return employes;
        }
    }
}