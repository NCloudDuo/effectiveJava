# int 상수 대신 열거타입(enum)을 사용하라

### 열거타입(Enum)이란?
+ 일정 개수의 상수 값을 정의한 다음, 그 외의 값은 허용하지 않는 타입이다.
+ 클래스 안에, public static final들로 상수를 선언해 사용하는 정수 열거패턴보다 장점이 많다.
  + type_safe를 보장한다.
  + 상수를 위해서 namespace 접두어, 접미어를 사용할 필요가 없다.
  + 정수 열거패턴을 사용하고 있는 클래스에서, 상수값이 바뀌면 재컴파일해야한다.
  + 정수 상수는 문자열로 출력하기가 까다롭다
    + 출력하거나, 디버거로 살펴보면 단지 숫자로만 보이기 때문
  + 문자열 열거패턴은 더 좋지 않다.

+ 열거타입은 완전한 형태의 클래스이다.
+ 상수 하나당 자신의 인스턴스를 하나씩 만들어 `public static final`필드로 공개한다.

### 장점은?
+ 클라이언트가 인스턴스를 직접생성하거나 확장이 불가능하므로, 싱글턴이 보장된다.
+ 컴파일 타임에서, type-safe가보장된다.
  + 열거타입 변수를 선언해놓고, 그에맞는 열거타입 상수를 할당하지않고, null을 제외한, 다른값을 할당하려고 시도할경우 컴파일 오류가 발생한다.
+ 열거타입에 새로운 상수를 추가하거나, 순서를 바꿔도 재컴파일을 하지 않아도 된다.
  + 공개되는 것이 오직 필드의 이름이라서
+ toString메서드로 식별하기 편하다.
+ 메서드, 필드추가가능하고, 인터페이스도 implements가 가능하다
+ values()라는 메서드를 제공해서, 상수들을 배열로 묶어서 제공하는 메서드도 제공한다.
+ valueOf(String)메서드를 제공해서, 상수이름을 인자로 넣으면 enum상수를 반환하는 메서드도 제공한다.
````java
public enum Operation {
    PLUS("+"){
        @Override
        public double apply(double x, double y) {
            return x+y;
        }
    },
    MINUS("-"){
        @Override
        public double apply(double x, double y) {
            return x-y;
        }
    },
    TIMES("*"){
        @Override
        public double apply(double x, double y) {
            return x*y;
        }
    },DIVIDE("/"){
        @Override
        public double apply(double x, double y) {
            return x/y;
        }
    };

    private final String symbol;

    Operation(final String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);

}


class OperationTest {

    @Test
    void test(){
        Operation plus = Operation.valueOf("PLUS");
        assertEquals(plus,Operation.PLUS);

        assertThrows(IllegalArgumentException.class, () -> Operation.valueOf("XXXX"), "enum상수와 일치하지 않는 값을 인자로 넣으면 IllegalArgumentException이 발생합니다.");
    }
}
````

### 상수별 메서드 구현에서, 열거타입 상수끼리 코드가 중첩될 경우, 전략 열거타입패턴을 사용하자.
````java
public enum PayrollDay {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),FRIDAY(PayType.WEEKDAY),SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(final PayType payType) {
        this.payType = payType;
    }

    int pay(int minutesWorked, int payRate){
        return payType.pay(minutesWorked, payRate);
    }

    enum PayType{
        WEEKDAY{
            @Override
            int overtimePay(int mins, int payRate) {
                return 0;
            }
        },
        WEEKEND{
            @Override
            int overtimePay(int mins, int payRate) {
                return 0;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate){
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}

````