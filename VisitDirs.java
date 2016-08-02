

import java.io.File;

public class VisitDirs {
   public static void main(String[] argv)
   throws Exception {
      System.out.println("遍历目录");
      File dir = new File("D:/毛文强的文件/家客资料"); //要遍历的目录
      visitAllDirsAndFiles(dir);
   }
   public static void visitAllDirsAndFiles(File dir) {
      System.out.println(dir);
      if (dir.isDirectory()) {
         String[] children = dir.list();
         for (int i = 0; i < children.length; i++) {
            visitAllDirsAndFiles
            (new File(dir, children[i]));
         }
      }
   }
}