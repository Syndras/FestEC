package com.sun.latte.app;

import java.util.WeakHashMap;

/**
 * Created by Sun on 2017/9/13.
 */

//配置文件的存储以及获取
public class Configurator {

    //存储信息的数据结构，静态方法
    //WeakHashMap:键值对不使用的时候回收，最大限度的时候避免内存爆满。
    //public static final变量名要大写。
    public static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    //创建枚举类
    private Configurator() {
        //配置开始了，但是没有完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }


    //get静态内部类
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }


    //静态内部类单例模式的初始化
    private static class Holder {
        public static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //配置Host
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //检查---配置项没有完成
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configure is not ready");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }
}
