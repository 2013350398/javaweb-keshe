package main.Util;


import java.io.*;

public class LoadEvid {
    public static String uploadEvid(String localPath) {
        String serverPath = "";

        try {
            File source = new File(localPath);
            FileInputStream fis = new FileInputStream(source);

            File dfile = new File("./evidence" + source.getName());
            if(!dfile.exists())
                dfile.createNewFile();

            FileOutputStream fos = new FileOutputStream(dfile);
            byte[] b = new byte[1024];
            int len;
            while((len = fis.read(b)) != -1)
                fos.write(b, 0, len);

            serverPath = dfile.getPath();
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serverPath;
    }

    public static String downloadEvid(String serverPath) {
        try {
            File source = new File(serverPath);
            FileInputStream fis = new FileInputStream(source);

            File dfile = new File("C://Download//" + source.getName());
            if(!dfile.exists())
                dfile.createNewFile();

            FileOutputStream fos = new FileOutputStream(dfile);
            byte[] b = new byte[1024];
            int len;
            while((len = fis.read(b)) != -1)
                fos.write(b, 0, len);

            serverPath = dfile.getPath();
            System.out.println("下载完成!");
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return serverPath;
    }
}