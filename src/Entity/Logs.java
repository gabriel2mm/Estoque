package Entity;

import Constantes.Severidade;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sgttb003_Logs")
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="co_id")
    private Long id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="co_severidade")
    private Severidade severidade = Severidade.INFO;
    
    @Column(name="bt_log")
    private String bt_log;
    
    @Column(name="dt_log")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt_log = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Logs[ id=" + id + " ]";
    }

    /**
     * @return the bt_log
     */
    public String getBt_log() {
        return bt_log;
    }

    /**
     * @param bt_log the bt_log to set
     */
    public void setBt_log(String bt_log) {
        this.bt_log = bt_log;
    }

    /**
     * @return the dt_log
     */
    public Date getDt_log() {
        return dt_log;
    }

    /**
     * @param dt_log the dt_log to set
     */
    public void setDt_log(Date dt_log) {
        this.dt_log = dt_log;
    }

    /**
     * @return the severidade
     */
    public Severidade getSeveridade() {
        return severidade;
    }

    /**
     * @param severidade the severidade to set
     */
    public void setSeveridade(Severidade severidade) {
        this.severidade = severidade;
    }
    
}
