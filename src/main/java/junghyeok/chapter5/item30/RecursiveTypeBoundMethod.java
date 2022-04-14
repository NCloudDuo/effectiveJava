package junghyeok.chapter5.item30;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class RecursiveTypeBoundMethod {

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> collection) {
        if(collection.isEmpty()){
            return Optional.empty();
        }

        E result = null;
        for(E e : collection){
            if(result==null || e.compareTo(result)>0){
                result = Objects.requireNonNull(e);
            }
        }

        return Optional.of(result);
    }
}
