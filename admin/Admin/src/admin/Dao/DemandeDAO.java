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
import java.sql.Date;
import java.util.*;

public class DemandeDAO {
    
    public static int createDemande(String title, String desc, int idEmp, String idEquip,int idLabo, Date dateDem, String typeDem,String urgence, String statut) throws SQLException {
        String sql = "INSERT INTO demande (title, desc, idEmp, idEquip, idLabo, dateDem, typeDem, urgence, statut) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, title);
            stmt.setString(2, desc);
            stmt.setInt(3, idEmp);
            stmt.setString(4, idEquip);
            stmt.setInt(5, idLabo);
            stmt.setDate(6, new java.sql.Date(dateDem.getTime()));
            stmt.setString(7, typeDem);
            stmt.setString(8, urgence);
            stmt.setString(9, statut);
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public static Map<String, Object> getDemandeById(int idDem) throws SQLException {
        String sql = "SELECT * FROM demande WHERE idDem = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDem);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> demande = new HashMap<>();
                    demande.put("idDem", rs.getInt("idDem"));
                    demande.put("title", rs.getString("title"));
                    demande.put("desc", rs.getString("desc"));
                    demande.put("idEmp", rs.getInt("idEmp"));
                    demande.put("idEquip", rs.getString("idEquip"));
                    demande.put("idLabo", rs.getInt("idLabo"));
                    demande.put("dateDem", rs.getDate("dateDem"));
                    demande.put("typeDem", rs.getString("typeDem"));
                    demande.put("urgence", rs.getString("urgence"));
                    demande.put("statut", rs.getString("statut"));
                    return demande;
                }
            }
        }
        return null;
    }
    
    // Get all demandes with status = 'en attente'
public static List<Map<String, Object>> getDemandesEnAttente() throws SQLException {
    String sql = "SELECT * FROM demande WHERE statut = 'en attente'";
    List<Map<String, Object>> demandes = new ArrayList<>();

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Map<String, Object> demande = new HashMap<>();
            demande.put("idDem", rs.getInt("idDem"));
            demande.put("title", rs.getString("title"));
            demande.put("desc", rs.getString("desc"));
            demande.put("idEmp", rs.getInt("idEmp"));
            demande.put("idEquip", rs.getString("idEquip"));
            demande.put("idLabo", rs.getInt("idLabo"));
            demande.put("dateDem", rs.getDate("dateDem"));
            demande.put("typeDem", rs.getString("typeDem"));
            demande.put("urgence", rs.getString("urgence"));
            demande.put("statut", rs.getString("statut"));
            demandes.add(demande);
        }
    }

    return demandes;
}


    public static Map<String, Object> getDemandeBystatus(String status) throws SQLException {
        String sql = "SELECT * FROM demande WHERE idDem = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> demande = new HashMap<>();
                    demande.put("idDem", rs.getInt("idDem"));
                    demande.put("title", rs.getString("title"));
                    demande.put("desc", rs.getString("desc"));
                    demande.put("idEmp", rs.getInt("idEmp"));
                    demande.put("idEquip", rs.getString("idEquip"));
                    demande.put("idLabo", rs.getInt("idLabo"));
                    demande.put("dateDem", rs.getDate("dateDem"));
                    demande.put("typeDem", rs.getString("typeDem"));
                    demande.put("urgence", rs.getString("urgence"));
                    demande.put("statut", rs.getString("statut"));
                    return demande;
                }
            }
        }
        return null;
    }
    
    public static void updateDemande(int idDem, String title, String desc, int idEmp, String idEquip,int idLabo, Date dateDem, String typeDem,String urgence, String statut) throws SQLException {
        String sql = "UPDATE demande SET title = ?, desc = ?, idEmp = ?, idEquip = ?, idLabo = ?, dateDem = ?, typeDem = ?, urgence = ?, statut = ? WHERE idDem = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, desc);
            stmt.setInt(3, idEmp);
            stmt.setString(4, idEquip);
            stmt.setInt(5, idLabo);
            stmt.setDate(6, new java.sql.Date(dateDem.getTime()));
            stmt.setString(7, typeDem);
            stmt.setString(8, urgence);
            stmt.setString(9, statut);
            stmt.setInt(10, idDem);
            stmt.executeUpdate();
        }
    }
    
    public static void deleteDemande(int idDem) throws SQLException {
        String sql = "DELETE FROM demande WHERE idDem = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDem);
            stmt.executeUpdate();
        }
    }
    
    public static List<Map<String, Object>> getAllDemandes() throws SQLException {
        String sql = "SELECT * FROM demande";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            List<Map<String, Object>> demandes = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> demande = new HashMap<>();
                demande.put("idDem", rs.getInt("idDem"));
                demande.put("title", rs.getString("title"));
                demande.put("desc", rs.getString("desc"));
                demande.put("idEmp", rs.getInt("idEmp"));
                demande.put("idEquip", rs.getString("idEquip"));
                demande.put("idLabo", rs.getInt("idLabo"));
                demande.put("dateDem", rs.getDate("dateDem"));
                demande.put("typeDem", rs.getString("typeDem"));
                demande.put("urgence", rs.getString("urgence"));
                demande.put("statut", rs.getString("statut"));
                demandes.add(demande);
            }
            return demandes;
        }
    }
}