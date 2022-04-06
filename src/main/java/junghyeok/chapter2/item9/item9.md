# try-finally보다는 try-with-resources를 사용하라

AutoCloseable 인터페이스를 구현한 클래스라면 try-with-resources를 사용해서, 자원의 스코프를 제한하고, 사용을 다했다면 반환해주는 것이 좋다.

```java
public interface Closeable extends AutoCloseable {
    
    public void close() throws IOException;
}

public abstract class InputStream implements Closeable {
    
}

public abstract class OutputStream implements Closeable, Flushable {
    
}

static void copy(String src, String dst) throws IOException{
    try(InputStream in = new FileInputStream(src); OutputStream out=new FileOutputStream(dst)){
        byte[] buf=new byte[BUFFER_SIZE];
        int n;
        while ((n=in.read(buf))>=0){
            out.write(buf, 0, n);
        }
    }
}

```