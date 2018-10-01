package Model.Dao;

import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import Entity.Marca;
import Interface.MarcaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

public class MarcaDaoImpl extends GenericDaoImpl<Marca,Long> implements MarcaDao{
    private static MarcaDaoImpl instance;
   
    public static MarcaDaoImpl getInstance(){
        if(instance == null)
            instance = new MarcaDaoImpl();
        return instance;
    }
    
    public MarcaDaoImpl() {
        super(Marca.class);
    }

    @Override
    public Marca SalvarMarca(Marca Entity) {
        return super.Salvar(Entity);
    }

    @Override
    public List<Marca> listAllMarca() {
        List<Marca> list = super.findAll();
        return list;
    }

    @Override
    public void deletar(Marca Entity) {
        super.Deletar(Entity);
    }
    
   
    public Marca findByName(String name){
       return super.findByName("marca", name);
    }   
}
