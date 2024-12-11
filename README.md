## 외부 설정 4가지 방법
- OS 환경변수: OS에서 지원하는 외부 설정, 해당 OS를 사용하는 모든 프로세스에서 사용
- 자바 시스템 속성: 자바에서 지원하는 외부 설정, 해당 JVM 안에서 사용
- 자바 커맨드 라인 인수: 커맨드 라인에서 전달하는 외부 설정, 실행시 `main(args)` 메서드에서 사용
- 외부 파일: 프로그램에서 외부 파일을 직접 읽어서 사용

### OS 환경변수
OS 환경 변수는 해당 OS를 사용하는 모든 프로그램에서 읽을 수 잇는 설정값이다. 다른 외부 설정과 비교해서 사용범위가 가장 넓다.
- Mac: 터미널에서 `printenv` 명령어로 확인 가능 (`OsEnv`)

### 자바 시스템 속성
자바 시스템 속성은 실행한 JVM 안에서 접근 가능한 외부 설정이다. 추가로 자바가 내부에서 미리 설정해두고 사용하는 속성들도 있다.
- 자바 시스템 속성은 다음과 같이 자바 프로그램을 실행할 때 사용한다. (`JavaSystemProperty`)
  - 예) `java -Durl=dev -jar app.jar` 
  - `-D` VM 옵션을 통해서 `key=value` 형태로 전달한다.

### 자바 커맨드 라인 인수
커맨드 라인 인수는 애플리케이션 실행 시점에 외부 설정값을 `main(args)` 메서드의 `args` 파라미터로 전달하는 방법이다.
- 다음과 같이 사용한다. (`CommandLineV1`)
  - 예) `java -jar app.jar dataA dataB`
  - 필요한 데이터를 마지막 위치에 스페이스로 구분해서 전달하면 된다. 이 경우 `dataA`, `dataB` 2개의 문자가 `main(args)` 메서드의 `args` 파라미터로 전달된다.
- 커맨드 라인에 전달하는 값은 형식이 없고, 단순히 띄어쓰기로 구분한다.
  - `aaa bbb` -> `[aaa, bbb]` 값 2개
  - `key=value` -> `[key=value]` 값 1개
- 커맨드 라인 옵션 인수: 커맨드 라인 인수를 `key=value` 형태로 전달하는 방법 (`CommandLineV2`)
  - 예) `java -jar app.jar --username=userA --password=passB`
  - `--`를 사용해서 옵션을 전달한다.

### 외부 설정 - 스프링 통합
외부 설정값이 어디에 위치하든 상관없이 일관성 있고 편리하게 `key=value` 형태로 외부 설정값을 읽을 수 있도록 스프링은 
`Environment`와 `PropertySource`라는 추상화를 통해서 해결한다. (`EnvironmentCheck`)
- 만약 중복된 설정이 있다면, 가장 우선순위가 높은 설정이 적용된다.
- 우선순위는 상식 선에서 2가지를 기억하면 된다.
  - 더 유연한 것이 우선권을 가진다. (변경하기 어려운 파일보다 실행 시 원하는 값을 줄 수 있는 자바 시스템 속성이 우선권을 가진다.)
  - 범위가 넓은 것보다 좁은 것이 우선권을 가진다. (OS 환경변수보다 자바 시스템 속성이 우선권을 가진다.)

### 실행 시점에 내부 설정 파일 조회
- 예)
  - `application-local.yml` -> 로컬 환경에서 사용
  - `application-dev.yml` -> 개발 환경에서 사용
  - `application-prod.yml` -> 운영 환경에서 사용
- 프로필
  - 스프링은 위와 같이 환경에 따라 설정 파일을 나눠서 사용할 수 있는 기능을 제공하는데 프로필이라는 개념을 통해서 이를 구현한다.
  - `spring.prfiels.active` 프로퍼티를 통해서 프로필을 지정할 수 있다.
    - `application-{profile}.yml`
- 실행
  - IDE에서 커맨드 라인 옵션 인수 실행: `--spring.profiles.active=dev`
  - IDE에서 자바 시스템 속성 실행: `-Dspring.profiles.active=dev`
  - Jar 실행:
    - `java -jar -Dspring.profiles.active=dev app.jar`
    - `java -jar app.jar --spring.profiles.active=dev`