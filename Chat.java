package DACS1_UngDungNhanTin;

import java.awt.*;
import javax.swing.*;

public class Chat {

	private JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		Chat window = new Chat();
		window.frame.setVisible(true);

	}

	public Chat() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1015, 611);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/DACS1_UngDungNhanTin/Img/nt.jpg"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setContentPane(backgroundLabel);

		JButton btnThot = new JButton("Đăng xuất");
		btnThot.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThot.setBounds(10, 13, 139, 33);
		frame.getContentPane().add(btnThot);
		
		JLabel lblNewLabel = new JLabel(" Message");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 47));
		lblNewLabel.setBounds(397, 10, 237, 57);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(216, 514, 542, 50);
		frame.getContentPane().add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 27));
		textField.setColumns(1);

		JButton btnNewButton = new JButton("Gửi");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 27));
		btnNewButton.setBounds(754, 514, 237, 50);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 76, 217, 488);
		frame.getContentPane().add(scrollPane);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(216, 76, 775, 439);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
	}

}
