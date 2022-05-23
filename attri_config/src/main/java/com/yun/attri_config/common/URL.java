package com.yun.attri_config.common;

import com.yun.attri_config.common.url.component.PathURLAddress;
import com.yun.attri_config.common.url.component.URLAddress;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 15:25
 */
public class URL implements Serializable {

    private final URLAddress urlAddress;

    public URL(){
        this.urlAddress = null;
    }
    public URLAddress getUrlAddress(){
        return this.urlAddress;
    }
    public URL(String protocol,String username,String password,String path,String host, int port){
        urlAddress = new PathURLAddress(protocol,username,password,path,host,port);
    }
}
