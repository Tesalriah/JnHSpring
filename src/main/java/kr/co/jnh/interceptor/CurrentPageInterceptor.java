package kr.co.jnh.interceptor;

import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CurrentPageInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // 현재 요청 URI를 추출
        String currentUrl = request.getRequestURI();
        String current = "";

        if(currentUrl.contains("/order")){
            current = "order";
        }if(currentUrl.contains("/return")){
            current = "return";
        }if(currentUrl.contains("/review")) {
            current = "review";
        }if(currentUrl.contains("/product")){
            current = "product";
        }if(currentUrl.contains("/ask")){
            current = "ask";
        }if(currentUrl.contains("/asking")){
            current = "asking";
        }if(currentUrl.contains("/asking/write")){
            current = "write";
        }

        request.setAttribute("current", current); // request에 현재 요청 타입 저장

        return true;
    }
}
