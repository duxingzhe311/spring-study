# spring-boot的hello world

启动，访问地址：[http://localhost:8080/](http://localhost:8080/)

约定优于配置（Convention over configuration）

@SpringBootApplication
是以下三个的组合
- @Configuration

- @EnableAutoConfiguration

- @ComponentScan

@EnableAutoConfiguration就是从classpath下找所有META/INFspring.factories配置文件，并将其中org.springframework.boot.autoconfigure.EnableAutoConfiguration对应的配置项通过反射实例化到标注了@Configuration的javaconfig形式的ioc容器配置类，然后汇总成为一个并加载到ioc容器。