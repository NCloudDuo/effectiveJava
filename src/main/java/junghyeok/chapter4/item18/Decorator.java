package junghyeok.chapter4.item18;

public abstract class Decorator implements FileOut{
    private final FileOut delegate; //위임대상

    public Decorator(FileOut delegate) {
        this.delegate = delegate;
    }

    protected String doDelegate(String data){
        return delegate.writeOut(data);
    }
}
