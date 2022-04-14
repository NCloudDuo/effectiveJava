# 배열보다는 리스트를 사용하라

### 배열과 제네릭 타입과의 두가지 중요한 차이
+ 배열은 공변, 제네릭은 불공변
  + Sub가 Super의 하위타입이라면, Sub[]는 Super[]의 하위타입이 된다.
    ```java
    public class Super {
    }
    public class Sub extends Super{

    }
  
    public class Main {

      public static void main(String[] args) {
          Sub[] subs = new Sub[5];
          Main.output(subs); //컴파일 오류가 나지않는다.
      }

      static void output(Super[] arr){
          for(Super superItem : arr){
              System.out.println(superItem);
          }
      }
    }
  
    ```
  + 제네릭은 반면에, 불공변이다. 즉 `List<Super>`와 `List<Sub>`가 다르다.
    ```java
    List<Sub> subList = new ArrayList<>();
    genericOutput(subList); //컴파일오류가난다.
  
    static void genericOutput(List<Super> superList){
      superList.forEach(System.out::println);
    }
    ```
+ 배열은 실체화된다.
  + 제네릭은 컴파일타임에 타입정보를 활용하고, type erasure를 하기 때문에, 런타임에는 타입정보를 알 수 없다.

### 그래서, 배열과 제네릭은 잘 어우러지지 못한다.
+ 즉, 제네릭 배열을 생성하지 못한다.

### 근데, 제네릭은 타입세이프를 컴파일타임에 보장하므로, 배열보다는 리스트를 사용해 타입정보를 알려주는것이 좋다.