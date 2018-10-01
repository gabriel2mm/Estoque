package Model.Dao;

import Constantes.Severidade;
import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import Entity.Marca;
import Entity.Modelo;
import Entity.Produto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Long> implements Interface.ProdutosDao{
    private static ProdutoDaoImpl instance;
    
    public ProdutoDaoImpl() {
        super(Produto.class);
    }

    public static ProdutoDaoImpl getInstance(){
        if(instance == null)
            instance = new ProdutoDaoImpl();
        return instance;
    }
    
    @Override
    public Produto SalvarProduto(Produto Entity) {
       return super.Salvar(Entity);
    }

    @Override
    public void DeletarProduto(Produto Entity) {
        super.Deletar(Entity);
    }

    @Override
    public Produto AlterarProduto(Produto Entity) {
        return super.Update(Entity);
    }

    @Override
    public List<Produto> ListarTodos() {
       List<Produto> list = super.findAll();
       return list;
    }

    @Override
    public Produto FindById(Long id) {
        Produto prod = super.findById(id);
        return prod;
    }

    @Override
    public Produto FindByName(String nome) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Produto prod = null;
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
            Root<Produto> root = criteria.from(Produto.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("produto"), nome));
            return prod = entityManager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            LogsDaoImpl.getInstance().CreateLog("Erro ao achar Produto por nome : " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return prod;
    }

    @Override
    public Marca FindMarcaByName(String nome) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Marca marca = null;
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Marca> criteria = builder.createQuery(Marca.class);
            Root<Marca> root = criteria.from(Marca.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("marca"), nome));
            return marca = entityManager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            LogsDaoImpl.getInstance().CreateLog("Erro ao achar Produto por nome : " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return marca;
    }

    @Override
    public Modelo FindModeloByName(String nome) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Modelo modelo = null;
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Modelo> criteria = builder.createQuery(Modelo.class);
            Root<Modelo> root = criteria.from(Modelo.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("modelo"), nome));
            return modelo = entityManager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            LogsDaoImpl.getInstance().CreateLog("Erro ao achar Produto por nome : " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return modelo;
    }
    
    public List<Object[]> findByNameObject(){
        List<Object[]> list = new ArrayList<>();
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
            Root<Produto> root = criteria.from(Produto.class);
            criteria.distinct(true).select(root.get("produto"));
            criteria.orderBy(builder.asc(root.get("produto")));
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
