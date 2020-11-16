package com.min.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 此类为路由配置文件类
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route",         //id可以随意，方便区分，
                r ->r.path("/guonei")       //入口，当访问本服务的/guonei时，自动转发到下面的uri中
                        .uri("https://news.baidu.com/guonei"));

        return routes.build();
    }

    @Bean
    public RouteLocator routeLocator2(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route",         //id可以随意，方便区分，
                r ->r.path("/guoji")       //入口，当访问本服务的/guonei时，自动转发到下面的uri中
                        .uri("https://news.baidu.com/guoji"));

        return routes.build();
    }
}
