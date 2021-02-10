package jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HelloClassLoader1 extends ClassLoader {

    public static void main(String[] args) throws Throwable {
        try {
            Object obj = new HelloClassLoader1().findClass(null).newInstance();

            obj.getClass().getDeclaredMethod("hello").invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) {
        byte[] classData = getClassBytes();
        //如果不知道名字，可以传null
        return defineClass(null, classData, 0, classData.length);
    }

    private byte[] getClassBytes() {
        FileInputStream is = null;
        try {
            String path = this.getClass().getResource("./Hello.class").getPath();
            File file = new File(path);
            byte[] buff = new byte[1024 * 4];
            int len = -1;
            is = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
