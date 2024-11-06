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
            String sql = "INSERT INTO `SANPHAM` (`MSP`, `TEN`, `HINHANH`, `DANHMUC`, `NAMXB`, `MNXB`, `TENTG`, `MKVS`, `TIENX`, `TIENN`, `SL`, `ISBN`, `TT`) VALUES (?,?,?,?,?,?,?,?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMSP());
            pst.setString(2, t.getTEN());
            pst.setString(3, t.getHINHANH());
            pst.setString(4, t.getDANHMUC());
            pst.setInt(5, t.getNAMXB());
            pst.setInt(6, t.getMNXB());
            pst.setString(7, t.getTENTG());
            pst.setInt(8, t.getMKVS());
            pst.setInt(9, t.getTIENX());
            pst.setInt(10, t.getTIENN());
            pst.setInt(11, t.getSL());
            pst.setString(12, t.getISBN());
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
            String sql = "UPDATE `SANPHAM` SET `TEN` = ?, `HINHANH` = ?, `DANHMUC` = ?, `NAMXB` = ?, `MNXB` = ?, `TENTG` = ?, `MKVS` = ?, `TIENX` = ?, `TIENN` = ?, `SL` = ?, `ISBN` = ? WHERE `MSP`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTEN());
            pst.setString(2, t.getHINHANH());
            pst.setString(3, t.getDANHMUC());
            pst.setInt(4, t.getNAMXB());
            pst.setInt(5, t.getMNXB());
            pst.setString(6, t.getTENTG());
            pst.setInt(7, t.getMKVS());
            pst.setInt(8, t.getTIENX());
            pst.setInt(9, t.getTIENN());
            pst.setInt(10, t.getSL());
            pst.setString(11, t.getISBN());
            pst.setInt(12, t.getMSP());

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
                String DANHMUC = rs.getString("DANHMUC");
                int NAMXB = rs.getInt("NAMXB");
                int MNXB = rs.getInt("MNXB");
                String TENTG = rs.getString("TENTG");
                int MKVS = rs.getInt("MKVS");
                int TIENX = rs.getInt("TIENX");
                int TIENN = rs.getInt("TIENN");
                int SL = rs.getInt("SL");
                String ISBN = rs.getString("ISBN");
                SanPhamDTO sp = new SanPhamDTO(madm, tendm, HINHANH, DANHMUC, NAMXB, MNXB, TENTG, MKVS, TIENX, TIENN, SL, ISBN);
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
                String DANHMUC = rs.getString("DANHMUC");
                int NAMXB = rs.getInt("NAMXB");
                int MNXB = rs.getInt("MNXB");
                String TENTG = rs.getString("TENTG");
                int MKVS = rs.getInt("MKVS");
                int TIENX = rs.getInt("TIENX");
                int TIENN = rs.getInt("TIENN");
                int SL = rs.getInt("SL");
                String ISBN = rs.getString("ISBN");
                result = new SanPhamDTO(madm, tendm, HINHANH, DANHMUC, NAMXB, MNXB, TENTG, MKVS, TIENX, TIENN, SL, ISBN);
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
            String sql = "SELECT * FROM SANPHAM WHERE DANHMUC = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int madm = rs.getInt("MSP");
                String tendm = rs.getString("TEN");
                String HINHANH = rs.getString("HINHANH");
                String DANHMUC = rs.getString("DANHMUC");
                int NAMXB = rs.getInt("NAMXB");
                int MNXB = rs.getInt("MNXB");
                String TENTG = rs.getString("TENTG");
                int MKVS = rs.getInt("MKVS");
                int TIENX = rs.getInt("TIENX");
                int TIENN = rs.getInt("TIENN");
                int SL = rs.getInt("SL");
                String ISBN = rs.getString("ISBN");
                result = new SanPhamDTO(madm, tendm, HINHANH, DANHMUC, NAMXB, MNXB, TENTG, MKVS, TIENX, TIENN, SL, ISBN);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlycuahangsach' AND   TABLE_NAME   = 'SANPHAM'";
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
}
