package Sem2.ClassWork.vbn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by admin on 07.04.2017.
 */
public class Response {
    static void sendResponse(AsynchronousSocketChannel s) {
        ByteBuffer buf = ByteBuffer.wrap("Hello".getBytes());
        s.write(buf, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                try{
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });
    }
}
