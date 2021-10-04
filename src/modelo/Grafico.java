package modelo;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author williams parra
 * @version 1.0
 * @date 03/19/2021
 */
public class Grafico {
    
    public static JFreeChart grafico;

    public static void dibujarGraficoBarras(javax.swing.JTable tabla, javax.swing.JPanel panel) {
        DefaultCategoryDataset datosGrafico = new DefaultCategoryDataset();
        //capturando datos
        for (int i = 0; i < tabla.getRowCount(); i++) {
            datosGrafico.setValue(Integer.parseInt(tabla.getValueAt(i, 1).toString()), tabla.getValueAt(i, 0).toString(), "");
        }
        grafico = ChartFactory.createBarChart3D("Grafico", "", "Valores", datosGrafico, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel panelGrafico = new ChartPanel(grafico);
        panelGrafico.setPreferredSize(panel.getSize());

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(panelGrafico, BorderLayout.CENTER);
        panel.validate();
        panel.repaint();
    }

    public static void dibujarGraficoPie(javax.swing.JTable tabla, javax.swing.JPanel panel) {
        DefaultPieDataset datosGrafico = new DefaultPieDataset();

        for (int i = 0; i < tabla.getRowCount(); i++) {
            datosGrafico.setValue(tabla.getValueAt(i, 0).toString(), Integer.parseInt(tabla.getValueAt(i, 1).toString()));
        }
        grafico = ChartFactory.createPieChart("Grafico", datosGrafico, true, true, true);
        ChartPanel panelGrafico = new ChartPanel(grafico);
        panelGrafico.setPreferredSize(panel.getSize());

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(panelGrafico, BorderLayout.CENTER);
        panel.validate();
        panel.repaint();

    }

}
