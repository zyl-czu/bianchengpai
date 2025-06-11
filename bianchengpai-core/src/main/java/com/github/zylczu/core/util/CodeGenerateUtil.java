package com.github.zylczu.core.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author YiHui
 * @date 2022/8/15
 */
public class CodeGenerateUtil {
    public static final Integer CODE_LEN = 3;

    private static final Random random = new Random();

    private static final List<String> specialCodes = Arrays.asList(
            "666", "888", "000", "999", "555", "222", "333", "777",
            "520", "911",
            "234", "345", "456", "567", "678", "789"
    );

    public static String genCode(int cnt) {
        if (cnt >= specialCodes.size()) {
            int num = random.nextInt(1000);
            if (num >= 100 && num <= 200) {
                // 100-200之间的数字作为关键词回复，不用于验证码
                return genCode(cnt);
            }
            // "%0" + CODE_LEN + "d" 是一个格式化字符串，其中：
            //% 表示格式说明符的开始。
            //0 表示使用前导零填充。
            //CODE_LEN 表示字符串的总长度。
            //d 表示参数是一个十进制整数。
            //num 是要格式化的整数。
            //String.format 方法根据格式化字符串的要求，将 num 转换为一个三位数的字符串，如果 num 的位数不足三位，则在前面补零。
            return String.format("%0" + CODE_LEN + "d", num);
        } else {
            return specialCodes.get(cnt);
        }
    }

    public static boolean isVerifyCode(String content) {
        if (!NumberUtils.isDigits(content) || content.length() != CodeGenerateUtil.CODE_LEN) {
            return false;
        }

        int num = Integer.parseInt(content);
        return num < 100 || num > 200;
    }
}
