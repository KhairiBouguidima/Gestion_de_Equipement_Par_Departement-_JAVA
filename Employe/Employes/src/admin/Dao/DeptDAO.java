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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeptDAO {
    
    // Create a new department
    public static void createDept(String nomDept, int nbLabo, int idAdmin) throws SQLException {
        String sql = "INSERT INTO dept (nomDept, nbLabo, idAdmin) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomDept);
            stmt.setInt(2, nbLabo);
            stmt.setInt(3, idAdmin);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Get department by ID
    public static Map<String, Object> getDeptById(int id) throws SQLException {
        String sql = "SELECT * FROM dept WHERE id = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Map<String, Object> dept = new HashMap<>();
                dept.put("id", rs.getInt("id"));
                dept.put("nomDept", rs.getString("nomDept"));
                dept.put("nbLabo", rs.getInt("nbLabo"));
                dept.put("idAdmin", rs.getInt("idAdmin"));
                return dept;
            }
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Update department
    public static void updateDept(int id, String nomDept, int nbLabo, int idAdmin) throws SQLException {
        String sql = "UPDATE dept SET nomDept = ?, nbLabo = ?, idAdmin = ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomDept);
            stmt.setInt(2, nbLabo);
            stmt.setInt(3, idAdmin);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Delete department
    public static void deleteDept(int id) throws SQLException {
        String sql = "DELETE FROM dept WHERE id = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Get all departments
    public static List<Map<String, Object>> getAllDepts() throws SQLException {
        String sql = "SELECT * FROM dept";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            List<Map<String, Object>> depts = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> dept = new HashMap<>();
                dept.put("id", rs.getInt("id"));
                dept.put("nomDept", rs.getString("nomDept"));
                dept.put("nbLabo", rs.getInt("nbLabo"));
                dept.put("idAdmin", rs.getInt("idAdmin"));
                depts.add(dept);
            }
            return depts;
        } finally {
            DBUtil.close(conn);
        }
    }
}