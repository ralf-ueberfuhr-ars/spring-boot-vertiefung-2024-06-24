package de.sample.schulung.accounts.consumer.shared.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
@Slf4j
public class LogPerformanceInterceptor
  extends AbstractBeanFactoryAwareAdvisingPostProcessor
  implements InitializingBean {

  private final MethodInterceptor interceptor = invocation -> {
    final Consumer<Long> logging = startTime -> {
      long ts2 = System.currentTimeMillis();
      log.info(
        "Methode {} brauchte {}ms",
        invocation.getMethod().getName(),
        ts2 - startTime
      );
    };
    long ts = System.currentTimeMillis();
    boolean isSync = false;
    try {
      Object result = invocation.proceed();
      if (result instanceof Flux<?> flux) {
        return flux.doFinally(__ -> logging.accept(ts));
      }
      if(result instanceof Mono<?> mono) {
        return mono.doFinally(__ -> logging.accept(ts));
      }
      isSync = true;
      return result;
    } finally {
      if (isSync) {
        logging.accept(ts);
      }
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
