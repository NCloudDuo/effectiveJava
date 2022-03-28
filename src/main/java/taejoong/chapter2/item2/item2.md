# 생성자에 매개변수가 많다면 빌더를 고려하라

- 요약
  - 선택적 매개변수가 많을 때 대응하기 위한 방법
      - `점층적 생성자 패턴`
      - `자바빈즈 패턴`
      - `빌더 패턴`

- lombok `@builder`
```
Before:
   @Builder
   class Example<T> {
   	private T foo;
   	private final String bar;
   }
   
After:
   class Example<T> {
   	private T foo;
   	private final String bar;
   	
   	private Example(T foo, String bar) {
   		this.foo = foo;
   		this.bar = bar;
   	}
   	
   	public static <T> ExampleBuilder<T> builder() {
   		return new ExampleBuilder<T>();
   	}
   	
   	public static class ExampleBuilder<T> {
   		private T foo;
   		private String bar;
   		
   		private ExampleBuilder() {}
   		
   		public ExampleBuilder foo(T foo) {
   			this.foo = foo;
   			return this;
   		}
   		
   		public ExampleBuilder bar(String bar) {
   			this.bar = bar;
   			return this;
   		}
   		
   		@java.lang.Override public String toString() {
   			return "ExampleBuilder(foo = " + foo + ", bar = " + bar + ")";
   		}
   		
   		public Example build() {
   			return new Example(foo, bar);
   		}
   	}
   }
```

- 질문
  - @Builder annotation target 별로 차이?
  - 제네릭에 대한 공부 => 재귀적 타입 한정(p20), ?와 T 차이 등등...
  - 공변 반환 타이핑(p21)
  - [직렬화 1편](https://techblog.woowahan.com/2550/)
  - [직렬화 2편](https://techblog.woowahan.com/2551/)