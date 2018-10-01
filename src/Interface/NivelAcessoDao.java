package Interface;

import Core.GenericDao;
import Entity.NivelAcesso;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface NivelAcessoDao extends GenericDao<NivelAcesso, Long>{
    
    public NivelAcesso FindNivelById(Long id);
    public List<NivelAcesso> FindNivelAll();
    public List<NivelAcesso> FindNivelWhere(Predicate p);
    
}
