package be.fastned.application.service;

import org.aspectj.lang.annotation.*;

@Aspect
public class SetWachtwoordLogger {
    
  @Pointcut("execution(* be.fastned.application.domain.Persoon.setWachtwoord(..))")
  public void setWachtwoordInvocation() {
  }
  
  @Before("setWachtwoordInvocation()")
  public void markStartOfMethod() {
      System.out.println("==> setWachtwoord() werd opgeroepen");
  }
    
  @AfterReturning("setWachtwoordInvocation()")
  public void markNormalCompletionOfMethod() {
      System.out.println("==> setWachtwoord() is normaal geëindigd");
  }

  @AfterReturning(
          pointcut="setWachtwoordInvocation()",
          returning="retVal")
  public void discloseReturnValue(Object retVal) {
      System.out.println("==> setWachtwoord() retourneerde "+retVal);
  }
  
  @AfterThrowing("setWachtwoordInvocation()")
  public void markAbnormalCompletionOfMethod() {
      System.out.println("==> setWachtwoord() is abnormaal geëindigd");
  }
}




