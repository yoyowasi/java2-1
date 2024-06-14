# 유민혁 학번 202130419
## 6월 14일 강의
#### 내용정리 
자바의 입출력 스트림 종류
문자 스트림
문자를 입출력하는 스트림
문자가 아닌 바이너리 데이터는 스트림에서 처리하지 못함
문자가 아닌 데이터를 문자 스트림으로 출력하면 깨진 기호가 출력
바이너리 파일을 문자 스트림으로 읽으면 읽을 수 없는 바이트가 생겨서 오류 발생
ex) 텍스트 파일을 읽는 입력 스트림
바이트 스트림
입출력 데이터를 단순 바이트의 흐름으로 처리
문자 데이터든 바이너리 데이터든 상관없이 처리 가능
ex) 바이너리 파일을 읽는 입력 스트림

스트림 연결의 흐름
System.in에서 바이트 입력
바이트 스트림을 InputStreamReader를 통해 문자 입력 스트림으로 변환
변환된 문자 스트림을 자바 응용 프로그램에서 처리

## 문자 스트림으로 텍스트 파일 읽기

텍스트 파일을 읽기 위해 문자 스트림 `FileReader` 클래스를 이용합니다.

### 1. 파일 입력 스트림 생성 (파일 열기)
- 스트림을 생성하고 파일을 열어 스트림과 연결합니다.

```java
FileReader fin = new FileReader("C:\\W\\test.txt");
```

### 2. 파일 읽기
- `read()`로 문자 하나씩 파일에서 읽습니다.

```java
int c;
while((c = fin.read()) != -1) { // 문자를 c에 읽음. 파일 끝까지 반복
    System.out.print((char)c); // 문자 c 화면에 출력
}
```

### 3. 스트림 닫기
- 스트림이 더 이상 필요 없으면 닫아야 합니다. 닫힌 스트림에서는 읽을 수 없습니다.

```java
fin.close();
```

## 파일 입출력과 예외 처리

파일 입출력 동안 예외가 발생할 수 있습니다.

### 1. 스트림 생성 동안 예외 발생 가능
- 스트림 생성 시 `FileNotFoundException`이 발생할 수 있습니다.
  - 파일의 경로명이 틀리거나, 디스크의 고장 등으로 파일을 열 수 없음

```java
FileReader fin = new FileReader("C:\\W\\test.txt"); // FileNotFoundException 발생 가능
```

### 2. 파일 읽기, 쓰기, 닫기를 하는 동안 예외 발생 가능
- 디스크 오류 또는 파일이 중간에 깨진 경우, 디스크 공간이 모자라서 파일 입출력 불가

```java
int c = fin.read(); // IOException 발생 가능
```

### 3. try-catch 블록 사용 필요
- 자바 컴파일러의 강제 사항입니다.

```java
try {
    FileReader fin = new FileReader("C:\\W\\test.txt");
    int c = fin.read();
    fin.close();
} catch (FileNotFoundException e) {
    System.out.println("파일을 열 수 없음");
} catch (IOException e) {
    System.out.println("입출력 오류");
}
```

## 문자 스트림으로 텍스트 파일 쓰기

텍스트 파일에 쓰기 위해 문자 스트림 `FileWriter` 클래스를 이용합니다.

### 1. 파일 출력 스트림 생성 (파일 열기)
- 스트림을 생성하고 파일을 열어 스트림과 연결

```java
FileWriter fout = new FileWriter("c:\\W\\Temp\\test.txt");
```

### 2. 파일 쓰기
- `write()`로 문자 하나씩 파일에 기록

```java
fout.write('A'); // 문자 'A'를 파일에 기록
```

- 블록 단위로 쓰기 가능

```java
char[] buf = new char[1024];
fout.write(buf, 0, buf.length); // buf[0]부터 버퍼 크기만큼 쓰기
```

### 3. 스트림 닫기
- `close()`로 스트림 닫기

```java
fout.close(); // 스트림 닫기. 더 이상 스트림에 기록할 수 없음.
```
## 바이트 스트림으로 바이너리 파일 쓰기

바이너리 값을 파일에 저장하기 위해 프로그램 내의 변수, 배열, 버퍼에 든 바이너리 값을 파일에 그대로 기록하는 방법입니다. `FileOutputStream` 클래스를 이용합니다.

### 1. 파일 출력 스트림 생성 (파일 열기)
- 스트림을 생성하고 파일을 열어 스트림과 연결

```java
FileOutputStream fout = new FileOutputStream("c:\\W\\Temp\\test.out");
```

### 2. 파일 쓰기
- `write()`로 배열의 값을 하나씩 파일에 기록

```java
byte[] b = {7, 51, 3, 4, -1, 24};
for(int i = 0; i < b.length; i++) 
    fout.write(b[i]); // 배열 b를 바이너리 그대로 기록
```

### 3. 스트림 닫기
- `close()`로 스트림 닫기

```java
fout.close(); // 스트림 닫기
```
## File 클래스

### File 클래스
- 파일의 경로명 및 속성을 다루는 클래스
  - `java.io.File`
  - 파일과 디렉터리 경로명의 추상적 표현
- 파일 이름 변경, 삭제, 디렉터리 생성, 크기 등 파일 관리
- `File` 객체에는 파일 읽기/쓰기 기능 없음
  - 파일 입출력은 파일 입출력 스트림 이용

### File 객체 생성
- 생성자에 파일 경로명을 주어 `File` 객체 생성

```java
File f = new File("c:\\W\\Temp\\test.txt");
```

- 디렉터리와 파일명을 나누어 생성자 호출

```java
File f = new File("c:\\W\\Temp", "test.txt");
```
## File 클래스 활용

### 파일 크기
```java
long size = f.length();
```

### 파일 경로명
```java
File f = new File("c:\\windows\\system.ini");
String filename = f.getName(); // "system.ini"
String path = f.getPath(); // "c:\\windows\\system.ini"
String parent = f.getParent(); // "c:\\windows"
```

### 파일 타입
```java
if (f.isFile())
    System.out.println(f.getPath() + "는 파일입니다."); // 파일
else if (f.isDirectory())
    System.out.println(f.getPath() + "는 디렉터리입니다."); // 디렉터리
```
예시 출력: `c:\\windows\\system.ini는 파일입니다.`

### 디렉터리 파일 리스트 얻기
```java
File f = new File("c:\\W\\Temp");
File[] subfiles = f.listFiles(); // c:\\W\\Temp의 파일 및 서브 디렉터리 리스트 얻기

for (int i = 0; i < subfiles.length; i++) {
    System.out.print(subfiles[i].getName()); // 서브 파일명 출력
    System.out.println(" 파일 크기: " + subfiles[i].length()); // 서브파일 크기 출력
}
```
## TCP/IP 소개

### TCP/IP 프로토콜
- 두 시스템 간에 데이터가 손상없이 안전하게 전송되도록 하는 통신 프로토콜
- TCP에서 동작하는 응용프로그램 사례
  - e-mail, FTP, 웹(HTTP) 등

### TCP/IP 특징
- 연결형 통신
  - 한 번 연결 후 계속 데이터 전송 가능
- 보낸 순서대로 받아 응용프로그램에게 전달

### 계층 구조
- **응용프로그램**: HTTP, e-mail, FTP 등
- **Transport**: TCP 등
- **Network**: IP 등
- **Link**: 디바이스 드라이버 등

### IP 주소
- 네트워크 상에서 유일하게 식별될 수 있는 컴퓨터 주소
- 숫자로 구성된 주소
  - 4개의 숫자가 `.`으로 연결
  - 예) `192.168.1.1`

### 도메인 이름
- 숫자로 된 주소는 기억하기 어려우므로 `www.naver.com`과 같은 문자열로 구성된 도메인 이름으로 바꿔 사용
  - DNS(Domain Name System)
    - 문자열로 구성된 도메인 이름을 숫자로 구성된 IP 주소로 자동 변환

### IP 버전
- 현재는 32비트의 IP 버전 4(IPv4)가 사용되고 있음
  - IP 주소 고갈로 인해 128비트의 IP 버전 6(IPv6)이 점점 사용되는 추세

### 로컬 IP 주소
- 자신의 IP 주소를 간단히 `localhost`라는 이름으로 사용 가능

### 내 IP주소 확인하기
- ipconfg 사용하여 확인할수있음

## 포트

### 포트
- 통신하는 프로그램 간에 가상의 연결단 포트 생성
  - IP 주소는 네트워크 상의 컴퓨터 또는 시스템을 식별하는 주소
  - 포트 번호를 이용하여 통신할 응용프로그램 식별
- 모든 응용프로그램은 하나 이상의 포트 생성 가능
  - 포트를 이용하여 상대방 응용프로그램과 데이터 교환

### 잘 알려진 포트 (well-known ports)
- 시스템이 사용하는 포트 번호
- 잘 알려진 응용프로그램에서 사용하는 포트 번호
  - 0번부터 1023번 사이의 포트 번호
  - 예: SSH 22, HTTP 80, FTP 21

### 포트 번호 사용
- 잘 알려진 포트 번호는 개발자가 사용하지 않는 것이 좋음
  - 충돌 가능성이 있음

## 소켓 프로그래밍

### 소켓 (socket)
- TCP/IP 네트워크를 이용하여 쉽게 통신 프로그램을 작성하도록 지원하는 기본 기술

### 소켓의 기능
- 두 응용프로그램 간의 양방향 통신 링크의 한쪽 끝 단
- 소켓끼리 데이터를 주고받음
- 소켓은 특정 IP 포트 번호와 결합

### 자바의 소켓 통신
- 자바로 소켓 통신할 수 있는 라이브러리 지원

### 소켓 종류
- 서버 소켓과 클라이언트 소켓

### 예시
- 웹 브라우저와 웹 서버 간의 통신에서 소켓과 포트를 이용하여 데이터를 주고받음

Socket 클래스, 클라이언트 소켓
Socket 클래스
클라이언트 소켓에 사용되는 클래스
java.net 패키지에 포함

## 클라이언트에서 소켓으로 서버에 접속하는 코드

### 클라이언트 소켓 생성 및 서버에 접속
```java
Socket clientSocket = new Socket("128.12.1.1", 9999);
```
- `Socket`의 생성자에서 128.12.1.1의 주소의 9999포트에 접속

### 소켓으로부터 데이터를 전송할 입출력 스트림 생성
```java
BufferedReader in = new BufferedReader(
    new InputStreamReader(clientSocket.getInputStream()));
BufferedWriter out = new BufferedWriter(
    new OutputStreamWriter(clientSocket.getOutputStream()));
```

### 서버로 데이터 전송
- `flush()`를 호출하면 스트림 속에 데이터 모두 전송

```java
out.write("hello" + "\n");
out.flush();
```

### 서버로부터 데이터 수신
```java
String line = in.readLine(); // 서버로부터 한 행의 문자열 수신
```

### 네트워크 접속 종료
```java
clientSocket.close();
```
## 서버에 클라이언트가 연결되는 과정

### 1. 서버는 서버 소켓으로 들어오는 연결 요청을 기다림 (listen)
- 서버 소켓은 특정 포트에서 클라이언트의 연결 요청을 대기

### 2. 클라이언트가 서버에게 연결 요청
- 클라이언트는 서버의 IP 주소와 포트 번호로 연결 요청을 보냄

### 3. 서버가 연결 요청 수락 (accept)
- 서버는 새로운 클라이언트 소켓을 만들어 클라이언트와 통신하게 함
- 그리고 다시 다른 클라이언트의 연결을 기다림

### 과정 요약
1. 서버는 listen 상태에서 클라이언트의 연결 요청을 기다림
2. 클라이언트가 서버에게 연결 요청을 보냄
3. 서버는 연결 요청을 수락하여 새로운 클라이언트 소켓을 만듦
4. 서버는 클라이언트와 통신을 시작하고, 동시에 다른 클라이언트의 연결 요청을 계속 대기

## 서버에 클라이언트가 연결되는 과정

1. **서버는 서버 소켓으로 들어오는 연결 요청을 기다림 (listen)**
   - 서버는 특정 포트에서 들어오는 연결 요청을 수신 대기합니다.

2. **클라이언트가 서버에게 연결 요청**
   - 클라이언트는 서버 소켓에 연결 요청을 보냅니다.

3. **서버가 연결 요청 수락 (accept)**
   - 새로운 클라이언트 소켓을 만들어 클라이언트와 통신하게 함
   - 그리고 다시 다른 클라이언트의 연결을 기다림


## 서버가 클라이언트와 통신하는 과정

1. **서버 소켓 생성**
   ```java
   ServerSocket serverSocket = new ServerSocket(9999);
   ```
   - 서버는 9999 포트에서 접속 대기합니다.

2. **클라이언트로부터 접속 기다림**
   ```java
   Socket socket = serverSocket.accept();
   ```
   - `accept()` 메소드는 접속 요청이 오면 접속 후 새 Socket 객체를 반환합니다.
   - 접속 후 새로 만들어진 Socket 객체를 통해 클라이언트와 통신합니다.

3. **네트워크 입출력 스트림 생성**
   ```java
   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
   ```
   - Socket 객체의 `getInputStream()`과 `getOutputStream()` 메소드를 이용하여 입출력 데이터 스트림을 생성합니다.

## 서버-클라이언트 채팅 프로그램 만들기

### 간단한 채팅 프로그램
- 서버와 클라이언트가 1:1로 채팅
- 클라이언트와 서버가 서로 한 번씩 번갈아 가면서 문자열 전송
- 문자열 끝에 "\n"을 덧붙여 보내고 라인 단위로 수신
- 클라이언트가 "bye"를 보내면 프로그램 종료

### 채팅 프로그램 흐름
1. **연결 대기**
   - 서버는 클라이언트의 연결 요청을 대기
2. **연결 요청**
   - 클라이언트가 서버에 연결 요청을 보냄
3. **새로운 소켓으로 연결**
   - 서버가 새로운 소켓을 생성하여 클라이언트와 연결
4. **메시지 전송**
   - 클라이언트: "안녕?\n"
   - 서버: "너도 안녕\n"
5. **연결 종료**
   - 클라이언트가 "bye\n"를 보내면 연결 종료


## 6월 7일 강의
#### 내용 정리
## 스윙 컴포넌트 그리기: `paintComponent()`

```java
public void paintComponent(Graphics g)
```
## 스윙의 페인팅 기본
- 모든 컴포넌트는 자신의 모양을 스스로 그린다.
- 컨테이너는 자신을 그린 후 그 위에 자식 컴포넌트들에게 그리기 지시를 한다.
- 모든 스윙 컴포넌트는 자신의 모양을 그리는 `paintComponent()` 메소드를 보유하고 있다.

## `paintComponent()` 메소드
- 스윙 컴포넌트가 자신의 모양을 그리는 메소드이다.
- Component의 메소드로 스윙 컴포넌트가 이 메소드를 오버라이딩한다.

### 언제 호출되는가?
- 컴포넌트가 `그려져야 하는 시점마다` 호출된다.
  - 크기가 변경되거나 위치가 변경되거나, 컴포넌트가 가려졌던 것이 사라지는 등

### 매개변수인 `Graphics` 객체
- 그래픽 컨텍스트: 컴포넌트 그리기에 필요한 도구를 제공하는 객체이다.
- 자바 플랫폼에 의해 공급된다.

### 주요 기능
- 색상 설정
- 도형 그리기
- 이미지 그리기 등의 메소드 제공

## paintComponent()의 오버라이딩과 JPanel

### paintComponent(Graphics g)의 오버라이딩

- `paintComponent(Graphics g)` 메서드는 그래픽 컨텍스트를 이용하여 컴포넌트를 그릴 때 사용되는 메서드입니다.
- 개발자가 `JComponent`를 상속받아 새로운 컴포넌트를 설계할 때 주로 사용됩니다.
- 기존 컴포넌트의 모양을 변화시키거나, 새로운 모양을 만들고자 할 때 유용하게 활용됩니다.
- 예시 코드:
  ```java
  class MComponent extends JComponent {
      @Override
      public void paintComponent(Graphics g) {
          super.paintComponent(g);
          // 필요한 그리기 코드 작성
      }
  }

## JPanel
- JPanel은 비어 있는 컨테이너로, 다양한 GUI를 구성할 수 있는 캔버스로 활용됩니다. 개발자가 임의의 모양을 가지는 패널을 만들기 위해 JPanel을 상속받아 사용됩니다. JPanel을 이용하면 다양한 구성요소들을 자유롭게 배치하고 사용할 수 있습니다.

## 예제 11-1: JPanel을 상속받은 패널에 도형 그리기

- JPanel을 상속받아 패널을 구성하고, 이곳에 그림과 같은 3개의 도형을 그리는 예제입니다.

### 코드 예제

```java
import javax.swing.*;
import java.awt.*;

public class PaintJPanelEx extends JFrame {
    public PaintJPanelEx() {
        setTitle("Panel paintComponent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(250, 200);
        setVisible(true);
    }

    // JPanel을 상속받는 새 패널 구현
    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE); // 파란색 선택
            g.drawRect(10, 10, 50, 50);
            g.drawRect(50, 50, 50, 50);
            g.setColor(Color.MAGENTA); // 마젠타색 선택
            g.drawRect(90, 90, 50, 50);
        }
    }

    public static void main(String[] args) {
        new PaintJPanelEx();
    }
}
```

### PaintJPanelEx 클래스 설명

- `PaintJPanelEx` 클래스는 `JFrame`을 상속받아 GUI 창을 생성합니다.
- 생성자에서는 제목을 설정하고, 기본 닫기 동작을 정의하며, 커스텀 패널인 `MyPanel`을 컨텐트 패널로 설정합니다.

### MyPanel 클래스 설명

- `MyPanel` 클래스는 `JPanel`을 상속받아 새 패널을 구현합니다.
- `paintComponent` 메서드를 오버라이드하여 도형을 그립니다.
  - `super.paintComponent(g)`를 호출하여 패널을 초기화합니다.
  - `g.setColor(Color.BLUE)`로 파란색을 선택하고, `g.drawRect(10, 10, 50, 50)`와 `g.drawRect(50, 50, 50, 50)`로 두 개의 파란색 사각형을 그립니다.
  - `g.setColor(Color.MAGENTA)`로 마젠타색을 선택하고, `g.drawRect(90, 90, 50, 50)`로 마젠타색 사각형을 그립니다.

## main 메서드 설명

- `main` 메서드에서는 `PaintJPanelEx` 인스턴스를 생성하여 프로그램을 시작합니다.

## 그래픽 기반 GUI 프로그래밍

### 그래픽 기반 GUI 프로그래밍이란?

`그래픽 기반 GUI 프로그래밍은 스윙 컴포넌트에 의존하지 않고 선, 원, 이미지 등을 이용하여 직접 화면을 구성하는 방법을 말합니다.`

### 그래픽 기반 GUI 프로그래밍의 필요성

- 컴포넌트의 한계를 극복하고 차트, 게임 등의 자유로운 콘텐츠를 표현할 수 있습니다.
- 그래픽은 컴포넌트에 비해 화면 출력 속도가 빠르므로, 성능이 중요한 애플리케이션에서 유용합니다.
- 스윙 컴포넌트들은 모두 그래픽으로 작성되어 있기 때문에, 그래픽에 대한 학습은 GUI의 기반 기술을 이해하는데 도움이 됩니다.
- 그래픽을 이용하여 개발자 자신만의 컴포넌트를 개발할 수 있습니다.

## Graphics와 문자열 출력

### Graphics의 기능

- `색상 선택하기`
- `문자열 그리기`
- `도형 그리기`
- `도형 칠하기`
- `이미지 그리기`
- `클리핑`

### 문자열 출력을 위한 Graphics 메소드

```java
void drawString(String str, int x, int y)
```
- *str 문자열을 (x, y) 위치에 그립니다. 현재 Graphics 설정된 색과 폰트로 문자열이 출력됩니다.*

```java
Graphics g;
g.drawString("자바는 재밌다", 30, 30); // (30, 30) 위치에 문자열 출력
```

### 그래픽의 색과 폰트

- 색: `Color` 클래스를 사용합니다. 자바의 색은 (Red, Green, Blue) 성분으로 구성되며, 각 성분은 0에서 255(8비트) 사이의 정수값입니다. 예를 들어, 빨간색은 `new Color(255, 0, 0)`으로 표현됩니다.

- 폰트: `Font` 클래스를 사용합니다. `Font(String fontFace, int style, int size)` 생성자를 사용하여 폰트를 설정합니다. `fontFace`는 폰트 이름, `style`은 폰트 스타일 (BOLD, ITALIC, PLAIN 중 하나), `size`는 폰트 크기 (픽셀 단위)를 나타냅니다.

```java
raphics에 색과 폰트 설정
void setColor(Color color)
```
- 그래픽의 색을 설정합니다. 이 색은 그릴 때 사용됩니다.

```java
void setFont(Font font)
```
- 그래픽에 사용할 폰트를 설정합니다.

예시:
```java
Graphics g;
Font f = new Font("Arial", Font.ITALIC, 30);
g.setFont(f);
g.setColor(Color.RED);
g.drawString("How much", 30, 30);
```
- 위 예시에서는 "How much"라는 문자열을 "Arial" 폰트의 빨간색으로 (30, 30) 위치에 출력합니다.

## 도형 그리기와 칠하기

### < 도형 그리기 >

### 선, 타원, 사각형, 둥근 모서리 사각형, 원호, 다각형 그리기

- **선(Line) 그리기:** `drawLine(int x1, int y1, int x2, int y2)` 메서드를 사용하여 (x1, y1)에서 (x2, y2)까지 선을 그립니다.

- **타원(Oval) 그리기:** `drawOval(int x, int y, int width, int height)` 메서드를 사용하여 (x, y)를 왼쪽 상단 모서리로 하는 타원을 그립니다.

- **사각형(Rectangle) 그리기:** `drawRect(int x, int y, int width, int height)` 메서드를 사용하여 (x, y)를 왼쪽 상단 모서리로 하는 사각형을 그립니다.

- **둥근 모서리 사각형(Round Rectangle) 그리기:** `drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)` 메서드를 사용하여 (x, y)를 왼쪽 상단 모서리로 하는, 너비가 width이고 높이가 height인 사각형의 모서리를 둥글게 그립니다. `arcWidth`와 `arcHeight`는 모서리 원의 수평 반지름과 수직 반지름입니다.

- **원호(Arc) 그리기:** `drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)` 메서드를 사용하여 (x, y)를 시작점으로 하는 원호를 그립니다. startAngle은 시작 각도이고, arcAngle은 호의 각도입니다.

- **다각형(Polygon) 그리기:** `drawPolygon(int[] xPoints, int[] yPoints, int nPoints)` 메서드를 사용하여 주어진 점(xPoints, yPoints)들을 연결하여 다각형을 그립니다.

## 도형 칠하기

도형을 그린 후 내부를 채우는 기능입니다. 그리기 메서드 명에서 `draw` 대신 `fill`을 사용하여 내부를 채울 수 있습니다. 예를 들어, `drawRect()` 대신 `fillRect()`를 사용하여 사각형 내부를 채울 수 있습니다.

## Graphics의 원호와 폐다각형 그리기 메소드

### 원호(Arc) 그리기

```java
void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
```

#### 원호의 시작 각도

- **startAngle**: 원호의 시작 각도입니다. 3시 방향이 0도의 기준점입니다.

#### 원호의 각도

- **arcAngle**: 원호의 각도입니다.

#### (x, y)에서 width와 height 크기의 사각형에 내접하는 원호 그리기

- 원본을 유지하면서, startAngle 지점에서 arcAngle 각도만큼 원호를 그립니다. arcAngle이 양수이면 반시계 방향으로, 음수이면 시계 방향으로 그립니다.

### 폐다각형(직선으로 이루어진 다각형) 그리기
```java
void drawPolygon(int[] xPoints, int[] yPoints, int n)
```
- xPoints, yPoints 배열에 저장된 점들 중 n개를 연결하는 폐다각형을 그립니다.

- (xPoints[0], yPoints[0]), (xPoints[1], yPoints[1]), ..., (xPoints[n-1], yPoints[n-1]), (xPoints[0], yPoints[0])의 점들을 순서대로 연결합니다.

예제:
```java
class MyPanel extends JPanel ( public void paintComponent/Graphics g) ( super paintComponent(g): g setColor Color RED g.drawArc(20,100,80,80,90,270)
class MyPanel extends JPanel ( public void paintComponent(Graphics g) ( super paintComponent(g); g.setColor(Color.RED);
int x= (80,40,80,120); int ly (40,120,200,120); g.drawPolygon(x, y, 4);
```

## 스윙에서 이미지를 그리는 2가지 방법

1. **JLabel을 이용한 이미지 그리기**
    - 이미지를 JLabel을 통해 그립니다.
    ```java
    ImageIcon image = new ImageIcon("images/apple.jpg");
    JLabel label = new JLabel(image);
    panel.add(label);
    ```
    - **장점**: 이미지 그리기가 간편합니다.
    - **단점**: 이미지의 원본 크기대로 그려지므로 이미지 크기를 조절할 수 없습니다.

2. **Graphics의 drawImage()로 이미지 출력**
    - 이미지의 일부분을 그릴 수 있고, 원본 크기와 다르게 그릴 수 있습니다.
    - **장점**: 이미지의 일부분 등 이미지의 크기와 위치를 조절할 수 있습니다.
    - **단점**: 컴포넌트로 관리할 수 없으며, 이미지의 위치나 크기를 적절히 조절하는 코딩이 필요합니다.

### 이미지 그리기 샘플 코드

#### 이미지 로딩
```java
ImageIcon icon = new ImageIcon("image/image0.jpg");
Image img = icon.getImage();

// (20, 20) 위치에 원본 크기로 그리기 (고정 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 20, 20, this);
}

// (20, 20) 위치에 100x100 크기로 그리기 (고정 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 20, 20, 100, 100, this);
}

// 이미지를 패널에 꽉 차도록 그리기 (가변 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 20, 20, 100, 100, this);
}

// JPanel의 크기로 그리기 (가변 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
}
```

## repaint()

- **설명**: 자바의 모든 컴포넌트가 가지고 있는 메소드로, 자바 플랫폼에게 컴포넌트를 강제로 그리도록 지시합니다.
- `repaint()`가 호출되면, 자바 플랫폼은 컴포넌트의 `paintComponent()` 메소드를 호출합니다.

### `repaint()`를 호출해야 하는 경우:

1. 개발자가 컴포넌트를 다시 그리고자 할 때:
   - 프로그램에서 컴포넌트의 모양과 위치를 변경하고 즉시 화면에 반영해야 하는 상황에서 사용합니다.
   - 컴포넌트가 변경된 위치에 변경된 모양으로 즉시 다시 그려져야 할 때 사용됩니다.
   - `repaint()`는 자바 플랫폼에게 즉시 컴포넌트를 다시 그리도록 지시합니다.

### 최적의 사용 방법:

- 부모 컴포넌트부터 다시 그리는 것이 좋습니다.
- `component.repaint()`가 호출될 때:
  - 컴포넌트는 새로운 위치에 다시 그려지지만, 이전 모양은 이전 위치에 그대로 남아 있습니다.

### 부모 컴포넌트에서 `repaint()`를 호출하는 경우:

- 부모 컴포넌트에서 `repaint()`가 호출되면:
  - 부모 컨테이너의 모든 내용이 지워지고, 자식 컴포넌트들이 다시 그려집니다.
  - 결과적으로 컴포넌트의 이전 모양이 지워지고, 변경된 크기나 위치에 새롭게 다시 그려집니다.

예시:
```java
component.getParent().repaint();
```
### 코드예제
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsDrawOvalMouseEx extends JFrame {

    public GraphicsDrawOvalMouseEx() {
        setTitle("마우스 드래깅으로 타원 그리기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicsDrawOvalMouseEx();
    }
    
    // 타원을 그릴 패널 작성. 이 패널에 마우스 리스너 구현
    class MyPanel extends JPanel {
        private Point start = null, end = null;

        public MyPanel() {
            MyMouseListener listener = new MyMouseListener();
            // listener를 아래 두 리스너로 공통으로 등록해야 한다.
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                repaint(); // 패널의 그리기 요청 주목
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (start == null)
                return;
            g.setColor(Color.BLUE);
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            g.drawOval(x, y, width, height);
        }
    }
}

```
### 코드예제 2
```java
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineMouseEx extends JFrame {

    public GraphicsDrawLineMouseEx() {
        setTitle("마우스로 여러 개의 선 그리기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicsDrawLineMouseEx();
    }

    class MyPanel extends JPanel {
        private Vector<Point> vStart = new Vector<Point>();
        private Vector<Point> vEnd = new Vector<Point>();

        public MyPanel() {
            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                vStart.add(e.getPoint());
                vEnd.add(e.getPoint());
                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                int lastIndex = vEnd.size() - 1;
                vEnd.set(lastIndex, e.getPoint());
                repaint();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (int i = 0; i < vStart.size(); i++) {
                Point start = vStart.get(i);
                Point end = vEnd.get(i);
                g.drawLine(start.x, start.y, end.x, end.y);
            }
        }
    }
}
```

### 멀티태스킹 ( multi-tasking ) 개념
- 멀티태스킹
    - 여러 개의 작업(태스크)이 동시에 처리되는 것

# 스레드와 운영체제

## 스레드(thread)
- 운영체제에 의해 관리되는 하나의 작업 혹은 태스크
- 스레드와 태스크(혹은 작업)은 바꾸어 사용해도 무관

## 멀티스레딩(multi-threading)
- 여러 스레드를 동시에 실행시키는 응용프로그램을 작성하는 기법

### 스레드 구성
- 스레드 코드
  - 작업을 실행하기 위해 작성한 프로그램 코드
  - 개발자가 작성
- 스레드 정보
  - 스레드 명, 스레드 ID, 스레드의 실행 소요 시간, 스레드의 우선 순위 등
  - 운영체제가 스레드에 대해 관리하는 정보


### 멀티태스킹 구현 기술
- 멀티프로세싱(multi-processing)
  - 하나의 응용프로그램이 여러 개의 프로세스를 생성하고, 각 프로세스가 하나의 작업을 처리하는 기법
  - 각 프로세스 독립된 메모리 영역을 보유하고 실행
  - 프로세스 사이의 문맥 교환에 따른 과도한 오버헤드와 시간 소모의 문제점
- 멀티스레딩(multi-threading)
  - 하나의 응용프로그램이 여러 개의 스레드를 생성하고, 각 스레드가 하나의 작업을 처리하는 기법
  - 하나의 응용프로그램에 속한 스레드는 변수 메모리, 파일 오픈 테이블 등 자원으로 공유하므로, 문맥 교환에 따른 오버헤드가 매우 작음
  - 현재 대부분의 운영체제가 멀티스레딩을 기본으로 함

## 자바 스레드(Thread)와 JVM

### 자바 스레드
- 자바 가상 기계(JVM)에 의해 스케쥴되는 실행 단위의 코드 블럭
- 스레드의 생명 주기는 JVM에 의해 관리됨: JVM은 스레드 단위로 스케쥴링

### JVM과 자바의 멀티스레딩
- 하나의 JVM은 하나의 자바 응용프로그램만 실행
- 자바 응용프로그램이 시작될 때 JVM이 함께 실행됨
  - 자바 응용프로그램이 종료하면 JVM도 함께 종료함
- 응용프로그램은 하나 이상의 스레드로 구성 가능

# Thread 클래스를 상속받아 스레드 만들기

## Thread 클래스를 상속받아 스레드 만들기
- Thread 클래스 상속
- run() 메소드 오버라이딩
  - run() 메소드는 스레드 코드를 포함
  - run() 메소드에서 스레드 실행 시작

## 스레드 객체 생성 및 시작
- 스레드 객체 생성
  - Thread 클래스의 생성자 이용
- 스레드 시작
  - start() 메소드 호출
    - JVM에 의해 스케쥴되기 시작

## Thread 클래스의 주요 메소드
- `Thread()`
  - 스레드 객체 생성
- `Thread(Runnable target)`
  - Runnable 객체를 이용하여 스레드 객체 생성
- `Thread(String name)`
  - 이름이 있는 스레드 객체 생성
- `void run()`
  - 스레드 코드 작성을 위한 메소드
- `void start()`
  - JVM에게 스레드 실행 시작 요청
- `void interrupt()`
  - 스레드 강제 종료
- `static void yield()`
  - 다른 스레드에게 실행 양보
- `void join()`
  - 스레드가 종료할 때까지 대기
- `long getId()`
  - 스레드의 ID값 반환
- `String getName()`
  - 스레드의 이름 반환
- `int getPriority()`
  - 스레드의 우선순위 값 반환
- `void setPriority(int priority)`
  - 스레드의 우선순위 값을 변경
- `Thread.State getState()`
  - 스레드의 상태 값 반환
- `static void sleep(long millis)`
  - 스레드를 지정한 시간만큼 재우기
- `static Thread currentThread()`
  - 현재 실행 중인 스레드 객체의 레퍼런스 반환

### 예제 코드
- Thread를 상속받아 1초 단위로 초 시간을 출력하는
TimerThread 스레드 작성 사례

```java
// TimerThread 클래스 선언
class TimerThread extends Thread {
    // 타이머 값이 출력되는 레이블
    private Label timerLabel;

    // 생성자
    public TimerThread(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    // 스레드 코드 run()이 종료하면 스레드 종료
    @Override
    public void run() {
        // 타이머 카운트 값
        int n = 0;

        while (true) {
            // 타이머 값을 레이블에 설정
            timerLabel.setText(Integer.toString(n));
            n++; // 카운트 증가
            try {
                Thread.sleep(1000); // 1초 동안 잠을 잔 후 실행
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
```

### 예제코드 2
- Thread를 상속받아 1초 단위 타이머 스레드 만들기

```java
// TimerThread를 사용하는 예제
public class ThreadTimerEx extends JFrame {
    public ThreadTimerEx() {
        setTitle("Thread를 상속받은 타이머 스레드 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨테이너 설정
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 타이머 값을 출력할 레이블 생성
        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic", Font.ITALIC, 20));
        c.add(timerLabel);

        // 타이머 스레드 객체 생성
        TimerThread th = new TimerThread(timerLabel);

        setSize(250, 150);
        setVisible(true);

        // 타이머 스레드 시작
        th.start();
    }

    public static void main(String[] args) {
        new ThreadTimerEx();
    }
}
```
### Runnable 인터페이스로 스레드 만들기
```java
// Runnable을 구현하는 새 클래스 작성
class MyRunnable implements Runnable {
    // run() 메소드 구현
    @Override
    public void run() {
        // 스레드 코드 작성
        // 스레드 실행 시작
        System.out.println("스레드 실행 중");

        // 여기에 스레드가 수행할 작업을 작성합니다.
        // 예를 들어, 파일을 읽거나 네트워크 요청을 보낼 수 있습니다.
    }
}
```

```java
// 스레드 객체 생성 및 시작
public class ThreadExample {
    public static void main(String[] args) {
        // 스레드 객체 생성
        MyRunnable myRunnable = new MyRunnable();

        // 스레드 시작
        Thread thread = new Thread(myRunnable);
        thread.start();
        
        // start() 메소드 호출을 통해 스레드가 스케줄되어 JVM에 의해 실행됩니다.
    }
}
```
## Main 스레드
- main 스레드
    - JVM이 응용프로그램을 실행할 때 디폴트로 생성되는 스레드
    - main()메소드
    - main 메소드가 종료하면 main 쓰레드 종료

## 스레드 동기화(Thread Synchronization)

### 멀티스레드 프로그램 작성시 주의점
- 다수의 스레드가 공유 데이터에 동시에 접근하는 경우 공유 데이터의 값에 예상치 못한 결과가 발생할 수 있음.

### 스레드 동기화
- **동기화란?**
    - 스레드 사이의 실행 순서를 제어하고 공유 데이터에 대한 접근을 원활하게 하는 기법.
- 멀티스레드의 공유 데이터의 동시 접근 문제를 해결하기 위한 방법:
    1. 공유 데이터를 접근하는 모든 스레드를 한 줄로 세우기.
    2. 한 스레드가 공유 데이터에 대한 작업을 끝낼 때까지 다른 스레드가 대기하도록 함.

### 자바의 스레드 동기화 방법 - 2가지
1. `synchronized` 키워드로 동기화 블록 지정
2. `wait()`-`notify()` 메소드로 스레드의 실행 순서를 제어함.


## synchronized 블록 지정

### synchronized 키워드
- 스레드가 독점적으로 실행해야 하는 부분(동기화 코드)을 표시하는 키워드.
- 일제 영역(critical section) 표기 키워드.

### synchronized 블록 지정 방법
- 메소드 전체 혹은 코드 블록을 synchronized 블록으로 지정할 수 있음.

### synchronized 블록이 실행될 때
- 먼저 실행한 스레드가 모니터 소유.
    - 모니터: 해당 객체를 독점적으로 사용할 수 있는 권한.
- 모니터를 소유한 스레드가 모니터를 내놓을 때까지 다른 스레드는 대기해야 함.

## wait()-notify()를 이용한 스레드 동기화

### wait()-notify()가 필요한 경우
- 공유 데이터로 두 개 이상의 스레드가 데이터를 주고 받을 때, 예를 들어 producer-consumer 문제.

### 동기화 메소드
- **wait()**: 다른 스레드가 `notify()`를 호출할 때까지 기다립니다.
- **notify()**: `wait()`를 호출하여 대기 중인 스레드를 깨웁니다.
    - `wait()`와 `notify()`는 Object 클래스의 메소드입니다.

### 예시: 비디오 서버
- 비디오 버퍼를 채우고 대기 중인 재생 스레드를 깨웁니다 (`notify()`).
- 만약 버퍼가 비어 있으면 비디오 프레임이 도착할 때까지 대기합니다 (`wait()`).


## 5월 31일 강의
#### 내용 정리
스윙 컴포넌트의 이해/정의
스윙 컴포넌트는 자바에서 GUI를 구축하기 위해 제공하는 경량 컴포넌트입니다. 모든 스윙 컴포넌트는 `JComponent` 클래스를 상속받아 다양한 UI 요소를 제공합니다. 이를 통해 개발자는 윈도우, 버튼, 텍스트 필드 등 다양한 인터페이스 요소를 쉽게 구현할 수 있습니다.

자바의 GUI 프로그래밍 방법

컴포넌트 기반 GUI 프로그래밍
- 스윙 컴포넌트를 이용하여 쉽게 GUI를 구축
- 자바에서 제공하는 컴포넌트의 한계를 벗어나지 못함

그래픽 기반 GUI 프로그래밍
- 그래픽을 이용하여 GUI 구축
- 개발자가 직접 그래픽으로 화면을 구성하는 부담
- 다양한 GUI를 구축할 수 있는 장점
- GUI의 처리 실행 속도가 빨라, 게임 등에 주로 이용

추가 설명
- 사용자 인터페이스 제공: 그래픽 기반 GUI(Graphical User Interface)는 아이콘, 버튼, 윈도우 등 시각적 요소를 통해 사용자와 컴퓨터가 상호작용할 수 있게 하는 인터페이스입니다.
- 사용자 경험 향상: GUI는 명령어 기반 인터페이스(CLI)보다 직관적이고 사용하기 쉬워, 사용자 경험을 크게 향상시킵니다. 비전문가도 컴퓨터를 쉽게 사용할 수 있게 합니다.
- 그래픽 요소 활용: GUI는 그래픽 요소를 활용해 정보를 더 명확하고 이해하기 쉽게 전달합니다. 이를 통해 사용자들은 복잡한 작업도 쉽게 수행할 수 있습니다.
- 운영 체제와 애플리케이션 통합: GUI는 대부분의 현대 운영 체제와 애플리케이션에서 표준으로 사용되며, 윈도우, 맥OS, 리눅스 등의 시스템에서 다양한 소프트웨어와 함께 작동합니다.

컴포넌트 기반 GUI 프로그래밍에서 사용되는 스윙 컴포넌트

스윙 컴포넌트의 공통 메소드 - JComponent의 메소드

컴포넌트의 모양과 관련된 메소드
```java
- void setForeground(Color): 전경색 설정
- void setBackground(Color): 배경색 설정
- void setOpaque(boolean): 불투명성 설정
- void setFont(Font): 폰트 설정
- Font getFont(): 폰트 리턴
```

컴포넌트의 위치와 크기에 관련된 메소드
```java
- int getWidth(): 폭 리턴
- int getHeight(): 높이 리턴
- int getX(): x 좌표 리턴
- int getY(): y 좌표 리턴
- Point getLocationOnScreen(): 스크린 좌표상에서의 컴포넌트 좌표 리턴
- void setLocation(int, int): 위치 지정
- void setSize(int, int): 크기 지정
```

컴포넌트의 상태와 관련된 메소드
```java
- void setEnabled(boolean): 컴포넌트 활성화/비활성화 설정
- void setVisible(boolean): 컴포넌트 보이기/숨기기 설정
- boolean isVisible(): 컴포넌트의 보이는 상태 리턴
```

컨테이너를 위한 메소드
```java
- Component add(Component): 자식 컴포넌트 추가
- void remove(Component): 자식 컴포넌트 제거
- void removeAll(): 모든 자식 컴포넌트 제거
- Component[] getComponents(): 자식 컴포넌트 배열 리턴
- Container getParent(): 부모 컨테이너 리턴
- Container getTopLevelAncestor(): 최상위 부모 컨테이너 리턴
```

JComponent

1. 스윙 컴포넌트는 모두 상속받는 슈퍼 클래스: JComponent는 스윙 컴포넌트들이 공통으로 상속받는 `추상 클래스`입니다.
2. 스윙 컴포넌트들이 상속받는 공통 메소드와 상수 구현: JComponent는 다양한 메소드와 상수를 구현하여 스윙 컴포넌트들이 이를 활용할 수 있게 합니다.
3. JComponent의 주요 메소드 사례:
   - `setBackground(Color bg)`: 컴포넌트의 배경색을 설정합니다.
   - `setForeground(Color fg)`: 컴포넌트의 전경색(텍스트 색상 등)을 설정합니다.
   - `setFont(Font f)`: 컴포넌트의 폰트를 설정합니다.
   - `repaint()`: 컴포넌트를 다시 그립니다.
   - `addMouseListener(MouseListener l)`: 마우스 이벤트를 감지하기 위해 리스너를 추가합니다.

실제활용 예제 코드
```java
import javax.swing.*;
import java.awt.*;

public class CustomComponent extends JComponent {
    
    // 이 메서드는 컴포넌트를 그릴 때 호출됩니다.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 클래스의 기본 페인팅 작업 수행
        g.setColor(Color.BLUE); // 색상을 파란색으로 설정
        g.fillRect(10, 10, 100, 100); // (10, 10) 위치에 100x100 크기의 파란색 사각형 그리기
    }

    // 메인 메서드: 애플리케이션 실행 진입점
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom JComponent"); // 새로운 프레임 생성
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 애플리케이션 종료
        frame.add(new CustomComponent()); // 커스텀 컴포넌트를 프레임에 추가
        frame.setSize(200, 200); // 프레임 크기를 200x200으로 설정
        frame.setVisible(true); // 프레임을 화면에 표시
    }
}
```

## 레이블 생성 예
1. 문자열 레이블 생성
```java
JLabel textLabel = new JLabel("사랑합니다");
```

## 이미지 레이블 생성
1. 이미지 파일로부터 이미지를 읽기 위해 Imageicon 클래스 사용
2. 다를 수 있는 이미지 : png , gif , jpg
```java
--> sunset.jpg의 경로명이 "images/sumset.jpg"인 경우
```

## 수평 정령 값을 가진 레이블 컴포넌트 생성

1. 수평 정렬로, 문자열과 이미지를 모두 가진 레이블
```java
Imageicon image = new Imageicon("images/sunset.jpg");
JLabel textLabel = new JLabel("사랑합니다"), image, SwingConstants CENTER");
```

이미지 버튼 만들기

버튼에 3개의 이미지 등록하기

- 하나의 버튼에 3개의 이미지를 등록합니다.
- 마우스 조작에 따라 3개의 이미지 중 적절한 이미지가 자동으로 출력됩니다.

3개의 버튼 이미지

- normallcon
  - 버튼의 보통 상태(디폴트) 때 출력되는 이미지
  - 생성자에 이미지 아이콘을 전달하거나 `JButton`의 `setIcon(normalIcon)` 메소드를 사용합니다.
  
- rolloverlcon
  - 버튼에 마우스가 올라갈 때 출력되는 이미지
  - 이미지 설정 메소드: `JButton`의 `setRolloverIcon(rolloverIcon)`을 사용합니다.

- pressedicon
  - 버튼을 누른 상태 때 출력되는 이미지
  - 이미지 설정 메소드: `JButton`의 `setPressedIcon(pressedIcon)`을 사용합니다.

이미지 설정

이미지 로딩

필요한 이미지를 로딩합니다. 예시:
```java
ImageIcon normalIcon = new ImageIcon("images/normalIcon.gif");
ImageIcon rolloverIcon = new ImageIcon("images/rolloverIcon.gif");
ImageIcon pressedIcon = new ImageIcon("images/pressedIcon.gif");
```

버튼에 이미지 등록
- JButton의 메소드를 호출하여 이미지를 등록합니다. 예시:
```java
JButton button = new JButton("테스트버튼", normalIcon); // normalIcon 설정
button.setRolloverIcon(rolloverIcon);
button.setPressedIcon(pressedIcon);
```

실행 중에 이미지 변경
- 실행 중에는 다른 이미지로 변경할 수 있습니다. 예시:
```java
// rolloverIcon으로 변경
// pressedIcon으로 변경
ImageIcon newIcon = new ImageIcon("images/newIcon.gif");
button.setIcon(newIcon); // 디폴트 이미지 변경
```

체크박스의 Item 이벤트 처리

Item 이벤트

- 체크박스의 선택 상태에 변화가 생길 때 발생하는 이벤트입니다. 이는 사용자가 마우스나 키보드로 체크박스를 선택하거나 해제하거나, 프로그램에서 체크박스를 선택하거나 해제하여 체크 상태에 변화가 생길 때 발생합니다.

체크박스 설정

```java
JCheckBox checkbox = new JCheckBox("A");
checkbox.setSelected(true); // 선택 상태로 변경
```

이벤트 처리

- Item 이벤트가 발생하면 `ItemEvent` 객체가 생성됩니다.
- `ItemListener` 리스너를 이용하여 이벤트를 처리합니다.

ItemListener의 추상 메소드

- `void itemStateChanged(ItemEvent e)`: 체크박스의 선택 상태가 변하는 경우 호출됩니다.

ItemEvent의 주요 메소드

- `int getStateChange()`: 체크박스가 선택된 경우 `ItemEvent.SELECTED`, 해제된 경우 `ItemEvent.DESELECTED`를 리턴합니다.
- `Object getItem()`: 이벤트를 발생시킨 아이템 객체를 리턴합니다. 체크박스의 경우 `JCheckBox` 컴포넌트의 레퍼런스를 리턴합니다.


```java
checkbox.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 체크박스가 선택된 경우 처리
            System.out.println("체크박스가 선택되었습니다.");
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // 체크박스가 해제된 경우 처리
            System.out.println("체크박스가 해제되었습니다.");
        }
    }
});
```
JRadioButton으로 라디오버튼 만들기

JRadioButton의 용도

JRadioButton은 버튼 그룹을 형성하고, 그룹에 속한 버튼 중 하나만 선택되는 라디오버튼입니다.

체크박스와의 차이점

- 체크박스는 각각 선택 또는 해제가 가능하지만,
- 라디오버튼은 그룹에 속한 버튼 중 하나만 선택됩니다.

라디오버튼 생성

다양한 생성자를 사용하여 라디오버튼을 생성할 수 있습니다:

- `JRadioButton()`: 텍스트 없이 기본 라디오버튼을 생성합니다.
- `JRadioButton(Icon icon)`: 아이콘 이미지가 있는 라디오버튼을 생성합니다.
- `JRadioButton(Icon icon, boolean selected)`: 아이콘 이미지가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.

- `JRadioButton(String text)`: 텍스트가 있는 라디오버튼을 생성합니다.
- `JRadioButton(String text, boolean selected)`: 텍스트가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.
- `JRadioButton(String text, Icon icon)`: 텍스트와 아이콘 이미지가 있는 라디오버튼을 생성합니다.
- `JRadioButton(String text, Icon icon, boolean selected)`: 텍스트와 아이콘 이미지가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.

라디오버튼 생성 및 Item 이벤트 처리

버튼 그룹과 라디오버튼 생성 과정

```java
ButtonGroup group = new ButtonGroup();
JRadioButton apple = new JRadioButton("Apple");
JRadioButton pear = new JRadioButton("Pear");
JRadioButton cherry = new JRadioButton("Cherry");

group.add(apple);
group.add(pear);
group.add(cherry);

container.add(apple);
container.add(pear);
container.add(cherry);
```

라디오버튼에 Item 이벤트 처리
라디오버튼의 선택 상태 변경을 감지하기 위해 ItemListener를 사용합니다. 이벤트가 발생하면 setSelected()를 호출하여 선택 상태를 변경할 수 있습니다.

```java
apple.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 라디오버튼이 선택되었을 때 처리
        }
    }
});
```
JTextField로 한 줄 입력 창 만들기

JTextField는 한 줄의 문자열을 입력 받는 창, 즉 텍스트필드를 나타냅니다. 사용자가 텍스트를 입력하는 도중 <Enter> 키가 입력되면 Action 이벤트가 발생합니다. 입력 가능한 문자 개수와 입력 창의 크기는 서로 다를 수 있습니다.

텍스트필드 생성

다양한 생성자를 사용하여 텍스트필드를 생성할 수 있습니다:

- `JTextField()`: 빈 텍스트필드를 생성합니다.
- `JTextField(int cols)`: 입력 열의 갯수가 cols개인 텍스트필드를 생성합니다.
- `JTextField(String text)`: 초기 텍스트로 문자열 text를 갖는 텍스트필드를 생성합니다.
- `JTextField(String text, int cols)`: 입력 열의 수는 cols이고 초기 텍스트로 문자열 text를 갖는 텍스트필드를 생성합니다.

초기값이 "컴퓨터공학과"인 텍스트필드 생성 예
```java
JTextField tf2 = new JTextField("컴퓨터공학과");
```

JList<E>

JList는 하나 이상의 아이템을 보여주고 사용자가 아이템을 선택할 수 있도록 하는 리스트입니다. Java 7부터는 제네릭 리스트로 변경되어 `<E>`에 지정된 타입의 객체만 저장할 수 있습니다. 

JList를 JScrollPane에 삽입하여 스크롤이 가능하도록 만들 수 있습니다.

리스트 생성

다양한 생성자를 사용하여 리스트를 생성할 수 있습니다:

- `JList()`: 빈 리스트를 생성합니다.
- `JList<E>(List<E> listData)`: 리스트에 아이템을 공급받는 리스트를 생성합니다.
- `JList<E>(E[] listData)`: 리스트에 아이템을 배열로부터 공급받는 리스트를 생성합니다.

예시: 9개의 과일 이름 문자열이 든 리스트 만들기

```java
String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"};
JList<String> strList = new JList<String>(fruits);
```
메뉴 구성
메뉴 만들기에 필요한 스윙 컴포넌트는 다음과 같습니다:

- 메뉴아이템 (MenuItems): 여러 개의 메뉴 아이템을 가지는 항목입니다.
- 메뉴 (Menus): 여러 개의 메뉴 아이템을 가지는 그룹입니다.
- 메뉴바 (JMenuBar): 여러 개의 메뉴를 붙이는 바이며, 프레임에 부착됩니다.
- 분리선 (Separator): 메뉴 아이템 사이의 분리선으로, separator라고도 합니다. Menu 클래스의 `addSeparator()` 메소드를 호출하여 삽입할 수 있습니다.

메뉴 만드는 과정

1. JMenuBar 컴포넌트 생성: 메뉴바를 생성합니다.
2. JMenu 컴포넌트를 생성하여 JMenuBar에 붙임: 메뉴를 생성하고 메뉴바에 추가합니다.
3. JMenuItem 컴포넌트를 생성하여 JMenu에 붙임: 각 메뉴에 메뉴 아이템을 추가합니다. 여러 개의 메뉴와 메뉴 아이템을 생성할 수 있습니다.
4. JMenuBar 컴포넌트를 JFrame에 붙임: 생성한 메뉴바를 JFrame에 추가합니다.

```java
JMenuBar mb = new JMenuBar(); // 1. JMenuBar 생성
JMenu screenMenu = new JMenu("Screen"); // 2. JMenu 생성
mb.add(screenMenu); // 2. JMenu를 JMenuBar에 추가
screenMenu.add(new JMenuItem("Load")); // 3. JMenuItem 추가
screenMenu.add(new JMenuItem("Hide"));
screenMenu.add(new JMenuItem("ReShow"));
screenMenu.addSeparator();
screenMenu.add(new JMenuItem("EXIT"));
frame.setMenuBar(mb); // 4. JMenuBar를 JFrame에 추가
```

메뉴 아이템에 Action 리스너 설정
```java
JMenuItem item = new JMenuItem("Load");
item.addActionListener(new MenuActionListener());
screenMenu.add(item);

// Action 이벤트를 처리할 리스너 작성
class MenuActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // 사용자가 Load 메뉴 아이템을 선택하는 경우 처리할 작업을 구현합니다.
    }
}
```

팝업 다이얼로그, JOptionPane

팝업 다이얼로그
- 사용자에게 메시지를 전달하거나 문자열을 간단히 입력받는 용도
- JOptionPane 클래스를 이용하여 생성
  - static 타입의 간단한 메소드 이용

입력 다이얼로그 - `JOptionPane.showInputDialog()`
- 한 줄을 입력 받는 다이얼로그
- `static String JOptionPane.showInputDialog(String msg)`
  - `msg`: 다이얼로그 메시지
  - `리턴 값`: 사용자가 입력한 문자열, 취소 버튼이 선택되거나 창이 닫히면 null 리턴

```java
String name = JOptionPane.showInputDialog("이름을 입력하세요");
// name: 사용자가 입력한 값 (예: "Java Kim")
```
## 5월 24일 강의
#### 내용 정리

이벤트 기반 프로그래밍
이벤트의 발생에 의해 프로그램 효율이 결정되는 방식
- 이벤트가 발생하면 이벤트를 처리하는 루틴실행
- 실행할 코드는 이벤트의 발생에 의해 전적으로 결정
반대되는 개념: 배치 실행
-프로그램의 개발자가 프로그램이 호출을 결정하는 방식
이벤트 종휴
- 사용자의 입력 : 마우스 드래그, 마우스 클릭 , 키보드 누름등 
-센서로부터의 입력: 네트워크로부터 데이터 송수신
- 다른 응용프로그램이나 다른 스레드로부터의 메시지
이벤드 기반 응용 프로그램의 구조
- 각 이벤트마다 처리하는 리스너 코드 보유
GUI 응용 프로그램은 이벤트 기반 프로그래밍으로 작성됨 
GUI 라이브러리 종휴
C++ 의 MFC, C# GUI visual vasic, X window, Andorid 등
자바의 AWT와 Swing

이벤트가 처리되는 과정 
- 이벤트 발생 
- 이벤트 객체 생성
- 응용프로그램에 작성된 이벤트 리스너 찾기
- 이벤트 리스너 실행

이벤트 객체 
- 발생한 이벤트에 관한 정보를 가진 객체
- 이벤트 리스너에 전달됨 

이벤트 객체가 포함하는 정보
- 이벤트 종류와 이벤트 소스
- 이벤트가 발생한 화면 좌표 및 건포넌트 내 좌표
- 이벤트가 발생한 버튼이나 메뉴 아니템의 문자열
- 클릭된 마우스 버튼 및 마우스의 클릭 횟수
- 키의 코드 값과 문자 값
- 체크박스, 라디오버튼등과 같은 컴포넌트에 이벤트가 발생하였다면 체크 상태

이벤트 소스를 알아 내는 메소드
Object getSource()
-발생한 이벤트의 소스 컴포넌트 리턴 
Object 타입으로 리턴하므로 캐스팅해여 사용
- 모든 이벤트 객체에 대해 적용

이벤트 리스너 
-이벤트를 처리하는 자바 프로그램 코드 클래스로 작성 
사용자의 이벤트 리스너 작성
- 자바의 리스너 인터페이스를 상속받아 구현
- 리스너 인터페이스의 모든 추상 메소드 구현

이벤트 리스너 작성 방법
- 3가지 방법 
독립 클래스로 작성
- 이벤트 리스너를 완전한 클래스로 작성
- 이벤트 리스너를 여러 곳에서 사용할때 적합
내부 클래스로 작성
- 클래스 안에 멤버처럼 클래스 작성
- 이벤트 리스너를 특정 클래스에서만 사용할 때 적합
익명 클래스 
- 클래스의 이름 없이 간단히 리스너 작성 
- 클래스 조차 만들 필요없이 리스너 코드가 간단한 경우에 적합

익명 클래스로 이벤트 리스너 작성
익명 클래서
- 클래스 선언 + 인스턴스 생성을 한번에 달성 
- 간단한 리스너의 경우 익명 클래스 사용 추천

어댑터 클래스 
- 이벤트 리스너

KEY 이벤트와 포커스 
키 입력시, 다음 세 경우 각각 KEY 이벤트 발생
- 키를 누르는 순간 
- 누른 키를 떼는 순간 
-누른 키를 뗴는 순간
키 이벤트를 받을 수 있는 조건 
- 모든 컴포넌트 
- 현재 포커스를 가진 컴포넌트가 키 이벤트 독점
포커스 
- 컴포넌트나 응용프로그램이 키 이벤트를 독점하는 권한

KEYListner 
- 응용프로그램에서 KEYListner 를 상속받아 키 리스너 구현

유니코드 키
유키코드 키의 특징
- 국제 산업 표준
- 전 세계의 문자를 컴퓨터에서 일관되게 표현하기 위한 코드 체게
- 문자들에 대해서만 키 코드 값 입력
유니코드 키가 입력 되는 경우
- KEYpressed(),KEYTyped(),KEYReleased() rk tnstjeofh ghcnf
유니코드 키가 아닌 경우
- keyPressed()
가상키와 입력된 키 판별
KEYEvent 객체
- 입력된 키 정보를 가진 이벤트 객체
- KeyEvent 객체의 메소드로 입력된 키 판별

KeyEvent 객체의 메소드로 입력된 키 판별
- charKeyEventget(Keychar)
- 키의 유니코드 문자 값 리턴 
- uncode 문자 키인 경우에만 의미 있음


## 5월 17일 강의
#### 내용정리

FLOWLAyout 배치관리자
-컴포넌트가 삽입되는 순서대로 왼쪽에서 오른쪽으로 배치
- 배치할 공간이 없으면 아래로 내려와서 반복한다
BorderLayout 배치 관리자
- 컨테이너의 공간을 동 서 남 북 중앙의 5개의 영역으로 나눔
- 5개의 영역 중 응용프로그램에서 지정한 영역으로 나눔
배치 방법
add(Component comp, int index)
GridLatout 배치관리자 
-컨테이너를 프로그램에서 성정한 동일한 크기의 2차원 격자로 나눔
-컴포넌트는 삽입 순서대로 좌에서 우로 다시 위에서 아래로 배치
CardLayout
-컨테이너의 공간에 카드를 쌓아 놓는듯이 컴포넌트를 포개어 배치
컨테이너의 디폴트 배치 과니자
- 컨테이너 생성시 자동으로 생성되는 배치 관리자
- AWT와 스윙 컨테이너
- 디폴트 배치 관리자
컨테이너에 새로운 배치관리자 성정
-setLayout 메소드 호출
- im을 새로운 배치 관리자로 설정
-JPanel 컨테이너에 BorderLauout 배치관리자를 설정하는 예
JPanel p = new JPanel();
p.serLayout(new BorederLayout();) // JPanel에 BorderLayout 설정
배치방법 - 컴포넌트를 컨테이너 내에 왼쪽에서 오른쪽으로 배치
- 다시 위에서 아래소 순서대로 배치
생성자
-FlowLayout()
-FlowLayout(int hGao, int vGap)
align : 컴포넌트를 정렬하는 방법 지정, 왼쪽 정렬 오른쪽 정렬 중앙 정렬
hgap : 좌우 두 컴포넌트 사이의 수평 간격 픽셀 단위, 디폴트는 5
vgap : 상하 두 컴포넌트 사이의 수평 간격 픽셀 단위, 디폴트는 5
GridLayout 생성자
- Gridlayout()
- GridLayout(int rows, int cols)
- GridLayout(int rows, int cols, int hGap, int vGap)
- rows : 격자의 행수 디폴트 1
- cols 격자의 열수 디폴트 1
- hGap: 좌우 두 컴포턴트 사이의 수평 간격 , 픽셀단위 디폴트 0
- vGap : 상하 두 컴포넌트 사이의 수직 간격, 픽셀 단위 디폴트 0
- rows x cols 만큼의 셀을 가진 격자로 컨테이너 공간을 분할, 배치
배치관리자가 없는 컨테이너가 필요한 경우 - 응용프록램에서 직접 컴포넌트의 크기와 위치를 결정하고자 하는 경우
1 컴포넌트의 크기나 위치를 개발자 임읠결정하고자 하는경우
2 게임 프로그램과 같이 시간이나 마우스/키보드의 입력에 따라 컴포넌트들의 위치와 크기가 변하는경우 
3 여러 컴포넌트들이 서로 겹쳐 출력하고자 하는 경우
배치관리작가 없는 컴테이너에 컴포넌트를 삽입할 떄 
프로그램에서 컴포넌트의 절대 크기와 위치 설정
컴포넌트들이 서로 겹치게 할수 있음
컴포넌트의 크기와 위치 설정 메소드
void setSize(int width, int height)



## 4월 19일 강의
#### 내용정리
추상 메소드 
abstract로 선언된 메소드 메소드의 코드는 없고 원형만 선언
추상 클래스 상속 
- 추상 클래스를 상속받으면 추상클래스가 됨
- 서브 클래스도 abstract로 선언해야 함
추상 ㅡㄹ래스 구현 
-서브 클래스에서 슈퍼 클래스의 추상 메소드 구현(오버라이딩)
-추상 클래스를 구현한 서브 클래스는 추상 클래스 아님
추상 클래스의 목적
-상속을 위한 슈퍼 클래스로 활용하는것
-서브 클래스에서 추상 메소드 구현
-다형성 실현
자바의 인터페이스
-클래스가 구현해야 할 메소드들이 선언되는 추상형
-인터페이스 선언 - interface 키워드로 선언 - EX) public interface SerialDriver
자바 인터페이스에 대한 변화
- JAVA 7까지 - 인터페이스는 상수와 추상 메소드로만 구성
- JAVA 8 부터
인터페이스의 구성 요소들 -상수와 추상메소드 포함,defalut 메소드 포함,private 메소드 포함

인터페이스의 구성 요소들의 특징
- 메서드 시그니처(Method Signature): 메서드의 이름, 매개변수 목록, 반환 유형으로 구성되고, 메서드의 내용은 없으며 단지 정의만 포함

- 상수(Constant): 상수는 인터페이스 내에서 선언되는 변수로, 값을 변경할 수 없는 고정된 값, 주로 관련된 상수들을 그룹화하여 사용

- 디폴트 메서드(Default Method): Java 8부터 도입된 기능으로, 인터페이스에 메서드의 구현을 제공하고, 구현된 메서드를 갖게 됨으로써, 해당 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩 안함

-  정적 메서드(Static Method): Java 8부터 지원되며, 정적 메서드는 인터페이스에 속하면서도 인스턴스를 생성하지 않고도 호출할 수 있는 메서드, 주로 도우미 함수나 유틸리티 메서드를 제공하는 데 사용

-  추상 메서드(Abstract Method): 메서드의 정의만 있고 구현이 없는 메서드, 이러한 메서드들은 인터페이스를 구현하는 클래스에서 반드시 구현되어야 함

인터페이스 상속/구현
-  인터페이스의 추상 메소드를 모두 구현한 클래스 작성
    <1>implements 키워드 사용
    <2>여러개의 인터페이스 사용 가능-
패키지 
-서로 관련된 클래스와 인터페이스를 컴파일한 클래스 파일들을 묶어놓은 디렉토리
-하나의 응용 프로그램은 한개 이상의 패키지로 작성
패키지는 jar파일로 아북할수 있음
모듈
-여러 패키지와 이미지들의 자원을 모아 놓은 컨테이너 
-하나의 모듈을 하나의 .jmod파일에 저장
JAVA9 부터 모듈화 도입
- 플랫폼의 모듈화
- 몯ㄹ화의 목적 
-Java 9부터 자바 API의 모든 클래스들(자바 실행 환경)을 패키지 기반에서 모듈들로 완전히 재구성
java 8까지는 rt.jar의 한파일에 모드 API 저장
-응용프로그램이 실행핳때 꼭 필요한 모듈들로만 실행 환경 구축
-메모리 자원이 열악한 작은 소형 기기에 꼭 필요한 모듈로 구성된 작은 크기의 실행 이미지를 만들기 위함
모듈의 현실
-java 9 부터 전면적으로 도입
-복잡한 개념
-큰 자바 응용프로그램에는 개발 ,유지보수 등에 적합
-현실적으로 모듈로 나누어 자바프로그램을 작성할 필요 없음
자바 JDK에 자공되는 모듈 파일들 
- 자바가 설치된 hmods디렉토리에 모듈 파일 존재 - .jmod 확장자를 가진 파일, 모듈은 수십개 ,모듈 파일은 ZIP포맷으로 압축된 파일
모듈 파일에는 자바 API의 패키지와 크래스들이 들어있음
jmod 명령을 이용하여 모듈 파일에 들어있는 패키지를 풀어낼수있음
다은 패키지에 작성된 클래스 사용
- import를 이용하지 않는 경우 - 소스에 클래스 이름의 완전 경로명 사용
2. 필요한 클래스만 import
    <1>소스 시각 부분에 클래스의 경로명 import
    <2>import 패키지, 클래스
    <3>소스에는 클래스 명만 명시하면 됨

3. 패키지 전체를 import
    <1>소스 시각 부분에 패키지의 경로명 import
    <2>import 패키지.*
    <3>import.java.util.*
클래스 파일이 저장되는 윈치는 
-클래스나 인터페이스가 컴파일되면 클래스 파일 생성
클래스 파일은 패키지로 선언된 디렉터리에 저장
패키지 선언
-소스 파일의 맨앞에 컴파일 후 저장될 패키지 지정
package 선언문이 없는 자바 소스 파일의 경우
-컴파일러는 
모듈 
-java 9에서 도입된 개념
-패키지와 이미지등의 리소스를 담은 컨테이너 
-모듈 파일(.jmod)로 저장
자바 모듈화의 목적
-자바 컴포넌트들을 필요에 따라 조립하여 사용하기 위함
-컴퓨터 시스템의 불필요한 부담 감소 - 세밀한 모듈화를 통해 필요 없는 모듈이 로드되지않게 함, 소형 IOT장치에도 자바 응용프로그램이 실행되고 
java.lang
-스트링,수학 함수
object 클래스 특징
-모든 자바 클래스는 반드시 Object를 상속받도록 자동 컴파일 - 모든 크래스의 수퍼 클래스
객체의 속성을 나타내는 메소드로는 toString(), hashCode(), equals(), getClass() 등이 있습니다.
이러한 메소드들은 모든 자바 클래스에서 사용할 수 있으며, 필요에 따라 오버라이딩하여 해당 클래스의 속성에 맞게 동작을 재정의할 수 있습니다.
toString() 메소드는 객체를 문자열로 표현하는 데 사용되며, hashCode() 메소드는 객체의 해시 코드를 반환합니다.
equals() 메소드는 객체 간의 동등성을 비교하고, getClass() 메소드는 객체의 클래스를 반환합니다.

toString() 메소드, 객체를 문자열로 변환

1. 각 클래스는 toString()을 오버라이딩하여 자신만의 문자열 리턴 가능
    <1>객체를 문자열로 반환
    <2>원형

2. 컴파일러에 의한 바이트 코드
    <1>자바 소스 코드는 컴파일러에 의해 바이트 코드로 변환됩니다.
    <2>컴파일러는 toString() 메소드를 오버라이딩한 경우에도 해당 메소드를 유지하고, 오버라이딩된 내용을 반영한 바이트 코드를 생성합니다.
    <3>따라서 컴파일 이후에도 오버라이딩된 toString() 메소드가 호출되어 객체를 문자열로 변환할 수 있습니다.

객체 비교(==)와 equals()메소드
1. == 연산자
    <1>객체 레퍼런스를 비교

2. boolean.equals()
    <1>두 객체의 내용물 비교
    <2>객체의 내용물을 비교하기 위해 클래스의 맴버로 작성

wrapper 클래스
1. 자바의 기본타입을 클래스화한 8개 클래스를 통칭

2. 용도
    <1>객체만 사용할 수 있는 켈렉션 등에 기본 타입의 값을 사용하기 위해 wrapper 객체로 만들어서 사용

주요 메소드
1. 가장 많이 사용하는 integer 클래스의 주요 메소드
    <1>다른 wrapper 클래스와 메소ㅓ드는 이와 유사

2. 메소드 종류/설명
    <1>toString(): 객체를 문자열로 반환합니다.
    <2> hashCode(): 객체의 해시 코드를 반환합니다.
    <3>equals(Object obj): 다른 객체와 동등한지 여부를 판단합니다.
    <4> getClass(): 객체의 클래스 정보를 반환합니다.
    <5> notify(): 대기 중인 하나의 스레드를 깨웁니다.
    <6>notifyAll(): 대기 중인 모든 스레드를 깨웁니다.
    <7> wait(): 스레드를 일시 정지시킵니다.

박싱과 언박싱
1. 박싱
    <1>기본 타입의 값을 wrapper 객체로 변환하는 것

2. 언박싱
    <1>Wrapper 클래스의 객체에서 기본 타입의 값을 추출하는 것
    Wrapper 클래스는 기본 데이터 타입을 감싸는 클래스로, 예를 들어 Integer, Double, Boolean 등
    <2>언박싱은 Wrapper 클래스의 객체에서 intValue(), doubleValue(), booleanValue() 등의 메소드를 사용하여 해당 기본 타입의 값을 추출

String 활용
1. String의 생성:
    문자열을 생성하고 문자열 리터럴을 저장하는 객체
    쌍따옴표(" ")로 감싸진 문자열 리터럴을 사용하여 String 객체를 생성가능
    문자열 연결 (Concatenation): '+' 연산자를 사용하여 문자열을 연결가능
    문자열 리터럴과 변수, 또는 문자열 변수와 문자열 변수를 연결가능

2. 문자열의 길이 확인:
    length() 메소드를 사용하여 문자열의 길이를 확인
    공백을 포함하여 문자열의 전체 길이를 반환

3. 문자열 비교:
    equals() 메소드를 사용하여 두 문자열이 동일한지 비교
    equalsIgnoreCase() 메소드는 대소문자를 무시하고 비교

4. 부분 문자열 추출:
    substring() 메소드를 사용하여 문자열의 일부분을 추출
    시작 인덱스와 종료 인덱스를 지정하여 추출

5. 문자열 검색:
    indexOf() 메소드를 사용하여 특정 문자 또는 문자열이 처음으로 등장하는 위치확인 가능
    lastIndexOf() 메소드는 문자열의 끝에서부터 검색을 수행

6. 문자열 분할:
    split() 메소드를 사용하여 문자열을 특정 구분자를 기준으로 분할
    분할된 부분은 문자열 배열로 반환

7. 문자열 치환:
    replace() 메소드를 사용하여 문자열 내의 특정 문자 또는 문자열을 다른 문자열로 대체
    대체될 문자열이 없으면 원래 문자열을 그대로 반환


## 4월 12일 강의
#### 내용정리 
static 메소드는 오직 static 멤버만 접근 가능
-객체가 생성되지 않은 상화에서도 static 메소드는 실행될 수 있기 떄문에 ,non-static 멤버 활용 불가
-non-static 메소드는 static 멤버 활용 가능 
static 메소드는 this 사용 불가
- static 메소드는 객체없이도 사용 가능하므로 this 레퍼런스 사용할수 없음
상속 선언 
extends 키워드로 선언 -부모 클래스를 물려받아 확장한다는 의미
부모 클래서 -> 슈퍼클래스
자식 클래스 -> 서브 클래스
서브 클래스 객체의 모양
- 슈퍼 클래스 객체와 서브클래스의 객체는 별개
- 서브 클래스 객체는 슈퍼 클래스 멤버 포함
자바의 상속의 특징
클래스 다중 상속 불허
-C++ 은 다중상속 클래스가 가능 

슈퍼 클래스의 멤버에 대한 서브 클래스의 접근
- 슈퍼 클래스의 private 멤버 - 서브 클래스에서 접근 할수 없음
슈퍼 클래스의 디폴트 멤버
- 서브 클래스가 동일한 패키지에 있을떄 접근간으
슈퍼 클래스의 public 멤버
-서브 클래스는 항상 접근 가능
슈퍼클래스의 protected 상속
 protected 멤버에 대한 접근
 -같은 패키지의 모든 클래스에게 허용
 - 상속되는 서브 클래스(같은 패키지든 다른 패키지든 상관 없음)에게 허용
 서브 클래스의 개게가 생성될 때
 슈퍼 클래스 생성자와 서브 클래스 생성자 모두 실행
 슈퍼 클래스와 서브 클래스
 - 각각 여러 개의 생성자 작성 가능
 서브 클래스의 객체가 생성될 때
 - 슈퍼 클래스 생성자 1개와 서브 클래스 생성자 1개가 실행
 서브 클래스의 생성자와 슈퍼 클래스의 생성자가 결정되는 ㅂㅇ식
 1 개발자의 명시적 선택
 - 서브 클래스 개발자가 슈퍼 클래스의 생성자 명시적 선택
 - super() 키워드를 이용하여 선택
 2 컴파일러가 기본 생성자 선택
 super() 
 -서브 클래스에서 명시적으로 슈퍼클래스 호출
 

업캐스팅의 개념
업캐스팅은 하위 클래스를 상위 클래스 타입으로 변환하는 것. 상속 계층 구조에서 자주 사용되며, 다형성을 활용하여 다양한 객체를 동일한 인터페이스로 다룰 수 있게 컴파일 타임 다형성을 제공하여 유연하고 확장 가능한 코드를 작성하는 데 도움이 됨

업캐스팅
서브 클래스의 레퍼런스를 슈퍼 클래스 레퍼런스에 대입
슈퍼 클래스 레퍼런스로 서브 클래스 객체를 가르키게 되는 현상

다운캐스팅의 개념
다운캐스팅은 상위 클래스 타입을 하위 클래스 타입으로 변환하는 것. 업캐스팅과 반대되며 명시적인 형 변환을 필요로, 주로 상위 클래스로 선언된 객체를 실제로는 하위 클래스로 사용해야 할 때 사용되며 타입 안전성을 보장하기 위해 주의해서 사용해야 함

다운캐스팅
슈퍼 클래스 레퍼런스를 서브 클래스 레퍼런스에 대입
업캐스팅된 것을 다시 월래대로 되돌리는 것
반드시 명시적 타입 변환 지정

[ 업캐스팅 레퍼런스로 객체 구별?? ]
업캐스팅된 레퍼런스로는 객체의 실제 타입을 구분하기 어려움
    --> 슈퍼 클래스에서 여러 서브 클래스에 상속되기 때문.


Instanceof 연산자 사용
 레퍼런스가 가르키는 객체의 타입 식별

instanceof 연산자의 활용예제 ( by.코드 )
[ ex.code ]-----------------------------------------------------
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        System.out.println(myDog instanceof Animal); // true
        System.out.println(myDog instanceof Dog);    // true
        System.out.println(myDog instanceof Cat);    // false

        System.out.println(myCat instanceof Animal); // true
        System.out.println(myCat instanceof Dog);    // false
        System.out.println(myCat instanceof Cat);    // true
    }
}

설명: 이 예제는 instanceof 연산자를 사용하여 각 객체가 Animal 클래스, Dog 클래스 또는 Cat 클래스의 인스턴스인지를 확인한다.
[ ex.code ]------------------------------------------------------

메소드 오버라이딩의 개념
1. 메소드 오버라이딩은 상위 클래스의 메소드를 하위 클래스에서 덮어쓰는 것, 이를 통해 하위 클래스는 상위 클래스의 동작을 재정의할 수 있다. 이 개념은 다형성을 지원하며, 런타임 시에 객체의 실제 타입에 따라 적절한 메소드가 호출이 됨

오버라이딩의 목적, 다향성 실현
1. 오버라이딩 + 다향성
    <1>하나의 인터페이스에 서로 다른 구현
    <2>슈퍼 클래스의 메소드를 서브 클래세어서 각각 목적에 맞게 다르게 구현

 

## 4월 5일 강의
#### 내용정리
메소드의 배열 리턴 - 배열의 레퍼런스만 이턴 (배열 전체가 리턴되는것이 아님)
메소드의 리턴 타임
- 리턴하는 배열 타입과 리턴 받는 배열 타입 일치
- 이천 차입에 배열의 크기를 지정하지 않음
자바의 예외처리 
예외 - 실행 중 오동작이나 결과에악영향을 미치는 예상치 못한 상황이 발생
실행 중 예외가 발생하면 - 자바 플랫폼은 응용프로그램이 예외를 처러하도록 호출
예외 발생 경우
-정수를 0으로 나누는 경우
-배열의 크기보다 큰 인덱스로 배열의 원소를 접급하는경우
-정수를 읽는 코드가 실행되고 있을 떄 사용자가 문자를 입력한 경우
자바의 예외처리, try-catch-finaly문
자바의 예외 클래스
배열의 범위를 벗어나 원소를 접근하는 예외 처리
-자바에서 배열의 범위를 벗어나 원소를 접근하려고 할 때 발생하는 예외는 ArrayIndexOutOfBoundsException이고
 이 예외는 배열의 유효하지 않은 인덱스에 접근하려고 할 때 발생 배열의 유효한 인덱스 범위는 0부터 배열의 길이 - 1까지이며
 이 범위를 벗어나는 인덱스에 접근하면 이 예외가 발생

실세꼐 객체의 특징
- 객체마다 고유한 특성과 행등을 가짐
- 다른 객체들과 정보를 주고 받는 등, 상호작용하면서 살아감
컴퓨터 프로그램에서 객체 사례
테트리스 게임의 각 블록들 
한글 프로그램의 메뉴나 버튼들
캡슐화 - 객체의 가장 본질적인 특징 - 외부의 접근으로부터 객체 보호
자바의 캡슐화 
- 클래스: 객체 모양을 선언한 틀
-객체 : 생성된 실체
자바의 객체 지향 특성 : 상속
-상속 상위 개체의 속성이 하위 개체에 물려짐, 하위 개체가 상위 개체의 속성을 모두 가지는 관계
상위 클래스 - 수퍼 클래스
하위 클래스 - 서브 클래스, 수퍼 클래스 코드의 재사용 , 새로운 특성 추가 기능
다형성
- 같은 이름으 ㅣ메소드가 클래스 혹은 객체에 따라 다르게 구현되는것
다형성 사례 
- 메소드 오버로딩 : 한클래스 내에서 같은 이름이지만 다르게 작동하는 여러 메소드
- 메소드 오버라이딩 : 슈퍼 클래스의 메소드를 동일한 이름으로 서브 클래스 마다 다르게 구현
소프트웨어의 생산성 향상
- 컴퓨터 산업 발전에 따라 소프트웨어의 생명주기 단축 - 소프트 웨어를 빠른 속도로 생산할 필요성 증대
객체 지향 언어
- 상속,다형성,객체,캡슐화 등 소프트웨어 재사용을 위한 여러 장치 내장
-소프트웨어 재사용과 부분 수정 빠름
-소프트웨어를 다시 만드는 부담 대폭 줄임
-소프트웨어 생산성 향상
실세계의 대한 쉬운 모델링 
- 초기 프로그래밍 - 수학계산.통계 처리를 하는 등 처리과정
절차 지향 프로그래밍 
- 작업 순서를 표현하는 컴퓨터 명령 집합
함수들의 집합으로 프로그램 작성
객체지향 프로 그래밍
- 컴퓨터가 수행하는 작업을 객체들간의 상호 작용르로 표현 
클래스 혹은 객체들의 집합으로 프로그램 작성
글래스 
-객체의 속성과 행위 선언
객체의 설계도 혹은 틀
객체
- 클래스의 트로 찍어낸 실체 - 프로그램 실행 중에 생성되는 실체, 메모리 공간을 갖는 구체적인 실체, 인스턴스 라고도 부름
사례 - 클래스:소나타 자동차,객체 출고된 실제 소나타 100대 ,클래스: 벽시계 , 객체 : 우리집 벽에 걸린 벽시계들
클래스 
- class 키워드로 선언
-멤버 : 클래스 구성요소 - 필드와 메소드
클래스의 데한 public 접근 지정 : 다른 모든 클래스에서 클래스 사용 허락
멤버에 대한 public 접근 지정 : 다른 모든 클래스에게 멤버 접근 허용
생성자의 특징
-생성자 이름은 클래스 이름과 동일 
-생성자는 여러 개 작성 가능(생성자 중복)
생성자는 객체 생성시 한번만 호출
기본 생성자가 자동 생성되는 경우
클래스에 생성자가 하나도 선언 되어 있지 않을 때 - 컴파일러에 의해 기본 생성자 자동 생성
this 래퍼런스
this 
객체 자신에 대한 레퍼런스
-컴파일러에 의해 자동관리,개발자는 사용하기만 하면 됨
-this.멤버 형태로 접근할때 사용
this() - 같은 클래스의 다른 생성자 호출,생성자 내에서만 사용 가능, 생성자 코드의 제일 처음에 있어야 함
자바의 객체배열 - 객체에 대한 레퍼런스 배열임
자바의 객체배열 만들기 3단계 - 배열 레처런스 변수 선언,레퍼런스 배열 생성, 배열의 각 원소 객체 생성
메소드 - 메소드는 c/c++ 의 함수와 동일,자바의 모든 메소드는 반드시 클래스 안에 있어야 함
메소드 오버로딩 - 한클래스 내에서 두 개 이상의 이름이 같은 메소드 작성
-메소드 이름이 동일하여야 함
매개 변수의 개수가 서로 다르거나 타입이 서로 달라야 함
객체 소명 - new에 의해 할당 받은 객체와 배열 메모리 포함,소멸된 객체 공간은 가용 메모리에 포함
자바에서 사용자 임의로 객체 소명안됨
-자바는 객체 소명 연산자 없음-객체 생성 연산자 :new
객체 소멸은 자바 가상 기계의 고유한 역할 
자바 개발자 에게는 매우 다행스러운 기는 
-c/c++ 에서는 할당 받은 객체를 개발자가 프로그램 내엣 삭제해야함
-c/c++ 의 프로그램 작성을 어렵게만드는 요인
- 자바에서 사용하지 않는 객체나 배열을 돌려주는 코딩 책입으로부터 개발자 해방
가비지 -가르키는 레퍼런스가 하나도 없는 객체 - 더이상 접근할 수 없어 사용할 수 없게 된 메모리 
가비지 컬렉션 -자바 가상 기계의 가비지 컬렉터가 자동으로 가비지 수집,반환
강제 가비지 컬렉션 강제 수행
System 또는 Runtime 객체의 gc() 메소드 호출- 이코드는 자바 가상 기계에 강력한 가비지 컬렉션 요청, 
그러나 자바 가상 기계가 가비지 컬렉션 시점을 전적으로 판단
패키지 - 상호관련 있는 클래스 파일을 저장하여 관리하는 디텍토리
자바 응용프로그램은 하나 이상의 패키지로 구성
접근 지정자 - 4가지 private,protected,public,디폴트
접근 지정자의 목정 
-클래스나 일부 멤버를 공개하여 다른 클래스에서 접근하도록 허용
- 객체 지향 언어의 캡슐롸 정책은 멤버를 보호하는 것
클래스의 접근지정
- 다른 클래스에서 사용하도록 허용할 지 지정
public 클래서 - 다른 모든 클래스에게 접근 허용
디폴트 클래스
-packagge -private 라고도 함
- 같은 패키지의 클래스에만 접근 허용
static 멤버 
객체 생성과 non -static 멤버의 생성
non-static 멤버는 객체가 생성될 때,객체마다 생긴다
static 멤버는 클래스당 하나만 생성
객체들에 의해 공유됨
static 멤버 사용
- 클래스 이름으로 접근 가능
-객체의 멤버로 접근 가능
- non-static 멤버는 클래스 이름으로 접근 안됨

## 3월 29일강의
#### 내용정리
Scanner 클래스
읽은 바이트를 문자,정수,실수,문자열등 다양한 타입으로 변환하여 리턴
Scanner는 입력되는 키 값을 공백으로 구분되는 토큰 단위로 읽음
공백문자 : "\t" , "\f" , "\r" \ "n"
산술 연산자 
더하기(+) 뺴기(-) 곱하기(*) 나누기(/) 나머지(%)
증감 연산
++, --
대입 연산
int a = 1 ,b = 3;
a = b;
a += b;
조건 연산
3개의 피연산자로 구성된 삼항 연산자
opr1?opr2:opr3
opr1 이 true이면 연산식의 결과는 opr2, false이면 opr3
if else을 조건 연산자로 간결하게 표현 가능
비트 연산 
비트개념 = byte X = 10;
비트 논리 연산
피 연산자의 각 비트들을 대상으로 하는 연산
비트끼리 AND,OR,XOR,NOT 연산
비트 시프트 연산
비트를 오른쪽이나 왼쪽으로 이동
조건문 - 단순if 문 , if - else 문
단순 if 문
if의 괄호안에 조건식(논리형 변수나 논리 연산)
if-else 문 
조건식이 true면 실행문장1, false면 실행문장 2실행
다중 if-else 문
다중 if 문
조건문이 너무 많은 경우 swich 문 사용 권장
swich문
swich문은 식과 case 문의 값과 비교 
case의 비교 값과 일치하면 해당 case 의 실행문장 수행- break를 만나면 swich문을 벗어남
반복문
자바 반복문 - for 문, while 문 , do - while 문- for문  가장 많이 사용하는 반복문
 while문 - 조건식이 '참'인 동안 반복실행
 do-while문 - 조건식이 참인 동안 반복 실행 , 작업문은 한번 반드시 실행
braek 문 
반복문하나를 즉시 벗어갈 떄 사용
자바 배열
배열(array)
인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료 구조- 배열을 이용하면 한 번에 많은 메모리 공간 선언 가능
배열은 같은 타입의 데이터들이 순차적으로 저장되는 공간- 원소 데이터들이 순차적으로 저장됨,인젝스를 이용하여 원소 데티어 접근,반복문을 이용하여 처리하기에 적합한 자료구조
배열 인덱스 - 0부터 시작 , 인덱스는 배열의 시작 위치에서부터 데이터가 있는 상대 위치
배열 선언 - 배열 공간 할당 받는 과정
배열 초기화 - 배열 생성과 값 초기화
래퍼런스 치환과 배열 공유 - 래퍼런스 치환으로 두 래퍼런스가 하나의 배열 공유
배열의 크기,lenght
자바의 배열은 객체로 처리
배열 객체의 lenght 필드- 배열의 크기는 배열객체의 lenght필드에 저장





## 3월 22일강의
#### 내용정리
깃 그래프로 태그 및 버젼을 넣을수있다
Ctrl + Shift +p  를 이용하여 자바 프로젝트를 쉽고 빠르게 만들수있다
프로그래밍의 역사에 대해서 배웠다 
자바 : java -> class 
자바
그린 프로젝트 : 선마이크로시즈템즈의 제임스 고슬링에 의해 시작{가전 제품에 들어갈 소프트웨어를 위해 개발}
플랫폼 호환성 문제 해결
플랫폼 돌릭적인 언어 개발
메모리 사용량이 적고 다양한 플랫폼을 가지는 가전 제품에 적용
WORA
자바는 한번 작성된 코드는 모든 플랫폼에서 바로 실행됨
기존 언어의 플랫폼 종속성 극복
자바의 특징{바이트 코드}
자바 소스를 컴파일한 목적 코드
CPU에 종속적이지 않는 중립적인 코드
JVM에 의해 해석되고 실행됨
JVM은 자바 바이트 코드를 실행하는 자바 가상 기계

실행 환경
자바 가상 기계 + 자바 플랫폼의 다양한 클래스 라이브러리(자바 API)
응용 프로그램 실행
main() 메소드를 가진 클래스의 main()에서 실핼

JDK
자바 응용 개발 환경, 개발에 필요한 도구 포함
JRE
자바 실행 환경
개발자가 아닌경우 JRE만 따로 다운 가능

자바 API
JDK에 포함된 클래스 라이브러리 
주요한 기능들을 미리 구현한 클래스 라이브러리의 집합
API에서 정의한 규격에 따라 글래스 사용
자바 패키지 
서로 관련된 클래스들을 분류하여 묶어 놓은 것
	클래스의 이름에 패키지 이름도 포함
	다른 패키지의 동일한 이름의 클래스 존재 가능
자바 통합 개발 환경-이클립스
IDE
통합 개발 환경
편집,컴파일,디버깅을 한번에 할 수 있는 통합된 개발 환경

이클립스
자바 응용 프로그램 개발을 위한 통합 개발 환경
IBM에 의해 개발된 오픈 소스 프로젝트

자바 모바일 응용 : 안드로이드 앱

자바의 특징
플랫폼 독립성
객체지향-캡슐화,상속,다형성 지원
클래스의 캡슐화
자바의 모슨 변수나 함수는 클래스 내에 선언
클래스 안에서 클래스(내부 클래스) 작성 가능
소스(.java) 와 클래스 (.class) 파일
하나의 소스 파일에 여러 클래스를 작성 가능 - public 클래스는 하나만 가능
소스 파일의 이름과 public으로 선언된 클래스 이름은 같아야 함
클래스 파일에는 하나의 클래스만 존재- 다수의 클래스를 가진 자바 소스를 컴파일하면 클래스마다 별도 클래스 파일 생성
실행 코드-자바 응용프로그램의 실행은 main() 메소드에서 시작
패키지-서로 관련 있는 여러 클래스를 패키지로 묶어 관리
멀티스레드- 여러 스레드의 동시 수행 환경 지원
가비지 컬렉션-자바 언어는 메모리 할당 기능은 있어도 메모리 변환 기능 없음
실시간 응용프로그램에 부적합-실행 도중 예측할 수 없는 시점에 가비지 컬렉션 실행 때문
자바 프로그램은 안전-타입 체크 엄격,물리적 주소를 사용하는 포인터 개념 없음
프로그램 작성 쉬움-포인터 개념이 없음 ,동적 메모리 반환 하지 않음,다양한 라이브러리 지원
실행 속도 개선을 위한 JIT 컴파일러 사용 - 자바는 바이트 코드를 인터프리터 방식으로 실행,JIT 컴파일 기법으로 실행 속도 개선
식별자-클래스,변수,상수,메소드 등에 붙이는 이름
식별자의 원칙-특수 문자,공백,탭은 식별자 사용불가지만 "_", "$"는 사용가능
자바의 데이터 타입 
기본타입- boolean,char,byte,short,int,long,float,doubel
문자열-문자열은 기본 타입아님
문자열 이터럴 - "JDK","한글","계속하세요"
변수-변수값은 프로그램 수행 중 변경될 수 있음
변수 선언 - 데이터 타입에서 정한 크기의 메모리 할당
리터럴-프로그램에서 직접 표현한 값,정수,실수,문자,놀리 ,문자열 리터럴 있음
정수 리터럴-10진수,8진수,16진수,2진수 리터럴








## 3월 15일 강의
#### 내용정리
깃 유저네임, 이메일 등록

깃배쉬 익스텐션 깔기

이클립스를 이용하여 Hello.java 실습

수업에서는 vscode 사용 예정 - vscode에 자바 익스텐션 실치

hello.java와 test.java 파일 만들기 - 커밋과 푸쉬

##### 코드블럭 생성 및 주석 처리
 ```java
 public class Test {
	public static void main(String[] args) {
		System.out.println("Hello");
		//Hello를 출력
	}
}
```

## 3월 8일 강의
깃 아이디 생성 및 깃 레파지토리 만들기
