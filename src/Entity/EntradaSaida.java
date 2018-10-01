package Entity;

import Constantes.Severidade;
import Constantes.TipoEntradaSaida;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sgttb003_EntradaSaida")
public class EntradaSaida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_produto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_usuario")
    private Usuario usuario;

    @Column(name="dt_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacao;
    
    @Column(name="nu_quantidade")
    private int quantidade = 0;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="co_entradaSaida")
    private TipoEntradaSaida TipoEntradaSaida;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the atualizacao
     */
    public Date getAtualizacao() {
        return atualizacao;
    }

    /**
     * @param atualizacao the atualizacao to set
     */
    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the TipoEntradaSaida
     */
    public TipoEntradaSaida getTipoEntradaSaida() {
        return TipoEntradaSaida;
    }

    /**
     * @param TipoEntradaSaida the TipoEntradaSaida to set
     */
    public void setTipoEntradaSaida(TipoEntradaSaida TipoEntradaSaida) {
        this.TipoEntradaSaida = TipoEntradaSaida;
    }

   

}
