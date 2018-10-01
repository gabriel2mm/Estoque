package Model.Dao;

import Constantes.TipoEntradaSaida;
import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import Entity.EntradaSaida;
import Interface.EntradaSaidaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EntradaSaidaDaoImpl extends GenericDaoImpl<EntradaSaida, Long> implements EntradaSaidaDao{
    
    private static EntradaSaidaDaoImpl instance;
    
    public static EntradaSaidaDaoImpl getInstance(){
        if(instance == null)
            instance = new EntradaSaidaDaoImpl();
        return instance;
    }

    public EntradaSaidaDaoImpl() {
        super(EntradaSaida.class);
    }

    @Override
    public EntradaSaida SalvarEntradaSaida(EntradaSaida Entity) {
        return super.Salvar(Entity);
    }

    @Override
    public List<EntradaSaida> getAllEntrada() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<EntradaSaida> list = new ArrayList<>();
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntradaSaida> criteria = builder.createQuery(EntradaSaida.class);
            Root<EntradaSaida> root = criteria.from(EntradaSaida.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("TipoEntradaSaida"), TipoEntradaSaida.ENTRADA));
            criteria.orderBy(builder.desc(root.get("atualizacao")));
            return list = entityManager.createQuery(criteria).getResultList();
        }catch(Exception e){
            e.printStackTrace();
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return list;
    }

    @Override
    public List<EntradaSaida> getAllSaida() {
         EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<EntradaSaida> list = new ArrayList<>();
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EntradaSaida> criteria = builder.createQuery(EntradaSaida.class);
            Root<EntradaSaida> root = criteria.from(EntradaSaida.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("TipoEntradaSaida"), TipoEntradaSaida.SAIDA));
            criteria.orderBy(builder.desc(root.get("atualizacao")));
            return list = entityManager.createQuery(criteria).getResultList();
        }catch(Exception e){
            e.printStackTrace();
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return list;
    }
    
    
    
    
    
    
}
