package junghyeok.chapter4.item18;

public class EncryptionOut extends Decorator {
    public EncryptionOut(FileOut delegate) {
        super(delegate);
    }

    @Override
    public String writeOut(String data) {
        String encryptedData = "암호화" + data + "됐습니다";
        return super.doDelegate(encryptedData);
    }
}
