package com.odde.atddv2;

import com.odde.atddv2.page.OrderPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class OrderSteps {

    @Autowired
    private Browser browser;

    @Autowired
    private OrderPage orderPage;

    @当("用如下数据录入订单:")
    public void 用如下数据录入订单(DataTable dataTable) {

        orderPage.lunch();

        Map<String, String> map = dataTable.asMap(String.class, String.class);


//        orderPage.keyin(map);


    }

    @那么("显示如下订单")
    public void 显示如下订单(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
}

