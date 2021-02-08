# Java program 단위

## Class 

> 기본 단위

- **단일 상속**만 가능



## Enum 

> 열거타입 
>
> **상수**를 표현하기 위한 프로그램 단위
>
> static + final + (public)  =>  상수

```java
public class Week {
	public static final char MON = '월';
}

public class Test {
	public static void main(String[] args) {
		System.out.println(Week.MON);
	}
}
결과: 월
```

```java
public enum Week {
	MON;		//간편하게 작성 가능
}

public class Test {
	public static void main(String[] args) {
		System.out.println(Week.MON);
        
        Week o1 = Week.MON;			// 객체로 호출 가능
		System.out.println(o1);
	}
}
결과: MON
    MON
```





## interface

> **다중 상속**을 위한 프로그램 단위 





# Exception (예외) 처리

> 프로그래밍 오류를 잡고, 실행을 계속할 수 있는 기법

## **try ~catch** 기법

- 하위 exception 을 위에 작성해야함 => Exception 먼저 실행되는 것 방지

```java
public class Test {

	public static void main(String[] args) {	
		try {
			int x = 100;
			int y = Integer.parseInt(args[0].trim());
			System.out.println(x/y);
		}catch(ArithmeticException e) {			// (타입 객체참조 변수)
			System.out.println("0으로 나누지 마세요");
		}catch(NumberFormatException e) {		// 중첩 catch 가능
			System.out.println("숫자로 바꿀 수 없는 입력입니다");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("숫자를 입력하세요");
		}catch(Exception e) {					// 모든 예외를 잡지만 권고하지는 않음
			System.out.println("어떤 exception 발생 ");	
		}
		System.out.println("아주 중요한 일 시작..");
	}
}
```

- try 구문 내 변수 선언 후, 해당 변수 return 하면 에러 발생  =>  try 의 local data 가 되므로

```java
public class Calculator {
	public int divide(int x, int y) {
		int z = 0;	// local data : 초기화 필수
		try {
			z = x / y;
			// int z = x / y;	z는 try의 local 변수가 되어 return z 불가
		}catch(ArithmeticException e) {
			System.out.println("0으로 나눈 에러");
		}
		return z;
	}
}

public class Test {
	public static void main(String[] args) {
		Calculator c = new Calculator();
		int result = c.divide(100, 0);		
		System.out.println(result);
	}
}
결과: 0으로 나눈 에러
0
```



## **throws **기법

```java
public class Calculator {
	public int divide(int x, int y) throws MyException {// exception발생을 client에게 throw
		int z = 0;
		if (y==0) {										// 사전에 exception발생 가능성 확인
			throw new MyException("0으로 입력하지 마세요");// exception 발생시킴
		}							
		z = x / y;
		
		return z;
	}
}
```

```java
public class MyException extends Exception {
	public MyException(String message) {
		super(message);
	}	
}
```

```java
public class Test {
	public static void main(String[] args) {	// main method 에서는 throws 불가
		Calculator c = new Calculator();		// JVM 에게 throw 되어 프로그램 터짐
		int result = 0;							// main 함수 있으면 try-catch 문 사용
		try {
			result = c.divide(100, 2);
		} catch(MyException e) {
			System.out.println(e.getMessage());
		} finally {								// 에러가 발생하든 아니든 항상 실행하는 블럭
			System.out.println(result);			// 중요한 구문은 이 곳에 입력
			System.out.println("아주 중요한 일 시작...");
		}		
	}
}
```





## 기타 함수



### Integer.parseInt(args[0])

> String 을 int 로 변환해주는 함수



### .trim()

> String 이 입력됐을 때 공백을 없애주는 함수
