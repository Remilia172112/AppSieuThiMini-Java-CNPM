package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.ChiTietMaKhuyenMaiDTO;


public class ChiTietMaKhuyenMaiDAO implements DAOinterface<ChiTietMaKhuyenMaiDTO> {
    public static ChiTietMaKhuyenMaiDAO getInstance() {
        return new ChiTietMaKhuyenMaiDAO();
    }

    @Override
    public int insert(ChiTietMaKhuyenMaiDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `CTMAKHUYENMAI`(`MKM`, `MSP`, `PTG`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getMKM());
            pst.setInt(2, t.getMSP());
            pst.setInt(3, t.getPTG());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietMaKhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int insert(ArrayList<ChiTietMaKhuyenMaiDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTMAKHUYENMAI`(`MKM`, `MSP`, `PTG`) VALUES (?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, t.get(i).getMKM());
                pst.setInt(2, t.get(i).getMSP());
                pst.setInt(3, t.get(i).getPTG());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int update(ChiTietMaKhuyenMaiDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `CTMAKHUYENMAI` SET `MKM`=?,`MSP`=?,`PTG`=? WHERE MKM=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getMKM());
            pst.setInt(2, t.getMSP());
            pst.setInt(3, t.getPTG());
            pst.setString(5, t.getMKM());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietMaKhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `CTMAKHUYENMAI` WHERE `MKM` = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietMaKhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietMaKhuyenMaiDTO> selectAll() {
        ArrayList<ChiTietMaKhuyenMaiDTO> result = new ArrayList<ChiTietMaKhuyenMaiDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTMAKHUYENMAI";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String MKM = rs.getString("MKM");
                int MSP = rs.getInt("MSP");
                int PTG = rs.getInt("PTG");
                ChiTietMaKhuyenMaiDTO kh = new ChiTietMaKhuyenMaiDTO(MKM, MSP, PTG);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public ArrayList<ChiTietMaKhuyenMaiDTO> selectAll(String t) {
        ArrayList<ChiTietMaKhuyenMaiDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTMAKHUYENMAI WHERE MKM = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String MKM = rs.getString("MKM");
                int MSP = rs.getInt("MSP");
                int PTG = rs.getInt("PTG");
                ChiTietMaKhuyenMaiDTO kh = new ChiTietMaKhuyenMaiDTO(MKM, MSP, PTG);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    @Override
    public ChiTietMaKhuyenMaiDTO selectById(String t) {
        ChiTietMaKhuyenMaiDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTMAKHUYENMAI WHERE MKM=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String MKM = rs.getString("MKM");
                int MSP = rs.getInt("MSP");
                int PTG = rs.getInt("PTG");
                result = new ChiTietMaKhuyenMaiDTO(MKM, MSP, PTG);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlycuahangsach' AND   TABLE_NAME   = 'CTMAKHUYENMAI'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietMaKhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
