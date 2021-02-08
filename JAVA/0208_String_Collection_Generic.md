# String

> String 은 final class 이므로 문자열 변경 시 새로운 객체를 할당받아야 함

- String literal pool 에 저장된 후 문자열 변경이 생기면, 새로운 literal pool 만든 후 주소값 연결
  - 메모리 효율적으로 사용
- 객체 생성 후 문자열 변경이 생기면, 매번 새로운 객체를 생성함

```java
public class Test {
	public static void main(String[] args) {
		String s1 = new String("java");
		String s2 = new String("java");	// 객체 생성
		String s3 = "java";	// string literal pool 에 올라감
		String s4 = "java";	// string literal pool 에 같은 문자열있는지 확인 후 연결
		s1.concat("1");	// 새로운 string 객체를 만들어 문자열 추가
		s2.concat("1");	
		s3.concat("1");
		s4.concat("1");
		System.out.println(s1);	// 배열은 resize 안되서 java 출력
		System.out.println(s2);	// immutable 속성
		System.out.println(s3);
		System.out.println(s4);
		
		String s5 = s1.concat("1");	// 해결방법1
		System.out.println(s5);
		System.out.println(s5==s1);
		s1 = s1.concat("1");	// 해결방법2
		System.out.println(s1);	// 기존 연결 끊고 새로 연결
	}
결과: java
java
java
java
java1
false
java1
```



## StringBuffer

> 버퍼라는 독립 공간을 가지며, 16개의 문자를 저장할 수 있는 크기

```java
public class Test {
	public static void main(String[] args) {
		String s1=new String("java");		
		String s3="java";
		
		StringBuffer sb1=new StringBuffer("java");
		StringBuffer sb2=sb1.append("1");	// append 사용
		
		System.out.println(sb1);
		System.out.println(sb2);
		System.out.println(sb1==sb2);
	}
}
결과:java1
java1
true
```



### 언제 무엇을 사용해야 할까?

| String      | StringBuffer | StringBuilder                          |
| ----------- | ------------ | -------------------------------------- |
| 고정 문자열 | 변경 많을 때 | 변경 많을 때                           |
|             | 성능이 나쁨  | 성능이 좋음                            |
|             | thread 안정  | 단일 thread 일 때 사용 (thread 불안정) |

- web 을 다루기 위해서는 StringBuffer, StringBuilder 사용을 지향



# Collection



| java       | python     | python 표현     | 특징                    |
| ---------- | ---------- | --------------- | ----------------------- |
| [] 배열    | tuple      | (1,2,3)         | 고정 길이               |
| array List | list       | [1,2,3]         | 유동길이 (순서O, 중복O) |
| set        | set        | {1,2,3}         | 순서X, 중복X            |
| map        | dictionary | {'a':1,  'b':2} | key-value 쌍으로 표현   |

## ArrayList

- 초기에 10개의 칸이 생성

```java
import java.util.ArrayList;
public class ListTest {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("1");
		list.add(new ListTest());	// 객체도 들어갈 수 있음
		System.out.println(list); // 주소가 아닌 내용값이 나옴
		System.out.println(list.size());
		for (int i = 0; i < list,size(); i++)	//String만 있지 않으므로 enhanced문 사용 불가
			System.out.println(list.get(i));
	}
}
결과: [1, 2, 1, test.collection.ListTest@139a55]
4
1
2
1
test.collection.ListTest@139a55
```

## Set

### iterator

- hasNext()
- next()

```java
import java.util.HashSet;
import java.util.Iterator;
public class SetTest {
	public static void main(String[] args) {
		HashSet set = new HashSet();
		set.add("1");
		set.add("2");
		set.add(new SetTest());
		set.add("1");
		System.out.println(set);
		System.out.println(set.size());
		Iterator ite = set.iterator();
		System.out.println(ite);
		while (ite.hasNext()) {		//데이터 유무 묻는 것
			Object o = ite.next();
			System.out.println(o);
		}
	}
}
결과: [1, 2, test.collection.SetTest@139a55]
3
java.util.HashMap$KeyIterator@1db9742
1
2
test.collection.SetTest@139a55
```



## Map

### Enumeration

- hasMoreElements()
- nextElement()

```java
import java.util.Enumeration;
import java.util.Hashtable;
public class MapTest {

	public static void main(String[] args) {
		Hashtable map = new Hashtable();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "1");
		map.put("d", new MapTest());
		
		System.out.println(map);
		System.out.println(map.size());
		Object o1 = map.get("a");	// key로 꺼냄
		Object o2 = map.get("c");
		System.out.println(o1==o2);
		
		Enumeration enu = map.keys();
		while (enu.hasMoreElements()) {
			Object o = enu.nextElement();
			System.out.println(o+":"+map.get(o));
		}
	}
}
결과: {b=2, a=1, d=test.map.MapTest@139a55, c=1}
4
true
b:2
a:1
d:test.map.MapTest@139a55
c:1
```



# 제네릭

> 타입 매개변수를 가지는 클래스와 인터페이스

- 컴파일시 타입이 결정 => 안전함
- 강제 형변환 필요X

- `<>` 을 타입 파라미터 라고 부름

아래처럼 코드를 구현하면, `instanceof` 구문에 의해 성능이 매우 낮아지게 됨

```java
import java.util.ArrayList;
public class ListTest {
	public static void main(String[] args) {
		ArrayList list=new ArrayList();
		list.add("1");
		list.add("2");
		list.add("1");
		list.add(new ListTest());		// list에 객체를 원소로 추가
		for(int i=0;i<list.size();i++) {
			Object o=list.get(i);
			if(o instanceof String) {	// instanceof 이용해서 String만 출력
				String s=(String)o;
				System.out.println(s);
			}			
		}
	}
}
결과: 1
2
1
```

제네릭을 사용하여 보다 나은 코드를 구현해보자

```java
import java.util.ArrayList;
public class ListTest {
	public static void main(String[] args) {
		ArrayList<String> list=new ArrayList<String>();		// 제네릭
		list.add("1");
		list.add("2");
		list.add("1");
		
		for(String s:list) {			
			System.out.println(s);			
		}
	}
}
```

**아래 코드는 다시 복습하기 **

```java
package test.gengric2;
import java.util.ArrayList;
public class Test {
	public static void main(String[] args) {
		Cat cat = new Cat();
		AnimalList<LandAnimal> zoo = new AnimalList();
		zoo.add(cat);
		
		AnimalList<WaterAnimal> zoo2 = new AnimalList();
		//zoo2.add(cat);
		Fish fish = new Fish();
		zoo2.add(fish);
		
		AnimalList.cryingAnimalList(zoo);
	}
}

class AnimalList<T>{
	ArrayList<T> al = new ArrayList<T>();
	static public void cryingAnimalList(AnimalList<? extends LandAnimal> al) {	// 모든 동물을 울게 함
		LandAnimal la = al.get(0);
		la.crying();
	}
	public void add(T o) {	// 동물 추가
		al.add(o);
	}
	public T get(int index) {	// 동물 리턴
		return al.get(index);
	}
	public void remove() { 	// 동물 삭제
		
	}
}

class WaterAnimal{
	public void swim() {
		System.out.println("수중동물");
	}
}
class Fish extends WaterAnimal{
	@Override
	public void swim() {
		System.out.println("지느러미를 움직임");
	}
}

class LandAnimal{
	public void crying() {
		System.out.println("육지동물");
	}
}
class Cat extends LandAnimal{
	@Override
	public void crying() {
		System.out.println("야옹냐옹");
	}
}
class Dog extends LandAnimal{
	@Override
	public void crying() {
		System.out.println("멍멍");
	}
}
class Sparrow extends LandAnimal{
	@Override
	public void crying() {
		System.out.println("짹짹");
	}
}
```


