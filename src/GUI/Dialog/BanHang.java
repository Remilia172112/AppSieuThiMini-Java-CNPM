package GUI.Dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;
import java.awt.*;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import BUS.KhachHangBUS;
import BUS.LoaiBUS;
import BUS.MaKhuyenMaiBUS;
import BUS.DonViBUS;
import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import DAO.NhanVienDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietMaKhuyenMaiDTO;
import DTO.DonViDTO;
import DTO.KhachHangDTO;
import DTO.LoaiDTO;
import DTO.MaKhuyenMaiDTO;
import DTO.NhanVienDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.Notification;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import GUI.Panel.HoaDon;
import helper.Formater;
import helper.writePDF;

public final class BanHang extends JFrame {
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    // gọi phương thức compoment tổ tiên có kiểu window của compoment hiện tại
    // kiểu như cái listKhachHang thì cho owner dô sẽ gọi đc cái jframe của
    // listkhachhang
    PanelBorderRadius right, left;
    JPanel contentCenter, left_top, left_bottom; // , content_btn
    JTable tablePhieuXuat, tableSanPham;
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP; // table co san
    ButtonCustom btnTaoSp, btnAddSp, btnEditSP, btnDelete, btnRefresh, btnThanhToan;
    InputForm txtTenSp, txtMaSp, txtMaISBN, txtSoLuongSPxuat, txtMaGiamGia, txtGiaGiam;

    SelectForm cbxMaKM;
    JTextField txtTimKiem;
    Color BackgroundColor = new Color(193, 237, 220);

    int sum = 0, dungdiem = 0, diemtamtinh = 0; // do ctpxuất ko có sẵn tính tiền
    double giagiam = 0, khachcantra = 0, tienthua = 0, dathu = 0;

    int maphieu;
    int masp;
    // int manv;
    int makh = -1;
    int dtl = 0;
    String type;

    // ArrayList<SanPhamDTO> ctpb;
    SanPhamBUS spBUS = new SanPhamBUS();
    MaKhuyenMaiBUS mkmBUS = new MaKhuyenMaiBUS();
    HoaDonBUS phieuXuatBUS = new HoaDonBUS();
    // SanPhamBUS chiTietSanPhamBUS = new SanPhamBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    ArrayList<ChiTietHoaDonDTO> chitietphieu = new ArrayList<>();
    ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    ArrayList<DTO.ChiTietMaKhuyenMaiDTO> listctMKM = new ArrayList<>();

    TaiKhoanDTO tk;
    DonViBUS dvbus = new DonViBUS();

    public JLabel lbltongtien, lblgiamgia, lbldungdiem, lblkhachcantra, lbltienthua, lbldiemtamtinh;
    public JTextField txtKh, txtDTL, txtDTLG, txtDaThu;
    // private Main mainChinh;
    public InputForm txtGiaXuat;
    protected Frame mainChinh; // ???
    ArrayList<String> listMaKM = new ArrayList<>();

    // public BanHang(Main mainChinh, TaiKhoanDTO tk, String type) {
    public BanHang(TaiKhoanDTO tk, String type) {
        this.tk = tk;
        this.type = type;
        maphieu = phieuXuatBUS.getMPMAX() + 1;
        initComponent(type);
        loadDataTalbeSanPham(listSP);
    }

    private void initComponent(String type) {
        this.setSize(new Dimension(1400, 700));
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(BackgroundColor);
        // this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("POS");

        // Phiếu xuất
        tablePhieuXuat = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        // String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Đơn giá", "Số
        // lượng"};
        String[] header = new String[] { "Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng", "Đơn vị", "Giảm", "Số tiền",
                "Xóa" };
        tblModel.setColumnIdentifiers(header);
        tablePhieuXuat.setModel(tblModel);
        int[] columnWidths = { 120, 400, 80, 70, 70, 50, 150, 30 }; // Mảng chứa kích thước các cột
        for (int i = 0; i < tablePhieuXuat.getColumnCount(); i++) {
            TableColumn column = tablePhieuXuat.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
        scrollTablePhieuNhap.setViewportView(tablePhieuXuat);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tablePhieuXuat.getColumnModel();
        for (int i = 0; i < 8; i++) {
            if (i != 1) { // Ngoại trừ cột thứ 2 thì tất cả đều căn giữa
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tablePhieuXuat.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablePhieuXuat.setFocusable(false);
        tablePhieuXuat.setDefaultEditor(Object.class, null);
        scrollTablePhieuNhap.setViewportView(tablePhieuXuat);

        tablePhieuXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablePhieuXuat.rowAtPoint(e.getPoint());
                int column = tablePhieuXuat.columnAtPoint(e.getPoint());
        
                if (row == -1 || column == -1) {
                    return; // Không có hàng hoặc cột nào được nhấp
                }
        
                // Kiểm tra nếu cột "Delete" được nhấp
                if (column == 7) {
                    tblModel.removeRow(row);
                    chitietphieu.remove(row);
                    tblModel.fireTableDataChanged(); // Cập nhật lại bảng
                    resetForm();
                    resetFormRight();
                    return;
                }
        
                int index = tablePhieuXuat.getSelectedRow();
                if (index >= 0 && index < chitietphieu.size()) {
                    actionbtn("update");
                    cbxMaKM.setSelectedItem(chitietphieu.get(index).getMKM());
                    setFormChiTietPhieu(chitietphieu.get(index));
                }
            }
        });
        

        // Table sản phẩm // THAY ĐỔI
        tableSanPham = new JTable();
        tableSanPham.setBackground(new Color(0xA1D6E2));
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[] { "Mã SP", "Tên sản phẩm", "Số lượng tồn" };
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);

        tableSanPham.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index != -1) {
                    // resetForm();
                    setInfoSanPham(listSP.get(index));
                    if (!checkTonTai()) {
                        actionbtn("add");
                    } else {
                        actionbtn("update");
                    }
                }
            }
        });

        // content_CENTER (chứa hết tất cả left+right)
        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        // this.add(contentCenter, BorderLayout.CENTER);
        this.add(contentCenter);

        // LEFT
        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left_top = new JPanel(); // Chứa tất cả phần ở phía trái trên cùng, chứa {content_top, content_btn}
        left_top.setLayout(new BorderLayout());
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_left, content_right, content_right_top; // content_top {content_left +
                                                                            // content_right} -> trong left_top
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);

        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(100, 40));
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm, ...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) { // chạy khi nhả phím trong tìm kiếm
                ArrayList<SanPhamDTO> rs = spBUS.search(txtTimKiem.getText(), "Tất cả");
                loadDataTalbeSanPham(rs);
            }
        });

        content_left.add(txtTimKiem, BorderLayout.NORTH);
        content_left.add(scrollTableSanPham, BorderLayout.CENTER);

        // content_right = new JPanel(new BorderLayout(5, 5));
        // content_right.setOpaque(false);
        // content_right.setBackground(Color.WHITE);

        // content_right_top = new JPanel(new BorderLayout());
        // content_right_top.setPreferredSize(new Dimension(100, 335));
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        txtTenSp.setPreferredSize(new Dimension(100, 90));
        txtMaSp = new InputForm("Mã sản phẩm");
        txtMaSp.setEditable(false);
        txtMaISBN = new InputForm("Mã vạch");
        txtMaISBN.setEditable(false);
        txtGiaXuat = new InputForm("Giá xuất");
        PlainDocument dongia = (PlainDocument) txtGiaXuat.getTxtForm().getDocument();
        dongia.setDocumentFilter((new NumericDocumentFilter())); // chỉ cho nhập số

        txtSoLuongSPxuat = new InputForm("Số lượng");
        txtSoLuongSPxuat.setText(1 + "");
        PlainDocument soluong = (PlainDocument) txtSoLuongSPxuat.getTxtForm().getDocument();
        soluong.setDocumentFilter((new NumericDocumentFilter())); // chỉ cho nhập số

        String[] maGiamGia = { "Chọn" };
        cbxMaKM = new SelectForm("Mã giảm giá", maGiamGia);
        txtGiaGiam = new InputForm("Giá giảm");
        txtGiaGiam.setText(" ");
        txtGiaGiam.setEditable(false);
        cbxMaKM.cbb.addItemListener((ItemListener) new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = cbxMaKM.cbb.getSelectedIndex();
                if (index != 0) {
                    double giaxuat = Integer.parseInt(txtGiaXuat.getText());
                    double phantramgiam = (double) listctMKM.get(index - 1).getPTG();
                    int giagiam = (int) (giaxuat * (1 - phantramgiam / 100));
                    txtGiaGiam.setText(Integer.toString(giagiam));
                }
            }

        });

        content_top.add(content_left);
        left_top.add(content_top, BorderLayout.CENTER);

        // btnTaoSp = new ButtonCustom("Tạo sản phẩm", "success", 14);
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        btnAddSp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkInfo()) {
                    listMaKM.add(cbxMaKM.getSelectedItem().toString());
                    addCtPhieu(listMaKM.size() - 1);
                    // thông báo dạng popup dùng của Notification trong Compoment của Gui
                    Notification thongbaoNoi = new Notification(mainChinh, Notification.Type.SUCCESS,
                            Notification.Location.TOP_CENTER, "Thêm sản phẩm thành công!");
                    thongbaoNoi.showNotification();
                    loadDataTableChiTietPhieu(chitietphieu);
                    cbxMaKM.setSelectedItem("Chọn");
                    resetForm();
                    // actionbtn("update");
                }

            }

        });

        btnEditSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tablePhieuXuat.getSelectedRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn cấu hình cần chỉnh");
                } else {
                    chitietphieu.get(index).setSL(Integer.parseInt(txtSoLuongSPxuat.getText()));
                    if (!txtGiaGiam.getText().equals(" ")) {
                        chitietphieu.get(index).setTIEN(Integer.parseInt(txtGiaGiam.getText()));
                        chitietphieu.get(index).setMKM(cbxMaKM.getSelectedItem().toString());
                    } else
                        chitietphieu.get(index).setTIEN(Integer.parseInt(txtGiaXuat.getText()));
                    actionbtn("add");
                    loadDataTableChiTietPhieu(chitietphieu);
                    cbxMaKM.setSelectedItem(chitietphieu.get(index).getMKM());
                }
                resetForm();

            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tablePhieuXuat.getSelectedRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn cấu hình cần xóa");
                } else {
                    chitietphieu.remove(index);
                    actionbtn("add");
                    loadDataTableChiTietPhieu(chitietphieu);
                    resetForm();
                }
            }
        });

        btnEditSP.setEnabled(false);
        btnDelete.setEnabled(false);

        JPanel merge1 = new JPanel();
        merge1.setPreferredSize(new Dimension(0, 40));
        merge1.setOpaque(false);
        merge1.add(btnAddSp);
        merge1.add(btnEditSP);
        JPanel merge2 = new JPanel(new GridLayout(1, 2));
        merge2.setPreferredSize(new Dimension(0, 80));
        merge2.add(txtSoLuongSPxuat);
        merge2.add(cbxMaKM);

        // left_bottom này là danh sách xuất ở left phía nam, chứa tablelistnhap
        left_bottom = new JPanel();
        left_bottom.setOpaque(false);
        left_bottom.setPreferredSize(new Dimension(0, 250));
        left_bottom.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(left_bottom, BoxLayout.Y_AXIS);
        left_bottom.setLayout(boxly);
        left_bottom.add(scrollTablePhieuNhap);
        left.add(left_top, BorderLayout.CENTER);
        left.add(left_bottom, BorderLayout.SOUTH);

        // RIGHT
        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(400, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_button;
        right_top = new JPanel(new BorderLayout());
        right_top.setPreferredSize(new Dimension(0, 300));
        right_top.setBorder(new EmptyBorder(0, 10, 30, 5));
        right_top.setOpaque(false);

        JPanel khachJPanel = new JPanel(new BorderLayout());
        khachJPanel.setPreferredSize(new Dimension(0, 60));
        khachJPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        khachJPanel.setOpaque(false);
        JPanel kJPanelLeft = new JPanel(new GridLayout(1, 1));
        kJPanelLeft.setPreferredSize(new Dimension(40, 0));
        ButtonCustom btnKh = new ButtonCustom("Chọn khách hàng", "success", 14);
        kJPanelLeft.add(btnKh);
        btnKh.addActionListener((ActionEvent e) -> {
            new ListKhachHang(BanHang.this, owner, "Chọn khách hàng", true);

        });

        txtKh = new JTextField("Chon khach hang");
        txtKh.setEditable(false);
        JLabel lbDTL = new JLabel("Điểm: ");
        txtDTL = new JTextField();
        // txtDTL.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtDTL.setEditable(false);
        JLabel lbDTLG = new JLabel("Dùng: ");
        txtDTLG = new JTextField();
        txtDTLG.setText("0");
        txtDTLG.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabels();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabels();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabels();
            }

            private void updateLabels() {
                String text = txtDTLG.getText();
                lbldungdiem.setText(Formater.FormatVND(Integer.parseInt(text)));
                khachcantra = (double) (sum - giagiam - Integer.parseInt(text));
                lblkhachcantra.setText(Formater.FormatVND(khachcantra));
                diemtamtinh = (int) (khachcantra * 0.01);
                lbldiemtamtinh.setText(Formater.FormatVND(diemtamtinh));
                // loadDataMenuRight();
            }
        });

        khachJPanel.add(txtKh, BorderLayout.CENTER);
        khachJPanel.add(kJPanelLeft, BorderLayout.EAST);
        JPanel khPanel = new JPanel(new GridLayout(4, 1, 5, 0));
        khPanel.setBackground(Color.WHITE);
        khPanel.setPreferredSize(new Dimension(0, 120));
        JLabel khachKhangJLabel = new JLabel("Khách hàng");
        khachKhangJLabel.setPreferredSize(new Dimension(0, 40));
        khachKhangJLabel.setBorder(new EmptyBorder(0, 10, 0, 10));

        khPanel.add(merge2);
        khPanel.add(merge1);
        khPanel.add(khachKhangJLabel);
        khPanel.add(khachJPanel);

        JPanel dtlPanel = new JPanel(new GridLayout(1, 4, 5, 0));
        dtlPanel.setPreferredSize(new Dimension(0, 40));
        dtlPanel.setOpaque(false);
        dtlPanel.add(lbDTL);
        dtlPanel.add(txtDTL);
        dtlPanel.add(lbDTLG);
        dtlPanel.add(txtDTLG);
        // right_top.add(content_btn, BorderLayout.NORTH);
        right_top.add(khPanel, BorderLayout.CENTER);
        right_top.add(dtlPanel, BorderLayout.SOUTH);

        right_center = new JPanel(new GridLayout(8, 2, 10, 0));
        right_center.setBorder(new EmptyBorder(5, 20, 5, 20));
        right_center.setOpaque(false);

        JLabel lbl1 = new JLabel("Tổng tiền: ");
        lbl1.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        // lbl1.setForeground(new Color(255, 51, 51));
        lbltongtien = new JLabel("0đ");
        lbltongtien.setHorizontalAlignment(JLabel.RIGHT);
        lbltongtien.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl2 = new JLabel("Giảm giá: ");
        lbl2.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        lblgiamgia = new JLabel("0đ");
        lblgiamgia.setHorizontalAlignment(JLabel.RIGHT);
        lblgiamgia.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl3 = new JLabel("Dùng điểm: ");
        lbl3.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        lbldungdiem = new JLabel("0đ");
        lbldungdiem.setHorizontalAlignment(JLabel.RIGHT);
        lbldungdiem.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl4 = new JLabel("Khách cần trả: ");
        lbl4.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        lblkhachcantra = new JLabel("0đ");
        lblkhachcantra.setHorizontalAlignment(JLabel.RIGHT);
        lblkhachcantra.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl5 = new JLabel("Hình thức: ");
        lbl5.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        JLabel lblhinhthuc = new JLabel("Tiền mặt");
        lblhinhthuc.setHorizontalAlignment(JLabel.CENTER);
        lblhinhthuc.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl6 = new JLabel("Đã thu: ");
        lbl6.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        txtDaThu = new JTextField();
        txtDaThu.setSize(40, 30);
        txtDaThu.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabels();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabels();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabels();
            }

            private void updateLabels() {
                Double text = Double.parseDouble(txtDaThu.getText());
                lbltienthua.setText(Formater.FormatVND(text - khachcantra));
            }
        });
        // lbldathu = new JLabel("0đ");
        // lbldathu.setHorizontalAlignment(JLabel.RIGHT);
        // lbldathu.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl7 = new JLabel("Tiền thừa: ");
        lbl7.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        lbltienthua = new JLabel("0đ");
        lbltienthua.setHorizontalAlignment(JLabel.RIGHT);
        lbltienthua.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        JLabel lbl8 = new JLabel("Điểm tạm tính: ");
        lbl8.setFont(new Font(FlatRobotoFont.FAMILY, 1, 16));
        lbldiemtamtinh = new JLabel("0đ");
        lbldiemtamtinh.setHorizontalAlignment(JLabel.RIGHT);
        lbldiemtamtinh.setFont(new Font(FlatRobotoFont.FAMILY, 4, 16));

        right_center.add(lbl1);
        right_center.add(lbltongtien);
        right_center.add(lbl2);
        right_center.add(lblgiamgia);
        right_center.add(lbl3);
        right_center.add(lbldungdiem);
        right_center.add(lbl4);
        right_center.add(lblkhachcantra);
        right_center.add(lbl5);
        right_center.add(lblhinhthuc);
        right_center.add(lbl6);
        right_center.add(txtDaThu);
        right_center.add(lbl7);
        right_center.add(lbltienthua);
        right_center.add(lbl8);
        right_center.add(lbldiemtamtinh);

        right_bottom = new JPanel(new FlowLayout(1));
        right_bottom.setPreferredSize(new Dimension(350, 55));
        right_bottom.setBorder(new EmptyBorder(5, 10, 5, 10));
        right_bottom.setOpaque(false);

        btnRefresh = new ButtonCustom("Làm mới", "excel", 16, 100, 45);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblModel.setRowCount(0);
                chitietphieu.clear();
                resetForm();
                resetFormRight();
            }
        });
        // btnIn = new ButtonCustom("In", "excel", 16, 60, 45);

        btnThanhToan = new ButtonCustom("Thanh Toán", "excel", 16, 150, 45);
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventBtnNhapHang();
            }
        });
        right_bottom.add(btnRefresh);
        // right_bottom.add(btnIn);
        right_bottom.add(btnThanhToan);

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
        // actionbtn("add");
    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        // content_btn.revalidate();
        // content_btn.repaint();
    }

    public void resetForm() {
        this.txtTenSp.setText("");
        this.txtMaSp.setText("");
        this.txtMaISBN.setText("");
        this.txtGiaXuat.setText("");
        this.txtSoLuongSPxuat.setText("1");
        this.txtGiaGiam.setText(" ");
    }

    public String[] getMaGiamGiaTable(int masp) {
        listctMKM = mkmBUS.Getctmkm(masp);
        int size = listctMKM.size();
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (!validateSelectDate(listctMKM.get(i)))
                arr.add(listctMKM.get(i).getMKM());
        }
        String[] tmp = new String[arr.size()];
        for (int i = 0; i < tmp.length; i++)
            tmp[i] = arr.get(i);
        tmp = Stream.concat(Stream.of("Chọn"), Arrays.stream(tmp)).toArray(String[]::new);
        return tmp;
    }

    public int getPcCp(int masp, String maKM) {
        listctMKM = mkmBUS.Getctmkm(masp);
        for (ChiTietMaKhuyenMaiDTO ctMKM : listctMKM) {
            if (!validateSelectDate(ctMKM) && maKM.equals(ctMKM.getMKM())) {
                return ctMKM.getPTG();
            }
        }
        return 0;
    }

    public boolean validateSelectDate(DTO.ChiTietMaKhuyenMaiDTO tmp) {
        MaKhuyenMaiDTO a = mkmBUS.selectMkm(tmp.getMKM());
        Date time_start = a.getTGBD();
        Date time_end = a.getTGKT();
        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            return false;
        }
        return true;
    }

    public void setInfoSanPham(SanPhamDTO sp) {
        masp = sp.getMSP();
        this.txtMaSp.setText(Integer.toString(sp.getMSP()));
        this.txtTenSp.setText(sp.getTEN());
        this.txtMaISBN.setText(sp.getMV());
        this.txtGiaXuat.setText(Integer.toString(sp.getTIENX()));
        cbxMaKM.setArr(getMaGiamGiaTable(sp.getMSP()));

    }

    public void setFormChiTietPhieu(ChiTietHoaDonDTO phieu) { // set info vào inputform khi nhan ben tablephieunhap
        SanPhamDTO ctsp = spBUS.getByMaSP(phieu.getMSP());
        // ChiTietMaKhuyenMaiDTO ctmkm = mkmBUS.findCT(listctMKM, ctsp.getMSP());
        this.txtMaSp.setText(Integer.toString(ctsp.getMSP()));
        this.txtTenSp.setText(spBUS.getByMaSP(ctsp.getMSP()).getTEN());
        this.txtGiaXuat.setText(Integer.toString(phieu.getTIEN()));
        this.txtSoLuongSPxuat.setText(Integer.toString(phieu.getSL()));
        cbxMaKM.setArr(getMaGiamGiaTable(ctsp.getMSP()));
        cbxMaKM.setSelectedItem(phieu.getMKM());
    }

    public void loadDataTalbeSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[] { sp.getMSP(), sp.getTEN(), sp.getSL() });
        }
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietHoaDonDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        sum = 0;
        giagiam = 0;
        ArrayList<DonViDTO> listdv = dvbus.getAll();
        for (int i = 0; i < size; i++) {
            int percentCounpoint = getPcCp(ctPhieu.get(i).getMSP(), listMaKM.get(i));
            SanPhamDTO sp = spBUS.getByMaSP(ctPhieu.get(i).getMSP());
            sum += ctPhieu.get(i).getTIEN() * ctPhieu.get(i).getSL();
            tblModel.addRow(new Object[] {
                    sp.getMV(), spBUS.getByMaSP(sp.getMSP()).getTEN(),
                    Formater.FormatVND(ctPhieu.get(i).getTIEN()), ctPhieu.get(i).getSL(),
                    listdv.get(sp.getMDV() - 1).getTENDV(), percentCounpoint,
                    Formater.FormatVND(ctPhieu.get(i).getTIEN() * ctPhieu.get(i).getSL()), "X"

            });
            giagiam += (double) ((percentCounpoint) / 100.0) * sum;
        }
        lbltongtien.setText(Formater.FormatVND(sum));
        lblgiamgia.setText(Formater.FormatVND(giagiam));
        loadDataMenuRight();

    }

    public boolean checkTonTai() {
        ChiTietHoaDonDTO p = phieuXuatBUS.findCT(chitietphieu, Integer.parseInt(txtMaSp.getText()));
        // kiểm tra coi masp này có trong chitietphieu này chưa

        return p != null;
    }

    public boolean checkInfo() {
        boolean check = true;
        int index = tableSanPham.getSelectedRow();
        if (txtMaSp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            check = false;
        } else if (txtGiaXuat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Giá nhập không được để rỗng !", "Cảnh báo !",
                    JOptionPane.WARNING_MESSAGE);
            check = false;
        } else if (txtSoLuongSPxuat.getText().equals("")
                || Integer.parseInt(txtSoLuongSPxuat.getText()) > listSP.get(index).getSL()) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để rỗng và không lớn hơn đang có!", "Cảnh báo !",
                    JOptionPane.WARNING_MESSAGE);
            check = false;
        } else if (Integer.parseInt(txtSoLuongSPxuat.getText()) == 0) {
            JOptionPane.showMessageDialog(null, "Số lượng không được bằng 0!", "Cảnh báo !",
                    JOptionPane.WARNING_MESSAGE);
            check = false;
        }
        return check;
    }

    public void addCtPhieu(int index) { // them sp vao chitietphieu
        int masp = Integer.parseInt(txtMaSp.getText());
        int giaxuat;
        String mkm = listMaKM.get(index);
        if (!txtGiaGiam.getText().equals(" ")) {
            giaxuat = Integer.parseInt(txtGiaGiam.getText());
        } else
            giaxuat = Integer.parseInt(txtGiaXuat.getText());
        int soluong = Integer.parseInt(txtSoLuongSPxuat.getText());
        ChiTietHoaDonDTO ctphieu = new ChiTietHoaDonDTO(maphieu, masp, soluong, giaxuat, mkm);
        ChiTietHoaDonDTO p = phieuXuatBUS.findCT(chitietphieu, ctphieu.getMSP());
        if (p == null) {
            chitietphieu.add(ctphieu);
            loadDataTableChiTietPhieu(chitietphieu);
            resetForm();
        } else {
            int input = JOptionPane.showConfirmDialog(this,
                    "Sản phẩm đã tồn tại trong phiếu !\nBạn có muốn chỉnh sửa không ?", "Sản phẩm đã tồn tại !",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                setFormChiTietPhieu(ctphieu);
            }
        }
    }

    public void eventBtnNhapHang() {
        if (chitietphieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu!", "Cảnh báo !",
                    JOptionPane.ERROR_MESSAGE);
        } else if (makh == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng!", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        } else if (Integer.parseInt(txtDTLG.getText()) > dtl || Integer.parseInt(txtDTLG.getText()) > sum) {
            JOptionPane.showMessageDialog(null, "Điểm tích lũy không lớn hơn điểm đang có và giá đang bán!",
                    "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        } else {
            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo hóa đơn !",
                    "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                if (!phieuXuatBUS.checkSLPx(chitietphieu)) {
                    JOptionPane.showMessageDialog(null, "Không đủ số lượng để tạo phiếu!");
                } else {
                    long now = System.currentTimeMillis();
                    Timestamp currenTime = new Timestamp(now);
                    HoaDonDTO phieuXuat = new HoaDonDTO(makh, maphieu, tk.getMNV(), currenTime, (int)(khachcantra), 1, Integer.parseInt(txtDTLG.getText()));
                    
                    ArrayList<SanPhamDTO> lstSpDTO = new ArrayList<>();
                    lstSpDTO = spBUS.listSP;
                    for (int i = 0; i < chitietphieu.size(); i++) {
                        if(chitietphieu.get(i).getMKM().equals("Chọn")) {
                            chitietphieu.get(i).setMKM(null);
                        }
                        if (chitietphieu.get(i).getMSP() == lstSpDTO.get(i).getMSP()) {
                            lstSpDTO.get(i).setSL(lstSpDTO.get(i).getSL() - chitietphieu.get(i).getSL());
                            spBUS.update(lstSpDTO.get(i));
                        }
                    }
                    phieuXuatBUS.insert(phieuXuat, chitietphieu); // update số lượng trong kho
                    /// gọi BUS, BUS gọi DAO, DAO chỉnh trong sql
                    // SanPhamBUS.updateXuat(chitietsanpham);
                    JOptionPane.showMessageDialog(null, "Xuất hàng thành công !");
                    khachHangBUS.update(makh, (dtl - Integer.parseInt(txtDTLG.getText())) + diemtamtinh);
                    new ChiTietPhieuDialog(this, "Thông tin phiếu xuất", true, phieuXuat);
                    // this.setPanel(new HoaDon(this, tk));
                    chitietphieu.clear();
                    tblModel.fireTableDataChanged();
                    resetForm();
                    resetFormRight();
                    // this.dispose();
                }
            }
        }
    }

    public void setKhachHang(int index) {
        makh = index;
        KhachHangDTO khachhang = khachHangBUS.selectKh(makh);
        txtKh.setText(khachhang.getHOTEN());
        if (index != 1) {
            dtl = khachhang.getDIEMTICHLUY();
            txtDTLG.setEditable(true);
            txtDTL.setText(khachhang.getDIEMTICHLUY() + "");
            txtDTLG.setText("0");
        } else {
            dtl = 0;
            txtDTLG.setEditable(false);
            txtDTLG.setText("0");
            txtDTL.setText("");

        }

    }

    public void loadDataMenuRight() {

        if (!txtDTL.getText().equals("")) {
            dungdiem = Integer.parseInt(txtDTLG.getText());

            if (dungdiem <= dtl) {
                // thông báo
                lbldungdiem.setText(Formater.FormatVND(dungdiem));
                khachcantra = (double) (sum - giagiam - dungdiem);
                lbldiemtamtinh.setText(Formater.FormatVND(diemtamtinh));
                lblkhachcantra.setText(Formater.FormatVND(khachcantra));

            } else {
                lbldungdiem.setText("0đ");
                khachcantra = (double) (sum - giagiam);
                lbldiemtamtinh.setText("0đ");
                lblkhachcantra.setText(Formater.FormatVND(khachcantra));

            }
        } else {
            khachcantra = (double) (sum - giagiam);
            lbldiemtamtinh.setText(Formater.FormatVND(khachcantra * 0.01));
            lblkhachcantra.setText(Formater.FormatVND(khachcantra));

        }

    }

    public void resetFormRight() {
        lbltongtien.setText("0đ");
        lblgiamgia.setText("0đ");
        lbldungdiem.setText("0đ");
        lblkhachcantra.setText("0đ");
        txtDaThu.setText("");
        lbltienthua.setText("0đ");
        lbldiemtamtinh.setText("0đ");
        txtDTLG.setText("0");
        txtDTL.setText("");
        txtKh.setText("Chọn khách hàng");
    }
}