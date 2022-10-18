package com.example.simpleblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBlogApplication

fun main(args: Array<String>) {
    runApplication<SimpleBlogApplication>(*args)
}


/**
 * 이제 사이드 컨텐츠를 생각 중인데
 * 계획은 회사 퇴근하고나서, 30분 ~ 1시간 정도는 꾸준하게 영상 찍으면서 해볼려고 하거든요.
 *
 * dev: aws ec2(프리티어) + s3 + codedeploy + github action
 *
 * back: springboot + kotiln + JPA
 *
 * 로그인 상태유지 + 계층형 DB+ 공통관심사 분리 + 로깅추적필터 + 멀티gradle + 스프링 배치
 *
 * front: react + typescript + zustand
 *
 * JPA와 Kotiln의 궁합이 약간 좀 삐걱거린다..
 *
 *
 */