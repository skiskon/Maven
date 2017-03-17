package Sem2.ClassWork.InputOutputFromWeb;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://lib.ru");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try(InputStream stream = connection.getInputStream())
        {
            String ct = connection.getHeaderField("Content-Type");
            MimeType mt = new MimeType(ct);
            String cs = mt.getParameter("charset");

            ByteArrayOutputStream str = new ByteArrayOutputStream();
            while (true)
            {
                int c = stream.read();
                if (c < 0) break;
                str.write(c);
            }
            System.out.println(str.toString(cs));

        } catch (MimeTypeParseException e) {
            e.printStackTrace();
        }
        connection.disconnect();
    }
}