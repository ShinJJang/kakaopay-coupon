# kakaopay-coupon
> 2018 카카오페이 공채 경력 서버 과제

사용자로부터 이메일 주소를 입력으로 받아서 16자리의 알파벳과 숫자로 이루어진 **중복없는** 쿠폰 번호를 발급하고 발급된 쿠폰 정보를 같은 페이지에 리스팅하는 웹어플리케이션 개발

## Getting Started

### Prerequisites
- NPM
- Java 1.8.x
- Lombok plugin

### Run in production 
```
./graldlew spring-boot:run
```

### Run in development
#### Run front server 
```
npm run dev
```

#### Run backend server
```
# Using terminal
./gradlew ...

# Using IntelliJ
1. Sync gradle
2. Run coupon:....
``` 

Project structure
```
# tree with comment
```

----

## API Specifications
| Action | API | Parameter | Body | Success Response | Fail Response |
|--------|-----|-----------|------|------------------|---------------|
| Get coupon with ID | GET /api/v1/coupon/{id}  | id=[Long] | N/A | Status 200 OK<br>{"id": 1, "email": "user@email.com", "code": "8io4-7KPN-dzc6-Ov6h", "createdAt": "2018-03-12 23:28:05"} | <ul><li>Invalid ID - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon/sdfdsf", "msg": "Argument type mismatch", "errorCode": "argument.type.mismatch"}</li><li>Not exist coupon with id - 404 Not found :  {"uri": "http://localhost:8080/api/v1/coupon/{id}", "msg": "Not exist coupon with id : {id}", "errorCode": "not.exist.coupon"}</li></ul>|
| Get coupon List with pagination  | GET /api/v1/coupon[?page={}&size={}&sort={}]  | page=[Integer, default=1], size=[Integer, default=20], sort=[String, multiple property name with (desc or asc(defeault), seperator is comma(,)] | N/A | Status 200 OK<br>ex) /api/v1/coupon?page=2&size=1&sort=id,desc<br>{"content": [{"id":3, "email": "user@email.com", "code":"XxPW-matM-j9BY-ON8n", "createdAt":"2018-03-12 23:30:44"}], "last":false, "totalPages":5, "totalElements":5, "size":1, "number":2, "sort": [{"direction": "DESC", "property": "id", "ignoreCase": false, "nullHandling": "NATIVE", "ascending": false, "descending": true}], "numberOfElements": 1,"first": false} | <ul><li>Invalid page info - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Pagination param is invalid", "errorCode": "invalid.pagination"}</li></ul>|
| Create coupon  | POST /api/v1/coupon  | N/A | {"email": "user@email.com"} | Status 201 Created<br>{"id": 1, "email": "user@email.com", "code": "8io4-7KPN-dzc6-Ov6h", "createdAt": "2018-03-12 23:28:05"} | <ul><li>Null Body - 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Required request body is missing", "errorCode": "null.body"}</li><li>Empty Email - 400 Bad Request : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Email is null or empty.", "errorCode": "empty.email"}</li><li>Invalid Email - Bad Request 400 : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Fail to create Coupon. Email format is invalid.", "errorCode": "invalid.email"}</li><li>415 Unsupported Media type : {"uri": "http://localhost:8080/api/v1/coupon", "msg": "Only support Content type 'application/json'", "errorCode": "not.json"}</li></ul>|

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

## TODO
- [x] README 초안 작성
- [x] 적정 기술 선정
- [x] Front 번들러와 Spring을 연동
- [x] 모델 정의하기
- [x] API Spec 정리
- [x] H2 DB 연동 (in memory)
- [x] Hello World API 테스트로 기폰 프로젝트 템플릿을 검증
- [x] TDD 기반으로 API 구현
- [ ] 프론트 구현
    - [x] 라우팅
    - [x] 레이아웃(sidebar vs header)
    - [x] API로 데이터를 받아와 페이징
    - [ ] 이메일로 쿠폰 생성
    - [ ] 디자인
- [ ] README 마무리하기

## Further More
- [ ] 쿠폰 생성 시, 이메일에 쿠폰 보내기
- [ ] API Spec을 가독성 있게 하기
- [ ] Production, Dev 프로필 분리
- [ ] 배포를 가정하고 배포 자동화와 인프라 구조 고려하기

## Dependencies

## 고려한 사항
- Why Java 1.8 
- Why Vue.js
- Why Webpack
- Why H2 DB
- Is page button on top or bottom of contents?
    - http://blogs.wayne.edu/web/2012/03/16/a-better-return-to-top-experience/

## License
MIT License

Copyright (c) 2018 shinjjang@github