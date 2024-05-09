package ru.maliutin.storage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Класс определяющий аспект поведения приложения.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * Логирование методов приложения.
     * @param joinPoint метод для обработки.
     * @return результат работы обрабатываемого метода.
     * @throws Throwable исключение при обработке метода.
     */
    @Around("@annotation(ru.maliutin.storage.aspect.ProductAction)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Вызван метод "
                + joinPoint.getSignature().getName()  + ".");
        log.warn("Вызван метод "
                + joinPoint.getSignature().getName()  + ".");
        Object proceed = joinPoint.proceed();
        System.out.println("Метод завершил работу.");
        return proceed;
    }
}
