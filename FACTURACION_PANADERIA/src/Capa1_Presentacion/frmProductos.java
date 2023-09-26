package Capa1_Presentacion;

import Capa_Entidades.Producto;
import Capa2_LogicaNegocio.LNProducto;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmProductos extends javax.swing.JInternalFrame {
    
    //-----------------------------------------------------------
    private Producto GenerarEntidad() {
        Producto producto = new Producto();
        if (!txtId.getText().equals("")) {
            producto.setExiste(true);
            producto.setId(Integer.parseInt(txtId.getText()));
        }
        producto.setNombre(txtNombre.getText());
        producto.setDescripcion(txtDescripcion.getText());
        producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
        producto.setStock(Integer.parseInt(txtStock.getText()));  
        return producto;
    }   
    
     //-----------------------------------------------------------
    DefaultTableModel modelo;
    private void LimpiarTabla(){
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return  false;
            }
        };
        tblProductos.setModel(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");        
        modelo.addColumn("Precio"); 
        modelo.addColumn("Stock"); 
    }
    
    //-------------------------------------------------------------
    private void CargarDatos(String condicion) throws Exception {
        try {
            LNProducto logica = new LNProducto();
            List<Producto> lista;
            LimpiarTabla();
            Object[] fila = new Object[5];
            lista = logica.ListarRegistros(condicion);
            for (Producto producto : lista) {
                fila[0] = producto.getId();
                fila[1] = producto.getNombre();
                fila[2] = producto.getDescripcion();                
                fila[3] = producto.getPrecio();
                fila[4] = producto.getStock();
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            throw e;
        }
    }
    
    //-----------------------------------------------------------
    private void Limpiar(){
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");     
        txtPrecio.setText("");
        txtStock.setText("");
    }
     //-----------------------------------------------------------  
    public frmProductos() {
        initComponents();
        try {
            CargarDatos("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
//-----------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLimpiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtStock = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel4.setText("Descripcion");

        txtId.setEditable(false);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        jLabel1.setText("Codigo");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel5.setText("Stock");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addGap(44, 44, 44)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(66, 66, 66)
                        .addComponent(btnEliminar)
                        .addGap(54, 54, 54)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(11, 11, 11)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        LNProducto logica = new LNProducto();
        Producto producto;
        try {
            producto=GenerarEntidad();
            if (producto.isExiste()) {
                if (logica.Eliminar(producto)>0) {
                    JOptionPane.showMessageDialog(this, logica.getMensaje());
                    Limpiar();
                    CargarDatos("");

                }else{
                    JOptionPane.showMessageDialog(this, "No fue posible eliminar el registro");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Debe seleccionar el registro que desea eliminar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se puede eliminar el registro. "+e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        try {
            LNProducto logica =new LNProducto();
            Producto producto;
            String condicion;
            if (evt.getClickCount()==2) {
                int fila = tblProductos.rowAtPoint(evt.getPoint());
                txtId.setText(tblProductos.getValueAt(fila, 0).toString());
                condicion=String.format("ID_PRODUCTO=%s", txtId.getText());
                producto=logica.ObtenerRegistro(condicion);
                txtId.setText(String.valueOf(producto.getId()));
                txtNombre.setText(producto.getNombre());
                txtDescripcion.setText(producto.getDescripcion());
                txtPrecio.setText(String.valueOf(producto.getPrecio()));
                txtStock.setText(String.valueOf(producto.getStock()));                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        frmBuscarProducto frmBuscar = new frmBuscarProducto(null, true);
        frmBuscar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                try {
                    int id = frmBuscar.ObtenerId();
                    String condicion = "";
                    LNProducto logica = new LNProducto();
                    Producto producto;
                    if (id > -1) {
                        condicion = String.format("ID_PRODUCTO=%d", id);
                        producto = logica.ObtenerRegistro(condicion);
                        txtId.setText(String.valueOf(producto.getId()));
                        txtNombre.setText(producto.getNombre());                        
                        txtDescripcion.setText(producto.getDescripcion());
                        txtPrecio.setText(String.valueOf(producto.getPrecio()));
                        txtStock.setText(String.valueOf(producto.getStock()));  
                    } else {
                        txtId.setText("");
                        txtNombre.setText("");                        
                        txtDescripcion.setText("");
                        txtPrecio.setText("");
                        txtStock.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        frmBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LNProducto logica = new LNProducto();
        Producto producto = GenerarEntidad();
        try {
            if (producto.isExiste()) {
                logica.Modificar(producto);
            }else{
                logica.Insertar(producto);
            }
            JOptionPane.showMessageDialog(this, logica.getMensaje());
            Limpiar();
            CargarDatos("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
