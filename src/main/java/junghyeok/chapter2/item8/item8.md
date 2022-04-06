# finalizer와 cleaner 사용을 피하라

자바는 두가지 객체 소멸자를 제공한다.  
첫번째는 finalizer는 예측 불가하고, 상황에 따라 위험할 수 있어 일반적으로 불필요하다.(쓰지 말아야함, 9부터 Deprecated로 되었다)  
cleaner가 finalizer의 대안으로 나왔지만, 일반적으로 불필요하다  

cleaner와 finalizer 둘 다 즉시 수행된다는 보장이 없어서 사용하면 안된다.

이 아이템은 굳이 사용해본적도 없고, 쓰지말라는데 정리할 필요가 없을 것 같아 이쯤에서 close