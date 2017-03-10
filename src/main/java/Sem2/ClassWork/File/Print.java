package Sem2.ClassWork.File;


public class Print {
    public static void print(PathInfo pi, String level){
        System.out.println(level+pi.path.getFileName()+" "+pi.size);
        for (PathInfo child:pi.children){
            print(child, level+"--");
        }
    }
}
