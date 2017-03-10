package Sem2.Labs.Lab1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String,Integer> hashMap = new HashMap<>();

        try (Scanner scan = new Scanner( new File("book1.txt"), "Cp1251")) {
            scan.useDelimiter("[ ,.;\\-\n\r:]");
           while ( scan.hasNext()){
               String word = scan.next();
               if (word.length()<1)
                    continue;
               if (hashMap.containsKey(word)) {
                   Integer count = hashMap.get(word);
                   hashMap.put(word,count + 1);

               } else {
                   hashMap.put(word, 1);
               }
           }
        }
        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(entrySet);

        list.sort((e1,e2)-> -e1.getValue().compareTo((e2.getValue())));

        //System.out.println(hashMap.toString().replace(' ', '\n'));

       /* for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry);
        }*/
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry);
        }
    }
}

