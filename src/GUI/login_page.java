package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Dialog.QuenMatKhau;
import javax.swing.border.EmptyBorder;
import GUI.Component.InputForm;
import helper.BCrypt;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;


public class login_page extends JFrame implements KeyListener{

    private JPanel login_nhap;
    private JLabel lb1 , lb2, lb_img_1;
    private InputForm txtUsername, txtPassword;
    private JButton bt;

    public login_page() {
        init();
        txtUsername.setText("admin");
        txtPassword.setPass("123456");
        this.setVisible(true);
    }

    private void init() { 
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);


        this.setTitle("Đăng nhập" );
        this.setSize(new Dimension(900 , 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0 , 0));
        JFrame jf = this ;

        imgIntro();
        
        login_nhap = new JPanel();
        login_nhap.setBackground(Color.WHITE);
        login_nhap.setLayout(new FlowLayout(1 , 0 , 10 ));
        login_nhap.setBorder(new EmptyBorder(20,0,0,0));
        login_nhap.setPreferredSize(new Dimension(490 , 500));
        
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel lb1 = new JLabel("<html><p style='font-size: 28px; font-weight: bold; font-family: Tahoma; color: #239F82;'>Đăng nhập</p><p style='font-size: 10px; font-family: Tahoma; margin-left: 20px;'>Xin chào, hãy bắt đầu!</p></html>");
        // lb1.setFont(new Font("Tahoma", Font.BOLD , 20));
        // lb1.setForeground(Color.BLACK); // Set text color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Center horizontally
        // lb1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text horizontally
        login_nhap.add(lb1);
        
        JPanel paneldn = new JPanel();
        paneldn.setBackground(Color.BLACK);
        paneldn.setPreferredSize(new Dimension(400, 200));
        paneldn.setLayout(new GridLayout(2, 1));
    
        txtUsername = new InputForm("Tên đăng nhập");
        // txtUsername.setPreferredSize(new Dimension(400, 200)); // Adjust dimensions
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add borde
        // gbc.gridx++;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        paneldn.add(txtUsername);

        // login_nhap.add(txtUsername);
        txtPassword = new InputForm(" Mật khẩu", "password");
        txtPassword.setPreferredSize(new Dimension(300, 40)); // Adjust dimensions
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add border
        gbc.gridy++;
        gbc.gridx = 0;
        paneldn.add(txtPassword);

        // login_nhap.add(txtPassword);
        txtUsername.getTxtForm().addKeyListener(this);
        txtPassword.getTxtPass().addKeyListener(this);
        
        login_nhap.add(paneldn);

        lb2 = new JLabel("<html><u><i style='font-size: 12px;'>Quên mật khẩu ?</i></u></html>", JLabel.LEFT);
        lb2.setPreferredSize(new Dimension(200,50));
        lb2.setForeground(Color.BLACK); // Set text color
        lb2.addMouseListener(new MouseAdapter() {
            @Override   
            public void mouseEntered(MouseEvent e) {
                lb2.setForeground(new Color(0, 202,232));
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                QuenMatKhau qmk = new QuenMatKhau(jf, true);
                qmk.setVisible(true);
            }
            public void mouseExited(MouseEvent e) {
                lb2.setForeground(Color.BLACK);
            }
        });

        login_nhap.add(lb2);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setBackground(Color.WHITE);   
        bt = new JButton("Đăng nhập");
        bt.setPreferredSize(new Dimension(300, 40));
        bt.setLayout(new FlowLayout(1, 0, 15));
        bt.setBackground(Color.BLACK);
        bt.setFont(new Font("Tahoma", Font.BOLD, 16)); // Adjust font and size
        bt.setForeground(Color.WHITE);
        buttonPanel.add(bt);
        
        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bt.setBackground(new Color(0, 202,232));
            }
            public void mousePressed(MouseEvent evt) {
                try {
                    checkLogin();
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            public void mouseExited(MouseEvent e) {
                bt.setBackground(Color.BLACK);
            }
        });

        login_nhap.add(buttonPanel); 
        this.add(login_nhap , BorderLayout.EAST);
    }
        public void checkLogin() throws UnsupportedLookAndFeelException {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getPass();
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        } else {
            TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectByUser(usernameCheck);
            if (tk == null) {
                JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (tk.getTT() == 0) {
                    JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (BCrypt.checkpw(passwordCheck, tk.getMK())) {
                        try {
                            this.dispose();
                            Main main = new Main(tk);
                            main.setVisible(true);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }
    

    // public static void main(String[] args) {
        // FlatRobotoFont.install();
        // FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        // FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        // FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        // FlatIntelliJLaf.registerCustomDefaultsSource("style");
        // FlatIntelliJLaf.setup();

        // UIManager.put("PasswordField.showRevealButton", true);
        // new login_page();
    // }

    public void imgIntro() {
        JPanel bo = new JPanel();
        bo.setPreferredSize(new Dimension(410, 500));
        bo.setBackground(Color.white);
        bo.setLayout(new BorderLayout());
        
        ImageIcon iconOrgin = new ImageIcon("./src/img/1.png");
        Image imageOrgin = iconOrgin.getImage();

        int width = bo.getPreferredSize().width;
        int height = bo.getPreferredSize().height;
        Image scaleImage = imageOrgin.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaleImage);
        JLabel lb_img_1 = new JLabel(scaledIcon);
        bo.add(lb_img_1, BorderLayout.CENTER);

        this.add(bo, BorderLayout.WEST);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                checkLogin();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        login_page login = new login_page();
        login.setVisible(true);
    }
}