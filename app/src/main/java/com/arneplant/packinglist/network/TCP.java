package com.arneplant.packinglist.network;

import java.io.OutputStream;
import java.net.Socket;

public class TCP {
    public static void send(String msg, String ip) throws Exception {
        Socket socket = null;
        OutputStream oos = null;

        socket = new Socket(ip, 6664);
        oos = socket.getOutputStream();
        oos.write(msg.getBytes());
        oos.flush();

        oos.close();
        socket.close();
    }
}
