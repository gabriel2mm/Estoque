package Model.Dao;

import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import Entity.Logs;
import Entity.Marca;
import Entity.Modelo;
import Interface.ModeloDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

public class ModeloDaoImpl extends GenericDaoImpl<Modelo, Long> implements ModeloDao {

    private static ModeloDaoImpl instance;

    public static ModeloDaoImpl getInstance() {
        if (instance == null) {
            instance = new ModeloDaoImpl();
        }
        return instance;
    }

    public ModeloDaoImpl() {
        super(Modelo.class);
    }

    @Override
    public Modelo SalvarModelo(Modelo Entity) {
        return super.Salvar(Entity);
    }

    @Override
    public List<Modelo> listAllModelo() {
        List<Modelo> list = super.findAll();
        return list;
    }

    @Override
    public void deletar(Modelo Entity) {
        super.Deletar(Entity);
    }

    @Override
    public Modelo findModeloByCampo(String campo, String name) {
        return super.findByName(campo, name);
    }

    public Modelo findModeloByMarca(Marca marca) {
        Modelo modelo = null;
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Modelo> criteria = builder.createQuery(Modelo.class);
            Root<Modelo> root = criteria.from(Modelo.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("marca") , marca));
            return modelo  = entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível achar modelo com a marca especificada!" , "Erro" , JOptionPane.ERROR_MESSAGE);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return modelo;
    }
    
    
    public List<Modelo> findListModeloByMarca(Marca marca) {
        List<Modelo> modelos = new ArrayList<>();
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Modelo> criteria = builder.createQuery(Modelo.class);
            Root<Modelo> root = criteria.from(Modelo.class);
            criteria.distinct(true).select(root);
            criteria.where(builder.equal(root.get("marca") , marca));
            return modelos  = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível achar modelo com a marca especificada!" , "Erro" , JOptionPane.ERROR_MESSAGE);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return modelos;
    }
}
