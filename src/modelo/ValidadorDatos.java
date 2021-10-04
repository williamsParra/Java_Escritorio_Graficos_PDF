/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Nukero
 */
public class ValidadorDatos {
    
    public static boolean validarModelo(JTextField txtTitulo,JTextField txtValor){
        boolean correcto = true;
        //comprobar que ningun campo este vacio
        if (txtTitulo.getText().isEmpty() || txtValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "los campos no pueden estar vacios", "Error!", JOptionPane.ERROR_MESSAGE);
            correcto = false;
        }
        //comprobar que el campo valor solo sea numerico
        if (txtValor.getText().length() != 0) {
            if (!txtValor.getText().matches("[+-]?\\d*(\\\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Valor solo puede contener numeros", "Error!", JOptionPane.ERROR_MESSAGE);
                correcto = false;
            }
        }
        return correcto;
    }  

    
}
