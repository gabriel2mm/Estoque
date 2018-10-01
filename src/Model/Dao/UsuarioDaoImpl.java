package Model.Dao;
import Constantes.Severidade;
import Interface.UsuarioDao;
import Entity.Usuario;
import java.util.List;
import Core.GenericDaoImpl;
import Core.PersistenceUtil;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Long> implements UsuarioDao{

    private static UsuarioDaoImpl instance;
    
    public static UsuarioDaoImpl getInstance(){
        if(instance == null)
            instance = new UsuarioDaoImpl();
        return instance;
    }
    
    public UsuarioDaoImpl() {
        super(Usuario.class);
    }

    @Override
    public Usuario CreateUser(Usuario user) {
        user = super.Salvar(user);
        return user;
    }

    @Override
    public List<Usuario> ListAllUser() {
        List<Usuario> users = super.findAll();
        return users;
    }

    @Override
    public List<Usuario> ListWhereUser(Predicate p){
         List<Usuario> users = super.findWhere(p);
         return users;
    }

    @Override
    public void DeleteUser(Usuario user) {
        super.Deletar(user);
    }

    @Override
    public Usuario UpdateUser(Usuario user) {
        user = super.Update(user);
        return user;
    }

    @Override
    public Usuario FindUserById(Long id) {
        Usuario user = super.findById(id);
        return user;
    }

    @Override
    public Usuario FindByLogin(String Usuario, String Senha) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Usuario user = null;
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            
            criteria.distinct(true).select(root);
            criteria.where(builder.and(
                    builder.equal(root.get("senha"), Senha),
                    builder.or(
                            builder.equal(root.get("matricula") , Usuario),
                            builder.equal(root.get("cpf") , Usuario)
            )));
             user = entityManager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            LogsDaoImpl.getInstance().CreateLog("Não foi possível carregar usuários", new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        }finally{
            PersistenceUtil.Close(entityManager);
        }
        return user;
    }
    
}
