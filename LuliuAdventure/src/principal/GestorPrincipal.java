package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstados;


public class GestorPrincipal 
{
    // Atributos.
    boolean enFuncionamiento = false;
    private final String titulo;
    private final int ancho;
    private final int alto;
    
    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;
    
    private static int fps;
    private static int aps;
    
    // Constructor.
    private GestorPrincipal(String titulo, int ancho, int alto)
    {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
        fps = 0;
        aps = 0;
    }
    
    // Método PRINCIPAL.
    public static void main(String[] args)
    {
        GestorPrincipal gp = new GestorPrincipal("Luliü in the Darkness", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        
        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();
    }

    // Métodos.
    private void iniciarJuego() 
    {
        enFuncionamiento = true;
        inicializar();
    }
    
    private void inicializar()
    {
        sd = new SuperficieDibujo(ancho, alto);
        ventana = new Ventana(titulo, sd);
        ge = new GestorEstados(sd);
    }

    private void iniciarBuclePrincipal() 
    {
        int actualizacionesAcumuladas = 0;
        int framesAcumulados = 0;
        
        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        
        while(enFuncionamiento)
        {
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
            while(delta >= 1)
            {
                actualizar();
                actualizacionesAcumuladas++;
                delta--;
            }

            dibujar();
            framesAcumulados++;
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO)
            {
                aps = actualizacionesAcumuladas;
                fps = framesAcumulados;
                
                actualizacionesAcumuladas = 0;
                framesAcumulados = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
    
    private void actualizar()
    {
        if(GestorControles.teclado.inventarioActivo)
            ge.cambiarEstadoActual(1);
        else
            ge.cambiarEstadoActual(0);
        
        ge.actualizar();
        sd.actualizar();
    }
    
    private void dibujar()
    {
        sd.dibujar(ge);
    }
    
    // Getters.
    public static int getFPS()
    {
        return fps;
    }
    
    public static int getAPS()
    {
        return aps;
    }
}
