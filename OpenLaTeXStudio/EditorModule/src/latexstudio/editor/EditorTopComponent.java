package latexstudio.editor;

import latexstudio.editor.util.ApplicationUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//latexstudio.editor//Editor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "latexstudio.editor.EditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_EditorAction",
        preferredID = "EditorTopComponent"
)
@Messages({
    "CTL_EditorAction=Editor",
    "CTL_EditorTopComponent=Editor Window",
    "HINT_EditorTopComponent=This is a Editor window"
})
public final class EditorTopComponent extends TopComponent {
    
    private boolean dirty = false;

    public EditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_EditorTopComponent());
        setToolTipText(Bundle.HINT_EditorTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        rSyntaxTextArea = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();

        rSyntaxTextArea.setColumns(20);
        rSyntaxTextArea.setRows(5);
        rSyntaxTextArea.setSyntaxEditingStyle(org.openide.util.NbBundle.getMessage(EditorTopComponent.class, "EditorTopComponent.rSyntaxTextArea.syntaxEditingStyle")); // NOI18N
        rSyntaxTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rSyntaxTextAreaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(rSyntaxTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSyntaxTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rSyntaxTextAreaKeyReleased
        dirty = true;
    }//GEN-LAST:event_rSyntaxTextAreaKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.fife.ui.rsyntaxtextarea.RSyntaxTextArea rSyntaxTextArea;
    // End of variables declaration//GEN-END:variables
    

    @Override
    public void componentOpened() {
        ApplicationUtils.deleteTempFiles();
    }

    @Override
    public void componentClosed() {
    }
    
    public String getEditorContent() {
        return rSyntaxTextArea.getText();
    }
    
    public void setEditorContent(String text) {
        rSyntaxTextArea.setText(text);
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
