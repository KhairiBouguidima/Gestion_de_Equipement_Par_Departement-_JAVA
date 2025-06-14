/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.Dao;

/**
 *
 * @author Admin
 */
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaboDAO {
    
    // Create a new lab
    public static void createLabo(String nomLabo, int nbEquipement, int idDept) throws SQLException {
        String sql = "INSERT INTO Labo (nomLabo, nbEquipement, idDept) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nomLabo);
            stmt.setInt(2, nbEquipement);
            stmt.setInt(3, idDept);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Get lab by ID
    public static Map<String, Object> getLaboById(int idLabo) throws SQLException {
        String sql = "SELECT * FROM Labo WHERE idLabo = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLabo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Map<String, Object> labo = new HashMap<>();
                labo.put("idLabo", rs.getInt("idLabo"));
                labo.put("nomLabo", rs.getString("nomLabo"));
                labo.put("nbEquipement", rs.getInt("nbEquipement"));
                labo.put("idDept", rs.getInt("idDept"));
                return labo;
            }
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Update lab
    public static void updateLabo(int idLabo, String nomLabo, int nbEquipement, int idDept) throws SQLException {
        String sql = "UPDATE Labo SET nomLabo = ?, nbEquipement = ?, idDept = ? WHERE idLabo = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomLabo);
            stmt.setInt(2, nbEquipement);
            stmt.setInt(3, idDept);
            stmt.setInt(4, idLabo);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Delete lab
    public static void deleteLabo(int idLabo) throws SQLException {
        String sql = "DELETE FROM Labo WHERE idLabo = ?";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLabo);
            stmt.executeUpdate();
        } finally {
            DBUtil.close(conn);
        }
    }
    
    // Get all labs
    public static List<Map<String, Object>> getAllLabos() throws SQLException {
        String sql = "SELECT * FROM Labo";
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            List<Map<String, Object>> labos = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> labo = new HashMap<>();
                labo.put("idLabo", rs.getInt("idLabo"));
                labo.put("nomLabo", rs.getString("nomLabo"));
                labo.put("nbEquipement", rs.getInt("nbEquipement"));
                labo.put("idDept", rs.getInt("idDept"));
                labos.add(labo);
            }
            return labos;
        } finally {
            DBUtil.close(conn);
        }
    }
}
