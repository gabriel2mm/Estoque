/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Core.PersistenceUtil;
import Constantes.Severidade;
import Entity.Categoria;
import Entity.Marca;
import Entity.Modelo;
import Model.Dao.CategoriaDaoImpl;
import Model.Dao.LogsDaoImpl;
import Model.Dao.MarcaDaoImpl;
import Model.Dao.ModeloDaoImpl;
import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class viewCategoria extends javax.swing.JFrame {

    private int operacao = 0;
    private Categoria categoria = new Categoria();

    public viewCategoria() {
        initComponents();
    }

    public void AtualizarTbModelo(){
        List<Modelo> list = ModeloDaoImpl.getInstance().listAllModelo();
        DefaultTableModel dtm = new DefaultTableModel();
        DateFormat df = new SimpleDateFormat("dd/MM/YY");
        if (list.size() > 0) {
            dtm.addColumn("Modelo");
            dtm.addColumn("Ano");
            dtm.addColumn("Marca");
            list.forEach((l) -> {
                dtm.addRow(new String[]{l.getModelo() , df.format(l.getData()) , String.valueOf(l.getMarca().getMarca())});
            });
        } else {
            dtm.addColumn("Resultado");
            dtm.addRow(new String[]{"Não há Modelo para exibir."});
        }
        table_Modelo.setModel(dtm);
    }
    
    public void AtualizarTbMarca() {
        List<Marca> list = MarcaDaoImpl.getInstance().listAllMarca();
        DefaultTableModel dtm = new DefaultTableModel();
        if (list.size() > 0) {
            dtm.addColumn("Marca");
            list.forEach((l) -> {
                dtm.addRow(new String[]{l.getMarca()});
            });
        } else {
            dtm.addColumn("Resultado");
            dtm.addRow(new String[]{"Não há Marca para exibir."});
        }
        table_Marca.setModel(dtm);
    }

    public void pesquisar() {
        if (txtPesquisa.getText() != null && txtPesquisa.getText().trim().length() > 0) {
            List<Categoria> listCategoria = CategoriaDaoImpl.getInstance().ListAllPesquisa(txtPesquisa.getText());
            DefaultTableModel dtm = new DefaultTableModel();
            if (listCategoria.size() > 0) {
                dtm.addColumn("id");
                dtm.addColumn("Categoria");
                listCategoria.forEach((list) -> {
                    dtm.addRow(new String[]{list.getId().toString(), list.getCategoria()});
                });
            } else {
                dtm.addColumn("Resultado");
                dtm.addRow(new String[]{"Não há resultados para : " + txtPesquisa.getText()});
            }
            tbCategoria.setModel(dtm);
            if (listCategoria.size() > 0) {
                tbCategoria.getColumnModel().getColumn(0).setMinWidth(50);
                tbCategoria.getColumnModel().getColumn(0).setMaxWidth(50);
            }
        }
    }

    public void Atualizar_Tabela() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        try {
            DefaultTableModel dtm = new DefaultTableModel();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
            Root<Categoria> root = criteria.from(Categoria.class);
            criteria.distinct(true).select(root);

            List<Categoria> listCategoria = entityManager.createQuery(criteria).getResultList();

            if (listCategoria.size() > 0) {
                dtm.addColumn("id");
                dtm.addColumn("Categoria");
                listCategoria.forEach((list) -> {
                    dtm.addRow(new String[]{String.valueOf(list.getId()), list.getCategoria()});
                });
            } else {
                dtm.addColumn("resultado");
                dtm.addRow(new String[]{"Nenhum resultado a ser exibido."});
            }

            tbCategoria.setModel(dtm);
            if (listCategoria.size() > 0) {
                tbCategoria.getColumnModel().getColumn(0).setMinWidth(50);
                tbCategoria.getColumnModel().getColumn(0).setMaxWidth(50);
            }
        } catch (Exception e) {
            LogsDaoImpl.getInstance().CreateLog("Erro : " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
            PersistenceUtil.rollback(entityManager);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
    }

    public void cadastrar() {
        if (txtCategoria.getText() != null && txtCategoria.getText().trim().length() > 0) {
            if (operacao == 0) {
                categoria = new Categoria();
            }
            categoria.setCategoria(txtCategoria.getText());
            CategoriaDaoImpl.getInstance().UpdateCategoria(categoria);
            if (operacao == 0) {
                JOptionPane.showMessageDialog(rootPane, "Categoria inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                LogsDaoImpl.getInstance().CreateLog("O usuario : " + Controller.Controller.getUserLogado().getNome() + "/" + Controller.Controller.getUserLogado().getMatricula() + " Inseriu a categoria : " + categoria.getCategoria(), new Date(), Severidade.INSERIR);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Categoria Alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                LogsDaoImpl.getInstance().CreateLog("O usuario : " + Controller.Controller.getUserLogado().getNome() + "/" + Controller.Controller.getUserLogado().getMatricula() + " Alterou a categoria : " + categoria.getCategoria(), new Date(), Severidade.ALTERAR);
            }
            operacao = 0;
            categoria = new Categoria();
            txtCategoria.setText("");
            Atualizar_Tabela();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro , Preencha o campo com o nome da categoria!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnInserir = new javax.swing.JButton();
        txtCategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCategoria = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Marca = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        txtMarca = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtModelo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        cbxMarca = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Modelo = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCIMTI - Gerenciar Categoria");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastrar Categoria"));

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_add_24.png"))); // NOI18N
        btnInserir.setText("Inserir/Atualizar");
        btnInserir.setEnabled(false);
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        txtCategoria.setEnabled(false);

        jLabel1.setText("Nome da Categoria:");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/file_24.png"))); // NOI18N
        jButton5.setText("Novo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInserir))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInserir))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Gerenciar Tabela"));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_edit_24.png"))); // NOI18N
        jButton3.setText("Alterar Selecionado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_del_24.png"))); // NOI18N
        jButton2.setText("Deletar Selecionado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoria: ");

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/search_16.png"))); // NOI18N
        jButton4.setText("Pesquisar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tbCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id", "Categoria"
            }
        ));
        jScrollPane1.setViewportView(tbCategoria);

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 330, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("Categoria", tab1);

        table_Marca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table_Marca);

        jLabel3.setText("Marca.:");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_del_16.png"))); // NOI18N
        jButton6.setText("Excluir Selecionado");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setText("Modelo.:");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_add_16.png"))); // NOI18N
        jButton7.setText("Inserir");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel5.setText("Marca.:");

        jLabel6.setText("Ano.:");

        txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel7.setText("Ex.: dd/MM/AAAA");

        cbxMarca.setToolTipText("Marca");
        cbxMarca.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbxMarcaPopupMenuWillBecomeVisible(evt);
            }
        });
        cbxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))))
                .addGap(12, 12, 12))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_add_16.png"))); // NOI18N
        jButton1.setText("Inserir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table_Modelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(table_Modelo);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cat_del_16.png"))); // NOI18N
        jButton8.setText("Excluir Selecionado");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton6)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gerenciamento de Marca e Modelo", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        cadastrar();
        txtCategoria.setEnabled(false);
        btnInserir.setEnabled(false);
    }//GEN-LAST:event_btnInserirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = tbCategoria.getSelectedRow();
        if (row != -1) {
            String id = String.valueOf(tbCategoria.getValueAt(row, 0));
            int dialog = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir a categoria ? ", "Excluir? ", JOptionPane.YES_NO_OPTION);
            if (dialog == JOptionPane.YES_OPTION) {
                Categoria cat = CategoriaDaoImpl.getInstance().FindCategoriaById(Long.valueOf(id));
                CategoriaDaoImpl.getInstance().DeleteCategoria(cat);
                JOptionPane.showMessageDialog(rootPane, "Categoria excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                LogsDaoImpl.getInstance().CreateLog("O usuário " + Controller.Controller.getUserLogado().getNome() + "/" + Controller.Controller.getUserLogado().getMatricula() + " Excluiu a categoria : " + cat.getCategoria(), new Date(), Severidade.EXCLUIR);
                Atualizar_Tabela();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro, Nenhuma linha foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = tbCategoria.getSelectedRow();
        if (row != -1) {
            String id = String.valueOf(tbCategoria.getValueAt(row, 0));
            categoria = CategoriaDaoImpl.getInstance().FindCategoriaById(Long.valueOf(id));
            if (categoria != null) {
                operacao = 1;
                txtCategoria.setEnabled(true);
                txtCategoria.setText(categoria.getCategoria());
                btnInserir.setEnabled(true);
            } else {
                LogsDaoImpl.getInstance().CreateLog("Erro : Falha ao achar categoria para edição.", new Date(), Severidade.EXCECAO);
                JOptionPane.showMessageDialog(rootPane, "Erro , não foi possível editar categoria", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro, Nenhuma linha foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Atualizar_Tabela();
        AtualizarTbMarca();
        AtualizarTbModelo();
    }//GEN-LAST:event_formWindowOpened

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pesquisar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        categoria = new Categoria();
        txtCategoria.setEnabled(true);
        btnInserir.setEnabled(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Atualizar_Tabela();
        AtualizarTbMarca();
        AtualizarTbModelo();
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Marca marca = new Marca();
        if (txtMarca != null && txtMarca.getText() != null && txtMarca.getText().trim().length() > 0) {             
            marca.setMarca(txtMarca.getText());
            MarcaDaoImpl.getInstance().SalvarMarca(marca);
            txtMarca.setText("");
            AtualizarTbMarca();
            LogsDaoImpl.getInstance().CreateLog("O usuário : " + Controller.Controller.getUserLogado().getNome()
                    +"/"+ Controller.Controller.getUserLogado().getMatricula() 
                    + " Inseriu a Marca : "  + marca.getMarca(), new Date(), Severidade.INSERIR);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Preencha o campo com o nome da marca que deseja inserir!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int row = table_Marca.getSelectedRow();
        if (row != -1) {
            String nome = String.valueOf(table_Marca.getValueAt(row, 0));
            assert (!nome.isEmpty()) : "O nome da marca veio empty";
            Marca marca = MarcaDaoImpl.getInstance().findByName(nome);
            Modelo modelo = ModeloDaoImpl.getInstance().findModeloByMarca(marca);
            int i = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente exluir a marca? ", "Exluir?", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                if(modelo != null && modelo.getModelo() != null && modelo.getModelo().trim().length() > 0){
                    JOptionPane.showMessageDialog(rootPane, "Não é possível deletar esta marca, ela está atrelada a um modelo!" , "Erro" , JOptionPane.ERROR_MESSAGE);
                }else{
                    MarcaDaoImpl.getInstance().deletar(marca);
                    AtualizarTbMarca();
                    JOptionPane.showMessageDialog(rootPane, "Marca Excluida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                     LogsDaoImpl.getInstance().CreateLog("O usuário : " + Controller.Controller.getUserLogado().getNome()
                    +"/"+ Controller.Controller.getUserLogado().getMatricula() 
                    + " Excluiu a Marca : "  + marca.getMarca(), new Date(), Severidade.EXCLUIR);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhuma linha foi selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cbxMarcaPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxMarcaPopupMenuWillBecomeVisible
        cbxMarca.removeAllItems();
        List<Marca> list = MarcaDaoImpl.getInstance().findAll();
        list.forEach((l) -> {
            cbxMarca.addItem(l.getMarca());
        });
    }//GEN-LAST:event_cbxMarcaPopupMenuWillBecomeVisible

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (cbxMarca.getSelectedIndex() >= 0 && cbxMarca.getSelectedItem().toString().trim().length() > 0) {
            if (txtModelo != null && txtModelo.getText() != null && txtModelo.getText().trim().length() > 0) {
                if (txtData != null && txtData.getText() != null && txtData.getText().trim().length() > 0) {
                    try {
                        String Modelo = txtModelo.getText();
                        String marca_string = cbxMarca.getSelectedItem().toString();
                        String Ano = txtData.getText();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Marca marca = MarcaDaoImpl.getInstance().findByName(marca_string);
                        Modelo md = new Modelo();
                        md.setMarca(marca);
                        md.setModelo(Modelo);
                        md.setData(sdf.parse(Ano));
                        ModeloDaoImpl.getInstance().SalvarModelo(md);
                        JOptionPane.showMessageDialog(rootPane, "Modelo inserido com sucesso!" , "Sucesso" , JOptionPane.INFORMATION_MESSAGE);
                        AtualizarTbModelo();
                        txtModelo.setText("");
                        txtData.setText("");
                        cbxMarca.setSelectedIndex(-1);
                         LogsDaoImpl.getInstance().CreateLog("O usuário : " + Controller.Controller.getUserLogado().getNome()
                    +"/"+ Controller.Controller.getUserLogado().getMatricula() 
                    + " Inseriu o Modelo : "  + md.getModelo(), new Date(), Severidade.INSERIR);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao guardar modelo!" , "ERRO" , JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro, Informe a Data do modelo!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro, Informe um modelo!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro, selecione uma marca antes de cadastrar um modelo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cbxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMarcaActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int row = table_Modelo.getSelectedRow();
        if (row != -1) {
            String nome = String.valueOf(table_Modelo.getValueAt(row, 0));
            Modelo modelo = ModeloDaoImpl.getInstance().findModeloByCampo("modelo", nome);
            assert (!nome.isEmpty()) : "O nome do Modelo veio empty";
            int i = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente exluir o modelo? ", "Exluir?", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                    ModeloDaoImpl.getInstance().Deletar(modelo);
                    AtualizarTbMarca();
                    JOptionPane.showMessageDialog(rootPane, "Modelo Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    LogsDaoImpl.getInstance().CreateLog("O usuário : " + Controller.Controller.getUserLogado().getNome()
                    +"/"+ Controller.Controller.getUserLogado().getMatricula() 
                    + " Excluiu o Modelo : "  + modelo.getModelo(), new Date(), Severidade.EXCLUIR);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhuma linha foi selecionada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(viewCategoria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewCategoria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewCategoria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewCategoria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInserir;
    private javax.swing.JComboBox<String> cbxMarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel tab1;
    private javax.swing.JTable table_Marca;
    private javax.swing.JTable table_Modelo;
    private javax.swing.JTable tbCategoria;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
