
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class AddInput extends javax.swing.JFrame implements ActionListener {
//    use timer for run actionPerformed overtime
//    Usage:    1. use timer.start() for start run actionPerformed function
//              2. use timer.stop() for stop run actionPerformed function

    Timer tm = new Timer(0, (ActionListener) this);
    DefaultListModel mfItem;  // create model of item in jList1
    AddMF mf;   // create add membership function window
    String output;

    GraphPanel graphPanel;  // create graph panel
    final int WIDTH = 600;
    final int HEIGHT = 250;

    FuzzySet fs;
    double start, end, step;

    /**
     * Creates new form AddInput
     */
    public AddInput() {
        initComponents();
        this.init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        You add a membership function
        if (mf.output != "") {
            if (mf.output == "Triangle") {
                Triangle tri = new Triangle();
                tri.copy(mf.tri);
                tri.membershipGrade(fs.start, fs.end, fs.step);
                this.fs.addMF(tri);
                println("You add a triangle " + tri.name + " into fuzzy set.");
                mfItem.addElement("triangle(" + tri.name + "; " + tri.a + ", " + tri.b + ", " + tri.c + ")");
            } else if (mf.output == "Trapezoid") {
                Trapezoid tra = new Trapezoid();
                tra.copy(mf.tra);
                tra.membershipGrade(fs.start, fs.end, fs.step);
                this.fs.addMF(tra);
                println("You add a trapezoid " + tra.name + " into fuzzy set.");
                mfItem.addElement("trapezoid(" + tra.name + "; " + tra.a + ", " + tra.b + ", " + tra.c + ", " + tra.d + ")");
            } else if (mf.output == "Gaussian") {
                Gaussian gau = new Gaussian();
                gau.copy(mf.gau);
                gau.membershipGrade(fs.start, fs.end, fs.step);
                this.fs.addMF(gau);
                println("You add a gaussian " + gau.name + " into fuzzy set.");
                mfItem.addElement("gaussian(" + gau.name + "; " + gau.c + ", " + gau.sigma + ")");
            } else if (mf.output == "Bell") {
                Bell bel = new Bell();
                bel.copy(mf.bel);
                bel.membershipGrade(fs.start, fs.end, fs.step);
                this.fs.addMF(bel);
                println("You add a bell " + bel.name + " into fuzzy set.");
                mfItem.addElement("bell(" + bel.name + "; " + bel.a + ", " + bel.b + ", " + bel.c + ")");
            } else if (mf.output == "Sigmoidal") {
                Sigmoidal sig = new Sigmoidal();
                sig.copy(mf.sig);
                sig.membershipGrade(fs.start, fs.end, fs.step);
                this.fs.addMF(sig);
                println("You add a sigmoidal " + sig.name + " into fuzzy set.");
                mfItem.addElement("sig(" + sig.name + "; " + sig.a + ", " + sig.c + ")");
            }

            mf = new AddMF();   // reset object
            this.jList1.setModel(mfItem);
            this.drawGraph();
            tm.stop();
        }
    }

    public void init() {
        this.output = "";
        mfItem = new DefaultListModel();
        this.start = 0;
        this.end = 0;
        this.step = 0;
        this.fs = new FuzzySet();

        graphPanel = new GraphPanel(WIDTH, HEIGHT);
        graphPanel.setOpaque(false);
        graphPanel.setVisible(true);
        graphPanel.setMargin(20, 20, 20, 20);

        this.jPanel1.add(graphPanel, BorderLayout.CENTER);
    }

//    ==========================================================================
//    Function for set start value, end value and step value
//    ==========================================================================
    public void setUniverse(double start, double end, double step) {
        this.fs.start = start;
        this.fs.end = end;
        this.fs.step = step;
    }

    public void drawGraph() {
        this.jPanel1.setVisible(false);
        this.jPanel1.removeAll();
        graphPanel = new GraphPanel(WIDTH, HEIGHT);
        graphPanel.setOpaque(false);
        graphPanel.setVisible(true);
        graphPanel.setMargin(20, 20, 20, 20);

        for (int i = 0; i < this.fs.MFList.size(); i++) {
            graphPanel.setData(this.fs.getMFName(i), this.fs.getMFmember(i));
        }

        this.jPanel1.add(graphPanel, BorderLayout.CENTER);
        this.jPanel1.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        addMF = new javax.swing.JButton();
        resetMF = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        cancle = new javax.swing.JButton();

        setTitle("Add Input");
        setPreferredSize(new java.awt.Dimension(635, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name");

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Membership Function");

        jList1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jList1.setToolTipText("");
        jScrollPane1.setViewportView(jList1);

        addMF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addMF.setText("Add MF");
        addMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMFActionPerformed(evt);
            }
        });

        resetMF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        resetMF.setText("Reset");
        resetMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMFActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Preview");

        ok.setBackground(new java.awt.Color(0, 255, 0));
        ok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        cancle.setBackground(new java.awt.Color(255, 0, 0));
        cancle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cancle.setText("Cancle");
        cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resetMF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancle)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addMF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetMF)))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancle)
                    .addComponent(ok))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    ==========================================================================
//    Function for event of cancle:JButton
//    ==========================================================================
    private void cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancleActionPerformed

//    ==========================================================================
//    Function for event of addMF:JButton
//    ==========================================================================
    private void addMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMFActionPerformed
        // TODO add your handling code here:
        mf = new AddMF();
        mf.setVisible(true);
        mf.setUniverse(fs.start, fs.end, fs.step);
        println("Click Add MF");
        tm.start();
    }//GEN-LAST:event_addMFActionPerformed

//    ==========================================================================
//    name JTextField
//    ==========================================================================
    private void nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyReleased
        // TODO add your handling code here:
        fs.name = this.name.getText();
        println("You set this fuzzy set name is " + fs.name);
    }//GEN-LAST:event_nameKeyReleased

//    ==========================================================================
//    ok: JButton
//    ==========================================================================
    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
        if (!this.name.getText().isEmpty() && (this.fs.MFList.size() > 0)) {
            this.output = "create Fuzzy Set: " + this.fs.name;
            println(this.output);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "You should fill name and add Membership Function.");
        }

    }//GEN-LAST:event_okActionPerformed

//    ==========================================================================
//    reset: JButton
//    ==========================================================================
    private void resetMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMFActionPerformed
        // TODO add your handling code here:
        this.mfItem = new DefaultListModel();
        this.jList1.setModel(mfItem);
        this.fs.resetMF();
        this.drawGraph();
    }//GEN-LAST:event_resetMFActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddInput().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMF;
    private javax.swing.JButton cancle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JButton ok;
    private javax.swing.JButton resetMF;
    // End of variables declaration//GEN-END:variables

//    ##########################################################################
//    Function for easy show something
//    ##########################################################################
    private void print(Object o) {
        System.out.print(o);
    }

    private void println(Object o) {
        System.out.println(o);
    }

}
