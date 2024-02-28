package com.odde.atddv2.page;

import com.odde.atddv2.Browser;
import org.openqa.selenium.support.ui.Sleeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@Component
public class OrderPage {

    @Autowired
    public Browser browser;

    public void keyin(Map<String, String> map) {

        browser.inputTextByPlaceholder("订单号", map.get("订单号"));

    }

    public void lunch() {
        browser.clickByText("订单");
    }
}
