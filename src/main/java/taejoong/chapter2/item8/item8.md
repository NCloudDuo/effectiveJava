# finalizer와 cleaner의 사용을 피해라

- 요약
  - 자바의 두 가지 객체 소멸자
    - finalizer
    - cleaner
  - finalizer와 cleaner의 용도
    - 자원의 소유자가 close()를 호출하지 않는 것에 대비한 안전망 역할
    - 네이티브 피어 회수

- lombok의 @CleanUp(쓰이는지는 모르겠음)
```java
// Example:
   public void copyFile(String in, String out) throws IOException {
       @Cleanup FileInputStream inStream = new FileInputStream(in);
       @Cleanup FileOutputStream outStream = new FileOutputStream(out);
       byte[] b = new byte[65536];
       while (true) {
           int r = inStream.read(b);
           if (r == -1) break;
           outStream.write(b, 0, r);
       }
   }
   
// Will generate:
   public void copyFile(String in, String out) throws IOException {
       @Cleanup FileInputStream inStream = new FileInputStream(in);
       try {
           @Cleanup FileOutputStream outStream = new FileOutputStream(out);
           try {
               byte[] b = new byte[65536];
               while (true) {
                   int r = inStream.read(b);
                   if (r == -1) break;
                   outStream.write(b, 0, r);
               }
           } finally {
               if (outStream != null) outStream.close();
           }
       } finally {
           if (inStream != null) inStream.close();
       }
   }
```
- 질문
  - [JNI](http://egloos.zum.com/sinuk/v/2676307)
  - [Thread vs Runnable](https://www.daleseo.com/java-thread-runnable/)
  
