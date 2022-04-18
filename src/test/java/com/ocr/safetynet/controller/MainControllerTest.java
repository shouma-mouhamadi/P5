package com.ocr.safetynet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class MainControllerTest {
    
    private static final MainController mainController = new MainController();
    
    @Test
    void home() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("www.example.com");
        request.setRequestURI("/foo");
        request.setQueryString("param1=value1&param");

        mainController.home(request);
    }
}