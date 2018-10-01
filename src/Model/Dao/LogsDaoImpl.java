package Model.Dao;

import Constantes.Severidade;
import Core.GenericDaoImpl;
import Entity.Logs;
import Interface.LogsDao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;

public class LogsDaoImpl extends GenericDaoImpl<Logs, Long> implements LogsDao{

    private static LogsDaoImpl instance;
    
    public static LogsDaoImpl getInstance(){
        if(instance == null)
            instance = new LogsDaoImpl();
        return instance;
    }
    
    public LogsDaoImpl() {
        super(Logs.class);
    }

    @Override
    public void CreateLog(String bt_arquivo , Date dt_log , Severidade severidade) {
        Logs log = new Logs();
        log.setBt_log(bt_arquivo);
        log.setDt_log(dt_log);
        log.setSeveridade(severidade);
        super.Salvar(log);
    }

    @Override
    public List<Logs> FindLogAll() {
        List<Logs> list = super.findAll();
        return list;
    }

    @Override
    public List<Logs> FindLogWhere(Predicate p) {
        List<Logs> list = super.findWhere(p);
        return list;
    }
    
}
