package com.yun.spi_expand.extension;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/25 15:47
 */
public class Test {
    public int process(){
        return 1;
    }
    public static void main(String[] args) throws IOException {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Test.class);
            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
            for(MethodDescriptor m:methodDescriptors){
                System.out.println(m.getMethod().getName());
                if(m.getMethod().getName().equals("process")){
                    Test test = new Test();
                    Object invoke = m.getMethod().invoke(test);
                    System.out.println(invoke+"-------");
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
