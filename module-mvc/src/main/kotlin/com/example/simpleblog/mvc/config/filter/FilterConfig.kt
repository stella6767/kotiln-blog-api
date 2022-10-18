package com.example.simpleblog.mvc.config.filter

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered

@Configuration
class FilterConfig {

    /**
     * 이런 필터랑 인터셉터를 이용해서 만든 강력한 인증처리 관련 프레임워크가 있거든요
     * 스프링 시큐리티라는 건데,
     *
     *
     * ==== back =====
     *
     * 1. 비동기 처리, 코루틴 vs @async completurefuture
     * 2. 파일 핸들링, 완
     * 3. sse event + web socket?을 활용한 실시간 챗봇
     * 4. aws 배포
     * 5. actuator + admin-server를 통한 간단한 모니터링,
     * 6. code deploy + github action을 통한 CI/CD + dockercompose 통해서 추가
     * 7. 스프링 시큐리티 + JWT 인증처리,
     * 8. Junit + mockito 테스트 환경설정
     * 9. restdoc 통한 API 문서 자동화 + controller 테스트 + swagger 연동하는
     * 10. gradle 멀티모듈을 통해서, domain을 공유하는  // Batch 서버 작성
     * 11. 인메모리 을 통한 cache 적용
     * 12. 게시글 예약발행시간, 공개/비공개 처리, 스케줄러, + entittylistner +
     * ... atrributeconverter, 게시글
     * 12. 계층형 테이블 전략, closure table
     * 13. 스프링 클라우드 모듈들을 활용해서 간단하게 MSA 환경 구축 // 잘모름
     * 14. Docker 연동해서 배포
     * 15. 오픈 API 연동 or 크롤링
     * 16. 환경설정 yml 분리, 클라우드 환경에서 관리
     * 17. 검색어 자동완성 API,
     * 18. nginx 통한 무중단배포
     * 19. 헥사고날 아키텍처로 변환? 근데 저도 잘 몰라서, 안할듯
     *
     *
     *
     *
     * === front ====
     *
     * 1. react- typescript 환경셋팅
     * 2. recoil + zustand를 통한 상태관리
     * 3. pm2 를 활용한 배포, 모니터링
     * 4. 정적 페이지 서버로서 s3에 배포
     * 5. next.js?? (미정이긴 한데 ) 를 활용해서 서버사이드랜더링 체험 + SEO
     * 6. antd를 활용한 ui 컴포넌트 활용
     * 7. 반응형 스타일링
     * 8. webpack 최적화 + usecallback을 활용한 랜더링 최적화
     *
     *
     *
     *
     *
     */

    @Bean
    fun mdcLoggingFilter(): FilterRegistrationBean<MDCLoggingFilter> {
        val bean = FilterRegistrationBean(MDCLoggingFilter())
        bean.addUrlPatterns("/*")
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }

}