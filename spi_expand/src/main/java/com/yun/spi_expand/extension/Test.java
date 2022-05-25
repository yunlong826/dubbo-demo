package com.yun.spi_expand.extension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/25 15:47
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Class<TestInterface> testInterfaceClass = TestInterface.class;
        SPI annotation = testInterfaceClass.getAnnotation(SPI.class);
        System.out.println(testInterfaceClass.getName());
//        ClassLoader classLoader = Test.class.getClassLoader();
        Enumeration<URL> urls;
        String fileName = "META-INF/yun/"+testInterfaceClass.getName();
        urls = ClassLoader.getSystemResources(fileName);
        while(urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            String line;
            while((line = reader.readLine())!=null) {
                System.out.println(line);
                final int ci = line.indexOf('#');
            }
        }
        System.out.println(ClassLoader.getSystemClassLoader());
        if(annotation == null)
            System.out.println("没有加spi注解");
        else
            System.out.println("加了spi注解");
    }
}
