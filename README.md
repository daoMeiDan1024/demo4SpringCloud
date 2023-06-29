# Spring Cloud微服务框架学习

## 一、项目初始化

初始化项目结构较为简单，父工程下有两个初始化子工程：

1. user-service(用户服务)
2. order-service(订单服务)

> 注意：
>
> 1. springboot和springCloud之间需要有合适的版本对应，负责会出现不兼容的情况，[版本查询](https://spring.io/projects/spring-cloud)，我这里使用的是`spring-boot-starter-parent 2.7.5`和`spring-cloud-dependencies 2021.0.6`
> 2. 本项目主要是入门SpringCloud微服务，本身项目结构和语法规范并不是很强

## 二、项目完善

### 2.1 阶段一：服务注册中心

实际的业务需求是不同服务之间需要相互调用服务，http请求做远程调用是与语言无关的调用，只要知道对方的**ip、端口、接口路径、请求参数**即可。调用有以下几种方法：

#### a. RestTemplate

spring框架自身提供的支持HTTP请求的工具，可以发起get、post等方式的请求，[相关文章](https://blog.csdn.net/Sophia_0331/article/details/121196840)

##### 业务实现

1. 注册RestTemplate

   ```java
   package com.daomeidan.orderService.config;
   
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.client.RestTemplate;
   
   @Configuration
   public class RestTemplateConfig {
   
       @Bean
       public RestTemplate restTemplate(){
           return new RestTemplate();
       }
   }
   ```

2. 实现远程调用

   ```java
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
   ```

很明显的问题就是，请求url等信息耦合度太高，修改麻烦，不好管理。





