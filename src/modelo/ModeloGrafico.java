package modelo;

/**
 *
 * @author williams parra
 * @version 1.0
 * @date 28/09/2021
 */

public class ModeloGrafico {
    
    private String titulo;
    private int valor;

    public ModeloGrafico() {
    }

    public ModeloGrafico(String titulo, int valor) {
        this.titulo = titulo;
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public Object[] recuperarDatos(){
        Object[] datos = new Object[2];
        datos[0] = this.getTitulo();
        datos[1] = this.getValor();        
        return datos;
    }
    
}
