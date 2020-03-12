package domain.repositories;

import java.util.List;

public interface IDAO<T> {
	
	public T insert(T t);
	
	public List<T> list() ;
	
	public void delete(int id);

}
