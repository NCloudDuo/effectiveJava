# 태그 달린 클래스보다는 클래스 계층구조를 활용하라

### 안티패턴은 넘어가고, 정답만 작성

이 아이템에서 말하고자하는것은, `추상화시켜야할 부분을 한클래스에 몰아넣어서 복잡함과, 오류가능성을 증가시키지 말라` 인것같다.

```java
abstract class Figure{
    abstract double area();
}

class Circle extends Figure{
    final double radius;
    
    Circle(double radius){
        this.radius = radius;
    }
    
    @Override
    double area(){
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends Figure{
    final double length;
    final double width;
    
    Rectangle(double length, double width){
        this.length=length;
        this.width=width;
    }
    
    @Override
    double area(){
        return length * width;
    }
}
```