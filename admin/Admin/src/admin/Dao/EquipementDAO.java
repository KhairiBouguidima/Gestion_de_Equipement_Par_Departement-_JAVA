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
import java.sql.Date;
public class EquipementDAO {
    
    public static void createEquipement(String numeroSerie, String model, String marque, 
                                      String description, Date dateAcqui, Date dateFinGarantie, 
                                      String etat, Integer idAdmin, Integer idEmp) throws SQLException {
        String sql = "INSERT INTO equipement (numero_serie, model, marque, description, dateAcqui, dateFinGarantie, etat, idAdmin, idEmp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            stmt.setString(2, model);
            stmt.setString(3, marque);
            stmt.setString(4, description);
            stmt.setDate(5, new java.sql.Date(dateAcqui.getTime()));
            stmt.setDate(6, new java.sql.Date(dateFinGarantie.getTime()));
            stmt.setString(7, etat);
            if (idAdmin != null) {
                stmt.setInt(8, idAdmin);
            } else {
                stmt.setNull(8, Types.INTEGER);
            }
            if (idEmp != null) {
                stmt.setInt(9, idEmp);
            } else {
                stmt.setNull(9, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }
    
    public static Map<String, Object> getEquipementByNumeroSerie(String numeroSerie) throws SQLException {
        String sql = "SELECT * FROM equipement WHERE numero_serie = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> equip = new HashMap<>();
                    equip.put("numero_serie", rs.getString("numero_serie"));
                    equip.put("model", rs.getString("model"));
                    equip.put("marque", rs.getString("marque"));
                    equip.put("description", rs.getString("description"));
                    equip.put("dateAcqui", rs.getDate("dateAcqui"));
                    equip.put("dateFinGarantie", rs.getDate("dateFinGarantie"));
                    equip.put("etat", rs.getString("etat"));
                    equip.put("idAdmin", rs.getObject("idAdmin"));
                    equip.put("idEmp", rs.getObject("idEmp"));
                    return equip;
                }
            }
        }
        return null;
    }
    
    public static void updateEquipement(String numeroSerie, String model, String marque, 
                                      String description, Date dateAcqui, Date dateFinGarantie, 
                                      String etat, Integer idAdmin, Integer idEmp) throws SQLException {
        String sql = "UPDATE equipement SET model = ?, marque = ?, description = ?, dateAcqui = ?, dateFinGarantie = ?, etat = ?, idAdmin = ?, idEmp = ? WHERE numero_serie = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model);
            stmt.setString(2, marque);
            stmt.setString(3, description);
            stmt.setDate(4, new java.sql.Date(dateAcqui.getTime()));
            stmt.setDate(5, new java.sql.Date(dateFinGarantie.getTime()));
            stmt.setString(6, etat);
            if (idAdmin != null) {
                stmt.setInt(7, idAdmin);
            } else {
                stmt.setNull(7, Types.INTEGER);
            }
            if (idEmp != null) {
                stmt.setInt(8, idEmp);
            } else {
                stmt.setNull(8, Types.INTEGER);
            }
            stmt.setString(9, numeroSerie);
            stmt.executeUpdate();
        }
    }
    
    public static void deleteEquipement(String numeroSerie) throws SQLException {
        String sql = "DELETE FROM equipement WHERE numero_serie = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            stmt.executeUpdate();
        }
    }
    
    public static List<Map<String, Object>> getAllEquipements() throws SQLException {
        String sql = "SELECT * FROM equipement";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            List<Map<String, Object>> equipements = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> equip = new HashMap<>();
                equip.put("numero_serie", rs.getString("numero_serie"));
                equip.put("model", rs.getString("model"));
                equip.put("marque", rs.getString("marque"));
                equip.put("description", rs.getString("description"));
                equip.put("dateAcqui", rs.getDate("dateAcqui"));
                equip.put("dateFinGarantie", rs.getDate("dateFinGarantie"));
                equip.put("etat", rs.getString("etat"));
                equip.put("idAdmin", rs.getObject("idAdmin"));
                equip.put("idEmp", rs.getObject("idEmp"));
                equipements.add(equip);
            }
            return equipements;
        }
    }
    public static Map<String, Integer> countEquipByEtatType() throws SQLException {
    String sql = "SELECT etat, COUNT(*) as count FROM equipement GROUP BY etat";
    Map<String, Integer> etatCounts = new HashMap<>();
    
    try (Connection conn = DBUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            String etat = rs.getString("etat");
            int count = rs.getInt("count");
            etatCounts.put(etat, count);
        }
    }
    
    return etatCounts;
}

}
