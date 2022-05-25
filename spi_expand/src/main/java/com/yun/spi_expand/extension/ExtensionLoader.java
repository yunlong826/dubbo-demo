package com.yun.spi_expand.extension;

import com.yun.spi_expand.utils.Holder;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/25 14:57
 */
public class ExtensionLoader<T>{
    private final Class<?> type;
    private String cachedDefaultName;
    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");
    private static final String SERVICES_DIRECTORY = "META-INF/services/";
    private static final String DUBBO_DIRECTORY = "META-INF/yun/";
    private static final String DUBBO_INTERNAL_DIRECTORY = DUBBO_DIRECTORY + "internal/";

    private final ConcurrentMap<Class<?>, String> cachedNames = new ConcurrentHashMap<Class<?>, String>();
    private ExtensionLoader(Class<?> type){
        this.type = type;
    }
    private final ConcurrentMap<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<String, Holder<Object>>();
    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();

    private static final ConcurrentMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<Class<?>, Object>();

    private static final ConcurrentMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<Class<?>, ExtensionLoader<?>>();
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type){
        if (type == null)
            throw new IllegalArgumentException("Extension type == null");
             // 必须是接口
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type(" + type + ") is not interface!");
        }
        if(type.getAnnotation(SPI.class) == null)
            throw new IllegalArgumentException("Extension type(" + type +
                    ") is not extension, because WITHOUT @" + SPI.class.getSimpleName() + " Annotation!");
        // 获得接口对应的扩展点加载器
        ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        if(loader == null){
            EXTENSION_LOADERS.putIfAbsent(type,new ExtensionLoader<>(type));
            loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }
    public T getExtension(String name){
        if(name == null||name.length() == 0)
            throw new IllegalArgumentException("Extension name == null");
        if("true".equals(name)){
            return getDefaultExtension();
        }
        Holder<Object> holder = cachedInstances.get(name);
        if(holder == null){
           cachedInstances.putIfAbsent(name,new Holder<>());
           holder = cachedInstances.get(name);
        }
        Object instance = holder.get();
        if(instance == null){
            synchronized (holder){
                instance = holder.get();
                if(instance == null){
                    instance = createExtension(name);
                    holder.set(instance);
                }
            }
        }
        return (T) instance;
    }

    private T createExtension(String name) {
        // 获得拓展名对应的拓展实现类
        Class<?> clazz = getExtensionClasses().get(name);
        if (clazz == null) {
            throw new NullPointerException(name); // 抛出异常
        }
        try {
            // 从缓存中，获得拓展对象。
            T instance = (T) EXTENSION_INSTANCES.get(clazz);
            if (instance == null) {
                // 当缓存不存在时，创建拓展对象，并添加到缓存中。
                EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }
            return instance;
        } catch (Throwable t) {
            throw new IllegalStateException("Extension instance(name: " + name + ", class: " +
                    type + ")  could not be instantiated: " + t.getMessage(), t);
        }
    }

    /**
     * Return default extension, return <code>null</code> if it's not configured.
     */
    public T getDefaultExtension() {
        getExtensionClasses();
        if (cachedDefaultName == null||cachedDefaultName.length() == 0 || "true".equals(cachedDefaultName)) {
            return null;
        }
        return getExtension(cachedDefaultName);
    }
    private Map<String, Class<?>> getExtensionClasses() {
        // Holder<Map<String, Class<?>>> Hodler是一个接口，cachedClasses.get() 返回map结构
        Map<String, Class<?>> classes = cachedClasses.get();
        if (classes == null) {
            synchronized (cachedClasses) {
                classes = cachedClasses.get();
                if (classes == null) {
                    classes = loadExtensionClasses();
                    cachedClasses.set(classes);
                }
            }
        }
        return classes;
    }
    /**
     * synchronized in getExtensionClasses
     */

    private Map<String, Class<?>> loadExtensionClasses() {
        // 通过 @SPI 注解，获得默认的拓展实现类名
        final SPI defaultAnnotation = type.getAnnotation(SPI.class);
        if (defaultAnnotation != null) {
            String value = defaultAnnotation.value();
            if ((value = value.trim()).length() > 0) {
                String[] names = NAME_SEPARATOR.split(value);
                if (names.length > 1) {
                    throw new IllegalStateException("more than 1 default extension name on extension " + type.getName()
                                                    + ": " + Arrays.toString(names));
                }
                if (names.length == 1) cachedDefaultName = names[0];
            }
        }

        // 从配置文件中，加载拓展实现类数组
        Map<String, Class<?>> extensionClasses = new HashMap<String, Class<?>>();
        loadFile(extensionClasses, DUBBO_INTERNAL_DIRECTORY);
        loadFile(extensionClasses, DUBBO_DIRECTORY);
        loadFile(extensionClasses, SERVICES_DIRECTORY);
        return extensionClasses;
    }
    private void loadFile(Map<String,Class<?>> extensionClasses,String dir){
        // 完整的文件名
        String fileName = dir+type.getName();
        try{
            Enumeration<URL> urls;
            // 获得文件名对应的所有文件数组
            urls = ClassLoader.getSystemResources(fileName);
            if(urls!=null){
                while(urls.hasMoreElements()){
                    URL url = urls.nextElement();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
                    String line;
                    while((line = reader.readLine())!=null){
                        final int ci = line.indexOf('#');
                        if(ci>=0) line = line.substring(0,ci);
                        line = line.trim();
                        if(line.length()>0){
                            try{
                                String name = null;
                                int i = line.indexOf('=');
                                if(i>0){
                                    name = line.substring(0,i).trim();
                                    line = line.substring(i+1).trim();
                                }
                                if(line.length()>0){
                                    Class<?> aClass = Class.forName(line, true,ClassLoader.getSystemClassLoader());
                                    if(!type.isAssignableFrom(aClass)){
                                        throw new IllegalStateException("Error when load extension class(interface: " +
                                                                                             type + ", class line: " + aClass.getName() + "), class "
                                                                                         + aClass.getName() + "is not subtype of interface.");
                                    }
                                    extensionClasses.put(name,aClass);
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
