package com.daomeidan.orderService.web;

import com.daomeidan.orderService.pojo.Order;
import com.daomeidan.orderService.pojo.User;
import com.daomeidan.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);

        //调用注册中心上的user-service服务
        String url = "http://user-service/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);

        order.setUser(user);

        return order;

    }
}
