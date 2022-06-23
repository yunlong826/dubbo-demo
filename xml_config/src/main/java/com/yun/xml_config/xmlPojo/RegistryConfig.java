package com.yun.xml_config.xmlPojo;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/30 12:48
 */
public class RegistryConfig implements Serializable {
    private String address;


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
