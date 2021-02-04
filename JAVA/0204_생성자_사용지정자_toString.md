# 생성자

> 객체 생성에 관여하는 특별한 메소드
>
> non -static 멤버 초기화

- 일반적으로는 접근지정자 public 사용
- 리턴 타입 없음
- 생성자 메소드 내에 `super();` 없으면 자동 추가됨
- 기본 생성자 (디폴트 생성자) 는 argument 없으며, 명시적 생성자는 argument  추가 가능
- 일단 명시적 생성자를 만들면, 디폴트 생성자도 직접 작성해야 함
- **생성자 오버로딩 가능**



## this

> 자기 자신 객체를 가리키는 reference



## this()

> 자신의 생성자를 (가리키는) 호출 구문



## super

> super 객체를 가리키는 reference



## super()

> super 생성자를 호출 구문

- 항상 생성자 메소드 제일 첫 줄에 작성
- 작성하지 않으면 컴퓨터가 알아서 호출 => 무조건 존재





# 배열



## Enhanced for loop

```java
public void want(String []all) {
		for (String s:all)
			System.out.println(s);
	}

//// 같은 for 문 ////

public void want(String []all) {
		for (int i=0; i<all.length; i++)
			System.out.println(all[i]);
	}
```



## `...` 파라미터

> 가변 길이 파라미터

```java
public class Book {
	public void want(String ...all) {
		for (String s:all)
			System.out.print(s);
	}
}
```

```java
public class BookTest {
	public static void main(String[] args) {
		Book book = new Book();
		book.want("a","b");
	}
}
결과: ab
```





# modifier (제한자, 지정자)



## 접근지정자 (Access modifier)

- private
- protected
- public
- default



## 사용 지정자 (Usage modifier)

### 1) static

> 객체 생성 없이 사용

```java
public class Test {
    int i = 10;
	public static void main(String[] args) {
		System.out.println(i);		//에러
	}
}

public class Test {
    int i = 10;
	public static void main(String[] args) {
        Test t = new Test();
		System.out.println(t.i);	// 10 출력됨
	}
}
```

```java
public class Test {
	static int i = 10;		// ****** static 변수 선언, 객체 선언 안함
	public static void main(String[] args) {
		System.out.println(i);		// 10 출력됨
        System.out.println(Test.i);	// 10 출력됨 -> 클래스명.static 변수
	}
}
```

- 가비지 컬렉터(GC)는 instance 영역에서만 활동하므로 static 을 남용하면 메모리 터질 수 있음 주의
- 공유 data가 필요할 때만 static 사용하자 !



- class 앞 : inner class 에서만
- method 앞 : 별로 이득 없음
- data 앞 : 공유하는 data

```java
public class Test {	
	public static void main(String[] args) {
		A o1=new A();		// o1 객체 생성
		o1.i++;				// static int i 가 저장되어 있는 곳에서 10 + 1
		o1.printI();
		
		A o2=new A();		// o2 객체 생성
		o2.i++;				// static int i 가 저장되어 있는 곳에서 11 + 1
		o2.printI();
	}
}

class A{
	static int i=10;			// static 영역에 저장되며 o1, o2 객체들이 번지수가 아닌
	public void printI() {		// 이름으로 접근
		System.out.println(i);
	}
}
결과: 11		
    12
```

>객체 생성했지만, static int i 를 공유하는 상황



```java
public class Test {	
	public static void main(String[] args) {
		A.i++;		//클래스명.static 변수명
		A.printI();
		
		A.i++;
		A.printI();
	}
}

class A{
	static int i=10;
	static public void printI() {
		System.out.println(i);
	}
}
결과: 11		
    12
```

> 객체 생성 X
> `클래스명.변수명` 으로 접근 가능



```java
public class Test {	
	public static void main(String[] args) {		
		A o1=new A("전은수");		// 객체 생성
		A.count++;
		
		A o2=new A("홍길동");		// 객체 생성
		A.count++;
		
		A.printCount();
	}
}

class A{
	String name;
	static int count=0;			// 방문자수 = 공유해야 하는 값
	static public void printCount() {	// static 메소드는 코드공유 개념이라 필요없음
		System.out.println(count);
	}
	A(String name){		//생성자
		this.name=name;
	}
}
결과: 2
```

> 꼭 필요한 경우는 언제일까? 	=> 	공유해야 하는 data가 존재할 경우



### 2) final

> 변경 없이 사용

- class 앞 : 상속 불가

```java
final class A{		// final 선언
	int i=10;
	public void printI() {
		System.out.println("A의 i는 "+i);
	}
}

class B extends A{	// 에러 - 상속 자체가 불가
	}				// 상속은 물려 받아서 변경할 수 있다는 의미이므로 : 변경 불가
```



- data 앞 : 상수

```java
public class Test {
	public static void main(String[] args) {
		A a = new A();
		a.i++;			// 에러 - 값 변경 불가
	}
}

class A{
	final int i=10;		// final 선언
}
```



- method 앞 : 오버라이딩 불가 == 재정의 불가

```java
class A{
	int i=10;
	final public void printI() {	// final 선언
		System.out.println("A의 i는 "+i);
	}
}

class B extends A{
	public void printI() {			// 에러 - 오버라이딩 불가
		System.out.println("A로부터 상속받는 i는 "+i);
	}
}
```



### 3) abstract

> 객체 생성이 불가능하므로 반드시 **상속**해서 사용





# 객체 toString()

결과보기 전에 답 생각해보고, 메모리 영역 떠올리기

```java
public static void main(String[] args) {
		Object o1=new Object();
		System.out.println(o1.toString());
		Object o2=new Object();
		System.out.println(o2.toString());
		System.out.println(o1==o2);
		System.out.println(o1.equals(o2));
		
		Test t1=new Test();
		System.out.println(t1.toString());
		Test t2=new Test();
		System.out.println(t2.toString());
		System.out.println(t1==t2);
		System.out.println(t1.equals(t2));
		
		String s1=new String("java");
		System.out.println(s1.toString());
		String s2=new String("java");
		System.out.println(s2.toString());
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		
		String s3="java";
		System.out.println(s3.toString());
		String s4="java";
		System.out.println(s4.toString());
		System.out.println(s3==s4);
		System.out.println(s3.equals(s4));

	}
```

결과

```java
java.lang.Object@139a55
java.lang.Object@1db9742
false
false
test.final_.Test@106d69c
test.final_.Test@52e922
false
false
java
java
false
true
java
java
true
true
```

