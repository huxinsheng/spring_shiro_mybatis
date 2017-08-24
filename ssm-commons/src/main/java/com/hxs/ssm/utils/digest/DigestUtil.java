package com.hxs.ssm.utils.digest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @项目名称： hhdwworkspace
 * @类名称： DigestUtil
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-06-28 09:02
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
public class DigestUtil {
    /**
     * sha2连续加密两次
     *
     * @param password
     * @return
     */
    public static String getSha1PassCount2(String password, String uniqueKey) {
        password = DigestUtils.sha1Hex(password + uniqueKey);
        password = DigestUtils.sha1Hex(password + uniqueKey);
        return password;
    }

    public static void main(String[] args) {
        String pwd = "123456abc";
        pwd = DigestUtils.sha256Hex(pwd);
        System.out.println(getSha1PassCount2(pwd,"13714383350"));
    }
}
