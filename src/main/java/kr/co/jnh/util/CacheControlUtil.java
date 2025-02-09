package kr.co.jnh.util;

import javax.servlet.http.HttpServletResponse;

public class CacheControlUtil {
    public static void setNoCacheHeaders(HttpServletResponse response) {
        // 항상 새로운 페이지를 보내여주기위해 캐싱방지
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}
