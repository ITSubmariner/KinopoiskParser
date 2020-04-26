package com.pet.kinopoiskparser.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class KinopoiskLoggerManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Pointcut("execution(* com.pet.kinopoiskparser.service.KinopoiskParser.parse())")
    private void parserPC() {}

    @AfterReturning(pointcut = "parserPC()")
    public void logParserSuccess() {
        logger.info("Parser worked successfully!");
    }

    @AfterThrowing(pointcut = "parserPC()")
    public void logParserError() {
        logger.error("Error while parsing site!");
    }

}
