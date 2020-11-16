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