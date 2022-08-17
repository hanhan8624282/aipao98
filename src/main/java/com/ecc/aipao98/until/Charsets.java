/**
 * 
 */
package com.ecc.aipao98.until;

/**
 * @author zym
 * @date 2015-8-13 上午09:59:47 
 */
public class Charsets {

    public static final String UTF8         = "UTF-8";
    public static final String GBK          = "GBK";
    public static final String UCS2         = "iso-10646-ucs-2";
    public static final String US_ASII      = "US-ASCII";
    public static final String ISO_8859_1   = "ISO-8859-1";
    public static final String UTF_16BE     = "UTF-16BE";

    public static final int    GBK_FMT      = 15;
    public static final int    UCS2_FMT     = 8;
    public static final int    BINARY_FMT   = 4;
    public static final int    UTF_16BE_FMT = 1;
    public static final int    US_ASII_FMT  = 0;

    public static String getChaset(int messageCoding) {
        String code = "";
        switch (messageCoding) {
            case 0: // '\0'
                code = US_ASII;
                break;
            case 1:
                code = UTF_16BE;
                break;
            case 4: // '\004'
                code = ISO_8859_1;
                break;

            case 8: // '\b'
                code = UCS2;
                break;

            case 15: // '\017'
                code = GBK;
                break;

            default:
                code = ISO_8859_1;
                break;
        }
        return code;
    }
}
