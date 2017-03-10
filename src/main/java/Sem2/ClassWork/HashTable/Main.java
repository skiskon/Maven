package Sem2.ClassWork.HashTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    /*
    public static void main(String[] args) {
        MyHashTable table = new MyHashTable(100);
        table.put("table", "стол");
        String r = table.getK("table");
        System.out.println(r);
    }*/
    static long run(int n) {
        int c = 10000;
        long t0=System.currentTimeMillis();
        MyHashTable t = new MyHashTable(n);
        for (int i = 0; i < c; i++){
            t.put("table"+i,"стол"+i);
        }
        for (int i = 0; i < 100;i++){
            for (int j = 0; j < c; j++ ){
                t.getK(("table"+j));
            }
        }
        long t1 = System.currentTimeMillis();
        return  t1-t0;
    }

    public static void main(String[] args) throws IOException {
        XYSeries s = new XYSeries("1");
        int []ns = {100,500,750,1000};
        for (int n : ns){
            long t = run(n);
            s.add(n,t);
        }
        XYSeriesCollection c = new XYSeriesCollection(s);
        JFreeChart ch = ChartFactory.createXYLineChart("Hach","n","T",c);
        BufferedImage image = ch.createBufferedImage(800,600);
        File file = new File("chart.png");
        ImageIO.write(image,"png",file);
    }

}
