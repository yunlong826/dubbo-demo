package com.yun.spi_expand.utils;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/25 14:21
 */
public class Holder<T> {

    private volatile T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

}