package cn.iamcrawler.crawlergoddess.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by liuliang on 2019/4/10.
 */
public class ObjectUtils {

    public ObjectUtils() {
    }

    public static HashMap<String, Object> objectToHashMap(Object obj) {
        HashMap hashMap = new HashMap();
        Class clazz = obj.getClass();
        ArrayList clazzs = new ArrayList();

        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));

        Iterator var4 = clazzs.iterator();

        while (var4.hasNext()) {
            Class iClazz = (Class) var4.next();
            Field[] fields = iClazz.getDeclaredFields();
            Field[] var7 = fields;
            int var8 = fields.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                Field field = var7[var9];
                Object objVal = null;
                field.setAccessible(true);

                try {
                    objVal = field.get(obj);
                } catch (IllegalAccessException var13) {
                    var13.printStackTrace();
                }

                hashMap.put(field.getName(), objVal);
            }
        }

        return hashMap;
    }

    public static Object hashMapToBean(Class type, Map map) {
        try {
            BeanInfo e = Introspector.getBeanInfo(type);
            Object obj = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = e.getPropertyDescriptors();

            for (int i = 0; i < propertyDescriptors.length; ++i) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[]{value};

                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (IllegalAccessException var11) {
                        var11.printStackTrace();
                    } catch (InvocationTargetException var12) {
                        var12.printStackTrace();
                    }
                }
            }

            return obj;
        } catch (Exception var13) {
            var13.printStackTrace();
            return null;
        }
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (!(obj instanceof Object[])) {
            return false;
        } else {
            Object[] object = (Object[]) ((Object[]) obj);
            if (object.length == 0) {
                return true;
            } else {
                boolean empty = true;

                for (int i = 0; i < object.length; ++i) {
                    if (!isNullOrEmpty(object[i])) {
                        empty = false;
                        break;
                    }
                }

                return empty;
            }
        }
    }

    public static boolean isNotNullAndEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }


    /**
     * <功能详细描述>判断传入的参数是否含有空或者null
     * @param val
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(String... val) {
        for (String str : val) {
            if (!isNullOrEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注解到对象复制，只复制能匹配上的方法。
     * @param annotation
     * @param object
     */
    public static void annotationToObject(Object annotation, Object object) {
        if (annotation != null) {
            Class<?> annotationClass = annotation.getClass();
            Class<?> objectClass = object.getClass();
            for (Method m : objectClass.getMethods()) {
                if (StringUtils.startsWith(m.getName(), "set")) {
                    try {
                        String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
                        Object obj = annotationClass.getMethod(s).invoke(annotation);
                        if (obj != null && !"".equals(obj.toString())) {
                            if (object == null) {
                                object = objectClass.newInstance();
                            }
                            m.invoke(object, obj);
                        }
                    } catch (Exception e) {
                        // 忽略所有设置失败方法
                    }
                }
            }
        }
    }

    /**
     * 序列化对象
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            if (object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化对象
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (bytes != null && bytes.length > 0) {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 获取byte[]类型Key
//     * @param object
//     * @return
//     */
//    public static byte[] getBytesKey(Object object) {
//        if (object instanceof String) {
//            return StringUtils.getBytes((String) object);
//        } else {
//            return ObjectUtils.serialize(object);
//        }
//    }

    /**
     * Object转换byte[]类型
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object) {
        return ObjectUtils.serialize(object);
    }

    /**
     * byte[]型转换Object
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        return ObjectUtils.unserialize(bytes);
    }


    /**
     * 将Map转换为对象
     * @param paramMap
     * @param cls
     * @return
     */
    public static <T> T parseMap2Object(Map<String, Object> paramMap, Class<T> cls) {
        return JSONObject.parseObject(JSONObject.toJSONString(paramMap), cls);
    }

}