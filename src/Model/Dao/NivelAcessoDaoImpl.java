package Model.Dao;

import Constantes.Severidade;
import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import Entity.NivelAcesso;
import Interface.NivelAcessoDao;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NivelAcessoDaoImpl extends GenericDaoImpl<NivelAcesso, Long> implements NivelAcessoDao{

    private static NivelAcessoDaoImpl instance;
    
    public static NivelAcessoDaoImpl getInstance(){
        if(instance == null)
            instance = new NivelAcessoDaoImpl();
        return instance;
    }
    public NivelAcessoDaoImpl() {
        super(NivelAcesso.class);
    }

    @Override
    public NivelAcesso FindNivelById(Long id) {
        NivelAcesso nivel = super.findById(id);
        return nivel;
    }

    @Override
    public List<NivelAcesso> FindNivelWhere(Predicate p) {
        List<NivelAcesso> listAcesso = super.findWhere(p);
        return listAcesso;
    }

    @Override
    public List<NivelAcesso> FindNivelAll() {
        List<NivelAcesso> niveis = super.findAll();
        return niveis;
    }
    
    public NivelAcesso getByName(String nivel){
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        assert(nivel.trim().length() > 0) : "Erro o nivel não foi preenchido";
        NivelAcesso nivelAcesso = null;
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<NivelAcesso> criteria = builder.createQuery(NivelAcesso.class);
            Root<NivelAcesso> root = criteria.from(NivelAcesso.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("no_nivel"), nivel));
            nivelAcesso = entityManager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            LogsDaoImpl.getInstance().CreateLog("Não foi possivel trazer categoria pelo nome : " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return nivelAcesso;
    }
    
}
