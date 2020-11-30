package tech.jiangchen.dynamicdatasource01.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(tech.jiangchen.dynamicdatasource01.config.DS)")
    public void ds() {
    }

    @Around("ds() && @annotation(DS)")
    public void initDB(ProceedingJoinPoint point, DS ds) throws Throwable {
        DataSourceContextHolder.setDB(ds.value());
        point.proceed();
        DataSourceContextHolder.clearDB();
    }
}
