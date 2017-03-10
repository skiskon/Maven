package Sem2.ClassWork.File;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("C:\\Qt\\Tools\\QtCreator\\bin");

        PathInfo info = PathInfo.scan(dir);

        Print.print(info,"");
    }
}
