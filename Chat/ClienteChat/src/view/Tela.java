package view;

import controller.Controller;
import controller.ObservadorTelaPrincipal;
import controller.ProcessadorMensagens;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import persistence.LeitorConfiguracoes;
import server.ObservadorConexao;
import server.Servidor;

public class Tela extends javax.swing.JFrame implements ObservadorTelaPrincipal{
    
    private Controller controle;
    
    public Tela(Controller controle) {
        initComponents();
        this.controle = controle;
        controle.addObservadorTelaPrincipal(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaContatos = new javax.swing.JTable();
        campoTextoMensagem = new javax.swing.JTextField();
        botaoEnviar = new javax.swing.JButton();
        labelDadosUsuario = new javax.swing.JLabel();
        labelMensagens = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelaContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contatos"
            }
        ));
        jScrollPane1.setViewportView(tabelaContatos);

        botaoEnviar.setText("Enviar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelDadosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoTextoMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(labelMensagens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(botaoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMensagens, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTextoMensagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoEnviar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDadosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JTextField campoTextoMensagem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDadosUsuario;
    private javax.swing.JLabel labelMensagens;
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

   
    @Override
    public void aparecer() {
        this.setVisible(true);
    }
}
