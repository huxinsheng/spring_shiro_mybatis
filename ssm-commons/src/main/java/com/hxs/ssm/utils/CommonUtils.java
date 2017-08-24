package com.hxs.ssm.utils;

import com.hxs.ssm.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @项目名称： hxs-mp
 * @类名称： CommonUtils
 * @类描述：
 * @创建人： hxs
 * @创建时间： 2016-09-23 20:03
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 * @Version v1.0
 */
public class CommonUtils {

    public static void fillingValue(UserInfo user, Object entity, boolean isAdd) {
        Object obj = entity;
        Class<?> cls = entity.getClass().getSuperclass();
        Method methods[] = cls.getDeclaredMethods();
        Field fields[] = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                String setMetName = pareSetName(fieldName);
                if (!checkMethod(methods, setMetName)) {
                    continue;
                }
                Method method = cls.getMethod(setMetName, field.getType());
                if (fieldName.equals("updateBy")) {
                    method.invoke(obj, user.getLoginId());
                } else if (fieldName.equals("updateTime")) {
                    method.invoke(obj, new Date());
                } else if (fieldName.equals("creator")) {
                    if (isAdd) {
                        method.invoke(obj, user.getLoginId());
                    }
                } else if (fieldName.equals("createTime")) {
                    if (isAdd) {
                        method.invoke(obj, new Date());
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Enumeration _enum = request.getParameterNames();
        while (_enum.hasMoreElements()) {
            String paramName = (String) _enum.nextElement();
            String paramValue = request.getParameter(paramName);
            //形成键值对应的map
            returnMap.put(paramName, paramValue);
        }
        return returnMap;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getParameterMap(HttpServletRequest request, String... params) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Enumeration _enum = request.getParameterNames();
        while (_enum.hasMoreElements()) {
            String paramName = (String) _enum.nextElement();
            String paramValue = request.getParameter(paramName);
            for (String key : params) {
                if (key.equals(paramName))
                    //形成键值对应的map
                    returnMap.put(paramName, paramValue);
            }
        }
        return returnMap;
    }

    /**
     * 拼接某属性set 方法
     *
     * @param fldName
     * @return
     */
    public static String pareSetName(String fldName) {
        if (null == fldName || "".equals(fldName)) {
            return null;
        }
        String pro = "set" + fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
        return pro;
    }

    /**
     * 判断该方法是否存在
     *
     * @param methods
     * @param met
     * @return
     */
    public static boolean checkMethod(Method methods[], String met) {
        if (null != methods) {
            for (Method method : methods) {
                if (met.equals(method.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 把date 类转换成string
     *
     * @param date
     * @return
     */
    public static String fmlDate(Date date) {
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            return sdf.format(date);
        }
        return null;
    }
}
