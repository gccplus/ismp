package utils.SMGP;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;
import java.lang.reflect.*;

import com.huawei.smproxy.util.*;
/**
 * 提供系统运行环境信息。
 */
public class Env {

  /** 配置读写类。*/
  static Cfg config;

 /**消息配置类*/
  static Cfg msgConfig;

  /** 资源文件读写类。*/
  static Resource resource;

  /**
   * 取得配置读写类。
   */
  public static Cfg getConfig() {

    //如果未初始化，则说明系统正处在安装过程中，则配置的读取
    if (config == null) {
      try {
        config = new Cfg("config.xml");
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return config;
  }

  /**
   * 取得消息配置类。
   */
  public static Cfg getMsgConfig() {

    //如果未初始化，则说明系统正处在安装过程中，则配置的读取
    if (msgConfig == null) {
      try {
        msgConfig = new Cfg("message.xml");
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return msgConfig;
  }

  /**
   * 取得资源读取类。
   */
  public static Resource getResource() {
    if (resource == null) {
      try {
        resource = new Resource("resource");
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return resource;
  }
}
