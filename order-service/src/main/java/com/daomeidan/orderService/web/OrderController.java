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

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);

        //使用RestTemplate远程调用服务
        String url = "http://127.0.0.1:8091/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);//返回值类型

        //赋值
        order.setUser(user);

        return order;


    }
}
