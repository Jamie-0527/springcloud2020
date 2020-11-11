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

### 基于Zookeeper建立的服务模拟
consumerzk-order80 ==>表示80端口的消费者模块

provider-payment8004 ==>表示CentOS7虚拟机上的8004端口的服务提供者模块

###### 注：启动顺序：先开启虚拟机上面Zookeeper的Server，在启动provider，最后启动consumerzk
至于CentOS7和Zookeeper的相关安装请自行百度
