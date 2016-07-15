package cn.linjk.jimapp_core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Utils to process String.
 *
 * @author LinJK
 * @version 1.0
 */
public class StringUtils {
    /**
     * Convert hex-string to byte[] array format.
     * @param string
     * @return
     */
    public static byte[] HexStringToByteArray(String string) {
        String resultString = null;

        try {
            /**remove the non-hex characteristic in the string parameter.*/
            Pattern regex = Pattern.compile("[^\\da-f]*",
                                            Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
            Matcher regexMatcher = regex.matcher(string);

            try {
                resultString = regexMatcher.replaceAll("");

                int bufferLength;
                if ( (resultString.length() % 2) == 1 ) {
                    bufferLength = resultString.length()/2 + 1;
                }
                else {
                    bufferLength = resultString.length()/2;
                }

                byte[] buffer = new byte[bufferLength];
                for (int i = 0; i < bufferLength-1; i++) {
                    int tmp = Integer.parseInt(resultString.substring(2*i, 2*i+2), 16);
                    buffer[i] = (byte)(tmp&0xFF);
                }
                if (bufferLength > 0) {
                    int index = bufferLength - 1;
                    int len   = resultString.length()%2 == 1 ? 1 : 2;
                    int tmp   = Integer.parseInt(resultString.substring(2*index, 2*index+len), 16);

                    buffer[bufferLength-1] = (byte)(tmp & 0xFF);
                }

                return buffer;
            }
            catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
        catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
