package com.xyk;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 徐亚奎
 * @date 23/07/2021 00:27
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.xyk")
public class SpringConfig {
}
