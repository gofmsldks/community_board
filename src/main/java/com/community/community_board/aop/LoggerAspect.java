package com.community.community_board.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
//AOP 기능을 하는 클래스의 클래스 레벨에 지정하는 애너테이션
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.community.community_board..controller.*Controller.*(..)) or execution(* com.community.community_board..service.*Impl.*(..)) or execution(* com.community.community_board..mapper.*Mapper.*(..))")
    //Around는 어드바이스(Advice)의 종류 중 한 가지로 어드바이스는 모두 다섯 가지의 타입. 다섯 가지 중 어라운드(Around)는 메서드의 호출 자체를 제어할 수 있음.
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        if (name.contains("Controller") == true) {
            type = "Controller ===> ";

        } else if (name.contains("Service") == true) {
            type = "ServiceImpl ===> ";

        } else if (name.contains("Mapper") == true) {
            type = "Mapper ===> ";
        }

        logger.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }

}


// 관점(Aspect)
//공통적으로 적용될 기능을 의미. 부가적인 기능을 정의한 코드인 어드바이스와 어드바이스를 어느 곳에 적용할지 결정하는 포인트컷의 조합으로 만들어음 짐 .

//어드바이스(Advice)
//실제로 부가적인 기능을 구현한 객체를 의미합니다.
// 어드바이스 종류--------------------------------------
//Before Advice
//@Before
//Target 메서드 호출 이전에 적용할 어드바이스 정의

//After returning
//@AfterReturning
//Target 메서드가 성공적으로 실행되고, 결괏값을 반환한 뒤에 적용

//After throwing
//@AfterThrowing
//Target 메서드에서 예외 발생 이후에 적용
//(try/catch 문의 catch와 유사)

//After
//@After
//Target 메서드에서 예외 발생에 관계없이 적용
//(try/catch 문의 finally와 유사)

//Around
//@Around
//Target 메서드 호출 이전과 이후 모두 적용 (가장 광범위하게 사용됨)
// 어드바이스 종류 끝 --------------------------------------

//조인 포인트(Join point)
//어드바이스를 적용할 위치를 의미합니다.예를 들어, BoardService에서 CRUD를 처리하는 메서드 중 원하는 메서드를 골라서 어드바이스를 적용할 수 있음 .
//이때 BoardService의 모든 메서드는 조인 포인트가 됨 .

//포인트컷(Pointcut)
//어드바이스를 적용할 조인 포인트를 선별하는 과정이나,그 기능을 정의한 모듈을 의미.
//정규 표현식이나 AspectJ 문법을 사용해서 어떤 조인 포인트를 사용할지 결정 .

//타겟(Target)
//실제로 비즈니스 로직을 수행하는 객체를 의미 .즉, 어드바이스를 적용할 대상 .

//프록시(Proxy)
//어드바이스가 적용되었을 때 생성되는 객체를 의미 .

//인트로덕션(Introduction)
//타겟에는 없는 새로운 메서드나 인스턴스 변수를 추가하는 기능입 .

//위빙(Weaving)
//포인트컷에 의해서 결정된 타겟의 조인 포인트에 어드바이스를 적용하는 것을 의미함 .