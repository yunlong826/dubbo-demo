package com.yun.attri_config.common.url.component;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 15:27
 */
public class URLAddress implements Serializable {

    protected String host;
    protected int port;
    public URLAddress(){

    }
    public URLAddress(String host, int port) {
        this.host = host;
        port = Math.max(port, 0);
        this.port = port;

    }

    public String getProtocol() {
        return "";
    }

    public URLAddress setProtocol(String protocol) {
        return this;
    }

    public String getUsername() {
        return "";
    }

    public URLAddress setUsername(String username) {
        return this;
    }

    public String getPassword() {
        return "";
    }

    public URLAddress setPassword(String password) {
        return this;
    }

    public String getPath() {
        return "";
    }

    public URLAddress setPath(String path) {
        return this;
    }

    public String getHost() {
        return host;
    }

    public URLAddress setHost(String host) {
        return new URLAddress(host, port);
    }

    public int getPort() {
        return port;
    }

    public URLAddress setPort(int port) {
        return new URLAddress(host, port);
    }
}
