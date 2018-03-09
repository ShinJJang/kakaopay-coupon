# kakaopay-coupon
> 2018 카카오페이 공채 경력 서버 과제

사용자로부터 이메일 주소를 입력으로 받아서 16자리의 알파벳과 숫자로 이루어진 **중복없는** 쿠폰 번호를 발급하고 발급된 쿠폰 정보를 같은 페이지에 리스팅하는 웹어플리케이션 개발

## Getting Started

### Prerequisites
- NPM
- Java 1.8.x

### Run in production 
```
./graldlew spring-boot:run
```

### Run in development
#### Run front server 
```
npm ...
```

#### Run backend server
```
# Using terminal
./gradlew ...

# Using IntelliJ
1. Sync gradle
2. Run coupon:....
``` 

## Requirement
* 웹어플리케이션 개발언어는 Java 또는 Scala, 프레임워크는 Spring-boot/PlayFramework/Akka-HTTP 활용
* 웹어플리케이션은 SPA (Single Page Application)로 개발되어야 함
* 쿠폰번호는 [0-9a-zA-Z]으로 구성
* 중복된 이메일 입력에 따른 쿠폰 발행은 불가 
* 쿠폰번호 생성은 라이브러리 사용없이 직접 구현
* 데이타베이스는 사용에 제약 없음 (가능하면 In-memory db 사용)
* 서버에 REST API 구현
* 프론트엔드 구현에는 Twitter Bootstrap등 사용 가능 
* 쿠폰번호 리스팅은 Pagination 가능하도록 구현
* 서버와 클라이언트는 JSON Object 통신으로 구현
* 클라이언트 구현을 위한 Javascript framework 제약은 없음
* README.md 파일에 문제해결 전략 및 프로젝트 빌드, 실행 방법 명시 
* Unit Test 코드 작성 

## TODO
- [ ] README 초안 작성
- [ ] 적정 기술 선정
- [ ] Front 번들러와 Spring을 연동
- [ ] Production, Dev 프로필 분리
- [ ] 모델 정의하기
- [ ] API Spec 정리
- [ ] H2 DB 연동
- [ ] Hello World API 테스트로 기폰 프로젝트 템플릿을 검증
- [ ] TDD 기반으로 API 구현
- [ ] 프론트 구현

## API Specifications

## Dependencies

## 고려한 사항
- Why Java 1.8 
- Why Vue.js
- Why Webpack
- Why H2 DB

## License
MIT License

Copyright (c) 2018 shinjjang@github