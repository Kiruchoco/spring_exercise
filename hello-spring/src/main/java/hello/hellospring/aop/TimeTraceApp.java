package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceApp {

    @Around("execution(* hello.hellospring.service..*(..))")    // Service 하위 클래스만 실행
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable{
        long start=System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());

        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish= System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
