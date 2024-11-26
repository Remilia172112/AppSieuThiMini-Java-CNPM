package DAO;

import DTO.ChiTietMaKhuyenMaiDTO;
import DTO.ChiTietPhieuDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import BUS.MaKhuyenMaiBUS;

import java.sql.ResultSet;


public class ChiTietHoaDonDAO implements ChiTietInterface<ChiTietPhieuDTO> {

    public static ChiTietHoaDonDAO getInstance() {
        return new ChiTietHoaDonDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTHOADON` (`MHD`, `MSP`, `SL`,  `TIENXUAT`) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMP());
                pst.setInt(2, t.get(i).getMSP());
                int SL = -(t.get(i).getSL());
                pst.setInt(3, t.get(i).getSL());
                SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), SL);
                pst.setInt(4, t.get(i).getTIEN());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int insertGH(ArrayList<ChiTietPhieuDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTHOADON` (`MHD`, `MSP`, `SL`, `TIENXUAT`) VALUES (?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMP());
                pst.setInt(2, t.get(i).getMSP());
                pst.setInt(3, t.get(i).getSL());
                pst.setInt(4, t.get(i).getTIEN());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int insert(ArrayList<ChiTietPhieuDTO> t, ArrayList<ChiTietMaKhuyenMaiDTO> ctmkm) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTHOADON` (`MHD`, `MSP`, `MKM`, SL`,  `TIENXUAT`) VALUES (?,?,?,?.?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMP());
                pst.setInt(2, t.get(i).getMSP());
                MaKhuyenMaiBUS mkmbus = new MaKhuyenMaiBUS();
                ChiTietMaKhuyenMaiDTO mkm = mkmbus.findCT(ctmkm, t.get(i).getMSP());
                pst.setString(4, mkm.getMKM());
                int SL = -(t.get(i).getSL());
                pst.setInt(4, t.get(i).getSL());
                SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), SL);
                pst.setInt(5, t.get(i).getTIEN());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int reset(ArrayList<ChiTietPhieuDTO> t){
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
        SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), +(t.get(i).getSL()));
        delete(t.get(i).getMP()+"");
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM CTHOADON WHERE MHD = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTHOADON WHERE MHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("MHD");
                int MSP = rs.getInt("MSP");
                int SL = rs.getInt("SL");
                int tienxuat = rs.getInt("TIENXUAT");
                ChiTietPhieuDTO ctphieu = new ChiTietPhieuDTO(maphieu, MSP, SL, tienxuat);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public void updateSL(String t) {
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTHOADON WHERE MHD = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("MHD");
                int MSP = rs.getInt("MSP");
                int SL = rs.getInt("SL");
                int tienxuat = rs.getInt("TIENXUAT");
                ChiTietPhieuDTO ctphieu = new ChiTietPhieuDTO(maphieu, MSP, SL, tienxuat);
                int SLsp = -(ctphieu.getSL());
                SanPhamDAO.getInstance().updateSoLuongTon(ctphieu.getMSP(), SLsp);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
