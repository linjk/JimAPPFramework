package cn.linjk.jimapp_core.utils;

import java.util.Locale;
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

    public static String ByteArrayToHexString(byte[] data) {
        return ByteArrayToHexString(data, 0, data.length);
    }

    public static String ByteArrayToHexString(byte[] data,
                                              int startIndex, int length) {
        StringBuffer sb = new StringBuffer(length*3);

        for (int i = 0; i < length; i++) {
            sb.append(ByteToHex(data[startIndex+i]));
            sb.append(" ");
        }

        return sb.toString().toUpperCase(Locale.ENGLISH);
    }

    public static String ByteToHex(byte data) {
        return ByteToHex(data, 2);
    }

    public static String ByteToHex(byte data, int outputLength) {
        String tmp = Integer.toHexString((data&0x000000FF));

        return padLeftToRightAlign(tmp, outputLength, '0');
    }

    public static boolean isStringNullOrEmpty(String string) {
        return string==null || string.trim().length()==0;
    }

    /**
     * RightAlign the string with filling specific characteristic in the left side.
     * @param string
     * @param totalLength
     * @param fillChar
     * @return
     */
    public static String padLeftToRightAlign(String string,
                                             int totalLength,
                                             char fillChar) {
        StringBuffer stringBuffer = new StringBuffer(string);

        while (stringBuffer.length() < totalLength) {
            stringBuffer.insert(0, fillChar);
        }

        return stringBuffer.toString();
    }
}
