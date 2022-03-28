# try-finally보다는 try-with-resources를 사용하자

- 요약
    - try-finally의 경우 try와 finally에서 각각 예외가 발생할때 try에서 발생한 예외를 finally가 집어삼킬 수 있다.
    - 회수해야 하는 자원을 다룰때에는 try-finally말고, AutoCloseable을 구현하여 try-with-resources를 이용하자.
- 질문
    - Throwable 계층도
