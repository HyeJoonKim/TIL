# singleton

> 객체가 단 하나만 생기게 하는 class 작성 기법

- 생성자를 private 하게 막음
- 스스로 객체를 생성함 -> static 으로 선언
- 생성한 객체를 리턴 -> static 으로 선언

```java
public class MyButtonHandler implements ActionListener {	//singleton
	static private MyButtonHandler me = new MyButtonHandler();
	
	static public MyButtonHandler getInstance() { 
		return me;						//메서드를 통해 얻어가라
	}
	
	private MyButtonHandler() {
		super();
	}	
}
```

호출 시에는 `MyButtonHandler b1Handler=MyButtonHandler.getInstance();`



# IO

## 1 byte씩 처리

### InputStream

### OutputStream



## 2byte씩 처리

### Reader

### Writer



# Thread

> 작은 단위의 실행 흐름

- run() : 수행내역
- start() : 작업 개시
- stop() : 작업 중지
- sleep(초) : 작업 시간만큼 중단 후 개시
- suspent() : 무한정 중단
- resume() : 개시