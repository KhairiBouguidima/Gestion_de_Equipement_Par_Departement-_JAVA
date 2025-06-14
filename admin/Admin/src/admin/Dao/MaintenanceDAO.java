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
import java.util.*;

public class MaintenanceDAO {
    
    public static int createMaintenance(int idAffect, String idEquip, Date dateDebut, 
                                      Date dateFin, String typeMaint, String resultat, 
                                      double cout) throws SQLException {
        String sql = "INSERT INTO maintenance (idAffect, idEquip, dateDebut, dateFin, typeMaint, resultat, cout) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idAffect);
            stmt.setString(2, idEquip);
            stmt.setDate(3, new java.sql.Date(dateDebut.getTime()));
            stmt.setDate(4, new java.sql.Date(dateFin.getTime()));
            stmt.setString(5, typeMaint);
            stmt.setString(6, resultat);
            stmt.setDouble(7, cout);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public static Map<String, Object> getMaintenanceById(int idMaint) throws SQLException {
        String sql = "SELECT * FROM maintenance WHERE idMaint = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMaint);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> maint = new HashMap<>();
                    maint.put("idMaint", rs.getInt("idMaint"));
                    maint.put("idAffect", rs.getInt("idAffect"));
                    maint.put("idEquip", rs.getString("idEquip"));
                    maint.put("dateDebut", rs.getDate("dateDebut"));
                    maint.put("dateFin", rs.getDate("dateFin"));
                    maint.put("typeMaint", rs.getString("typeMaint"));
                    maint.put("resultat", rs.getString("resultat"));
                    maint.put("cout", rs.getDouble("cout"));
                    return maint;
                }
            }
        }
        return null;
    }
    
    public static void updateMaintenance(int idMaint, int idAffect, String idEquip, Date dateDebut, 
                                       Date dateFin, String typeMaint, String resultat, 
                                       double cout) throws SQLException {
        String sql = "UPDATE maintenance SET idAffect = ?, idEquip = ?, dateDebut = ?, dateFin = ?, typeMaint = ?, resultat = ?, cout = ? WHERE idMaint = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAffect);
            stmt.setString(2, idEquip);
            stmt.setDate(3, new java.sql.Date(dateDebut.getTime()));
            stmt.setDate(4, new java.sql.Date(dateFin.getTime()));
            stmt.setString(5, typeMaint);
            stmt.setString(6, resultat);
            stmt.setDouble(7, cout);
            stmt.setInt(8, idMaint);
            stmt.executeUpdate();
        }
    }
    
    public static void deleteMaintenance(int idMaint) throws SQLException {
        String sql = "DELETE FROM maintenance WHERE idMaint = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMaint);
            stmt.executeUpdate();
        }
    }
    
    public static List<Map<String, Object>> getAllMaintenances() throws SQLException {
        String sql = "SELECT * FROM maintenance";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            List<Map<String, Object>> maintenances = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> maint = new HashMap<>();
                maint.put("idMaint", rs.getInt("idMaint"));
                maint.put("idAffect", rs.getInt("idAffect"));
                maint.put("idEquip", rs.getString("idEquip"));
                maint.put("dateDebut", rs.getDate("dateDebut"));
                maint.put("dateFin", rs.getDate("dateFin"));
                maint.put("typeMaint", rs.getString("typeMaint"));
                maint.put("resultat", rs.getString("resultat"));
                maint.put("cout", rs.getDouble("cout"));
                maintenances.add(maint);
            }
            return maintenances;
        }
    }
}