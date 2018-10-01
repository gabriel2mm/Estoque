package Core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class PersistenceUtil {

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static final EntityManager getEntityManager() {
            try {
                factory = Persistence.createEntityManagerFactory("EstoquePU");
                entityManager = factory.createEntityManager();
                if (!entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().begin();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro, O getEntityManager falhou!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        return entityManager;
    }

    public static final void Close(EntityManager em) {
        try {
            if (em != null && em.isOpen()
                    && em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro, não foi possivel realizar o commit antes de fechar a conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (em != null && em.isOpen()) {
                    em.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro, não foi possivel fechar a conexão", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static final void rollback(EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()
                && entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
            try {
                   entityManager.getTransaction().rollback();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Erro, Não foi possivel realizar o rollback!" , "Erro" , JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
