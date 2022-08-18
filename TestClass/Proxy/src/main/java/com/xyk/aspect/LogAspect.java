package com.xyk.aspect;

import com.xyk.annocation.MyLog;
import com.xyk.pojo.Food;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 徐亚奎
 * @date 23/07/2021 00:42
 */

@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(com.xyk.annocation.MyLog)")
    public void PointCut() throws Throwable{
    }
    @Around("PointCut()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         *JoinPoint
         * Signature getSignature();	获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
         * Object[] getArgs();	获取传入目标方法的参数对象
         * Object getTarget();	获取被代理的对象
         * Object getThis();	获取代理对象
         * */
        System.out.println("环绕通知_开始");
        System.out.println(joinPoint.getSignature().toString());
        // 获取目标方法对象(根据源码分析,MethodSignature封装了两个方法,一个获取方法的返回值类型,一个是获取封装的Method对象)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取目标方法上的日志注解
        MyLog annotation = method.getAnnotation(MyLog.class);
        System.out.println("获取目标方法上的日志注解:"+annotation);
        StringBuilder str = new StringBuilder();
        str.append(annotation.type()).append("正在进行:").append(annotation.name());
        System.out.println(str);
        // 获取目标方法上的参数
        System.out.println("参数数量:"+method.getParameterCount());
        if (method.getParameterCount()>0){
            Object[] paramsValue = joinPoint.getArgs();
            for (int i=0;i<paramsValue.length;i++){
                Object s1=Food.class;
                System.out.println(s1);
                Object s2= paramsValue[i].getClass();
                System.out.println(s2);
                System.out.println(s1.equals(s2));
                if(s1.equals(s2)){
                    System.out.println(paramsValue[i]);
                }
            }
        }
        System.out.println("环绕通知_执行目标方法:");
        Object result = joinPoint.proceed(joinPoint.getArgs());
        System.out.println("环绕通知_结束");
        return result;
    }
}
