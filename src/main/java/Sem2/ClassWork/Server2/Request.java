package Sem2.ClassWork.Server2;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class Request {
    static void readRequest(AsynchronousSocketChannel s) {
        ByteBuffer buf = ByteBuffer.allocate(10240);
        StringBuilder request = new StringBuilder();
        s.read(buf, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                buf.flip();
                byte[] data = new byte[buf.remaining()];
                buf.get(data);
                request.append(new String(data));
                int len = request.length();
                if (len >=4 & request.substring(len-4).equals("\r\n\r\n")){
                    // \r\n означает конец строки и если идет эта комбинация два раза подряд, \тол значит что одна строка кончилась и сразу кончилась дргаяю это проверка на пустую строку
                    System.out.println(request);
                    Response.sendResponse(s);
                } else{
                    buf.clear();
                    s.read(buf,null,this);
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });

    }


}
