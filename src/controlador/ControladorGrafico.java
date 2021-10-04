package controlador;

import java.awt.BorderLayout;
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
import modelo.Grafico;
import modelo.ModeloGrafico;
import modelo.ValidadorDatos;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import vista.JDGuardar;
import vista.JDModificar;

/**
 *
 * @author williams parra
 * @version 1.0
 * @date 28/09/2021
 */
public class ControladorGrafico {

    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtValor;
    private javax.swing.JPanel jpanelGrafico;
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
        this.jpanelGrafico = panelGrafico;
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
                dibujarGrafico();
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
                dibujarGrafico();
            }
        });

        this.popModificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Popup menu modificar pulsado");
                modificarDato();
                dibujarGrafico();
            }
        });

        this.popEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Popup menu eliminar pulsado");
                eliminarDato();
                dibujarGrafico();
            }
        });

    }

    private void guardarPDF() {
        //este metodo guardara el grafico en pdf en el escritorio del usuario.
        System.out.println("metodo GuardarPDF ejecutado");
        dibujarGrafico();
        JDGuardar guardar = new JDGuardar(null,true);
        guardar.setTabla(jtTabla);
        guardar.setModalidad(jCombo.getSelectedIndex());
        guardar.dibujarGrafico();
        guardar.setVisible(true);
    }

    private void dibujarGrafico() {
        System.out.println("este metodo dibujara el grafico en pantalla");

        if (jCombo.getSelectedIndex() == 0) {
            Grafico.dibujarGraficoBarras(jtTabla, jpanelGrafico);            
        }
        if(jCombo.getSelectedIndex() == 1){
            Grafico.dibujarGraficoPie(jtTabla, jpanelGrafico);            
        }

    }

    private void limpiarGrafico() {
        System.out.println("este metodo limpiara el grafico en pantalla");
    }

    private void capturarDato() {
        //este metodo tomara los datos de los txt y los agregara a la tabla
        System.out.println("metodo capturarDato ejecutado");
        //validar campo valor
        if (ValidadorDatos.validarModelo(txtTitulo, txtValor)) {
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
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe selecionar una fila", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            //capturar datos de la tabla
            String tituloSelecionado = jtTabla.getValueAt(fila, 0).toString();
            int valorSelecionado = Integer.parseInt(jtTabla.getValueAt(fila, 1).toString());
            JDModificar dialogo = new JDModificar(null, true);
            dialogo.setTitulo(tituloSelecionado);
            dialogo.setValor(valorSelecionado);
            dialogo.cargarDatos();
            dialogo.setVisible(true);
            System.out.println("valores de Jdialog : " + dialogo.getTitulo() + " " + dialogo.getValor());
            this.modelo.setTitulo(dialogo.getTitulo());
            this.modelo.setValor(dialogo.getValor());
            modeloTabla.setValueAt(this.modelo.recuperarDatos()[0], fila, 0);
            modeloTabla.setValueAt(this.modelo.recuperarDatos()[1], fila, 1);
        }
    }

    private void eliminarDato() {
        int fila = jtTabla.getSelectedRow();
        //validar que se alla seleccionado una fila de la tabla.
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debe selecionar una fila", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            //esperar respuesta de confirmacion
            int respuesta = JOptionPane.showOptionDialog(null, "¿Eliminar elemento?", "Aviso", 0, 0, null, new String[]{"Si", "No"}, this);
            if (respuesta == 0) {
                modeloTabla.removeRow(fila);
            }
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtValor.setText("");
    }
}
