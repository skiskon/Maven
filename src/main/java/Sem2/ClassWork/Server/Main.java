package Sem2.ClassWork.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static Sem2.ClassWork.Server.Handle.handle;

// после запуска в браузере ввести http://localhost

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(80);
        while (true) {
            Socket s = ss.accept();
            Runnable action = new Runnable() {
                public void run() {
                    handle(s);
                }
            };
            new Thread(action).start();

        }
    }
}
