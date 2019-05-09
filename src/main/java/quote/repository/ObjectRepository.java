package quote.repository;

import java.util.Collection;

public interface ObjectRepository<T> {

     void submit(T t);

    Collection<T> retrieveAll();

     T get(long idQuote);

}
