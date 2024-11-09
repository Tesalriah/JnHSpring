package kr.co.jnh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentPageInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 현재 요청 URI를 추출
        String currentUrl = request.getRequestURI();

        // "myPage"에만 적용
        if (currentUrl.contains("/myPage")) {
            if(currentUrl.contains("/order")){
                currentUrl = "/order";
            }else{
                currentUrl = currentUrl.replace("/jnh/myPage", "");
            }
            request.setAttribute("currentUrl", currentUrl); // request에 현재 URL 저장
        }

        return true;
    }
}
