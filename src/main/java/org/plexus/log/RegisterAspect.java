package org.plexus.log;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RegisterAspect {


    @Pointcut("execution( * org.plexus.service.SpaceShipService.*(..)) ")
    private void registerNegative() {}

    @Before("registerNegative() && args(id)")
    public void registerNegative(JoinPoint proceedingJoinPoint, Integer id) throws Throwable {
        if (id<0)
            log.info("Search SpaceShip with negative id {}. ",id);
    }
}