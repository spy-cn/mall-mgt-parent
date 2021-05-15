package com.spy.mall.common.utils;

import com.spy.mall.common.constants.MallConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表单验证工具类
 *
 * @Author: spy
 * @Date: 2021/5/15 7:09
 */

public class FormUtils {

    private static final String REGEX_PHONE = "^[1][3,4,5,7,8,9][0-9]{9}$";


    /**
     * 手机号验证
     */
    public static boolean isMobile(String str) {
        //使用正则表达式验证手机号是否符合
        Pattern p = Pattern.compile(REGEX_PHONE);
        Matcher m = p.matcher(str);
        return m.matches();
    }


}
