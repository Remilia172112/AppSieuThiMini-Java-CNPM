package GUI.Component;

import DAO.ChiTietQuyenDAO;
import DAO.NhanVienDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhanVienDTO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.login_page;
import GUI.Panel.KhachHang;
import GUI.Panel.MaKhuyenMai;
import GUI.Panel.NhaCungCap;
import GUI.Panel.NhanVien;
import GUI.Panel.PhanQuyen;
import GUI.Panel.PhieuKiemKe;
import GUI.Panel.PhieuNhap;
import GUI.Panel.ChucVu;
import GUI.Panel.HoaDon;
import GUI.Panel.SanPham;
import GUI.Panel.TaiKhoan;
import GUI.Panel.ThuocTinhSanPham;
import GUI.Panel.TrangChu;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Dialog.MyAccount;
import GUI.Dialog.BanHang;
import GUI.Panel.ThongKe.ThongKe;
public class MenuTaskbar extends JPanel {

    TrangChu trangChu;
    SanPham sanPham;
    ThuocTinhSanPham thuocTinhSanPham;
    PhieuNhap phieuNhap;
    HoaDon hoaDon;
    KhachHang khachHang;
    NhaCungCap nhacungcap;
    NhanVien nhanVien;
    ChucVu chucVu;
    TaiKhoan taiKhoan;
    MaKhuyenMai maKhuyenMai;
    PhieuKiemKe phieuKiemKe;
    PhanQuyen phanQuyen;
    ThongKe thongKe;
    BanHang banHang;
    JScrollPane scrollPaneMenuTask ;
    String[][] getSt = {
        {"Trang chủ", "home.svg", "trangchu"},
        {"Sản phẩm", "book.svg", "sanpham"},
        {"Thuộc tính", "brand.svg", "thuoctinh"},
        {"Mã khuyến mãi", "sale.svg", "makhuyenmai"},
        {"Nhân viên", "staff_1.svg", "nhanvien"},
        {"Chức vụ", "position.svg", "chucvu"},
        {"Khách hàng", "customer.svg", "khachhang"},
        {"Nhà cung cấp", "supplier.svg", "nhacungcap"},
        {"Hóa đơn", "export.svg", "hoadon"},
        {"Bán Hàng", "sell.svg", "banhang"},
        {"Phiếu nhập", "import.svg", "nhaphang"},
        {"Phiếu kiểm kê" , "inventory.svg", "kiemke"},
        {"Phân quyền", "protect.svg", "nhomquyen"},
        {"Tài khoản", "account.svg", "taikhoan"},
        {"Thống kê", "statistical_1.svg", "thongke"},
        {"Đăng xuất", "log_out.svg", "dangxuat"},
    };

    Main main;
    TaiKhoanDTO user;
    public ArrayList <itemTaskbar> listitem;
    ArrayList <Integer> check = new ArrayList<>();

    JLabel lblTenNhomQuyen, lblUsername;
    JScrollPane scrollPane;

    //tasbarMenu chia thành 3 phần chính là pnlCenter, pnlTop, pnlBottom
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(193,237,220);
    private ArrayList<ChiTietQuyenDTO> listQuyen;
    NhomQuyenDTO nhomQuyenDTO;
    public NhanVienDTO nhanVienDTO;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    public MenuTaskbar(Main main) {
        this.main = main;
        initComponent();
    }

    public MenuTaskbar(Main main, TaiKhoanDTO tk) {
        this.main = main;
        this.user = tk;
        this.nhomQuyenDTO = NhomQuyenDAO.getInstance().selectById(Integer.toString(tk.getMNQ()));
        this.nhanVienDTO = NhanVienDAO.getInstance().selectById(Integer.toString(tk.getMNV()));
        listQuyen = ChiTietQuyenDAO.getInstance().selectAll(Integer.toString(tk.getMNQ()));
        initComponent();
    }

    private void initComponent() {
        listitem = new ArrayList<itemTaskbar>();
        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));

        // bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BorderLayout(0, 0));
        pnlTop.add(info, BorderLayout.CENTER);

        // Cái info này bỏ vô cho đẹp tí, mai mốt có gì xóa đi đê hiển thị thông tin tài khoản và quyền
        in4(info);

        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 720));
        pnlCenter.setBackground(DefaultColor);
        // pnlCenter.setBorder(new EmptyBorder(0,15,0,35));
        pnlCenter.setLayout(new FlowLayout(0,0,0));

        bar3 = new JPanel();
        bar3.setBackground(new Color(204, 214, 219));
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 10));
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);

        for (int i = 0; i < getSt.length; i++) {
            if (i + 1 == getSt.length) {
                listitem.add(new itemTaskbar(getSt[i][1], getSt[i][0]));
                pnlBottom.add(listitem.get(i));
            } else {
                listitem.add(new itemTaskbar(getSt[i][1], getSt[i][0]));
                if (i != 0) {
                    if (!checkRole(getSt[i][2])) {
                        listitem.get(i).setVisible(false);
                        check.add(i);
                    }
                }
            }
        }

        listitem.get(0).setBackground(HowerBackgroundColor);
        listitem.get(0).setForeground(HowerFontColor);
        listitem.get(0).isSelected = true;

        for (int i = 0; i < getSt.length; i++) {
            listitem.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }

        listitem.get(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                trangChu = new TrangChu();
                main.setPanel(trangChu);
            }
        });

        listitem.get(1).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                sanPham = new SanPham(main);
                main.setPanel(sanPham);

            }
        });

        listitem.get(2).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                thuocTinhSanPham = new ThuocTinhSanPham(main);
                main.setPanel(thuocTinhSanPham);

            }
        });

        listitem.get(3).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                maKhuyenMai = new MaKhuyenMai(main, nhanVienDTO);
                main.setPanel(maKhuyenMai);
            }
        });
        listitem.get(4).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhanVien = new NhanVien(main);
                main.setPanel(nhanVien);
            }
        });
        listitem.get(5).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                chucVu = new ChucVu(main);
                main.setPanel(chucVu);
            }
        });
        listitem.get(6).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                khachHang = new KhachHang(main);
                main.setPanel(khachHang);
            }
        });
        listitem.get(7).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhacungcap = new NhaCungCap(main);
                main.setPanel(nhacungcap);
            }
        });
        listitem.get(8).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                hoaDon = new HoaDon(main, user);
                main.setPanel(hoaDon);
            }
        });
        listitem.get(9).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
//                trangChu = new TrangChu();
               banHang = new BanHang(user, "create");
                main.setPanel(banHang);
            }
        });
        listitem.get(10).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuNhap = new PhieuNhap(main, nhanVienDTO);
                main.setPanel(phieuNhap);
            }
        });
        
        listitem.get(11).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuKiemKe = new PhieuKiemKe(main , nhanVienDTO);
                main.setPanel(phieuKiemKe);
            }
        });
        listitem.get(12).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phanQuyen = new PhanQuyen(main);
                main.setPanel(phanQuyen);
            }
        });
        listitem.get(13).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                taiKhoan = new TaiKhoan(main);
                main.setPanel(taiKhoan);
            }
        });
        listitem.get(14).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                thongKe = new ThongKe();
                main.setPanel(thongKe);
            }
        });
        listitem.get(15).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {

                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    login_page login = new login_page();
                    main.dispose();
                    login.setVisible(true);
                }
            }
        });
        int count = 0;
        for (Integer i : check) listitem.remove((int) i - count++);
        if(listitem.size() - 1 <= 12) pnlCenter.setPreferredSize(new Dimension(230, listitem.size() - 1));
        for (int i = 0; i < listitem.size() - 1; i++) pnlCenter.add(listitem.get(i));
    }

    public boolean checkRole(String s) {
        boolean check = false;
        for (int i = 0; i < listQuyen.size(); i++) {
            if (listQuyen.get(i).getHanhdong().equals("view")) {
                if (s.equals(listQuyen.get(i).getMachucnang())) {
                    check = true;
                    return check;
                }
            }
        }
        return check;
    }

    public void pnlMenuTaskbarMousePress(MouseEvent evt) {

        for (int i = 0; i < listitem.size(); i++) {
            if (evt.getSource() == listitem.get(i)) {
                listitem.get(i).isSelected = true;
                listitem.get(i).setBackground(HowerBackgroundColor);
                listitem.get(i).setForeground(HowerFontColor);
            } else {
                listitem.get(i).isSelected = false;
                listitem.get(i).setBackground(DefaultColor);
                listitem.get(i).setForeground(FontColor);
            }
        }
    }
    public void resetChange(){
        this.nhanVienDTO = new NhanVienDAO().selectById(String.valueOf(nhanVienDTO.getMNV()));
    }
    public void in4(JPanel info) {
        JPanel pnlIcon = new JPanel(new FlowLayout());
        pnlIcon.setPreferredSize(new Dimension(60, 0));
        pnlIcon.setOpaque(false);
        info.add(pnlIcon, BorderLayout.WEST);
        JLabel lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(50, 70));
        if (nhanVienDTO.getGIOITINH() == 1) {
            lblIcon.setIcon(new FlatSVGIcon("./icon/man_50px.svg"));
        } else {
            lblIcon.setIcon(new FlatSVGIcon("./icon/women_50px.svg"));
        }

        pnlIcon.add(lblIcon);

        JPanel pnlInfo = new JPanel();
        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new FlowLayout(0, 10, 5));
        pnlInfo.setBorder(new EmptyBorder(15, 0, 0, 0));
        info.add(pnlInfo, BorderLayout.CENTER);
        lblUsername = new JLabel(nhanVienDTO.getHOTEN());
        lblUsername.putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
        pnlInfo.add(lblUsername);

        lblTenNhomQuyen = new JLabel(nhomQuyenDTO.getTennhomquyen());
        lblTenNhomQuyen.putClientProperty("FlatLaf.style", "font: 120% $light.font");
        lblTenNhomQuyen.setForeground(Color.GRAY);
        pnlInfo.add(lblTenNhomQuyen);

        lblIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new MyAccount(owner, MenuTaskbar.this, "Chỉnh sửa thông tin tài khoản", true);
            }
        });
    }
}
