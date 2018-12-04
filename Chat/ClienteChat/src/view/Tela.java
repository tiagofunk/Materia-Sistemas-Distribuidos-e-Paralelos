package view;

import controller.Controller;
import controller.ObservadorTelaPrincipal;
import controller.ProcessadorMensagens;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Conversa;
import model.Mensagem;
import persistence.LeitorConfiguracoes;
import server.ObservadorConexao;
import server.Servidor;

public class Tela extends javax.swing.JFrame implements ObservadorTelaPrincipal{
    
    private Controller controle;
    
    private DefaultTableModel modeloTabela;
    
    public Tela(Controller controle) {
        initComponents();
        this.controle = controle;
        
        modeloTabela = new DefaultTableModel(new Object[]{"Contato","Status"}, 0);
        tabelaContatos.setModel(modeloTabela);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaContatos = new javax.swing.JTable();
        campoTextoMensagem = new javax.swing.JTextField();
        botaoEnviar = new javax.swing.JButton();
        labelDadosUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoTextoNovoContato = new javax.swing.JTextField();
        botaoAdicionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTextoMensagens = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelaContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contatos", "Status"
            }
        ));
        tabelaContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaContatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaContatos);

        botaoEnviar.setText("Enviar");
        botaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviarActionPerformed(evt);
            }
        });

        jLabel1.setText("Dados:");

        jLabel2.setText("Novo contato:");

        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });

        areaTextoMensagens.setColumns(20);
        areaTextoMensagens.setRows(5);
        jScrollPane2.setViewportView(areaTextoMensagens);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botaoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelDadosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoTextoMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2))))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(campoTextoNovoContato)
                            .addComponent(botaoAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(campoTextoNovoContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoAdicionar))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoTextoMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDadosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(botaoEnviar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        controle.adicionarContato( campoTextoNovoContato.getText() );
    }//GEN-LAST:event_botaoAdicionarActionPerformed

    private void tabelaContatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaContatosMouseClicked
        controle.carregarConversas( tabelaContatos.getSelectedRow() );
    }//GEN-LAST:event_tabelaContatosMouseClicked

    private void botaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEnviarActionPerformed
        controle.enviarMensagem( campoTextoMensagem.getText() );
    }//GEN-LAST:event_botaoEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTextoMensagens;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JTextField campoTextoMensagem;
    private javax.swing.JTextField campoTextoNovoContato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDadosUsuario;
    private javax.swing.JTable tabelaContatos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void inserirDadosUsuario(String token, String nome, String telefone) {
        String s = "Token: " + token + "<br>"+
                   "Nome: " + nome + "<br>" +
                   "Telefone: " + telefone + "<br>";
        s = "<html>" + s + "</html>";
        labelDadosUsuario.setText( s );
    }
    
    
    public static void main(String[] args) {
        Controller controle = new Controller();
        
        int porta = 0;
        try {
            porta = LeitorConfiguracoes.lerPortaServidor();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        Tela t = new Tela(controle);

        ProcessadorMensagens pm = new ProcessadorMensagens( controle );
        
        List<ObservadorConexao> listaObs = new ArrayList<>();
        listaObs.add( pm );
        
        Servidor servidor = new Servidor( porta, listaObs );
        servidor.start();
        
        String dadosUsuario[];
        try {
            dadosUsuario = LeitorConfiguracoes.lerDadosUsuario();
            controle.addObservadorTelaPrincipal(t);
            
            if( dadosUsuario[0].equals("null") ){
                TelaNovoUsuario tnu = new TelaNovoUsuario(controle);
                controle.addObservadorTelaNovoUsuario(tnu);
                tnu.setVisible(true);
            }else{
                controle.autenticarUsuario(dadosUsuario[0], dadosUsuario[1]);
            }
            controle.carregarConversas();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

   
    @Override
    public void aparecer() {
        this.setVisible(true);
    }

    @Override
    public void atualizarConversas( List<Conversa> listaConversas ) {
        System.out.println("Inserindo as conversas");
        modeloTabela.setNumRows( 0 );
        for(Conversa c: listaConversas){
            modeloTabela.addRow(
                new Object[]{ 
                    c.getContato().getToken(),
                    c.getContato().isStatus()
                }
            );
        }
    }
    
    @Override
    public void carregarConversa(Conversa conversa) {
        final String TOPO = "Conversa:\n\n";
        String s = "";
        for( Mensagem mensagem: conversa.getListaMensagens() ){
            if( mensagem.isClienteEnviou() ){
                s += "\t\t";
            }
            s += mensagem.getTexto()+"\n";
        }
        areaTextoMensagens.setText( TOPO + s );
    }

    @Override
    public void avisarErroEnviarMensagem() {
        JOptionPane.showMessageDialog(this, "Erro ao enviar mensagem");
    }
}
