/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.Dao;

/**
 *
 * @author Admin
 */
import admin.Dao.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAO {
    
    // Create a new admin
    public static void createAdmin(String username, int idDept, int password) throws SQLException {
        String sql = "INSERT INTO Admin (username, idDept, password) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setInt(2, idDept);
            stmt.setInt(3, password);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Get admin by ID
    public static Map<String, Object> getAdminById(int idAdmin) throws SQLException {
        String sql = "SELECT * FROM Admin WHERE idAdmin = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAdmin);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Map<String, Object> admin = new HashMap<>();
                admin.put("idAdmin", rs.getInt("idAdmin"));
                admin.put("username", rs.getString("username"));
                admin.put("idDept", rs.getInt("idDept"));
                admin.put("password", rs.getInt("password"));
                return admin;
            }
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Update admin
    public static void updateAdmin(int idAdmin, String username, int idDept, int password) throws SQLException {
        String sql = "UPDATE Admin SET username = ?, idDept = ?, password = ? WHERE idAdmin = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idDept);
            stmt.setInt(3, password);
            stmt.setInt(4, idAdmin);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Delete admin
    public static void deleteAdmin(int idAdmin) throws SQLException {
        String sql = "DELETE FROM Admin WHERE idAdmin = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAdmin);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Get all admins
    public static List<Map<String, Object>> getAllAdmins() throws SQLException {
        String sql = "SELECT * FROM Admin";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            List<Map<String, Object>> admins = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> admin = new HashMap<>();
                admin.put("idAdmin", rs.getInt("idAdmin"));
                admin.put("username", rs.getString("username"));
                admin.put("idDept", rs.getInt("idDept"));
                admin.put("password", rs.getInt("password"));
                admins.add(admin);
            }
            return admins;
        } finally {
            DBUtil.close(conn);
        }
    }
    public static Map<String, String> getAdminByUsernameOrEmail(String username ) throws SQLException {
    String sql = "SELECT * FROM Admin WHERE username = ?  LIMIT 1";
    Connection conn = null;
    try {
        conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Map<String, String> admin = new HashMap<>();
            admin.put("idAdmin", String.valueOf(rs.getInt("idAdmin")));
            admin.put("username", rs.getString("username"));
            admin.put("idDept", String.valueOf(rs.getInt("idDept")));
            admin.put("password", String.valueOf(rs.getInt("password")));
            
            return admin;
        }
        return null;
    } finally {
        DBUtil.close(conn);
    }
}
}
