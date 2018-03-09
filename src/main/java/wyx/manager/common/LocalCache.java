package wyx.manager.common;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache {

    private static ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<>();

    /**
     * 获取缓存的对象
     * @return
     */
    public static Object get(String key) {
        // 如果缓冲中有该账号，则返回value
        if (cacheMap.containsKey(key)) {
            return cacheMap.get(key);
        }
        return null;
    }

    /**
     * 获取所有的Key
     * @return
     */
    public static Enumeration<String> getAllKey(){
        return cacheMap.keys();
    }

    /**
     * 查找值
     * @param key
     * @param <T>
     * @return
     */
     public  static <T> Map<String,T> findVal(String key){

         //获取所有缓存
         Enumeration<String> allKey = LocalCache.getAllKey();

         String _keyTemp=key;

         Map<String,T> _renArry = new HashMap<>();

         while (allKey.hasMoreElements())
         {
             String _key = allKey.nextElement();

             if(_key.indexOf(_keyTemp)!=-1)
             {
                 //数据转换
                 T _model = (T)LocalCache.get(_key);

                 //添加值
                 _renArry.put(_key,_model);
             }
         }

        return  _renArry;
    }

    /**
     * 初始化缓存
     *
     */
    public static void add(String key,Object val) {
        // 一般是进行数据库查询，将查询的结果进行缓存
        cacheMap.put(key, val);
    }


    /**
     * 移除缓存信息
     *
     */
    public static void remove(String key) {
        cacheMap.remove(key);
    }
}
