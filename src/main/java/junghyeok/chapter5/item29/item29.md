# 이왕이면 제네릭 타입으로 만들라

### 일반클래스를 제네릭 클래스로 만드는 방법
+ 첫번째는, 타입 매개변수를 추가하는것이다.
+ 기존의 일반클래스에서 Object[](Object 배열)이 사용됐을경우, 이를 E[](타입매개변수 배열)로 바꾸는것은 불가능하다.
  + 타입매개변수로는 배열을 만들 수가없다. (실체 불가능하기 때문에)
  + 첫번째 방법은 Object배열을 생성한 당므에 타입매개변수 배열로 형변환을 하는것
    + ```java
      private E[] elements;
      
      elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
      ```
    + 이렇게 할 경우, 컴파일러는 오류대신 경고를 내보낸다. (warning : unchecked cast)
    + 컴파일러가, type safe한지 증명할 방법이 없지만, 개발자 입장에서 type-safe를 확신한다면 `@SuppressWarnings`로 해당 경고를 숨기면된다.
  + 두번째 방법은 elements 필드의 타입을 Object[]로 바꾸는것이다.
    + 이럴경우 타입매개변수가 반환타입인 메서드에서 오류가 나게된다.
    + ```java
      private Object[] elements;
      
      public E pop(){
        E result = elements[--size]; // incompatible types 컴파일오류
      }
      ```
    + 그래서, 형변환을 할 경우? 경고가 뜬다.
    + ```java
      E result = (E) elements[--size]; //warning: [unchecked] cast
      ```
    + 컴파일러는 런타임에 이뤄지는 형변환이 안전한지 증명할 방법이 없어서 warning이 뜨게 되는데, 배열에 삽입되는 원소가 타입매개변수임을 확신할 수있다면 변수의 할당문에 `@SuppressWarnings`을 붙여 경고를 숨기면된다.

  + 그래서 두가지 방법의 차이점은?
    + 첫번째 방법은 형변환을 배열 생성시 단 한번만 해주면 된다.
    + 두번째 방식은 배열에서 원소를 읽을때마다 해줘야한다.

## 중요한점
+ 어찌 됐던간에, 기존 클래스를 제네릭클래스로 만들 수 있으니, 제네릭클래스로 만들것을 추천함