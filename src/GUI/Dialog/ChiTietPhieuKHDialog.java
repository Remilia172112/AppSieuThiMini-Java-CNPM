package GUI.Dialog;

import BUS.SanPhamBUS;
import BUS.HoaDonBUS;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuDTO;
import DTO.SanPhamDTO;
import DTO.HoaDonDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import helper.Formater;
import helper.writePDF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class ChiTietPhieuKHDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_btn; //bỏ pnmain_bottom_right, pnmain_bottom_left 
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian;
    DefaultTableModel tblModel;
    JTable table, tblImei;
    JScrollPane scrollTable;

    HoaDonDTO phieuxuat;
    SanPhamBUS spBus = new SanPhamBUS();
    HoaDonBUS phieuxuatBus;

    ButtonCustom btnPdf, btnHuyBo;

    ArrayList<ChiTietPhieuDTO> chitietphieu;

    //phieu xuat
    public ChiTietPhieuKHDialog(JFrame owner, String title, boolean modal, HoaDonDTO phieuxuatDTO) {
        super(owner, title, modal);
        this.phieuxuat = phieuxuatDTO;
        phieuxuatBus = new HoaDonBUS();
        chitietphieu = phieuxuatBus.selectCTP(phieuxuatDTO.getMP());
        initComponent(title);
        initPhieuXuat();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }


    public void initPhieuXuat() {
        txtMaPhieu.setText("PX" + Integer.toString(this.phieuxuat.getMP()));
        txtNhaCungCap.setTitle("Khách hàng");
        txtNhaCungCap.setText(KhachHangDAO.getInstance().selectById(phieuxuat.getMKH() + "").getHoten());
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieuxuat.getMNV() + "").getHOTEN());
        txtThoiGian.setText(Formater.FormatTime(phieuxuat.getTG()));
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuDTO> ctPhieu) {
        tblModel.setRowCount(0);
        for (int i = 0; i < ctPhieu.size(); i++) {
            SanPhamDTO sp = spBus.getByMaSP(ctPhieu.get(i).getMSP());
            tblModel.addRow(new Object[]{
                i + 1, sp.getMSP(), SanPhamDAO.getInstance().selectById(sp.getMSP()+"").getTEN(), 
                Formater.FormatVND(ctPhieu.get(i).getTIEN()), ctPhieu.get(i).getSL()
            });
        }
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhaCungCap = new InputForm("Nhà cung cấp");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
        pnmain_top.add(txtThoiGian);

        pnmain_bottom = new JPanel(new GridLayout(1, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        // pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
    
        pnmain_bottom.add(scrollTable);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        }
        if (source == btnPdf) {
            writePDF w = new writePDF();
            if (this.phieuxuat != null) {
                w.writePX(phieuxuat.getMP());
            }
        }
    }
}
