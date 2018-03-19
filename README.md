# kakaopay-coupon
> 2018 카카오페이 공채 경력 서버 과제

사용자로부터 이메일 주소를 입력으로 받아서 16자리의 알파벳과 숫자로 이루어진 **중복없는** 쿠폰 번호를 발급하고 발급된 쿠폰 정보를 **같은** 페이지에 리스팅하는 웹어플리케이션 개발

[동작 화면 살펴보기](https://www.useloom.com/share/65938e8e343943dca8a743e12a3df8d4)

## Getting Started

### Prerequisites
- NPM
- Java 1.8.x
- Lombok plugin

### Run in development
#### Run front server 
``` bash
# Change to frontend root directory
cd /frontend

# When use yarn, install dependencies
yarn install 

# When use npm, install dependencies
npm install 

# Serve in dev mode, with hot reload at localhost:8081
npm run dev

# Build for production
# **important** dist to spring resources dir
# After build, backend server can serve at localhost:8080.
npm run build 
```

#### Run backend server
``` bash
# Using terminal
./gradlew bootRun

# Using IntelliJ
1. Sync gradle
2. Run Application
```

### Project folder structure
``` bash
./tree kakaopay-coupon -L 2 -d -C
kakaopay-coupon
├── frontend    -------> # frontend root
│   ├── build   -------> # webpack config
│   ├── config  -------> # frontend environment profile
│   ├── node_modules
│   ├── src     -------> # frontend source
│   ├── static  -------> # frontend static file
│   └── test
├── gradle
│   └── wrapper
├── out         -------> # backend compile output
│   ├── production
│   └── test
└── src         -------> # backend source
    ├── main
    └── test
```

### Dependencies
Dependence         |Version
-------------------|-------
=======Frontend=====|
axios              |^0.18.0
element-ui         |^2.2.1
vue                |^2.5.2
vue-backtotop      |^1.3.9
vue-progressbar    |0.7.4
vue-router         |^3.0.1
=======Backend=====|
spring-boot       |1.5.10.RELEASE
spring-boot-starter-data-jpa |
spring-boot-starter-web |
spring-boot-starter-thymeleaf |
net.sourceforge.nekohtml:nekohtml |1.9.22
com.h2database:h2 |
org.projectlombok:lombok |

----

## API Specifications
| Action | API | Parameter | Body | Success Response | Fail Response |
|--------|-----|-----------|------|------------------|---------------|
| Get coupon with ID | GET /api/v1/coupon/{id}  | id=[Long] | N/A | Status 200 OK<br>{"id": 1, "email": "user@email.com", "code": "8io4-7KPN-dzc6-Ov6h", "createdAt": "2018-03-12 23:28:05"} | <ul><li>Invalid ID - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon/sdfdsf", "msg": "Argument type mismatch", "errorCode": "argument.type.mismatch"}</li><li>Not exist coupon with id - 404 Not found :  {"uri": "http://localhost:8080/api/v1/coupon/{id}", "msg": "Not exist coupon with id : {id}", "errorCode": "not.exist.coupon"}</li></ul>|
| Get coupon List with pagination  | GET /api/v1/coupon[?page={}&size={}&sort={}]  | page=[Integer, default=1], size=[Integer, default=20], sort=[String, multiple property name with (desc or asc(defeault), seperator is comma(,)] | N/A | Status 200 OK<br>ex) /api/v1/coupon?page=2&size=1&sort=id,desc<br>{"content": [{"id":3, "email": "user@email.com", "code":"XxPW-matM-j9BY-ON8n", "createdAt":"2018-03-12 23:30:44"}], "last":false, "totalPages":5, "totalElements":5, "size":1, "number":2, "sort": [{"direction": "DESC", "property": "id", "ignoreCase": false, "nullHandling": "NATIVE", "ascending": false, "descending": true}], "numberOfElements": 1,"first": false} | <ul><li>Invalid page info - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Pagination param is invalid", "errorCode": "invalid.pagination"}</li></ul>|
| Create coupon  | POST /api/v1/coupon  | N/A | {"email": "user@email.com"} | Status 201 Created<br>{"id": 1, "email": "user@email.com", "code": "8io4-7KPN-dzc6-Ov6h", "createdAt": "2018-03-12 23:28:05"} | <ul><li>Null Body - 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Required request body is missing", "errorCode": "null.body"}</li><li>Empty Email - 400 Bad Request : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Email is null or empty.", "errorCode": "empty.email"}</li><li>Invalid Email - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Email format is invalid.", "errorCode": "invalid.email"}</li><li>Duplicate Email - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Already coupon issued for this mail.", "errorCode": "duplicate.email"}</li><li>Generate empty code - Internal server error 500 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Code is null or empty.", "errorCode": "empty.code"}</li><li>When generate code, collision occur more than 5 - Internal server error 500 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Collision occur more than 5 in code generator.", "errorCode": "code.collision"}</li><li>415 Unsupported Media type : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Only support Content type 'application/json'", "errorCode": "not.json"}</li></ul>|

----

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

## 문제 해결
- 쿠폰번호 생성은 라이브러리 사용없이 직접 구현
    - 기본 아이디어
        - 쿠폰 코드에 가능한 글자 [0-9a-zA-Z]로, 62(=10+26*2) 글자를 한 String 변수로 만듬
        - 난수를 만들어 위 변수의 index로 부터 쿠폰 코드 한자리를 가져옴
        - 이것을 쿠폰 코드 자리수인 16번을 반복하여 코드를 만듬
    - Index를 고르는 랜덤한 난수가 중요
    - 난수 생성기(Random Number Generator, RNG)        
        - 완벽한 난수 생성기, True RNG는 하드웨어(전자기 소음, 방사선 원소, 원자 물리적 현상)를 사용해야함
        - 그래서 아주 긴 시간이 걸리더라도 결국 반복되는 의사 난수 생성기(Pseudorandom Number Generator, PRNG)가 필요
        - Java의 난수 생성기
            - JCA에서 여러 provider 중에서 결정하여 사용함
                - http://d2.naver.com/helloworld/197937
            - Secure Random library는 암호학적으로 안전한 PRNG(cryptographically secure PRNG, CSPRNG)를 사용
                - 블룸 블룸 슙(BBS)
    - 나이브한 아이디어
        - 하나의 Email에 하나의 쿠폰이 발행되므로, Email을 시드로 사용
        - epoch 타임을 shift 연산하여 시드로 사용
    - 현실
        - 라이브러리를 생성하지 않고 만들어야 함        
        - 나이브한 방법으로 했을 때, 어느 한 입력값으로부터 쿠폰 코드가 유추된다면?
            - 문제 없음: (공돌이 생각) 어차피 이메일에 하나의 쿠폰이 발급된다면, 쿠폰 사용 시 이메일 검사를 할 것이다.
            - 문제 있음: 기존 이메일 정보를 활용할 수 없는 자회사의 새로운 서비스 프로모션이거나 혹은 제휴사, 오프라인에서 제휴하는 이벤트라면 이메일로 검증을 할 수 없다.
        - WELL이나 메르센 트위스트(CSPRNG는 아니지만)을 구현하는게 맞다고 생각됨
        - 결론: WELL512와 Random seed를 위한 LGC 구현


## TODO
- [x] README 초안 작성
- [x] 적정 기술 선정
- [x] Front 번들러와 Spring을 연동
- [x] 모델 정의하기
- [x] API Spec 정리
- [x] H2 DB 연동 (in memory)
- [x] Hello World API 테스트로 기폰 프로젝트 템플릿을 검증
- [x] TDD 기반으로 API 구현
- [x] 프론트 구현
    - [x] 라우팅
    - [x] 레이아웃(sidebar vs header)
    - [x] API로 데이터를 받아와 페이징
    - [x] 이메일로 쿠폰 생성
    - [ ] Item size per page 변경
    - [ ] Dynamically update pagination (강제 렌더링 말고는 업데이트할 이벤트를 못찾음)
- [x] README 마무리하기
    - [x] 실행방법
    - [x] 기술스택 명시
    - [x] 폴더 트리 설명

## Further More
- [ ] 쿠폰 생성 시, 이메일에 쿠폰 보내기
- [ ] API Spec을 가독성 있게 하기
- [ ] Production, Dev 프로필 분리
- [ ] 배포를 가정하고 배포 자동화와 인프라 구조 고려하기
- [ ] 외부 API로 쿠폰의 상태를 업데이트 해야할 상황을 생각해보기

## After Interview, TODO
- [x] [인터뷰 질문 정리](interview-log.md)
- [ ] [인터뷰 질문 중 대답하지 못하거나, 잘못 대답한 것에 대해 공부하여 정리](interview-question-to-learn.md)
- [x] 잘못된 테스트 수정
- [x] DTO 사용의 모호함을 명확히 하기(모호한 것들 1.Contoller에서만 사용함. 그런데 서비스 레이어에 DTO를 넘기는게 맞나? 2.Model inner static class로 클래스를 찾기가 힘듬)

## 고려한 사항
- Why Java 1.8 
- Why Vue.js
- Why H2 DB
- Is page button on top or bottom of contents?
    - http://blogs.wayne.edu/web/2012/03/16/a-better-return-to-top-experience/

## License
MIT License

Copyright (c) 2018 shinjjang@github