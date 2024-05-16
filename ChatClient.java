package DACS1_UngDungNhanTin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatClient extends Thread {
    private final String receiver;
    private final String message;

    public ChatClient(String receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 8000)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF(receiver); // Gửi thông tin người nhận
            out.writeUTF(message); // Gửi nội dung tin nhắn
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
