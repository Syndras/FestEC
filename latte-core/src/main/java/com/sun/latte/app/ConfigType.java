package com.sun.latte.app;

/**
 * Created by Sun on 2017/9/13.
 */

//枚举类：唯一的单例，只能初始化一次，进行多线程操作，用枚举进行惰性的初始化，线程安全的懒汉模式
public enum ConfigType {

    //配置网络域名
    API_HOST,

    //全局上下文
    APPLICATION_CONTEXT,

    //控制初始化控制完成了没有
    CONFIG_READY,

    //存储字体的初始化
    ICON

}
