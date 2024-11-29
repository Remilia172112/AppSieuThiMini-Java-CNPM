package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.SanPhamDTO;

public class SanPhamDAO implements DAOinterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `SANPHAM` (`TEN`, `HINHANH`, `ML`, `TIENX`, `SL`, `MDV`, `MV`, `TT`) VALUES (?,?,?,?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTEN());
            pst.setString(2, t.getHINHANH());
            pst.setInt(3, t.getML());
            pst.setInt(4, t.getTIENX());
            pst.setInt(5, t.getSL());
            pst.setInt(6, t.getMDV());
            pst.setString(7, t.getMV());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `SANPHAM` SET `TEN` = ?, `HINHANH` = ?, `ML` = ?, `TIENX` = ?, `SL` = ?, `MDV` = ?, `MV` = ? WHERE `MSP`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTEN());
            pst.setString(2, t.getHINHANH());
            pst.setInt(3, t.getML());
            pst.setInt(4, t.getTIENX());
            pst.setInt(5, t.getSL());
            pst.setInt(6, t.getMDV());
            pst.setString(7, t.getMV());
            pst.setInt(8, t.getMSP());

            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `SANPHAM` SET `TT` = 0 WHERE MSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM SANPHAM WHERE `TT`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("MSP");
                String tendm = rs.getString("TEN");
                String HINHANH = rs.getString("HINHANH");
                int loai = rs.getInt("ML");
                int TIENX = rs.getInt("TIENX");
                int SL = rs.getInt("SL");
                int DV = rs.getInt("MDV");
                String MV = rs.getString("MV");
                SanPhamDTO sp = new SanPhamDTO(madm, tendm, HINHANH, loai, TIENX, SL, DV, MV);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM SANPHAM WHERE MSP=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("MSP");
                String tendm = rs.getString("TEN");
                String HINHANH = rs.getString("HINHANH");
                int loai = rs.getInt("ML");
                int TIENX = rs.getInt("TIENX");
                int SL = rs.getInt("SL");
                int DV = rs.getInt("MDV");
                String MV = rs.getString("MV");
                result = new SanPhamDTO(madm, tendm, HINHANH, loai, TIENX, SL, DV, MV);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public SanPhamDTO selectByDanhMuc(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM SANPHAM WHERE ML = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("MSP");
                String tendm = rs.getString("TEN");
                String HINHANH = rs.getString("HINHANH");
                int loai = rs.getInt("ML");
                int TIENX = rs.getInt("TIENX");
                int SL = rs.getInt("SL");
                int DV = rs.getInt("MDV");
                String MV = rs.getString("MV");
                result = new SanPhamDTO(madm, tendm, HINHANH, loai, TIENX, SL, DV, MV);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlysieuthimini' AND   TABLE_NAME   = 'SANPHAM'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateSoLuongTon(int MSP, int soluong) {
        int quantity_current = this.selectById(Integer.toString(MSP)).getSL();
        int result = 0;
        int quantity_change = quantity_current + soluong;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `SANPHAM` SET `SL`=? WHERE MSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, MSP);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateSoLuongTon(int MSP, int soluong, int tiennhap) {
        SanPhamDTO tmp = this.selectById(Integer.toString(MSP));
        if(tmp.getTIENX() < tiennhap*(120/100)) tiennhap = tiennhap*(120/100);
        else tiennhap = tmp.getTIENX();
        int quantity_current = tmp.getSL();
        int result = 0;
        int quantity_change = quantity_current + soluong;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `SANPHAM` SET `SL`=?, `TIENX` = ? WHERE MSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, quantity_change);
            pst.setInt(2, tiennhap);
            pst.setInt(3, MSP);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int updateGia(int MSP, int giaxuat) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `SANPHAM` SET `TIENX`=? WHERE MSP = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, giaxuat);
            pst.setInt(2, MSP);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
