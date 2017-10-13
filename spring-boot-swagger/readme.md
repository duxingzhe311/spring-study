# 说明

本项目是对swagger和spring-boot的整合。

跑起来后访问：[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

对swagger的核心思想谈一下个人理解：当我们为别人提供了Restful的接口时，我们一般会做这样几件事情：
- 会为调用者提供一份文档，包括接口的url、参数（类型、是否必填、意义），返回值（类型、是否必填、意义）等信息；
- 写单元测试代码，测试这些接口。

那么就会遇到以下问题：
- 这些接口经常经受不起快速变更的需求，所以经常要变更。而我们很难保证代码与文档的同步（程序员更喜欢写代码而非文档，即使有强制措施，也很难保证同步）；
- 测试代码也需要随着改变（一不小心参数就搞错了）。

于是，我们不禁会想：
- 我们能否把那些接口的说明写在代码里，然后生成类似javadoc的东西提供给调用者？
- 我们能否根据代码自动生成测试代码甚至测试页面呢？

swagger就解决了这些问题,有文档有测试页，连参数都能帮你搞，就是这么神奇！

综上所述，我认为swagger的核心思想就是**代码即文档**。

## 注意事项

- Swagger2Config.java中的那个**包名**；
```java
@Bean
public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.spring.test.swagger.controller"))//注意这个包名
            .paths(PathSelectors.any())
            .build();
}
```
- @ApiImplicitParam的paramType，可查看源码看它的取值范围（path,body,query）；


