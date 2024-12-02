package GUI.Dialog;

import BUS.DonViBUS;
import BUS.LoaiBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DAO.SanPhamDAO;
import DTO.LoaiDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Panel.SanPham;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.PlainDocument;

public final class SanPhamDialog extends JDialog implements ActionListener {

    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain;
    private ButtonCustom btnHuyBo, btnAddSanPham;
    private SelectForm donvi, loai;
    InputForm tenSP, mv;
    InputForm txtgiaxuat;
    InputImage hinhanh;
    JTable tblcauhinh;
    JScrollPane scrolltblcauhinh;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;

    SanPhamBUS spBus = new SanPhamBUS();
    DonViBUS dvbus = new DonViBUS();
    LoaiBUS loaibus = new LoaiBUS();
    PhieuNhapBUS pnbus = new PhieuNhapBUS();

    SanPhamDTO sp;
    String[] arrkhuvuc;
    String[] arrnxb;
    int masp;
    private ButtonCustom btnSaveCH;

    public void init(SanPham jpSP) {
        this.jpSP = jpSP;
        masp = jpSP.spBUS.getMaxMSP() + 1;
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        initComponents(title, type);
    }

    public void initCardOne(String type) {
        pnCenter = new JPanel(new BorderLayout());
        pninfosanpham = new JPanel(new GridLayout(3, 4, 0, 0));
        pninfosanpham.setBackground(Color.WHITE);
        pnCenter.add(pninfosanpham, BorderLayout.CENTER);

        pninfosanphamright = new JPanel();
        pninfosanphamright.setBackground(Color.WHITE);
        pninfosanphamright.setPreferredSize(new Dimension(300, 600));
        pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pninfosanphamright, BorderLayout.WEST);

        tenSP = new InputForm("Tên sản phẩm");
        loai = new SelectForm("Loại", loaibus.getArrTenLoai());
        donvi = new SelectForm("Đơn vị", dvbus.getArrTenDonVi());
        txtgiaxuat = new InputForm("Giá xuất");
        PlainDocument xuat = (PlainDocument) txtgiaxuat.getTxtForm().getDocument();
        xuat.setDocumentFilter((new NumericDocumentFilter()));
        mv = new InputForm("Mã vạch");
        PlainDocument ma = (PlainDocument) mv.getTxtForm().getDocument();
        ma.setDocumentFilter((new NumericDocumentFilter()));
        hinhanh = new InputImage("Hình minh họa");

        pninfosanpham.add(tenSP);
        pninfosanpham.add(loai);
        pninfosanpham.add(donvi);
        pninfosanpham.add(txtgiaxuat);
        pninfosanpham.add(mv);
        pninfosanphamright.add(hinhanh);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);

        switch (type) {
            case "update" -> {
                initView();
                btnSaveCH = new ButtonCustom("Lưu thông tin", "success", 14);
                btnSaveCH.addActionListener(this);
                pnbottom.add(btnSaveCH);
            }
            case "create" -> {
                initCreate();
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                pnbottom.add(btnAddSanPham);
            }
        }

        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new CardLayout());

        initCardOne(type);

        pnmain.add(pnCenter);

        switch (type) {
            case "view" ->
                setInfo(sp);
            case "update" ->
                setInfo(sp);
            default -> {
            }
        }
//                throw new AssertionError();

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public String addImage(String urlImg) {
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/img_product";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
        }
        return newName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        } else if (source == btnAddSanPham && checkCreate()) {
            eventAddSanPham();
        } else if (source == btnSaveCH) {
            if (getInfo() != null) {
                SanPhamDTO snNew = getInfo();
                snNew.setSL(spBus.getSPbyMV(snNew.getMV()).getSL());
                if (!snNew.getHINHANH().equals(this.sp.getHINHANH())) {
                    snNew.setHINHANH(addImage(snNew.getHINHANH()));
                }
                snNew.setMSP(this.sp.getMSP());
                int tIENX = Integer.parseInt(txtgiaxuat.getText());
                String maSanPhamString = String.valueOf(this.sp.getMSP());
                int giaSanPhamMoiNhat = pnbus.getGiaSanPhamByMaxMPN(maSanPhamString);
                System.out.println(giaSanPhamMoiNhat);
                System.out.println(tIENX);
                if (tIENX < giaSanPhamMoiNhat) {
                    JOptionPane.showMessageDialog(this, "Giá sản phẩm phải lớn hơn giá SP mới nhập! (> " + giaSanPhamMoiNhat + ")");
                    return;
                }
                SanPhamDAO.getInstance().update(sp);
                this.jpSP.spBUS.update(snNew);
                this.jpSP.loadDataTalbe(this.jpSP.spBUS.getAll());
                JOptionPane.showMessageDialog(this, "Sửa thông tin sản phẩm thành công !");
                dispose();
            }
        }

    }

    public void eventAddSanPham() {
        if (getInfo() != null) {
            SanPhamDTO sp = getInfo();
            sp.setHINHANH(addImage(sp.getHINHANH()));
            if (jpSP.spBUS.add(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
                jpSP.loadDataTalbe(jpSP.listSP);
                dispose();
            }
        }
    }

    public SanPhamDTO getInfo() {
        if (txtgiaxuat.getText().equals("") || tenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
            return null;
        }

        String vtensp = tenSP.getText();
        String hinhanh = this.hinhanh.getUrl_img();
        String loaiName = this.loai.getSelectedItem().toString();
        int loai = loaibus.getByName(loaiName).getML();
        String donViName = this.donvi.getSelectedItem().toString();
        int donvi = dvbus.getByName(donViName).getMDV();
        int tIENX = Integer.parseInt(txtgiaxuat.getText());

        String ISBN = mv.getText();
        SanPhamDTO result = new SanPhamDTO(masp, vtensp, hinhanh, loai, tIENX, 0, donvi, ISBN);
        return result;
    }

    public void setInfo(SanPhamDTO sp) {
        String maLoai = String.valueOf(sp.getML());
        String maDonVi = String.valueOf(sp.getMDV());
        hinhanh.setUrl_img(sp.getHINHANH());
        tenSP.setText(sp.getTEN());
        loai.setSelectedItem(loaibus.getById(maLoai).getTENL());
        donvi.setSelectedItem(dvbus.getById(maDonVi).getTENDV());
        txtgiaxuat.setText(Integer.toString(sp.getTIENX()));
        mv.setText(sp.getMV());
    }

    public boolean checkCreate() {
        boolean check = true;
        System.out.println(donvi.getSelectedItem());
        if (Validation.isEmpty(tenSP.getText())
                || Validation.isEmpty(txtgiaxuat.getText()) || Validation.isEmpty(mv.getText())) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else if (!spBus.checkMV(mv.getText())) {
            JOptionPane.showMessageDialog(this, "Mã vạch đã tồn tại!");
            check = false;
        } else if (mv.getText().length() != 13) {
            JOptionPane.showMessageDialog(this, "Mã vạch phải là dãy số bao gồm 13 chữ số");
            check = false;
        } else if (!spBus.checkDuplicate(tenSP.getText(), donvi.getSelectedItem().toString(), loai.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại (Trùng tên, Loại, Đơn vị)");
            check = false;
        } else {
            if (hinhanh.getUrl_img() == null) {
                JOptionPane.showMessageDialog(this, "Chưa thêm ảnh sản phẩm!");
                check = false;
            }
        }
        return check;
    }

    public boolean checkUpdate() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText())
                || Validation.isEmpty(txtgiaxuat.getText()) || Validation.isEmpty(mv.getText())) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            if (hinhanh.getUrl_img() == null) {
                JOptionPane.showMessageDialog(this, "Chưa thêm ảnh sản phẩm!");
                check = false;
            }
        }
        return check;
    }

    public void initView() {
        mv.setEditable(false);
    }

    public void initCreate() {
        mv.setEditable(true);
    }
}
