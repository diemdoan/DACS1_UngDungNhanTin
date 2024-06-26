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

				insertAccount(id, fullName, accName, pass);

				JOptionPane.showMessageDialog(frame, "Đăng kí tài khoản mới thành công!");

				Login lFrame = new Login();

				frame.dispose();

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

	private void insertAccount(String id, String fullName, String username, String password) {
		try {
			Connection conn = Database.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO account (id, fullName, accName, pass) VALUES (?, ?, ?, ?)");
			stmt.setString(1, id);
			stmt.setString(2, fullName);
			stmt.setString(3, username);
			stmt.setString(4, password);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
