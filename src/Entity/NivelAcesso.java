package Entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sgttb002_NivelAcesso")
public class NivelAcesso implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="co_id")
    private int id;
    
    @Column(name = "co_nivel")
    private int co_nivel;
    
    @Column(name="no_nivel" , unique = true , nullable = false)
    private String no_nivel;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the co_nivel
     */
    public int getCo_nivel() {
        return co_nivel;
    }

    /**
     * @param co_nivel the co_nivel to set
     */
    public void setCo_nivel(int co_nivel) {
        this.co_nivel = co_nivel;
    }

    /**
     * @return the no_nivel
     */
    public String getNo_nivel() {
        return no_nivel;
    }

    /**
     * @param no_nivel the no_nivel to set
     */
    public void setNo_nivel(String no_nivel) {
        this.no_nivel = no_nivel;
    }


  
    
    
}
