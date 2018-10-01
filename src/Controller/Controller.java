package Controller;

import Core.PersistenceUtil;
import Entity.Usuario;
import javax.persistence.EntityManager;
import javax.swing.JFrame;
import View.viewCategoria;
import View.viewEntrada;
import View.viewGerenciarUsuarios;
import View.viewLog;
import View.viewLogin;
import View.viewPrincipal;
import View.viewProduto;
import View.viewSaida;

public class Controller {
    private Usuario user;
    private static Usuario userLogado;
    private viewLog viewlog = new viewLog();
    private viewLogin login = new viewLogin();
    private viewProduto produto = new viewProduto();
    private viewCategoria categoria = new viewCategoria();
    private viewPrincipal principal = new viewPrincipal();
    private viewGerenciarUsuarios gerenciarUsuarios = new viewGerenciarUsuarios();
    private viewEntrada entrada = new viewEntrada();
    private viewSaida saida = new viewSaida();
    
    private static Controller instance;
    
     public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
        return instance;
    }
    
     public void init(){
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        PersistenceUtil.Close(entityManager);
    }
    public void Logar(Usuario user){
        this.user = user;
        getPrincipal().setState(JFrame.MAXIMIZED_BOTH);
        getPrincipal().setExtendedState(JFrame.MAXIMIZED_BOTH);
        getPrincipal().setLocationRelativeTo(null);
        getPrincipal().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getPrincipal().setTitle("SCIMTI - Principal");
        getPrincipal().setVisible(true);
    }
    
    public viewPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(viewPrincipal principal) {
        this.principal = principal;
    }

    public viewLogin getLogin() {
        login.setTitle("SCIMTI - Login");
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return login;
    }
    public void setLogin(viewLogin login) {
        this.login = login;
    }
    
    public Usuario getUser() {
        return user;
    }
    
    public void viewProdutos(){
        getProduto().setTitle("SCIMTI - Cadastrar produtos");
        getProduto().setLocationRelativeTo(null);
        getProduto().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getProduto().setVisible(true);
    }
    
    public void CadastroUsuario(){
        getGerenciarUsuarios().setTitle("SCIMTI - Gerenciar usuários");
        getGerenciarUsuarios().setLocationRelativeTo(null);
        getGerenciarUsuarios().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getGerenciarUsuarios().setVisible(true); 
    }
    
    public void ViewLogs(){
        getViewlog().setTitle("SCIMTI - LOGS");
        getViewlog().setLocationRelativeTo(null);
        getViewlog().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getViewlog().setVisible(true);
    }
    
    public void viewCategoria(){
        getCategoria().setTitle("SCIMTI - Gerenciar categoria");
        getCategoria().setLocationRelativeTo(null);
        getCategoria().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getCategoria().setVisible(true);
    }

    public void viewEntrada(){
        getEntrada().setTitle("SCIMTI - Entrada");
        getEntrada().setLocationRelativeTo(null);
        getEntrada().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getEntrada().setVisible(true);
    }
    
    public void viewSaida(){
        getSaida().setTitle("SCIMTI - SAÍDA");
        getSaida().setLocationRelativeTo(null);
        getSaida().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getSaida().setVisible(true);
    }
    
    public viewGerenciarUsuarios getGerenciarUsuarios() {
        return gerenciarUsuarios;
    }

    public void setGerenciarUsuarios(viewGerenciarUsuarios gerenciarUsuarios) {
        this.gerenciarUsuarios = gerenciarUsuarios;
    }

    public viewLog getViewlog() {
        return viewlog;
    }

    public void setViewlog(viewLog viewlog) {
        this.viewlog = viewlog;
    }

    public static Usuario getUserLogado() {
        return userLogado;
    }

    public static void setUserLogado(Usuario aUserLogado) {
        userLogado = aUserLogado;
    }
    
    public viewCategoria getCategoria() {
        return categoria;
    }
    public void setCategoria(viewCategoria categoria) {
        this.categoria = categoria;
    }

    public viewProduto getProduto() {
        return produto;
    }

    public void setProduto(viewProduto produto) {
        this.produto = produto;
    }

    public viewEntrada getEntrada() {
        return entrada;
    }

    public void setEntrada(viewEntrada entrada) {
        this.entrada = entrada;
    }

    public viewSaida getSaida() {
        return saida;
    }

    public void setSaida(viewSaida saida) {
        this.saida = saida;
    }
}
