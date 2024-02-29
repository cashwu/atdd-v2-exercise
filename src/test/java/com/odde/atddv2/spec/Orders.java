package com.odde.atddv2.spec;

import com.github.leeonky.jfactory.Spec;
import com.github.leeonky.jfactory.Trait;
import com.odde.atddv2.entity.Order;

public class Orders {

    public static class 订单 extends Spec<Order> {

        @Override
        public void main() {
            property("id").ignore();
        }

        @Trait
        public void 未發貨的(){

            property("status").value("toBeDelivered");
            property("deliveredAt").value(null);
            property("deliveredAt").value(null);
        }
    }
}
