package be.fastned.application.domain;

import org.aspectj.lang.annotation.*;

//@Aspect
public class AfspraakGetStatusLogger {

    /*@Pointcut("execution(* be.fastned.application.domain.Afspraak.getStatus(..))")
    public void getAfspraakStatusInvocation() {
        System.out.println("==> getStatus() werd opgeroepen");
    }

    @Before("getAfspraakStatusInvocation()")
    public void markStartOfMethod() {
        System.out.println("==> getStatus() werd opgeroepen");
    }

    @AfterReturning("getAfspraakStatusInvocation()")
    public void markNormalCompletionOfMethod() {
        System.out.println("==> getStatus() is normaal geëindigd");
    }

    @AfterReturning(
            pointcut="getAfspraakStatusInvocation()",
            returning="retVal")
    public void discloseReturnValue(Object retVal) {
        System.out.println("==> getStatus() retourneerde "+retVal);
    }

    @AfterThrowing(pointcut = "execution(* be.fastned.application.domain.Afspraak.getStatus(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable  { System.out.println("==> getStatus() retourneerde "+ex.getMessage()); }*/
}
