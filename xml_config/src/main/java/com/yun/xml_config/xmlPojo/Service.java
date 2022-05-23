package com.yun.xml_config.xmlPojo;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 19:55
 */
public class Service implements Serializable {
    private String interface_;
    private String ref;

    public Service(String interface_, String ref) {
        this.interface_ = interface_;
        this.ref = ref;
    }

    public String getInterface_() {
        return interface_;
    }

    public void setInterface_(String interface_) {
        this.interface_ = interface_;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
