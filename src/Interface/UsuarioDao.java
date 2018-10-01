package Interface;

import Core.GenericDao;
import Entity.Usuario;
import java.util.List;
import javax.persistence.criteria.Predicate;

public interface UsuarioDao extends GenericDao<Usuario, Long>{
    public Usuario CreateUser(Usuario user);
    public List<Usuario> ListAllUser();
    public List<Usuario> ListWhereUser(Predicate p);
    public void DeleteUser(Usuario user);
    public Usuario UpdateUser(Usuario user);
    public Usuario FindUserById(Long id);
    public Usuario FindByLogin(String Usuario, String Senha);
}
