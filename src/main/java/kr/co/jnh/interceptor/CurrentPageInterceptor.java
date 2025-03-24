package kr.co.jnh.interceptor;

import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CurrentPageInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // 현재 요청 URI를 추출
        String currentUrl = request.getRequestURI();
        String current = "";

        Map<String, String> urlMappings = new LinkedHashMap<>();
        urlMappings.put("/order", "order");
        urlMappings.put("/return", "return");
        urlMappings.put("/review", "review");
        urlMappings.put("/product", "product");
        urlMappings.put("/asking/write", "write"); // 더 구체적인 URL을 먼저 배치
        urlMappings.put("/asking", "asking");
        urlMappings.put("/user", "user");
        urlMappings.put("/report", "report");

        for (Map.Entry<String, String> entry : urlMappings.entrySet()) {
            if (currentUrl.contains(entry.getKey())) {
                current = entry.getValue();
                break;
            }
        }
/*
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
*/

        request.setAttribute("current", current); // request에 현재 요청 타입 저장

        return true;
    }
}
