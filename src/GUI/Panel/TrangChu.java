package GUI.Panel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Component.PanelShadow;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class TrangChu extends JPanel {

    JPanel top, center, bar1, bar2;
    PanelShadow content[];
    JPanel info[];
    JLabel title, subTit, infoDetail[], objDetail[], objDetail1[], infoIcon[];
        String[][] getSt = {
        {"Tính <br><br>tiện <br><br>lợi", "convenient_100px.svg", "<html>Ứng dụng cung cấp tính năng tìm kiếm nhanh các sản phẩm <br><br>dựa trên tên, danh mục, hoặc mã sản phẩm (SKU), giúp người <br><br>dùng dễ dàng xác định vị trí hàng hóa trong siêu thị hoặc kiểm <br><br>tra tình trạng còn hàng.</html>"},
        {"Tính <br><br>bảo <br><br>mật", "secure_100px.svg", "<html>Thông tin cá nhân và lịch sử mua sắm của khách hàng được <br><br>bảo mật tuyệt đối, chỉ có người dùng và hệ thống quản lý truy <br><br>cập, đảm bảo quyền riêng tư.</html>"},
        {"Tính <br><br>hiệu <br><br>quả", "effective_100px.svg", "<html>Tích hợp mã QR và công nghệ nhận diện sản phẩm giúp tăng <br><br>tốc độ thanh toán và giảm thời gian chờ, đồng thời cải thiện <br><br>quy trình quản lý kho và kiểm kê hàng hóa.</html>"},
    };
    Color MainColor = new Color(255,255,255);
    Color FontColor = new Color(96, 125, 139);
    Color BackgroundColor = new Color(0xC4E9B8);
    Color HowerFontColor = new Color(225, 230, 232);

    private void initComponent() {
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        top = new JPanel();
        top.setBackground(MainColor);
        top.setPreferredSize(new Dimension(1200, 230));
        top.setLayout(new FlowLayout(1, 0, 10));

        // JLabel slogan = new JLabel();
        // slogan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sloganMarket.png")));
        ImageIcon iconOrgin = new ImageIcon("./src/img/sloganMarket.png");
        Image imageOrgin = iconOrgin.getImage();

        int width = top.getPreferredSize().width;
        int height = top.getPreferredSize().height;
        Image scaleImage = imageOrgin.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaleImage);
        JLabel slogan = new JLabel(scaledIcon);
        top.add(slogan, BorderLayout.CENTER);

        this.add(top, BorderLayout.NORTH);

        center = new JPanel();
        center.setBackground(BackgroundColor);
        center.setPreferredSize(new Dimension(1100, 800));
        center.setLayout(new GridLayout(3 , 1 ,0,20));
        center.setBorder(new EmptyBorder(30,110,30,220));


        content = new PanelShadow[getSt.length];
        info = new JPanel[3];
        infoDetail = new JLabel[3];
        objDetail = new JLabel[3];
        objDetail1 = new JLabel[3];


        for (int i = 0; i < getSt.length; i++) {
            content[i] = new PanelShadow();
            content[i] = new PanelShadow(getSt[i][1], getSt[i][0], getSt[i][2]);

            center.add(content[i]);

        }

        this.add(center, BorderLayout.CENTER);

    }

    public TrangChu() {
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }


}
