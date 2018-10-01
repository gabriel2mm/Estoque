package Core;

import Entity.Marca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    public Class<T> PersistentClass;
    private EntityManager entityManager = PersistenceUtil.getEntityManager();

    public GenericDaoImpl(Class<T> perisClass) {
        this.PersistentClass = perisClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class<T> getPersistentClass() {
        return PersistentClass;
    }

    @Override
    public T findById(ID id) {
        entityManager = PersistenceUtil.getEntityManager();
        try {
            T entity = (T) entityManager.find(getPersistentClass(), id);
            return entity;
        } catch (Exception e) {
            PersistenceUtil.rollback(entityManager);
            JOptionPane.showConfirmDialog(null, "Nâo foi possivel achar registro por id", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        entityManager = PersistenceUtil.getEntityManager();
        List<T> list = new ArrayList<>();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery((Class<T>) getPersistentClass());
            Root<T> root = criteria.from((Class<T>) getPersistentClass());
            criteria.distinct(true).select(root);
            list = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            PersistenceUtil.rollback(entityManager);
            JOptionPane.showConfirmDialog(null, "Nâo foi possivel trazer todos os registros!", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        return list;
    }

    @Override
    public List<T> findWhere(Predicate p) {
        entityManager = PersistenceUtil.getEntityManager();
        List<T> list = new ArrayList<>();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
            Root<T> root = criteria.from(getPersistentClass());
            criteria.distinct(true).select(root);
            list = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            PersistenceUtil.rollback(entityManager);
            JOptionPane.showConfirmDialog(null, "Nâo foi possivel Procurar predicate!", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            PersistenceUtil.Close(entityManager);
        }

        return list;
    }

    @Override
    public T Salvar(T Entity) {
        entityManager = PersistenceUtil.getEntityManager();
        try {
            getEntityManager().persist(Entity);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            PersistenceUtil.rollback(entityManager);
            JOptionPane.showConfirmDialog(null, "Nâo foi possivel salvar o registro!", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        return Entity;
    }

    @Override
    public T Update(T Entity) {
        entityManager = PersistenceUtil.getEntityManager();
        try {
            Entity = getEntityManager().merge(Entity);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            PersistenceUtil.rollback(entityManager);
            JOptionPane.showConfirmDialog(null, "Nâo foi possivel realizar o update do registro!", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        return Entity;
    }

    @Override
    public void Deletar(T Entity) {
        entityManager = PersistenceUtil.getEntityManager();
        try {
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaDelete<T> criteria = builder.createCriteriaDelete((Class<T>) getPersistentClass());
            Root<T> root = criteria.from((Class<T>) getPersistentClass());
            criteria.where(builder.equal(root, Entity));
            entityManager.createQuery(criteria).executeUpdate();
        } catch (Exception e) {
            PersistenceUtil.rollback(entityManager);
            JOptionPane.showConfirmDialog(null, "Nâo foi possivel excluir o registro", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
    }

    @Override
    public T findByName(String campo, String name) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<T> Entity = new ArrayList<>();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery((Class<T>) getPersistentClass());
            Root<T> root = criteria.from((Class<T>) getPersistentClass());
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get(campo), name));
            Entity = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível achar registro pelo Nome " + e.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            PersistenceUtil.rollback(entityManager);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        if(Entity.get(0) != null){
            return Entity.get(0);
        }else{
            Entity.add(null);
            return Entity.get(0);
        }
    }
}
