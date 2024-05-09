package DACS1_UngDungNhanTin;

import java.awt.*;
import javax.swing.*;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		Login window = new Login();
		window.frame.setVisible(true);
	}

	public Login() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/DACS1_UngDungNhanTin/Img/bg.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(backgroundLabel);

		JLabel lblNewLabel = new JLabel(" Message");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 49));
		lblNewLabel.setBounds(389, 10, 237, 66);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 27));
		textField.setBounds(447, 138, 415, 52);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_1.setBounds(167, 136, 211, 52);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_1_1.setBounds(167, 225, 211, 52);
		frame.getContentPane().add(lblNewLabel_1_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		textField_1.setColumns(10);
		textField_1.setBounds(447, 227, 415, 52);
		frame.getContentPane().add(textField_1);

		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnNewButton.setBounds(389, 325, 188, 45);
		frame.getContentPane().add(btnNewButton);

		JButton btnngK = new JButton("Đăng kí ");
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnngK.setBounds(508, 448, 162, 48);
		frame.getContentPane().add(btnngK);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Đăng kí tài khoản mới");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblNewLabel_1_1_1_1.setBounds(179, 448, 314, 52);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		JButton btnThot = new JButton("Thoát");
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnThot.setBounds(793, 559, 162, 33);
		frame.getContentPane().add(btnThot);
	}
}
