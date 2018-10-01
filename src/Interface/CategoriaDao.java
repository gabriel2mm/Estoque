package Interface;

import Core.GenericDao;
import Entity.Categoria;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface CategoriaDao extends GenericDao<Categoria, Long>{
    public Categoria CreateCategoria(Categoria categoria);
    public List<Categoria> ListAllCategoria();
    public void DeleteCategoria(Categoria categoria);
    public Categoria UpdateCategoria(Categoria categoria);
    public Categoria FindCategoriaById(Long id);
    public List<Categoria> ListAllPesquisa(String pesquisa);
}
