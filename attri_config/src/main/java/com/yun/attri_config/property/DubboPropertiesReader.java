package com.yun.attri_config.property;

import com.yun.attri_config.common.URL;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 16:02
 */
public class DubboPropertiesReader {
    private  URL url;
    public DubboPropertiesReader(String filePath){
        parseProperties(filePath);
    }
    private void parseProperties(String filePath){
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            Properties pro = new Properties();
            pro.load(inputStream);
            // zookeeper://root:123456@127.0.0.1:2181/zk
            String property = pro.getProperty("yun.registry.address");
            int idx = property.indexOf(':');
            String protocol = property.substring(0,idx);
            if(protocol == null||protocol.isEmpty())
                return;
            property = property.substring(idx+1);
            String username = property.substring(2,property.indexOf(':'));
            if(username == null||username.isEmpty())
                return;
            property = property.substring(property.indexOf(':')+1);
            String password = property.substring(0,property.indexOf('@'));
            if(password == null||password.isEmpty())
                return;
            property = property.substring(property.indexOf('@')+1);
            String host = property.substring(0,property.indexOf(':'));
            if(host == null||host.isEmpty())
                return;
            property = property.substring(property.indexOf(':')+1);
            int port = Integer.valueOf(property.substring(0,property.indexOf('/')));
            if(port == 0)
                return;
            property = property.substring(property.indexOf('/')+1);
            String path = property;
            if(path == null||path.isEmpty())
                return;
            this.url = new URL(protocol,username,password,path,host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        DubboPropertiesReader reader = new DubboPropertiesReader("application.properties");
    }

}
