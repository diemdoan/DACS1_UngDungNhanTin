package DACS1_UngDungNhanTin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import javax.swing.*;

import java.io.*;

public class Login {

	private JFrame lFrame;
	private JTextField textField;
	private JPasswordField textField_pass;

	private String host = "localhost";
	private int port = 9999;
	private Socket socket;

	private DataInputStream dis;
	private DataOutputStream dos;

	private String username;

	public static void main(String[] args) {
		Login window = new Login();
		window.lFrame.setVisible(true);
	}

	public Login() {
		lFrame = new JFrame();
		lFrame.setBounds(100, 100, 1000, 650);
		lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lFrame.getContentPane().setLayout(null);
		lFrame.setLocationRelativeTo(null);
		lFrame.setResizable(false);
		lFrame.setVisible(true);

		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/DACS1_UngDungNhanTin/Img/bg.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, lFrame.getWidth(), lFrame.getHeight());
		lFrame.setContentPane(backgroundLabel);

		JLabel lblNewLabel = new JLabel(" Message");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 49));
		lblNewLabel.setBounds(389, 10, 237, 66);
		lFrame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 27));
		textField.setBounds(447, 138, 415, 52);
		lFrame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_1.setBounds(167, 136, 211, 52);
		lFrame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_1_1.setBounds(167, 225, 211, 52);
		lFrame.getContentPane().add(lblNewLabel_1_1);

		textField_pass = new JPasswordField();
		textField_pass.setFont(new Font("Tahoma", Font.PLAIN, 27));
		textField_pass.setColumns(10);
		textField_pass.setBounds(447, 227, 415, 52);
		lFrame.getContentPane().add(textField_pass);

		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnNewButton.setBounds(389, 325, 188, 45);
		lFrame.getContentPane().add(btnNewButton);

		JLabel notification = new JLabel("");
		notification.setForeground(Color.RED);
		notification.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		notification.setBounds(621, 374, 333, 45);
		lFrame.getContentPane().add(notification);

		JButton btnngK = new JButton("Đăng kí ");
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnngK.setBounds(508, 448, 162, 48);
		lFrame.getContentPane().add(btnngK);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String response = Login(textField.getText(), String.copyValueOf(textField_pass.getPassword()));

				
				if (response.equals("Log in successful")) {
					username = textField.getText();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Chat frame = new Chat(username, dis, dos);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					lFrame.dispose();
				} else {
					btnNewButton.setEnabled(false);
					textField_pass.setText("");
					notification.setText(response);
				}
			}
		});
		btnNewButton.setEnabled(false);

		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lFrame.dispose();
				Signup frame = new Signup();
			}
		});

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Đăng kí tài khoản mới");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblNewLabel_1_1_1_1.setBounds(179, 448, 314, 52);
		lFrame.getContentPane().add(lblNewLabel_1_1_1_1);

		JButton btnThot = new JButton("Thoát");
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnThot.setBounds(793, 559, 162, 33);
		lFrame.getContentPane().add(btnThot);
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lFrame.dispose();
			}
		});

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textField.getText().isBlank() || String.copyValueOf(textField_pass.getPassword()).isBlank()) {
					btnNewButton.setEnabled(false);
				} else {
					btnNewButton.setEnabled(true);
				}
			}
		});

		textField_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textField.getText().isBlank() || String.copyValueOf(textField_pass.getPassword()).isBlank()) {
					btnNewButton.setEnabled(false);
				} else {
					btnNewButton.setEnabled(true);
				}
			}
		});

	}

	public String Login(String username, String password) {
		try {
			Connect();

			dos.writeUTF("Log in");
			dos.writeUTF(username);
			dos.writeUTF(password);
			dos.flush();

			String response = dis.readUTF();
			return response;

		} catch (IOException e) {
			e.printStackTrace();
			return "Network error: Log in fail";
		}
	}

	public void Connect() {
		try {
			if (socket != null) {
				socket.close();
			}
			socket = new Socket(host, port);
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getUsername() {
		return this.username;
	}

}
