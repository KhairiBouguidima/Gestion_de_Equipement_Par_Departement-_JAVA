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

public class AffectDAO {
    
    public static int createAffect(int idEmp, int idAdmin, String status, Integer idTech) throws SQLException {
        String sql = "INSERT INTO affect (idEmp, idAdmin, status, idTech) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idEmp);
            stmt.setInt(2, idAdmin);
            stmt.setString(3, status);
            if (idTech != null) {
                stmt.setInt(4, idTech);
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
    
    public static Map<String, Object> getAffectById(int idAffect) throws SQLException {
        String sql = "SELECT * FROM affect WHERE idAffect = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAffect);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> affect = new HashMap<>();
                    affect.put("idAffect", rs.getInt("idAffect"));
                    affect.put("idEmp", rs.getInt("idEmp"));
                    affect.put("idAdmin", rs.getInt("idAdmin"));
                    affect.put("status", rs.getString("status"));
                    affect.put("idTech", rs.getObject("idTech"));
                    return affect;
                }
            }
        }
        return null;
    }
    
public static String getAffectByIdMaint(int idMaint) throws SQLException {
    String sql = "SELECT * FROM affect " +
                 "WHERE idMaint = ?";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idMaint);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int idAffect = rs.getInt("idAffect");
                int idEmp = rs.getInt("idEmp");
                int idAdmin = rs.getInt("idAdmin");
                String status = rs.getString("status");
                Object idTech = rs.getObject("idTech"); // can be null

                return " | Employé: " + idEmp +
                       " | Statut: " + status +
                       " | Technicien: " + (idTech != null ? idTech.toString() : "Non assigné");
            }
        }
    }

    return "Affectation introuvable pour maintenance ID: " + idMaint;
}


    
    public static void updateAffect(int idAffect, int idEmp, int idAdmin, String status, Integer idTech) throws SQLException {
        String sql = "UPDATE affect SET idEmp = ?, idAdmin = ?, status = ?, idTech = ? WHERE idAffect = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmp);
            stmt.setInt(2, idAdmin);
            stmt.setString(3, status);
            if (idTech != null) {
                stmt.setInt(4, idTech);
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, idAffect);
            stmt.executeUpdate();
        }
    }
    
    public static void deleteAffect(int idAffect) throws SQLException {
        String sql = "DELETE FROM affect WHERE idAffect = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAffect);
            stmt.executeUpdate();
        }
    }
    
    public static List<Map<String, Object>> getAllAffects() throws SQLException {
        String sql = "SELECT * FROM affect";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            List<Map<String, Object>> affects = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> affect = new HashMap<>();
                affect.put("idAffect", rs.getInt("idAffect"));
                affect.put("idEmp", rs.getInt("idEmp"));
                affect.put("idAdmin", rs.getInt("idAdmin"));
                affect.put("status", rs.getString("status"));
                affect.put("idTech", rs.getObject("idTech"));
                affects.add(affect);
            }
            return affects;
        }
    }
}