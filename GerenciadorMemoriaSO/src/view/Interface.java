package view;

import code.Alocador;
import code.Desalocador;
import code.VetorRequisicao;
import code.Semaforo;
import code.Heap;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author giuliana
 */

public class Interface extends javax.swing.JFrame {



    public Interface() {
        setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(169, 169, 169));
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jOptionPane1 = new javax.swing.JOptionPane();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jMenu1 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(new Color(169, 169, 169));
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setBounds(10, 50, 127, 24);
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setBounds(309, 163, 126, 24);
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setBounds(309, 128, 123, 24);
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setBounds(310, 50, 168, 24);
        txtTamMin = new javax.swing.JTextField();
        txtTamMin.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtTamMin.setBounds(442, 130, 112, 22);
        txtTamMax = new javax.swing.JTextField();
        txtTamMax.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtTamMax.setBounds(442, 165, 112, 22);
        txtNumRequi = new javax.swing.JTextField();
        txtNumRequi.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtNumRequi.setBounds(482, 52, 73, 22);
        textFieldPorcent = new JTextField();
        textFieldPorcent.setFont(new Font("Tahoma", Font.PLAIN, 11));
        textFieldPorcent.setBounds(169, 95, 100, 22);
        btnAdicionar = new javax.swing.JButton();
        btnAdicionar.setBounds(205, 208, 149, 23);
        btnSequencial = new javax.swing.JButton();
        btnSequencial.setBounds(437, 365, 117, 23);
        btnExecParalelamente = new javax.swing.JButton();
        btnExecParalelamente.setBounds(310, 365, 117, 23);
        btnHeap = new javax.swing.JButton();
        btnHeap.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnHeap.setBounds(10, 298, 81, 22);
        btnVetReq = new javax.swing.JButton();
        btnVetReq.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnVetReq.setBounds(10, 365, 175, 23);
        txtTamHeap = new javax.swing.JTextField();
        txtTamHeap.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtTamHeap.setBounds(169, 51, 100, 23);
        btnVerOcup = new javax.swing.JButton();
        btnVerOcup.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnVerOcup.setBounds(10, 331, 112, 23);

        jLabel5.setText("jLabel5");

        jButton7.setText("jButton7");

        jLabel7.setText("jLabel7");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Mem\u00F3ria");
        setResizable(false);

        jLabel1.setFont(new Font("Dubai", Font.PLAIN, 14)); // NOI18N
        jLabel1.setText("TAMANHO DA HEAP");

        jLabel2.setFont(new Font("Dubai", Font.PLAIN, 14)); // NOI18N
        jLabel2.setText("TAMANHO M\u00C1XIMO");

        jLabel3.setFont(new Font("Dubai", Font.PLAIN, 14)); // NOI18N
        jLabel3.setText("TAMANHO M\u00CDNIMO");

        jLabel4.setFont(new Font("Dubai", Font.PLAIN, 14)); // NOI18N
        jLabel4.setText("N\u00DAMERO DE REQUISI\u00C7\u00D5ES ");

        btnAdicionar.setText("ADICIONAR DADOS");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnSequencial.setText("SEQUENCIAL");
        btnSequencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSequencialActionPerformed(evt);
            }
        });

        btnExecParalelamente.setText("PARALELO");
        btnExecParalelamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecParalelamenteActionPerformed(evt);
            }
        });

        btnHeap.setText("VER HEAP");
        btnHeap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeapActionPerformed(evt);
            }
        });

        btnVetReq.setText("VER VETOR DE REQUISI��ES");
        btnVetReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVetReqActionPerformed(evt);
            }
        });

        btnVerOcup.setText("VER OCUPADOS");
        btnVerOcup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerOcupActionPerformed(evt);
            }
        });

        JLabel lblNewLabel = new JLabel("EXECUTAR EM MODO");
        lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 14));
        lblNewLabel.setBounds(370, 340, 159, 14);

        JLabel lblPercentual = new JLabel();
        lblPercentual.setBounds(10, 93, 141, 24);
        lblPercentual.setText("PERCENTUAL M\u00C1XIMO");
        lblPercentual.setFont(new Font("Dubai", Font.PLAIN, 14));

        lblNewLabel_2 = new JLabel("Configura\u00E7\u00F5es Heap:");
        lblNewLabel_2.setBounds(10, 15, 125, 24);
        lblNewLabel_2.setFont(new Font("Dubai", Font.BOLD, 14));

        lblNewLabel_3 = new JLabel("Configura\u00E7\u00F5es Vetor Requisi\u00E7\u00E3o:");
        lblNewLabel_3.setBounds(310, 15, 238, 24);
        lblNewLabel_3.setFont(new Font("Dubai", Font.BOLD, 14));

        lblNewLabel_4 = new JLabel("Configura\u00E7\u00F5es Requisi\u00E7\u00E3o");
        lblNewLabel_4.setBounds(310, 93, 200, 24);
        lblNewLabel_4.setFont(new Font("Dubai", Font.BOLD, 14));

        JLabel lblNewLabel_1 = new JLabel("GERENCIADOR DE MEMORIA");
        lblNewLabel_1.setFont(new Font("Super Mario Bros. 2", Font.BOLD, 18));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(79)
                                                .addComponent(lblNewLabel_1)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21)
                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );
        jPanel2.setLayout(null);
        jPanel2.add(btnVetReq);
        jPanel2.add(btnAdicionar);
        jPanel2.add(btnVerOcup);
        jPanel2.add(btnExecParalelamente);
        jPanel2.add(btnSequencial);
        jPanel2.add(btnHeap);
        jPanel2.add(lblPercentual);
        jPanel2.add(jLabel1);
        jPanel2.add(textFieldPorcent);
        jPanel2.add(txtTamHeap);
        jPanel2.add(jLabel4);
        jPanel2.add(txtNumRequi);
        jPanel2.add(jLabel2);
        jPanel2.add(jLabel3);
        jPanel2.add(lblNewLabel_4);
        jPanel2.add(lblNewLabel_3);
        jPanel2.add(txtTamMax);
        jPanel2.add(lblNewLabel);
        jPanel2.add(lblNewLabel_2);
        jPanel2.add(txtTamMin);

        lblNewLabel_5 = new JLabel("Informa\u00E7\u00F5es Extras:");
        lblNewLabel_5.setFont(new Font("Dubai", Font.BOLD, 14));
        lblNewLabel_5.setBounds(10, 263, 125, 24);
        jPanel2.add(lblNewLabel_5);

        JLabel lblPercentualMnimo = new JLabel();
        lblPercentualMnimo.setText("PERCENTUAL M\u00CDNIMO");
        lblPercentualMnimo.setFont(new Font("Dubai", Font.PLAIN, 14));
        lblPercentualMnimo.setBounds(10, 139, 141, 24);
        jPanel2.add(lblPercentualMnimo);

        txtPercentualMinimo = new JTextField();
        txtPercentualMinimo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtPercentualMinimo.setBounds(169, 141, 100, 22);
        jPanel2.add(txtPercentualMinimo);
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    //Criando as estruturas que ser�o usadas
    Heap vetorHeap = new Heap(); //Lembrando que o tamanho dela � setado pelo usu�rio - estrutura sequencial
    VetorRequisicao fila = new VetorRequisicao();//Essa fila � o meu vetor de requisi��es - estrutura sequencial
    VetorRequisicao filaParalela = new VetorRequisicao();//Essa fila � o meu vetor de requisi��es - estrutura paralela

    public  int RequisicaoMax = 0;
    public  int RequisicaoMin = 0;
    public int porcentagem = 0;
    public int porcentagemMin = 0;
    public static  int numRequisicoes = 0;
    public  int tamanho = 0;
    public int acerto = 0;
    long inicioSeq = 0;
    long fimSeq = 0;
    long inicioParal = 0;
    long fimParal = 0;
    Semaforo gestor = new Semaforo(); //Estrutura paralela

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        //O que acontece se eu clicar em Adicionar dados? Quero que os dados das caixas de texto sejam inseridas em vari�veis
        if(txtTamHeap.getText().trim().equals("") || txtTamMin.getText().trim().equals("")
                || txtTamMax.getText().trim().equals("")
                || txtNumRequi.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "H� campos de texto vazios. Insira dados!");
        }else{
            String x = txtTamHeap.getText(); //recebo valor que o usu�rio inseriu para o tamanho da heap
            tamanho = Integer.parseInt(x);

            x = "";
            x = textFieldPorcent.getText();
            porcentagem = Integer.parseInt(x);   //TAMANHO PORCENTAGEM MAX

            x = "";
            x = txtPercentualMinimo.getText();
            porcentagemMin = Integer.parseInt(x);   //TAMANHO PORCENTAGEM MIN

            vetorHeap.setTamanho(tamanho, porcentagem, porcentagemMin);  //Passa o valor tamanho da heap - sequencial
            gestor.setTamanho(tamanho, porcentagem, porcentagemMin);  //Passa o valor tamanho da heap - paralelo

            x = "";//limpo vari�vel
            x = txtTamMin.getText();
            RequisicaoMin = Integer.parseInt(x);   //TAMANHO MINIMO


            x = "";
            x = txtTamMax.getText();
            RequisicaoMax = Integer.parseInt(x);   //TAMANHO MAXIMO

            x = "";
            x = txtNumRequi.getText();
            numRequisicoes = Integer.parseInt(x);  //NUMERO DE REQUISI��ES
            x = "";


            txtTamHeap.setText("");
            txtTamMin.setText("");
            txtTamMax.setText("");
            txtNumRequi.setText("");
            textFieldPorcent.setText("");
            txtPercentualMinimo.setText("");

            fila.setTAMANHO(numRequisicoes); //Vou atender a essa quantidade - sequencial
            filaParalela.setTAMANHO(numRequisicoes); //Vou atender a essa quantidade - paralela
            criarRequisicoes(fila,filaParalela, RequisicaoMin, RequisicaoMax); //Criando fila sequencial e paralela


            if(RequisicaoMax > tamanho){
                JOptionPane.showMessageDialog(null, "Tamanho de requisicao m�ximo maior que o permitido. Insira novamente os dados ");

            }else{
                JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso! ");
                acerto = 1;
            }
        }
    }

    private void btnSequencialActionPerformed(java.awt.event.ActionEvent evt) {
        if(acerto == 1)
        {
            inicioSeq = System.currentTimeMillis();

            int requisicao = 0;
            int verifica = 0;


            while(fila.verifica() != true)                                                                                       // Enquanto n�o estiver vazia
            {
                requisicao = fila.removerFilaC();
                verifica = vetorHeap.inserirHeap(requisicao);
            }

            fimSeq  = System.currentTimeMillis();
            long tempo = fimSeq - inicioSeq;
            //System.out.println(" Tempo final = "+ fimSeq);
            JOptionPane.showMessageDialog(null, "Execu��o sequencial conclu�da com sucesso! Tempo de="+tempo+"ms");
        }else{
            JOptionPane.showMessageDialog(null, "N�o � possivel executar. Insira novamente os dados");
        }
    }

    private void btnHeapActionPerformed(java.awt.event.ActionEvent evt) {

        System.out.println("---A Heap sequencial---");
        vetorHeap.imprimirHeap();
        System.out.println("---A Heap paralela---");
        gestor.imprimirHeap();

    }

    private void btnVerOcupActionPerformed(java.awt.event.ActionEvent evt) {

        System.out.println("---Vetor de ocupados da execu��o sequencial �:---");
        vetorHeap.imprimirOcupados();

        System.out.println("---Vetor de ocupados da execu��o paralela �:---");
        gestor.imprimirOcupados();


    }

    private void btnExecParalelamenteActionPerformed(java.awt.event.ActionEvent evt) {

        if(acerto == 1)
        {
            System.out.println("--------------");

            Alocador t1 = new Alocador(gestor, filaParalela);
            Desalocador t2 = new Desalocador(gestor);

            Thread aloc = new Thread(t1);
            Thread desaloca = new Thread(t2);

            long tempoInicial = 0;
            tempoInicial = System.currentTimeMillis();

            aloc.start();       //Start no run
            desaloca.start();  //Start no run

            long tempoFinal = 0;
            long tempo = 0;

            while(gestor.getVerificacao() == 0){
                tempoFinal = gestor.getTempoFinal();
                tempo = tempoFinal - tempoInicial;
            }

            if(gestor.getVerificacao() == 1){
                tempoFinal = gestor.getTempoFinal();
                tempo = tempoFinal - tempoInicial;
                JOptionPane.showMessageDialog(null, "Execu��o paralela conclu�da com sucesso! Tempo de="+tempo+"ms");
            }
        }else{
            JOptionPane.showMessageDialog(null, "N�o � possivel executar. Insira novamente os dados");
        }
    }

    private void btnVetReqActionPerformed(java.awt.event.ActionEvent evt) {

        System.out.println("O vetor de requisi��es �");
        System.out.println("---Sequencial---");
        fila.print();
        System.out.println("---Paralela---");
        filaParalela.print();


    }

    //Fun��o auxiliar de gerar n�meros
    public static int gerador(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    //Manipula��o da minha fila, ou seja, gera��o de valores randomicos para ela
    public static void criarRequisicoes(VetorRequisicao f1, VetorRequisicao f2, int min, int max)
    { //Inicialmente inicio a fila como cheia
        int numero = 0;
        for(int i = 0; i< numRequisicoes; i++)
        {
            numero = gerador(min, max);                                                                 //random.nextInt((maximo - minimo) + 1) + minimo;
            //id = gerarID();
            f1.incluirFilaC(numero);
            f2.incluirFilaC(numero);
        }

    }



    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnExecParalelamente;
    private javax.swing.JButton btnHeap;
    private javax.swing.JButton btnSequencial;
    private javax.swing.JButton btnVerOcup;
    private javax.swing.JButton btnVetReq;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtNumRequi;
    private javax.swing.JTextField txtTamHeap;
    private javax.swing.JTextField txtTamMax;
    private javax.swing.JTextField txtTamMin;
    private JTextField textFieldPorcent;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JTextField txtPercentualMinimo;
}
