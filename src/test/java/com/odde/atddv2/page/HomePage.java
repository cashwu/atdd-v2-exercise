package com.odde.atddv2.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage {

    @Autowired
    private Browser browser;

    public void login(String userName, String password) {
        browser.inputByPlaceholder(userName, "用户名");
        browser.inputByPlaceholder(password, "密码");
        browser.clickByText("登录");
    }
}