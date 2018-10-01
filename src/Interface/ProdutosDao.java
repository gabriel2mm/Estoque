package Interface;

import Core.GenericDao;
import Entity.Marca;
import Entity.Modelo;
import Entity.Produto;
import java.io.Serializable;
import java.util.*;

public interface ProdutosDao extends GenericDao<Produto, Long>{
    public Produto SalvarProduto(Produto Entity); 
    public void DeletarProduto(Produto Entity);
    public Produto AlterarProduto(Produto Entity);
    public List<Produto> ListarTodos();
    public Produto FindById(Long id);
    public Produto FindByName(String nome);
    public Entity.Marca FindMarcaByName(String nome);
    public Modelo FindModeloByName(String nome);
}
