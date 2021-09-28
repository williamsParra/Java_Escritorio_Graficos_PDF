package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloGrafico;

/**
 *
 * @author williams parra
 * @version 1.0
 * @date 28/09/2021
 */

public class ControladorGrafico {

    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtValor;
    private javax.swing.JPanel panelGrafico;
    private ModeloGrafico modelo;
    private javax.swing.JTable jtTabla;
    private javax.swing.JComboBox jCombo;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private JPopupMenu popMenu = new JPopupMenu();
    private JMenuItem popModificar = new JMenuItem("Modificar");
    private JMenuItem popEliminar = new JMenuItem("Eliminar");
    

    public ControladorGrafico(JTextField titulo, JTextField valor, JPanel panelGrafico, JTable tabla, JComboBox combo, JButton btnAgregar, JButton btnGuardar) {
        this.txtTitulo = titulo;
        this.txtValor = valor;
        this.panelGrafico = panelGrafico;
        this.modelo = new ModeloGrafico();
        this.jtTabla = tabla;
        this.jCombo = combo;
        this.btnAgregar = btnAgregar;
        this.btnGuardar = btnGuardar;
        
        //creando menu para Jtable
        popMenu.add(popModificar);
        popMenu.add(popEliminar);
        jtTabla.setComponentPopupMenu(popMenu);

        //agregando escuchadores de evento
        this.btnAgregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton agregar precionado");
                capturarDato();
            }
        });

        this.btnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton guardar precionado");
                guardarPDF();
            }
        });

        this.jCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("el valor de combo box cambio a : " + jCombo.getSelectedItem().toString());
            }
        });
        
        this.popModificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Popup menu modificar pulsado");
            }
        });
        
        this.popEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Popup menu eliminar pulsado");
                eliminarDato();
            }
        });   
        
    }

    private void guardarPDF() {
        //este metodo guardara el grafico en pdf en el escritorio del usuario.
        System.out.println("metodo GuardarPDF ejecutado");
    }

    private void dibujarGrafico() {
        System.out.println("este metodo dibujara el grafico en pantalla");
    }

    private void limpiarGrafico() {
        System.out.println("este metodo limpiara el grafico en pantalla");
    }

    private void capturarDato() {
        //este metodo tomara los datos de los txt y los agregara a la tabla
        System.out.println("metodo capturarDato ejecutado");
        //validar campo valor
        if (validarCampos()) {
            //ingresando datos a tabla.
            modeloTabla = (DefaultTableModel) jtTabla.getModel();
            modelo = new ModeloGrafico(txtTitulo.getText(), Integer.parseInt(txtValor.getText()));
            modeloTabla.addRow(modelo.recuperarDatos());
            limpiarCampos();
        }
    }

    private void modificarDato() {
        int fila = jtTabla.getSelectedRow();
        //validar que se alla seleccionado una fila de la tabla.
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Debe selecionar una fila", "Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            modeloTabla.removeRow(fila);
        }
    }

    private void eliminarDato() {
        int fila = jtTabla.getSelectedRow();
        //validar que se alla seleccionado una fila de la tabla.
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Debe selecionar una fila", "Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            modeloTabla.removeRow(fila);
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtValor.setText("");
    }

    private boolean validarCampos() {
        boolean correcto = true;
        //comprobar que ningun campo este vacio
        if (txtTitulo.getText().isEmpty() || txtValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "los campos no pueden estar vacios", "Error!", JOptionPane.ERROR_MESSAGE);
            correcto = false;
        }
        //comprobar que el campo valor solo sea numerico
        if (txtValor.getText().length() != 0) {
            if (!txtValor.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Valor solo puede contener numeros", "Error!", JOptionPane.ERROR_MESSAGE);
                correcto = false;
            }
        }
        return correcto;
    }
}
