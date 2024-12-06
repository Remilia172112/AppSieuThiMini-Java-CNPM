package GUI.Dialog;

import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.ButtonCustom;
import DTO.KhachHangDTO;
import GUI.Panel.KhachHang;
import GUI.Component.NumericDocumentFilter;
import helper.Validation;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

public class KhachHangDialog extends JDialog implements MouseListener {

    KhachHang jpKH;
    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenKH, sdtKH, diachiKH, emailKH;
    private JTextField maKH;
    KhachHangDTO kh;

    public KhachHangDialog(KhachHang jpKH, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpKH = jpKH;
        tenKH = new InputForm("Tên khách hàng");
        sdtKH = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument)sdtKH.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        diachiKH = new InputForm("Địa chỉ");
        emailKH = new InputForm("Email");
        initComponents(title, type);
    }

    public KhachHangDialog(KhachHang jpKH, JFrame owner, String title, boolean modal, String type, DTO.KhachHangDTO kh) {
        super(owner, title, modal);
        this.kh=kh;
        maKH = new JTextField("");
        setMaKH(Integer.toString(kh.getMKH()));
        tenKH = new InputForm("Tên khách hàng");
        setTenKH(kh.getHOTEN());
        sdtKH = new InputForm("Số điện thoại");
        setSdtKH(kh.getSDT());
        diachiKH = new InputForm("Địa chỉ");
        setDiaChiKH(kh.getDIACHI());
        emailKH = new InputForm("Email");
        setEmailKH(kh.getEMAIL());
        this.jpKH = jpKH;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel(new GridLayout(3, 1, 20, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(tenKH);
        pnlMain.add(sdtKH);
        pnlMain.add(diachiKH);
        pnlMain.add(emailKH);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm khách hàng", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" ->
                pnlButtom.add(btnThem);
            case "update" ->
                pnlButtom.add(btnCapNhat);
            case "view" -> {
                tenKH.setDisable();
                sdtKH.setDisable();
                diachiKH.setDisable();
                emailKH.setDisable();
            }
            default ->
                throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTenKH(String name) {
        tenKH.setText(name);
    }

    public String getTenKH() {
        return tenKH.getText();
    }

    public String getMaKH() {
        return maKH.getText();
    }

    public void setMaKH(String id) {
        this.maKH.setText(id);
    }

    public String getSdtKH() {
        return sdtKH.getText();
    }

    public void setSdtKH(String id) {
        this.sdtKH.setText(id);
    }

    public String getDiaChiKH() {
        return diachiKH.getText();
    }

    public void setDiaChiKH(String id) {
        this.diachiKH.setText(id);
    }

    public String getEmailKH() {
        return emailKH.getText();
    }

    public void setEmailKH(String id) {
        this.emailKH.setText(id);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean Validation(){
        if (Validation.isEmpty(tenKH.getText())) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         else if (Validation.isEmpty(sdtKH.getText()) || !Validation.isNumber(sdtKH.getText()) || sdtKH.getText().length() != 10 || sdtKH.getText().charAt(0) != '0') {
    JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng, phải là 10 ký tự số và bắt đầu bằng 0", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
    return false;
}

        else  if (Validation.isEmpty(diachiKH.getText())) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
         else if(Validation.isEmpty(emailKH.getText()) || !Validation.isEmail(emailKH.getText())) {
            JOptionPane.showMessageDialog(this, "Email không được rỗng và đúng định dạng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return false;
         }
          return true;
    }
   @Override
public void mousePressed(MouseEvent e) {
    if (e.getSource() == btnThem && Validation()) {
        // Thêm khách hàng mới
        int id = jpKH.khachhangBUS.total() + 1;
        long now = System.currentTimeMillis();
        Timestamp currenTime = new Timestamp(now);
        boolean flag = jpKH.khachhangBUS.add(new DTO.KhachHangDTO(id, tenKH.getText(), sdtKH.getText(), diachiKH.getText(), emailKH.getText(), currenTime, 0));
        jpKH.loadDataTable(jpKH.khachhangBUS.getAll());
        if (flag) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    } else if (e.getSource() == btnHuyBo) {
        // Hủy bỏ
        dispose();
    } else if (e.getSource() == btnCapNhat && Validation()) {
        // Kiểm tra email trước khi cập nhật thông tin khách hàng
        if (Validation.isEmpty(emailKH.getText()) || !Validation.isEmail(emailKH.getText())) {
            JOptionPane.showMessageDialog(this, "Email không được rỗng và phải đúng định dạng!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Cập nhật thông tin khách hàng
        boolean flag = jpKH.khachhangBUS.update(new KhachHangDTO(kh.getMKH(), tenKH.getText(), sdtKH.getText(), diachiKH.getText(), emailKH.getText(), kh.getNGAYTHAMGIA(), kh.getDIEMTICHLUY()));
        if (flag) {
            JOptionPane.showMessageDialog(this, "Sửa khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Sửa khách hàng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }

        jpKH.loadDataTable(jpKH.khachhangBUS.getAll());
        dispose();
    }
}


    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
