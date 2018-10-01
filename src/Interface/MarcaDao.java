package Interface;

import Core.GenericDao;
import Entity.Marca;
import java.util.List;

public interface MarcaDao extends GenericDao<Marca, Long>{
    public Marca SalvarMarca(Marca Entity);
    public List<Marca> listAllMarca();
    public void deletar(Marca Entity);
}
