package com.yun.xml_config.xmlPojo;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 19:52
 */
public class Registry implements Serializable {
    private String address;


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
