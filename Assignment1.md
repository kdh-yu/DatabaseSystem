# Database Systems Assignment#1
2022094093 김도훈, Dept. of Data Science

## Summary
Postgresql과 JDBC를 설치하고, 이를 연결하였다.

## Specification
Driver를 찾을 수 없다는 오류 메시지를 해결하기 위해, 다음을 시도하였다.  
1. jdk 삭제 및 재설치, 환경변수 추가
2. maven 설치 및 이를 사용한 프로젝트 생성

물론 두 시도 모두 실패하였고, 현 개발 환경(vscode)에서 과제를 이어나가기 위해 code runner의 설정을 편집하였다.
```
.
├── .vscode
│   ├── postgresql-42.6.0.jar
│   └── settings.json
├── Assignment1.md
├── readme.md
├── test.class
└── test.java
```
.vscode 폴더 내에 settings.json을 만들어 다음과 같은 라인을 추가하여 문제를 해결하였다.
```json
"code-runner.executorMap": {
    "java": "javac -cp .vscode/postgresql-42.6.0.jar $fileName && java -cp .vscode/postgresql-42.6.0.jar:. $fileNameWithoutExt",
},
```
.vscode 폴더로 드라이버(.jar 파일)를 옮기고, classpath를 지정한 후 정상적으로 작동하였다.

## Design and Implementation
사용하고 있는 맥OS에서 실습을 위한 환경을 만들기 위해, Homebrew를 사용해 postgresql을 설치하였다.
```bash
brew install postgresql
```
postgresql을 시작하거나 끝내기 위해 다음의 커맨드를 사용할 수 있음을 알았다.
```bash
brew services start postgresql  # 서비스 시작
brew services stop postgresql  # 서비스 종료
psql postgresql  # SQL 쿼리 작성
```
또한 __postgresql-42.6.0.jar__, 즉 jdbc 드라이버 또한 다운로드하였다.

## Testing
자바를 사용해 데이터베이스에 연결할 수 있는지를 확인하기 위해, 다음의 자바 코드를 작성하였다.
```java
import java.sql.*;

public class test {
    public static void main(String[] args) {
        try {
            String dbID = "ID",
                    dbPassword = "PW",  // ID 및 PW는 가렸습니다.
                    dbURL = "jdbc:postgresql://localhost/",
                    dbName = "prac1";
            
            Connection connection = DriverManager.getConnection(dbURL+dbName, dbID, dbPassword);
            System.out.println("Successed to connect.");
        } catch (SQLException e) {
            System.out.println("Failed to connect.");
            System.out.println("Error message : " + e.getMessage());
        }
    }
}
```

출력
```bash
>> javac -cp .vscode/postgresql-42.6.0.jar test.java && java -cp .vscode/postgresql-42.6.0.jar:. test
Successed to connect.
```

정상적으로 연결되었음을 확인하였다.

## Comments
작성된 코드는 깃허브에 업로드되어 있습니다.