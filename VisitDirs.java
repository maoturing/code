

import java.io.File;

public class VisitDirs {
   public static void main(String[] argv)
   throws Exception {
      System.out.println("����Ŀ¼");
      File dir = new File("D:/ë��ǿ���ļ�/�ҿ�����"); //Ҫ������Ŀ¼
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