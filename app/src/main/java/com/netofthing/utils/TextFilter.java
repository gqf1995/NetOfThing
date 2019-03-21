package com.netofthing.utils;

import com.fivefivelike.mybaselibrary.utils.ToastUtil;

/**
 * Created by 郭青枫 on 2018/3/11 0011.
 */

public class TextFilter {

    public static boolean passWordLength(String text) {
        if (text.length() < 6 || text.length() > 20) {
            ToastUtil.show("密码长度在6-20位");
            return false;
        } else {
            return true;
        }
    }

}
