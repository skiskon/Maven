package Sem2.ClassWork.vbn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * Created by admin on 07.04.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(10));
        AsynchronousServerSocketChannel ass = AsynchronousServerSocketChannel.open(group);
        ass.bind(new InetSocketAddress(80));
        ass.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
               ass.accept(null,this);
               Request.readRequest(result);
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });
        //Thread.sleep(60000); если засунуть эту строчку в бесконечный цикл, то сервер будет спать
        //таким образом он ухадит в сон на вечно
        Object x = new Object();
        synchronized (x){
            x.wait();
        }
    }


}
