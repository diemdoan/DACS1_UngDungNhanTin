package DACS1_UngDungNhanTin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Chat {

	JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		Chat window = new Chat(0);
		window.frame.setVisible(true);

	}

	public Chat(int userID) {
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
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login();
			}
		});

		JLabel lblNewLabel = new JLabel(" Message");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 47));
		lblNewLabel.setBounds(397, 10, 237, 57);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(769, 10, 50, 50);
		frame.getContentPane().add(lblNewLabel_3);
		ImageIcon icon = new ImageIcon(getClass().getResource("/DACS1_UngDungNhanTin/Img/user.png"));
		Image image = icon.getImage();
		int labelWidth = lblNewLabel_3.getWidth();
		int labelHeight = lblNewLabel_3.getHeight();
		Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblNewLabel_3.setIcon(scaledIcon);

		JTextField textField_ten = new JTextField();
		textField_ten.setBounds(827, 10, 139, 27);
		textField_ten.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(textField_ten);
		textField_ten.setColumns(10);

		JTextField textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(827, 44, 63, 27);
		textField_id.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(textField_id);

		try {
			Connection conn = Database.getConnection();

			String sql = "SELECT * FROM account WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userID);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String acc = resultSet.getString("accName");
				textField_ten.setText(acc);
				String id = resultSet.getString("id");
				textField_id.setText(id);
			}
			resultSet.close();
			statement.close();
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		textField = new JTextField();
		textField.setBounds(216, 514, 542, 50);
		frame.getContentPane().add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 27));
		textField.setColumns(1);

		JButton btnNewButton = new JButton("Gửi");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 27));
		btnNewButton.setBounds(754, 514, 237, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nhắn");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(297, 36, 85, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JTextField textField_ngNhan = new JTextField();
		textField_ngNhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_ngNhan.setBounds(166, 36, 121, 27);
		frame.getContentPane().add(textField_ngNhan);
		textField_ngNhan.setColumns(10);

		JScrollPane scrollPane_tk = new JScrollPane();
		scrollPane_tk.setBounds(0, 76, 217, 488);
		frame.getContentPane().add(scrollPane_tk);
		scrollPane_tk.setOpaque(false);
		scrollPane_tk.getViewport().setOpaque(false);

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);
		panel.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));

		try {
			Connection conn = Database.getConnection();
			String sql = "SELECT accName FROM account";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String accountName = resultSet.getString("accName");
				if (!accountName.equals(textField_ten.getText())) {
					JButton button = new JButton(accountName);
					Dimension buttonSize = new Dimension(210, 57);
					button.setPreferredSize(buttonSize);
					button.setMaximumSize(buttonSize);
					button.setMinimumSize(buttonSize);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JButton clickedButton = (JButton) e.getSource();
							textField_ngNhan.setText(clickedButton.getText());
						}
					});
					panel.add(button);
				}
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		scrollPane_tk.setViewportView(panel);
		frame.getContentPane().add(textField_ngNhan);
		
		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(216, 76, 775, 439);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);

	}
}