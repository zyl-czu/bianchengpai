package com.github.zylczu.core.mdc;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author YiHui
 * @date 2023/5/26
 */
@Slf4j
@Aspect
@Component
public class MdcAspect implements ApplicationContextAware {
    private ExpressionParser parser = new SpelExpressionParser();
    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Pointcut("@annotation(MdcDot) || @within(MdcDot)")
    public void getLogAnnotation() {
    }

    @Around("getLogAnnotation()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        boolean hasTag = addMdcCode(joinPoint);
        try {
            Object ans = joinPoint.proceed();
            return ans;
        } finally {
            log.info("执行耗时: {}#{} = {}ms",
                    // 获得类名
                    joinPoint.getSignature().getDeclaringType().getSimpleName(),
                    // 获得方法名
                    joinPoint.getSignature().getName(),
                    System.currentTimeMillis() - start);
            if (hasTag) {
                MdcUtil.reset();
            }
        }
    }

    private boolean addMdcCode(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MdcDot dot = method.getAnnotation(MdcDot.class);
        if (dot == null) {
            dot = (MdcDot) joinPoint.getSignature().getDeclaringType().getAnnotation(MdcDot.class);
        }

        if (dot != null) {
            MdcUtil.add("bizCode", loadBizCode(dot.bizCode(), joinPoint));
            return true;
        }
        return false;
    }

    // loadBizCode 方法的作用是通过解析 SpEL（Spring 表达式语言）表达式，
    // 从连接点的方法参数中获取业务代码。
    private String loadBizCode(String key, ProceedingJoinPoint joinPoint) {
        // 该方法接收两个参数：key 是一个表示 SpEL 表达式的字符串，
        // joinPoint 是一个 ProceedingJoinPoint 对象，表示当前方法的执行点。
        // 首先，方法使用 StringUtils.isBlank 检查 key 是否为空白。
        // 如果是空白，则返回一个空字符串，表示没有业务代码需要解析。
        if (StringUtils.isBlank(key)) {
            return "";
        }


        // 创建一个 StandardEvaluationContext 对象，用于评估 SpEL 表达式。
        // 使用 BeanFactoryResolver 配置上下文，这样 SpEL 表达式在解析时可以访问 Spring 应用上下文中的 Bean。
        StandardEvaluationContext context = new StandardEvaluationContext();

        context.setBeanResolver(new BeanFactoryResolver(applicationContext));

        // 使用 parameterNameDiscoverer 获取方法参数的名称。获取连接点的参数值。
        // 将每个参数名称和值对添加到上下文中，作为变量，以便在 SpEL 表达式中使用。
        String[] params = parameterNameDiscoverer.getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(params[i], args[i]);
        }


        // 使用 parser 解析 key 中的 SpEL 表达式，并在上下文中求值，返回结果作为字符串。
        // 这个结果就是从方法参数中解析出的业务代码。
        return parser.parseExpression(key).getValue(context, String.class);
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
