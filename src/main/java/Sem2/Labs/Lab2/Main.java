package Sem2.Labs.Lab2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashSet;


public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Download.download (new URI("https://www.mirea.ru/"), new HashSet<>());

    }


}
