package Sem2.Labs.Lab2;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Download {
    public static void download(URI link, HashSet<URI> visited) {
        if (visited.contains(link) || visited.size() >= 16)
            return;
        visited.add(link);
        System.out.println(link);
        try {
            URL url = link.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String html;
            try (InputStream is = conn.getInputStream()) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while (true) {
                    int c = is.read();
                    if (c < 0) break;
                    bos.write(c);
                }
                html = (bos.toString("CP1251"));
            }
            conn.disconnect();
            Pattern p = Pattern.compile("href\\s*=\\s*([^\\s>]+|'[^']*'|\"[^\"]*\")");
            Matcher matcher = p.matcher(html);
            while (matcher.find()) {
                String href = matcher.group(1);
                if (href.startsWith("'") || href.startsWith("\"")) {
                    href = href.substring(1, href.length() - 1);
                }
                URI child = link.resolve(href);
                //download(child, visited);
                Runnable action = () -> download(child, visited);
                new Thread (action).start();
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
