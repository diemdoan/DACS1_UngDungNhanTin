package DACS1_UngDungNhanTin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login {

	private JFrame lFrame;
	private JTextField textField;
	private JPasswordField textField_pass;
	private int userID;

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(textField_pass.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(lFrame, "Vui lòng điền đầy đủ thông tin!");
				} else {
					try {
						Connection connection = Database.getConnection();

						String sql = "SELECT * FROM account WHERE accName = ? AND pass = ?";
						PreparedStatement statement = connection.prepareStatement(sql);
						statement.setString(1, username);
						statement.setString(2, password);

						ResultSet resultSet = statement.executeQuery();

						if (resultSet.next()) {
							userID = resultSet.getInt("id");
							showChat();
							lFrame.dispose();
						} else {
							JOptionPane.showMessageDialog(lFrame, "Thông tin đăng nhập không chính xác!");
						}

						resultSet.close();
						statement.close();
						connection.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		JButton btnngK = new JButton("Đăng kí ");
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnngK.setBounds(508, 448, 162, 48);
		lFrame.getContentPane().add(btnngK);
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lFrame.dispose();
				new Signup();
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
	}

	private void showChat() {
		Chat newChat = new Chat(userID);
		newChat.frame.setVisible(true);
	}
}
