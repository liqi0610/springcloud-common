package cn.v5cn.security2.security.filter;

import cn.v5cn.security2.security.CustomerSecurityExpressionRoot;
import cn.v5cn.security2.security.annotation.PreAuth;
import cn.v5cn.security2.security.exception.ForbiddenException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author ZYW
 */
@Aspect
@Component
public class AuthAspect {

    @Pointcut("@annotation(cn.v5cn.security2.security.annotation.PreAuth)")
    private void cut(){
    }

    /**
     * 定制一个环绕通知
     * 当想获得注解里面的属性，可以直接注入改注解
     *
     * @param joinPoint
     * @param preAuth
     */
    @Around("cut()&&@annotation(preAuth)")
    public Object record(ProceedingJoinPoint joinPoint, PreAuth preAuth) throws Throwable {
        String value = preAuth.value();

        SecurityContextHolder.getContext();

        //Spring EL 对 value解析
        SecurityExpressionOperations operations = new CustomerSecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication());
        StandardEvaluationContext operationContext = new StandardEvaluationContext(operations);
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(value);

        Boolean result = expression.getValue(operationContext, boolean.class);
        if(result) {
            return joinPoint.proceed();
        }

        //FORBIDDEN
        throw new ForbiddenException("没有权限访问！");
    }
}
