package com.odde.atddv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odde.atddv2.entity.Order;
import com.odde.atddv2.entity.OrderLine;
import com.odde.atddv2.entity.User;
import com.odde.atddv2.repo.OrderRepo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.zh_cn.并且;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class ApiOrderSteps {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private Api api;

    @当("API查询订单时")
    public void api查询订单时() {
        api.get("orders");
    }

    @那么("返回如下订单")
    public void 返回如下订单(String json) {
        api.responseShouldMatchJson(json);
    }

    @并且("存在订单{string}的订单项:")
    @Transactional
    public void 存在订单的订单项(String orderCode, DataTable table) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = orderRepo.findByCode(orderCode);
        table.asMaps().forEach(map -> order.getLines().add(objectMapper.convertValue(map, OrderLine.class).setOrder(order)));
        orderRepo.save(order);
    }

    @当("API查询订单{string}详情时")
    public void api查询订单详情时(String orderCode) {
        api.get(String.format("orders/%s", orderCode));
    }

    @当("通过API发货订单{string}，快递单号为{string}")
    public void 通过api发货订单快递单号为(String orderCode, String deliverNo) {
        var deliverOrderQuery = new DeliverOrderQuery();
        deliverOrderQuery.setDeliverNo(deliverNo);

        api.post(String.format("orders/%s/deliver", orderCode), deliverOrderQuery);
    }

    @那么("订单{string}已发货，快递单号为{string}")
    public void 订单快递单号为(String orderCode, String deliverNo) {

        Order order = orderRepo.findByCode(orderCode);
        Assertions.assertEquals(deliverNo, order.getDeliverNo());
    }

    @Data
    private class DeliverOrderQuery {
       private String deliverNo;
    }
}


