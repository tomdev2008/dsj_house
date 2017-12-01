package com.dsj.common.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.web.exhandler.UnknownResourceException;

/**
 * Default controller that exists to return a proper REST response for unmapped requests.
 */
@Controller
public class DefaultController {

//    @RequestMapping("/**")
//    public void unmappedRequest(HttpServletRequest request) {
//        String uri = request.getRequestURI();
//        throw new UnknownResourceException("There is no resource for path " + uri);
//    }
}
