package Model.Dao;

import Constantes.Severidade;
import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import Entity.Categoria;
import Interface.CategoriaDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Long> implements CategoriaDao {

    private static CategoriaDaoImpl instance;

    public CategoriaDaoImpl() {
        super(Categoria.class);
    }

    public static CategoriaDaoImpl getInstance() {
        if (instance == null) {
            instance = new CategoriaDaoImpl();
        }
        return instance;
    }

    @Override
    public Categoria CreateCategoria(Categoria categoria) {
        categoria = super.Salvar(categoria);
        return categoria;
    }

    @Override
    public List<Categoria> ListAllCategoria() {
        List<Categoria> ListCat = super.findAll();
        return ListCat;
    }

    @Override
    public void DeleteCategoria(Categoria categoria) {
        super.Deletar(categoria);
    }

    @Override
    public Categoria UpdateCategoria(Categoria categoria) {
        categoria = super.Update(categoria);
        return categoria;
    }

    @Override
    public Categoria FindCategoriaById(Long id) {
        Categoria categoria = super.findById(id);
        return categoria;
    }

    @Override
    public List<Categoria> ListAllPesquisa(String pesquisa) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<Categoria> list = new ArrayList<>();
        assert (pesquisa.trim().length() > 0) : "Erro, a pesquisa veio nula";
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
            Root<Categoria> root = criteria.from(Categoria.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.like(root.get("categoria"), "%" + pesquisa + "%"));
            list = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            PersistenceUtil.rollback(entityManager);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        return list;
    }

    public Categoria FindCategoriaByName(String nome) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Categoria cat = null;
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
            Root<Categoria> root = criteria.from(Categoria.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("categoria"), nome));
            return cat = entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            LogsDaoImpl.getInstance().CreateLog("Erro ao achar Produto por nome : " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        return cat;
    }

}
