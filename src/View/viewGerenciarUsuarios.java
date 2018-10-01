package View;

import Core.PersistenceUtil;
import Constantes.Severidade;
import Entity.NivelAcesso;
import Entity.Usuario;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.Criptografia;
import Model.Dao.LogsDaoImpl;
import Model.Dao.NivelAcessoDaoImpl;
import Model.Dao.UsuarioDaoImpl;
import java.awt.HeadlessException;

public class viewGerenciarUsuarios extends javax.swing.JFrame {

    private List<NivelAcesso> niveis;
    private Usuario user;
    private int operacao = 0;
    private String comparar_senha;
    
    public viewGerenciarUsuarios() {
        initComponents();
    }

    public void limpar_cadastro() {
        user = new Usuario();
        txtConfirmarSenha.setText("");
        txtSenha.setText("");
        txtEmail.setText("");
        txtMatricula.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        cbxNiveis.setSelectedIndex(0);
        txtNome.setEnabled(false);
        txtCpf.setEnabled(false);
        txtEmail.setEnabled(false);
        txtMatricula.setEnabled(false);
        txtSenha.setEnabled(false);
        txtConfirmarSenha.setEnabled(false);
        cbxNiveis.setEnabled(false);
        checkbox1.setEnabled(false);
        operacao = 0;
    }

    public void pesquisar() {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        DefaultTableModel dtm = new DefaultTableModel();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.distinct(true).select(root);
            criteria.where(cb.or(
                    cb.like(root.get("matricula"), "%" + txtPesquisar.getText() + "%"),
                    cb.like(root.get("nome"), "%" + txtPesquisar.getText() + "%"),
                    cb.like(root.get("email"), "%" + txtPesquisar.getText() + "%"),
                    cb.like(root.get("cpf"), "%" + txtPesquisar.getText() + "%")
            ));
            List<Usuario> usuarios = entityManager.createQuery(criteria).getResultList();
            if (usuarios.size() > 0) {
                dtm.addColumn("id");
                dtm.addColumn("Nome");
                dtm.addColumn("CPF");
                dtm.addColumn("Email");
                dtm.addColumn("Matricula");
                dtm.addColumn("Acesso");
                dtm.addColumn("Ativo");
                usuarios.forEach((u) -> {
                    dtm.addRow(new String[]{String.valueOf(u.getId()), u.getNome(), u.getCpf(), u.getEmail(), u.getMatricula(), u.getNivel().getNo_nivel(), u.isAtivo() ? "Ativo" : "Inativo"});
                });
            } else {
                dtm.addColumn("Pesquisa");
                dtm.addRow(new String[]{"Não há usuários com este termo : " + txtPesquisar.getText()});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro, Não foi possivel realizar pesquisa!", "Erro", JOptionPane.ERROR_MESSAGE);
            PersistenceUtil.rollback(entityManager);
        } finally {
            PersistenceUtil.Close(entityManager);
        }
        table_usuarios.setModel(dtm);
    }

    public void atualizar_nivel() {
        niveis = NivelAcessoDaoImpl.getInstance().FindNivelAll();
        cbxNiveis.removeAllItems();
        if (niveis.size() > 0) {
            niveis.forEach((acesso) -> {
                cbxNiveis.addItem(acesso.getNo_nivel());
            });
        } else {
            JOptionPane.showMessageDialog(rootPane, "Erro, a consulta de niveis veio vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizar_usuarios() {
        List<Usuario> usuarios = UsuarioDaoImpl.getInstance().ListAllUser();
        DefaultTableModel dtm = new DefaultTableModel();
        if (usuarios.size() > 0) {
            dtm.addColumn("id");
            dtm.addColumn("Nome");
            dtm.addColumn("CPF");
            dtm.addColumn("Email");
            dtm.addColumn("Matricula");
            dtm.addColumn("Acesso");
            dtm.addColumn("Ativo");
            usuarios.forEach((u) -> {
                dtm.addRow(new String[]{String.valueOf(u.getId()), u.getNome(), u.getCpf(), u.getEmail(), u.getMatricula(), u.getNivel().getNo_nivel() == null ? "Este usuário não tem nível" : u.getNivel().getNo_nivel(), u.isAtivo() ? "Ativo" : "Inativo"});
            });
        } else {
            dtm.addColumn("Resultado");
            dtm.addRow(new String[]{"Não encontramos usuários"});
        }
        table_usuarios.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_usuarios = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        txtConfirmarSenha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        cbxNiveis = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        checkbox1 = new java.awt.Checkbox();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        table_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "E-mail", "Matrícula", "Nível", "Editar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_usuarios);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar"));

        jLabel11.setText("Procurar por:");

        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/user_search_24.png"))); // NOI18N
        jButton2.setText("Pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/user_edit_24.png"))); // NOI18N
        jButton3.setText("Alterar Selecionado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/user_delet_24.png"))); // NOI18N
        jButton4.setText("Deletar Selecionado");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPesquisar)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Usuários", jPanel1);

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastrar usuário:"));

        jLabel1.setText("Nome.:");

        jLabel2.setText("CPF.:");

        jLabel3.setText("Matrícula.:");

        jLabel5.setText("Confirmar Senha.:");

        jLabel6.setText("E-mail.:");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/check_16.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setToolTipText("");

        jLabel7.setText("Nível de acesso.:");

        cbxNiveis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Administrador : Tem acesso total ao sistema.");

        jLabel9.setText("Usuário : Pode apenas Cadastrar e consultar produtos.");

        jLabel10.setText("View : Só pode ver os produtos disponiveis");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cancelar_16.png"))); // NOI18N
        jButton1.setText("Limpar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        checkbox1.setLabel("Ativo ?");
        checkbox1.setState(true);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/file_16.png"))); // NOI18N
        jButton5.setText("Novo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel12.setText("Senha.:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel1))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
                        .addGap(553, 553, 553))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbxNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                            .addComponent(txtNome))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtConfirmarSenha))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtEmail)))))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(btnSalvar)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(360, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastrar Usuário", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        atualizar_usuarios();
        atualizar_nivel();
        limpar_cadastro();
    }//GEN-LAST:event_formWindowOpened

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (txtNome.getText() != null && txtNome.getText().trim().length() > 0
                && txtCpf.getText() != null && txtCpf.getText().trim().length() > 0
                && txtEmail.getText() != null && txtEmail.getText().trim().length() > 0
                && txtMatricula.getText() != null && txtMatricula.getText().trim().length() > 0
                && txtSenha.getPassword() != null && txtSenha.getPassword().length > 0
                && txtSenha.getPassword() != null && txtSenha.getPassword().length > 0) {
            if (Arrays.toString(txtSenha.getPassword()).equals(Arrays.toString(txtConfirmarSenha.getPassword()))) {
                if (txtCpf.getText().trim().length() >= 14) {
                    if (txtEmail.getText().contains("@") && txtEmail.getText().contains(".")) {
                        if (txtSenha.getText().trim().length() > 5) {
                            try {
                                String Nome      = txtNome.getText();
                                String Email     = txtEmail.getText();
                                String CPF       = txtCpf.getText();
                                String Matricula = txtMatricula.getText();
                                String Senha     = txtSenha.getText();
                                boolean Ativo    = checkbox1.getState();
                                NivelAcesso nivel = NivelAcessoDaoImpl.getInstance().getByName(cbxNiveis.getSelectedItem().toString());
                                
                                user.setAtivo(Ativo);
                                user.setCpf(CPF);
                                user.setEmail(Email);
                                user.setMatricula(Matricula);
                                user.setNome(Nome);
                                user.setNivel(nivel);
                                
                                if (operacao == 0) {
                                    user.setSenha(Criptografia.Cripto(Senha));
                                    UsuarioDaoImpl.getInstance().CreateUser(user);
                                    JOptionPane.showMessageDialog(rootPane, "Registro inserido com sucesso!", "sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    LogsDaoImpl.getInstance().CreateLog("O Usuário : "
                                            + Controller.Controller.getUserLogado().getNome() + "/" + Controller.Controller.getUserLogado().getMatricula()
                                            + " Inseriu o usuário : " + user.getNome() + "/" + user.getMatricula(), new Date(), Severidade.INSERIR);
                                } else {
                                    if(!user.getSenha().equals(Senha))
                                        user.setSenha(Criptografia.Cripto(Senha));
                                    UsuarioDaoImpl.getInstance().UpdateUser(user);
                                    JOptionPane.showMessageDialog(rootPane, "Registro inserido com sucesso!", "sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    LogsDaoImpl.getInstance().CreateLog("O Usuário : "
                                            + Controller.Controller.getUserLogado().getNome() + "/" + Controller.Controller.getUserLogado().getMatricula()
                                            + " Alterou o usuário : " + user.getNome() + "/" + user.getMatricula(), new Date(), Severidade.ALTERAR);
                                }
                                user = new Usuario();
                                operacao = 0;
                                limpar_cadastro();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(rootPane, "Não foi possivel Salvar o Usuário!" , "Erro" , JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "A senha informada é muito fraca", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "O E-mail é inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "O CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "As Senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Por favor preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpar_cadastro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed

    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (txtPesquisar.getText() != null && txtPesquisar.getText().trim().length() > 0) {
            pesquisar();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Informe o que deseja buscar!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        operacao = 1;
        try {
            int row = table_usuarios.getSelectedRow();
            if (row != -1) {
                String id = String.valueOf(table_usuarios.getValueAt(row, 0));
                user = UsuarioDaoImpl.getInstance().FindUserById(Long.valueOf(id));
                if (user.getNome() != null && user.getNome().trim().length() > 0) {
                    txtNome.setText(user.getNome());
                    txtCpf.setText(user.getCpf());
                    txtEmail.setText(user.getEmail());
                    txtMatricula.setText(user.getMatricula());
                    txtSenha.setText(user.getSenha());
                    txtConfirmarSenha.setText(user.getSenha());
                    comparar_senha = user.getSenha();
                    jTabbedPane1.setSelectedIndex(1);
                    atualizar_usuarios();
                    txtNome.setEnabled(true);
                    txtCpf.setEnabled(true);
                    txtEmail.setEnabled(true);
                    txtMatricula.setEnabled(true);
                    txtSenha.setEnabled(true);
                    txtConfirmarSenha.setEnabled(true);
                    cbxNiveis.setEnabled(true);
                    checkbox1.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro", "Erro ao selecionar usuário", JOptionPane.ERROR_MESSAGE);
            LogsDaoImpl.getInstance().CreateLog("Erro: " + e.getLocalizedMessage(), new Date(), Severidade.EXCECAO);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = table_usuarios.getSelectedRow();
        if (row != -1) {
            String id = String.valueOf(table_usuarios.getValueAt(row, 0));
            Usuario user = UsuarioDaoImpl.getInstance().FindUserById(Long.valueOf(id));
            if (user != null) {
                int Option = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente deletar o usuario ? ", "Deletar ? ", JOptionPane.YES_NO_OPTION);
                if (Option == JOptionPane.YES_OPTION) {
                    UsuarioDaoImpl.getInstance().DeleteUser(user);
                    JOptionPane.showMessageDialog(rootPane, "Usuário " + user.getNome() + "/" + user.getMatricula() + " Foi deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    LogsDaoImpl.getInstance().CreateLog("O Usuário : " + Controller.Controller.getUserLogado().getNome() + "/" + Controller.Controller.getUserLogado().getMatricula()
                            + " Excluiu o usuário : " + user.getNome() + "/" + user.getMatricula(), new Date(), Severidade.EXCLUIR);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        user = new Usuario();
        limpar_cadastro();
        txtNome.setEnabled(true);
        txtCpf.setEnabled(true);
        txtEmail.setEnabled(true);
        txtMatricula.setEnabled(true);
        txtSenha.setEnabled(true);
        txtConfirmarSenha.setEnabled(true);
        cbxNiveis.setEnabled(true);
        checkbox1.setEnabled(true);
        operacao = 0;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        user = new Usuario();
        limpar_cadastro();
        operacao = 0;
    }//GEN-LAST:event_formWindowClosing

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(viewGerenciarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewGerenciarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewGerenciarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewGerenciarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewGerenciarUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxNiveis;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable table_usuarios;
    private javax.swing.JPasswordField txtConfirmarSenha;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
