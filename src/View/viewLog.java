package View;

import Core.PersistenceUtil;
import Constantes.Severidade;
import Entity.Logs;
import Model.Dao.LogsDaoImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class viewLog extends javax.swing.JFrame {

    
    public viewLog() {
        initComponents();
    }

    public void Pesquisar(String Busca, int sev) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            Calendar dt_inicial = Calendar.getInstance();
            dt_inicial.set(Calendar.DAY_OF_MONTH, -30);
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            
            CriteriaQuery<Logs> criteria = builder.createQuery(Logs.class);
            Root<Logs> root = criteria.from(Logs.class);
            criteria.distinct(true).select(root);
            
            List<Predicate> listPredicates = new ArrayList<Predicate>();
            listPredicates.add(builder.greaterThan(root.<Date>get("dt_log"), dt_inicial.getTime()));
            listPredicates.add(builder.like(root.get("bt_log"), "%" + txtPesquisa.getText() + "%"));
            listPredicates.add(builder.and(
                    builder.like(root.get("bt_log"), "%" + txtPesquisa.getText() + "%"),
                    builder.equal(root.get("severidade"), Severidade.parse(cbxSeveridade.getSelectedIndex()))
            ));
            if(Busca == null && sev == -1){
                criteria.where(listPredicates.get(0));
            }else if(Busca != null && sev == -1){
                criteria.where(listPredicates.get(1));
            }else if(Busca != null && sev != -1){
                criteria.where(listPredicates.get(2));
            }
            criteria.orderBy(builder.desc(root.get("dt_log")));
            List<Logs> logs = entityManager.createQuery(criteria).setMaxResults(1000).getResultList();
            DefaultTableModel dtm = new DefaultTableModel();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (logs.size() > 0) {
                dtm.addColumn("Data");
                dtm.addColumn("Severidade");
                dtm.addColumn("Log");
                logs.forEach((l) -> {
                    dtm.addRow(new String[]{
                        format.format(l.getDt_log()),
                        l.getSeveridade().Name(l.getSeveridade().getValue()),
                        l.getBt_log()
                    });
                });
            } else {
                dtm.addColumn("Resultado");
                dtm.addRow(new String[]{"Não há resultados para a pesquisa : " + txtPesquisa.getText()});
            }
            table_log.setModel(dtm);
            if (logs.size() > 0){
                table_log.getColumnModel().getColumn(0).setMinWidth(130);
                table_log.getColumnModel().getColumn(0).setMaxWidth(130);
                table_log.getColumnModel().getColumn(1).setMaxWidth(120);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro , não foi possivel trazer resultados : " + e.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            LogsDaoImpl.getInstance().CreateLog("Erro: " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxSeveridade = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_log = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Pesquisar no Log por:");

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        jLabel2.setText("Severidade.:");

        cbxSeveridade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INSERIR", "ALTERAR", "EXCLUIR", "CONSULTAR", "LISTAR", "SCHEDULE", "INFO", "EXCECAO", " " }));
        cbxSeveridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeveridadeActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/search_16.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSeveridade, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbxSeveridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        table_log.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_log);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Pesquisar(null, -1);
    }//GEN-LAST:event_formWindowOpened

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        Pesquisar(txtPesquisa.getText(), -1);
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Pesquisar(txtPesquisa.getText(), cbxSeveridade.getSelectedIndex()); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxSeveridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeveridadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeveridadeActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Pesquisar(null, -1);
    }//GEN-LAST:event_formWindowActivated

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewLog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxSeveridade;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_log;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
