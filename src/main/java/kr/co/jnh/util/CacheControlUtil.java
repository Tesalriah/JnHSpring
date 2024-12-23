package kr.co.jnh.util;

import javax.servlet.http.HttpServletResponse;

public class CacheControlUtil {
    public static void setNoCacheHeaders(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}
