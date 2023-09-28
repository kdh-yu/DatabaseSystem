# Database Systems Assignment#1
2022094093 김도훈 데베시 과제1  
Dept. of Data Science

## Summary
1. Postgresql과 JDBC를 설치하고, 이를 연결하였다. 
2. 아래 첨부된 코드를 사용해 드라이버를 사용해 데이터베이스와 연결이 가능함을 확인하였다. 
3. 실습 자료에 첨부된 자바 파일을 침고해 구성한 코드로, SQL 쿼리를 자바에서 또한 사용할 수 있음을 확인하였다.

## Specification
사용하고 있는 MacOS에서 실습을 위한 환경을 만들기 위해, Homebrew를 사용해 postgresql을 설치하였다.
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

## Design and Implementation
아래에 첨부된 Testing을 통해, 이번 과제의 목표를 달성하였다.
## Testing - Connection
자바를 사용해 데이터베이스에 연결할 수 있는지를 확인하기 위해, 다음의 자바 코드를 작성하였다.
```java
import java.sql.*;

public class test {
    public static void main(String[] args) {
        try {
            String dbID = "tt",
                    dbPassword = "tt",
                    dbURL = "jdbc:postgresql://localhost/",
                    dbName = "postgres";
            
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
```
>> javac -cp .vscode/postgresql-42.6.0.jar test.java && java -cp .vscode/postgresql-42.6.0.jar:. test
Successed to connect.
```

정상적으로 연결되었음을 확인하였다.  

## Testing - Query
실습 자료에 포함되어있는 자바 코드를 사용해, 쿼리가 제대로 실행됨 또한 확인했다. 아래는 SQL 쿼리를 전송하는 코드만을 첨부하였다.
```java
// INSERT QUERY
PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
preparedStatement.setInt(1, 06);
preparedStatement.setString(2, "Arnold");
preparedStatement.setString(3, "A");
preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
preparedStatement.executeUpdate();
System.out.println("record inserted successfully");

// SELECT QUERY
PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
ResultSet resultSet = preparedStatement.executeQuery();

// UPDATE QUERY
PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
preparedStatement.executeUpdate();
System.out.println("record updated successfully");

// DELETE QUERY
PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
preparedStatement.executeUpdate();
System.out.println("record deleted successfully");
```

## Known Problems
- 드라이버 연결 문제  

Driver를 찾을 수 없다는 오류 메시지를 해결하기 위해, 다음을 시도하였다.  
1. jdk 삭제 및 재설치, 환경변수 추가
2. maven 설치 및 이를 사용한 프로젝트 생성

물론 두 시도 모두 실패하였고, 현 개발 환경(vscode)에서 과제를 이어나가기 위해 code runner의 설정을 편집하였다. 자바의 컴파일 및 실행 과정에서 classpath를 임의로 지정하여 문제가 해결되었다.
```
.
├── .vscode
│   ├── postgresql-42.6.0.jar
│   └── settings.json
├── Assignment_1
│   ├── Assignment1.md
│   ├── Delete.class
│   ├── Delete.java
│   ├── Insert.class
│   ├── Insert.java
│   ├── Select.class
│   ├── Select.java
│   ├── Student.class
│   ├── Update.class
│   └── Update.java
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
.vscode 폴더로 드라이버(.jar 파일)를 옮기고, classpath를 지정하니 정상적으로 작동하였다. 다만, 다른 폴더 내 (ex, Assignment_1)에서는 실행이 되지 않는 문제가 남아있기에, 다음 과제까지는 해결하고자 한다.

## Comments
작성된 코드는 깃허브에 업로드되어 있습니다.  
https://github.com/kdh-yu/DatabaseSystem/blob/main/Assignment1.md