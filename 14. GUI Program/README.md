# GUI Program

Pada kesempatan kali ini, dinerikan latihan untuk implementasi code untuk membuat frame windows user login dan password, lalu dilanjutkan aplikasi image viewer

## Frame Windows User Login dan Password

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel lblUser, lblPass;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginFrame() {
        // Judul window
        setTitle("Login User");

        // Ukuran window
        setSize(300, 200);

        // Program berhenti saat window ditutup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout
        setLayout(new GridLayout(3, 2, 10, 10));

        // Inisialisasi komponen
        lblUser = new JLabel("Username:");
        lblPass = new JLabel("Password:");

        txtUser = new JTextField();
        txtPass = new JPasswordField();

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        // Menambahkan komponen 
        add(lblUser);
        add(txtUser);

        add(lblPass);
        add(txtPass);

        add(new JLabel("")); 
        add(btnLogin);

        // Tampilkan window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        // Login 
        if(username.equals("admin") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this,
                    "Login berhasil!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username atau password salah!",
                    "Gagal",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
```
Ketika user salah memasukkan username dan password :

<img width="858" height="293" alt="image" src="https://github.com/user-attachments/assets/f4b5ca40-e04a-42e5-8b64-5e8b47b65e0d" />

Ketika User sesuai memasukkan username dan password :

<img width="812" height="298" alt="image" src="https://github.com/user-attachments/assets/be7c6f4f-58f9-416e-9381-a75b42547789" />


## Image Viewer

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ImageViewer extends JFrame implements ActionListener {

    private JLabel imageLabel;
    private JButton btnOpen, btnZoomIn, btnZoomOut;
    private JFileChooser fileChooser;

    private Image originalImage;
    private double scale = 1.0;

    public ImageViewer() {
        setTitle("Image Viewer");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Label gambar
        imageLabel = new JLabel("No Image", JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        // Tombol
        btnOpen = new JButton("Open");
        btnZoomIn = new JButton("Zoom In");
        btnZoomOut = new JButton("Zoom Out");

        btnOpen.addActionListener(this);
        btnZoomIn.addActionListener(this);
        btnZoomOut.addActionListener(this);

        // Panel tombol
        JPanel topPanel = new JPanel();
        topPanel.add(btnOpen);
        topPanel.add(btnZoomIn);
        topPanel.add(btnZoomOut);

        // File chooser
        fileChooser = new JFileChooser();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOpen) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());

                originalImage = icon.getImage();
                scale = 1.0;

                updateImage();
            }
        }

        else if (e.getSource() == btnZoomIn && originalImage != null) {
            scale += 0.1;
            updateImage();
        }

        else if (e.getSource() == btnZoomOut && originalImage != null) {
            scale -= 0.1;
            if (scale < 0.1) scale = 0.1;
            updateImage();
        }
    }

    private void updateImage() {
        int width = (int)(originalImage.getWidth(null) * scale);
        int height = (int)(originalImage.getHeight(null) * scale);

        Image scaledImage = originalImage.getScaledInstance(
                width, height, Image.SCALE_SMOOTH);

        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setText("");
    }
}
```

Menu saat memilih foto :

<img width="858" height="612" alt="Screenshot 2025-12-17 093620" src="https://github.com/user-attachments/assets/94e27f1e-df92-470b-b1f8-6ffea208b080" />

<br>

Ketika menampilkan foto, terdapat 2 tombol pembantu yaitu, Zoom In dan Zoom Out :

<img width="856" height="606" alt="Screenshot 2025-12-17 093656" src="https://github.com/user-attachments/assets/51edac7c-7f04-4f4e-afcb-3b7662130127" />

<img width="855" height="609" alt="Screenshot 2025-12-17 093704" src="https://github.com/user-attachments/assets/9af44aef-39dc-4c44-91f3-1430ae3e8046" />

