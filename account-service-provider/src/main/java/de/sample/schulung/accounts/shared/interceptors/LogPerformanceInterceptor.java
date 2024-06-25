package de.sample.schulung.accounts.shared.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogPerformanceInterceptor
  extends AbstractBeanFactoryAwareAdvisingPostProcessor
  implements InitializingBean {


  private final MethodInterceptor interceptor = invocation -> {
    long ts = System.currentTimeMillis();
    try {
      return invocation.proceed();
    } finally {
      long ts2 = System.currentTimeMillis();
      log.info(
        "Methode {} brauchte {}ms",
        invocation.getMethod().getName(),
        ts2 - ts
      );
    }
  };

  // wird automatisch nach Dep. Inj. aufgerufen
  @Override
  public void afterPropertiesSet() throws Exception {
    var pointcut = new AnnotationMatchingPointcut(
      null,
      LogPerformance.class,
      true
    );
    this.advisor = new DefaultPointcutAdvisor(
      pointcut,
      interceptor
    );
  }
}
