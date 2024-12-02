package DAO;

import DTO.ChiTietHoaDonDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.ResultSet;

public class ChiTietHoaDonDAO implements ChiTietInterface<ChiTietHoaDonDTO> {

    public static ChiTietHoaDonDAO getInstance() {
        return new ChiTietHoaDonDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietHoaDonDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTHOADON` (`MHD`, `MSP`, `SL`, `TIENXUAT`, `MKM`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMP());
                pst.setInt(2, t.get(i).getMSP());
                int SL = -(t.get(i).getSL());
                pst.setInt(3, t.get(i).getSL());
                SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), SL);
                pst.setInt(4, t.get(i).getTIEN());
                pst.setString(5, t.get(i).getMKM());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int reset(ArrayList<ChiTietHoaDonDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), +(t.get(i).getSL()));
            delete(t.get(i).getMP() + "");
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
    public int update(ArrayList<ChiTietHoaDonDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietHoaDonDTO> selectAll(String t) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
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
                String mkm = rs.getString("MKM");
                ChiTietHoaDonDTO ctphieu = new ChiTietHoaDonDTO(maphieu, MSP, SL, tienxuat, mkm);
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
                String mkm = rs.getString("MKM");
                ChiTietHoaDonDTO ctphieu = new ChiTietHoaDonDTO(maphieu, MSP, SL, tienxuat, mkm);
                int SLsp = -(ctphieu.getSL());
                SanPhamDAO.getInstance().updateSoLuongTon(ctphieu.getMSP(), SLsp);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<ChiTietHoaDonDTO> searchByMSP(int msp) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();

            String sql = "SELECT * FROM CTHOADON WHERE MSP = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, msp);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int mhd = rs.getInt("MHD");
                int msP = rs.getInt("MSP");
                int sl = rs.getInt("SL");
                int tienXuat = rs.getInt("TIENXUAT");
                String mkm = rs.getString("MKM");

                ChiTietHoaDonDTO chiTiet = new ChiTietHoaDonDTO(mhd, msP, sl, tienXuat, mkm);
                result.add(chiTiet);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

}
