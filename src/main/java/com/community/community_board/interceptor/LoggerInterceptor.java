package com.community.community_board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("===============================================");
        logger.debug("==================== BEGIN ====================");
        logger.debug("Request URI ===> " + request.getRequestURI());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("==================== END ======================");
        logger.debug("===============================================");
    }

}

// preHandle : 컨트롤러의 메서드에 매핑된 특정 URI를 호출했을 때, 컨트롤러에 접근하기 전에 실행되는 메소드.
// 사용자가 화면에서 어떠한 기능을 수행했을 때 해당 기능과 매핑된 URI의 정보를 쉽게 파악할 수 있도록 콘솔에 로그를 출력하도록 처리.

//postHandle: 컨트롤러를 경유한 다음, 화면(View)으로 결과를 전달하기 전에 실행되는 메서드입니다. 요청의 끝을 알리는 로그를 콘솔에 출력하도록 처리합니다.