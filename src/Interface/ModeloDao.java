package Interface;

import Core.GenericDao;
import Entity.Marca;
import Entity.Modelo;
import java.io.Serializable;
import java.util.List;


public interface ModeloDao extends GenericDao<Modelo, Long>{
    public Modelo SalvarModelo(Modelo Entity);
    public List<Modelo> listAllModelo();
    public void deletar(Modelo Entity);
    public Modelo findModeloByCampo(String campo , String name);
}
