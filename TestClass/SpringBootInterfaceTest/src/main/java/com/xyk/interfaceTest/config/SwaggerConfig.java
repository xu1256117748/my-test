package com.xyk.interfaceTest.config;

import com.xyk.XykApi;
import com.xyk.interfaceTest.utils.XykApi2;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Swagger2的接口配置
 * 
 * @author 徐亚奎
 * @date 2021-11-02
 */
@Configuration
public class SwaggerConfig implements ApplicationRunner {
    @Resource
    private XykApi2 xykApi;
    // windows/linux
    private String host;
//    //端口号
//    @Value("${server.port:#{-1}}")
//    private Integer port;


    @Override
    public void run(ApplicationArguments args){
        host = getIpAddress();
        Integer port = xykApi.port;
        System.out.println("入口："+"http://"+host+":"+port+"/");
        System.out.println("文档："+"http://"+host+":"+port+"/doc.html");
    }

	@Bean
    public Docket backendApi() {
		String name="接口测试服务";
        Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName(name)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xyk.interfaceTest.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .groupName(name)
                .apiInfo(apiInfo(name));
        return docket;
    }
	
    private ApiInfo apiInfo(String name) {
        return new ApiInfo(name,"描述","版本","TermsOfServiceUrl",
                new Contact("徐亚奎", "1256117748@qq.com", "1256117748@qq.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
    /**
     * 获取本机host,windows和linux通用
     * */
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e);
        }
        return "";
    }


//
//	/**
//	 * 安全模式，这里指定token通过Authorization头请求头传递
//	 */
//	private List<ApiKey> securitySchemes() {
//		List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
//		apiKeyList.add(new ApiKey("Authorization", "authToken", "header")); //Authorization
//		return apiKeyList;
//	}
//
//	/**
//	 * 安全上下文
//	 */
//	private List<SecurityContext> securityContexts() {
//		List<SecurityContext> securityContexts = new ArrayList<>();
//		securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
//				.forPaths(PathSelectors.regex("^(?!auth).*$")).build());
//		return securityContexts;
//	}
//
//	/**
//	 * 默认的安全上引用
//	 */
//	private List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		List<SecurityReference> securityReferences = new ArrayList<>();
//		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
//		return securityReferences;
//	}
}
