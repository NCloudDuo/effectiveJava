package junghyeok.item18;

import junghyeok.chapter4.item18.EncryptionOut;
import junghyeok.chapter4.item18.FileOut;
import junghyeok.chapter4.item18.FileOutImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class DecoratorTest {

    @Test
    void test(){
        FileOut fileOutImpl = new FileOutImpl();
        FileOut encryptOut = new EncryptionOut(fileOutImpl);
        Assertions.assertEquals("암호화data됐습니다", encryptOut.writeOut("data"));
    }
}
