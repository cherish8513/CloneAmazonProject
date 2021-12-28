package com.logistic.aop;

import com.logistic.dto.member.request.SaveMemberReqDto;

import com.logistic.repository.member.JpaMemberRepository;
import com.logistic.repository.order.query.OrderQueryRepository;
import com.logistic.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
//@Import(LogTraceAspect.class)
public class LogTraceAspectTest {

    @Autowired
    MemberService memberService;

    @Autowired
    OrderQueryRepository orderQueryRepository;

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method serviceMethod;
    Method repositoryMethod;

    @BeforeEach
    void init() throws NoSuchMethodException {
        serviceMethod = MemberService.class.getMethod("join", SaveMemberReqDto.class);
        repositoryMethod = OrderQueryRepository.class.getMethod("findOrdersById", Long.class);
    }

    @DisplayName("포인트컷에 서비스가 매치되는지 확인")
    @Test
    public void isAvailablePointcut() throws Exception{
        pointcut.setExpression("execution(* com.logistic..*(..)) && execution(* *..*Service*.*(..))");
        assertEquals(pointcut.matches(serviceMethod, MemberService.class), true);
    }

    @DisplayName("포인트컷에 리포지토리가 매치되는지 확인")
    @Test
    public void isAvailablePointcutRepository() throws Exception{
        pointcut.setExpression("execution(* com.logistic..*(..)) && execution(* *..*Repository*.*(..))");
        assertEquals(pointcut.matches(repositoryMethod, JpaMemberRepository.class), true);
    }



    @DisplayName("서비스, 리포지토리가 프록시 객체인지 확인")
    @Test
    public void isAop() throws Exception{
        assertEquals(AopUtils.isAopProxy(memberService), true);
        assertEquals(AopUtils.isAopProxy(orderQueryRepository), true);
    }


    @DisplayName("join() 메서드를 호출해서 aop를 확인")
    @Test
    public void findOrders() throws Exception{
        //given
        SaveMemberReqDto saveMemberReqDto = new SaveMemberReqDto();
        saveMemberReqDto.setName("1");
        saveMemberReqDto.setAddress(null);
        saveMemberReqDto.setEmail("2");
        saveMemberReqDto.setPassword("3");

        //when
        memberService.join(saveMemberReqDto);

        //then

    }
}
