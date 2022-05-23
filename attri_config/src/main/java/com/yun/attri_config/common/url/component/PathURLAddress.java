package com.yun.attri_config.common.url.component;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/23 15:43
 */
public class PathURLAddress extends URLAddress {
    private String protocol;
    private String username;
    private String password;
    private String path;
    public PathURLAddress(){
        super();
    }
    public PathURLAddress(String protocol, String username, String password, String path, String host, int port) {
        super(host, port);

        this.protocol = protocol;
        this.username = username;
        this.password = password;

        // trim the beginning "/"
        while (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        if (path != null) {
            path = path.intern();
        }
        this.path = path;
    }
    public String getProtocol() {
        return protocol;
    }

    public URLAddress setProtocol(String protocol) {
        return new PathURLAddress(protocol, username, password, path, host, port);
    }

    public String getUsername() {
        return username;
    }

    public URLAddress setUsername(String username) {
        return new PathURLAddress(protocol, username, password, path, host, port);
    }

    public String getPassword() {
        return password;
    }

    public PathURLAddress setPassword(String password) {
        return new PathURLAddress(protocol, username, password, path, host, port);
    }

    public String getPath() {
        return path;
    }

    public PathURLAddress setPath(String path) {
        return new PathURLAddress(protocol, username, password, path, host, port);
    }

    @Override
    public URLAddress setHost(String host) {
        return new PathURLAddress(protocol, username, password, path, host, port);
    }

    @Override
    public URLAddress setPort(int port) {
        return new PathURLAddress(protocol, username, password, path, host, port);
    }


}
