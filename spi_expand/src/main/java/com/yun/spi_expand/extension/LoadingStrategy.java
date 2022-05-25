package com.yun.spi_expand.extension;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/25 14:41
 */
public interface LoadingStrategy {
    String directory();

    default boolean preferExtensionClassLoader() {
        return false;
    }

    default String[] excludedPackages() {
        return null;
    }

    /**
     * To restrict some class that should not be loaded from `org.apache.dubbo` package type SPI class.
     * For example, we can restrict the implementation class which package is `org.xxx.xxx`
     * can be loaded as SPI implementation.
     *
     * @return packages can be loaded in `org.apache.dubbo`'s SPI
     */
    default String[] includedPackages() {
        // default match all
        return null;
    }

    /**
     * To restrict some class that should not be loaded from `org.alibaba.dubbo`(for compatible purpose)
     * package type SPI class.
     * For example, we can restrict the implementation class which package is `org.xxx.xxx`
     * can be loaded as SPI implementation
     *
     * @return packages can be loaded in `org.alibaba.dubbo`'s SPI
     */
    default String[] includedPackagesInCompatibleType() {
        // default match all
        return null;
    }

    /**
     * To restrict some class that should load from Dubbo's ClassLoader.
     * For example, we can restrict the class declaration in `org.apache.dubbo` package should
     * be loaded from Dubbo's ClassLoader and users cannot declare these classes.
     *
     * @return class packages should load
     * @since 3.0.4
     */
    default String[] onlyExtensionClassLoaderPackages() {
        return new String[]{};
    }

    /**
     * Indicates current {@link LoadingStrategy} supports overriding other lower prioritized instances or not.
     *
     * @return if supports, return <code>true</code>, or <code>false</code>
     * @since 2.7.7
     */
    default boolean overridden() {
        return false;
    }
}
