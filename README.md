# springcloud2020
SpringCloud的学习成长之路

### api-commons包含复用实体类

### 基于Eureka建立的服务模拟
eureka-server7001 ==>表示7001端口的服务注册中心模块

eureka-server7002 ==>表示7002端口的服务注册中心模块（集群模拟）

provide-payment8001 ==>表示8001端口的服务提供者模块

provide-payment8002 ==>表示8002端口的服务提供者模块（集群模拟）

consumer-order80 ==>表示80端口的消费者模块

###### 注：服务启动顺序由上到下
##### 以下运行都建立在7001和7002两个集群服务器上，记得先开启

### 基于Zookeeper建立的服务模拟
consumerzk-order80 ==>表示80端口的消费者模块

provider-payment8004 ==>表示CentOS7虚拟机上的8004端口的服务提供者模块

###### 注：启动顺序：先开启虚拟机上面Zookeeper的Server，在启动provider，最后启动consumerzk
至于CentOS7和Zookeeper的相关安装请自行百度

### Consul的服务模拟
provideconsul-payment8006

1、[去官网下载安装Consul](https://learn.hashicorp.com/consul/getting-started/install.html)

2、下载完成后只有一个consul.exe文件，硬盘路径下双击运行，查看版本信息

3、使用开发模式启动 consul agent -dev

  通过以下地址可以访问Consul的首页:http://localhost:8500

### hystrix的服务模拟
consumer-feign-hystrix-order80和provider-hystrix-payment8001

实现高并发测试、服务降级（一对一降级、全局方法降级、配置类降级）、服务熔断等等

### 服务监控hystrixDashboard
consumer-hystrix-dashboard9001

先访问正确地址，再访问错误地址，再正确地址，会发现断路器都是慢慢放开的

### 服务配置中心Config
实现了客户端3355访问SpringCloud Config3344通过GitHub获取配置信息

config-center-3344 ==>服务器端

config-client-3355 ==>客户端

#### 实现分布式配置的动态刷新
1、修改bootstrap.yml文件，打开服务监控

2、在业务类Controller上加上@RefreshScope注解

3、打开cmd，[用curl工具向](http://localhost:3355/actuator/refresh) 发送post请求

命令如下：curl -X POST "http://localhost:3355/actuator/refresh"

### SpringCloud Stream消息驱动
stream-rabbitmq-provider8801 ==> 服务消息生产者

stream-rabbitmq-consumer8802 ==> 服务消息消费者（接收者）

stream-rabbitmq-consumer8803 ==> 服务消息消费者（接收者）

```
当两个消费者都启动时，生产者发送的消息会被重复消费；由于在RabbitMQ的配置当中没有进行分组，它将两个服务器自动分组；
当我们在application.yml文件中加入不同的分组时会得到相同的效果；
当我们将两个消费者分到同一个组的时候，生产者所发送的消息只能被消费一次，两个消费者平摊生产者发出的消息


不设置消息分组有可能错过接收消息

当我将8802消费者不设置分组，8803消费者设置分组时，（这两个消费者服务器处于关闭状态）8801生产者向它们发送消息，
此时再启动两个消费者，会看到8802无法接收到消息，8803会将错过的消息再次消费掉。
```

## Nacos

###Nacos作为服务注册中心模拟
1、在github上面下载[Nacos](https://github.com/alibaba/nacos/tags)

2、解压在cmd当中运行Nacos包中的startup.cmd

3、cloudalibaba-provider-payment9001 ==> 服务器9001

4、cloudalibaba-provider-payment9002 ==> 服务器9002

pom.xml引入新的依赖
```xml
<!--引入NacosDiscovery服务注册-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

application.yaml如下：
```yaml
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### Nacos实现服务负载均衡
cloudalibaba-consumer-nacos-order83 ==> 客户端（消费者）

Nacos默认继承了Hystrix的东西

### Nacos作为服务配置中心
cloudalibaba-config-nacos-client3377

引入新的依赖
```xml
<!--引入NacosDiscovery服务配置中心-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

进行配置文件是要严格按照官方文档来写

我的bootstrap.yml文件
```yaml
server:
  port: 3377
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
      config:
        server-addr: localhost:8848 #Nacos作为配置中心地址
        file-extension: yaml #指定yamL格式的配置
```
我的application.yml文件
```yaml
spring:
  profiles:
      active: dev #表示开发环境
```
公式为：# ${spring.application.name}-${spring.profile.active}. ${spring.cLoud.nacos.config.file-extension }

[详细参照Nacos手册](https://nacos.io/en-us/docs/quick-start-spring-cloud.html)

### Sentinel的服务降级规则
sentinel-service8401

在[官网下载](https://github.com/alibaba/Sentinel/releases) sentinel-dashboard的jar包

在8080端口不被占用的情况下，通过java -jar 命令来启动sentinel服务，打开http://localhost:8080 账号和密码都是sentinel

### Seata-Server服务模拟
order-service2001=====>订单服务器

storage-service2002====>仓库服务器

account-service2003=====>账户服务器

详细操作参考[官方文档](http://seata.io/zh-cn/docs/overview/terminology.html)

