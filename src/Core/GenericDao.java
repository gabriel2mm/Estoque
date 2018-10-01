package Core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface GenericDao<T , ID extends Serializable>{
    public List<T> findAll();
    public List<T> findWhere(Predicate p);
    public void Deletar(T Entity);
    public T Salvar(T Entity);
    public T Update(T Entity);
    public T findById(ID id);
    public T findByName(String campo ,String name);
}
