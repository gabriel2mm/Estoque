package Interface;

import Constantes.Severidade;
import Core.GenericDao;
import Entity.Logs;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface LogsDao extends GenericDao<Logs, Long>{
    public void CreateLog(String bt_arquivo , Date dt_log , Severidade severidade);
    public List<Logs> FindLogAll();
    public List<Logs> FindLogWhere(Predicate p);
}
