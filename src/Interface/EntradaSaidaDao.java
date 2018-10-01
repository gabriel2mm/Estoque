package Interface;

import Core.GenericDao;
import Entity.EntradaSaida;
import java.util.List;

public interface EntradaSaidaDao extends GenericDao<EntradaSaida, Long>{
    public EntradaSaida SalvarEntradaSaida(EntradaSaida Entity);
    public List<EntradaSaida> getAllEntrada();
    public List<EntradaSaida> getAllSaida();
}
