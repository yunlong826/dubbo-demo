package com.yun.xml_config.xmlPojo;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 19:57
 */
public class Reference implements Serializable {
    private String id;
    private String interface_;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterface_() {
        return interface_;
    }

    public void setInterface_(String interface_) {
        this.interface_ = interface_;
    }
    public Reference(){}

    public Reference(String id, String interface_) {
        this.id = id;
        this.interface_ = interface_;
    }
}
