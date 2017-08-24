package com.hxs.ssm.exception;

/**
 * @项目名称： hhdwworkspace
 * @类名称： FirstLoginException
 * @类描述： 自定义首次登录异常
 * @创建人： huxinsheng
 * @创建时间： 2017-06-29 09:18
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
public class FirstLoginException extends Exception {
    public FirstLoginException(Exception e) {
        super(e);
    }

    public FirstLoginException(String message) {
        super(message);
    }

    public FirstLoginException(String message, Exception e) {
        super(message, e);
    }
}
