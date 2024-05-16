package DACS1_UngDungNhanTin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ChatServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started, listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ChatServerHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ChatServerHandler extends Thread {
        private final Socket clientSocket;

        public ChatServerHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                // Xử lý tin nhắn từ client
                String sender = // lấy thông tin từ client
                String receiver = // lấy thông tin từ client
                String message = // lấy thông tin từ client

                // Lưu tin nhắn vào database
                Connection conn = Database.getConnection();
                String sql = "INSERT INTO messages (sender, receiver, message, timestamp) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, sender);
                statement.setString(2, receiver);
                statement.setString(3, message);
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                statement.executeUpdate();
                statement.close();
                conn.close();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
