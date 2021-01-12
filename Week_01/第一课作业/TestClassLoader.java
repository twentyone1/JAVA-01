package com.company;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassLoader extends ClassLoader {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<?> helloClass = new TestClassLoader().findClass("Hello");
        Method hello = helloClass.getDeclaredMethod("hello");
        hello.invoke(helloClass.newInstance());

    }
    public static byte[] readFile(String fileName)
    {
        File file = new File(fileName);
        InputStream in = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            in = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int byteValue;
            while ((byteValue = in.read()) != -1) {
                outputStream.write(255 - byteValue);
            }
            return outputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes  = readFile("/Users/ganshilin/Downloads/Hello/Hello.xlass");
        return defineClass(name,bytes, 0, bytes.length);
    }

}
