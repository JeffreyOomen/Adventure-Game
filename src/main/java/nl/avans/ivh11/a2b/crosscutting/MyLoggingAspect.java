package nl.avans.ivh11.a2b.crosscutting;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLoggingAspect
{
    @Pointcut("execution(* nl.avans.ivh11.a2b..*(..) ))")
    public void loggingPointcut() {

    }

    @Before("loggingPointcut()")
    public void LogginBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("(AOP-MyLoggingAspect) Executing:" +
            joinPoint.getSignature().getDeclaringTypeName() +
            "." +
            joinPoint.getSignature().getName());
    }
}
