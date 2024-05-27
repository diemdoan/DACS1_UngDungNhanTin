package DACS1_UngDungNhanTin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import javax.swing.*;

public class Signup {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private String host = "localhost";
	private int port = 9999;
	private Socket socket;

	private DataInputStream dis;
	private DataOutputStream dos;

	public static void main(String[] args) {
		Signup window = new Signup();
		window.frame.setVisible(true);

	}

	public Signup() {
		frame = new JFrame();
		frame.setBounds(100, 100, 793, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/DACS1_UngDungNhanTin/Img/bg.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(backgroundLabel);

		JLabel lblNewLabel = new JLabel("Đăng kí tài khoản mới");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(182, 10, 455, 66);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(144, 126, 170, 40);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(144, 176, 170, 40);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Tên đăng nhập:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_2.setBounds(144, 226, 170, 40);
		frame.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Mật khẩu:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_3.setBounds(144, 276, 170, 40);
		frame.getContentPane().add(lblNewLabel_1_3);

		textField = new JTextField();
		textField.setBounds(324, 135, 338, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 27));

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(324, 185, 338, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 27));

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(324, 235, 338, 33);
		frame.getContentPane().add(textField_2);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 27));

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(324, 283, 338, 33);
		frame.getContentPane().add(textField_3);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 27));

		JButton btnNewButton = new JButton("Đăng kí");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(307, 412, 170, 40);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String fullName = textField_1.getText();
				String accName = textField_2.getText();
				String pass = textField_3.getText();

				if (fullName.isEmpty() || id.isEmpty() || accName.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Vui lòng điền đầy đủ thông tin!");
					return;
				}

				try {
					Integer.parseInt(id);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "ID phải là số!");
					return;
				}
				try {
					Connection conn = Database.getConnection();
					String checkDuplicateSql = "SELECT accName FROM account WHERE accName = ?";
					PreparedStatement checkStatement = conn.prepareStatement(checkDuplicateSql);
					checkStatement.setString(1, accName);
					ResultSet resultSet = checkStatement.executeQuery();
					if (resultSet.next()) {
						JOptionPane.showMessageDialog(frame, "Tên tài khoản đã được sử dụng!");
						checkStatement.close();
						conn.close();
						return;
					}
					checkStatement.close();
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					return;
				}
				try {
					Connection conn = Database.getConnection();
					String checkDuplicateSql = "SELECT id FROM account WHERE id = ?";
					PreparedStatement checkStatement = conn.prepareStatement(checkDuplicateSql);
					checkStatement.setString(1, id);
					ResultSet resultSet = checkStatement.executeQuery();
					if (resultSet.next()) {
						JOptionPane.showMessageDialog(frame, "ID đã được sử dụng!");
						checkStatement.close();
						conn.close();
						return;
					}
					checkStatement.close();
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					return;
				}

				String response = Signup(id, fullName, accName, pass);
				if (response.equals("Sign up successful")) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								int confirm = JOptionPane.showConfirmDialog(null, "Đăng kí tài khoản mới thành công!",
										"Sign up successful", JOptionPane.DEFAULT_OPTION);
								Login lFrame = new Login();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					frame.dispose();

				} else {
					btnNewButton.setEnabled(false);
				}
			}

		});
		JButton btnThot = new JButton("Thoát");
		btnThot.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThot.setBounds(10, 13, 101, 33);
		frame.getContentPane().add(btnThot);
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login();
			}
		});
	}

	public String Signup(String id, String fullName, String username, String password) {
		try {
			Connect();

			dos.writeUTF("Sign up");
			dos.writeUTF(id);
			dos.writeUTF(fullName);
			dos.writeUTF(username);
			dos.writeUTF(password);
			dos.flush();

			String response = dis.readUTF();
			return response;

		} catch (IOException e) {
			e.printStackTrace();
			return "Network error: Sign up fail";
		}
	}

	/**
	 * Kết nối đến server
	 */
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

}
