package cn.linjk.jimapp_core.utils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Utils to process String.
 *
 * @author LinJK
 * @version 1.0
 */
public class StringUtils {
    public static byte[] HexStringToByteArray(String string) {
        String resultString = null;

        try {
            Pattern regex = Pattern.compile("[^\\da-f]*",
                                            Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
        }
        catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
