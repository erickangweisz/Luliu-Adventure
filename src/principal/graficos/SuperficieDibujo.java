package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.GestorEstados;


public class SuperficieDibujo extends Canvas
{   
    // Atributos.
    private int ancho;
    private int alto;
    
    private Raton raton;
    
    // Constructor.
    public SuperficieDibujo(final int ancho, final int alto)
    {
        this.ancho = ancho;
        this.alto = alto;

        this.raton = new Raton(this);
        
        setIgnoreRepaint(true);
        setCursor(raton.getCursor());
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(GestorControles.teclado);
        addMouseListener(raton);
        setFocusable(true);
        requestFocus();
    }
    
    // Métodos.
    public void actualizar()
    {
        raton.actualizar(this);
    }
    
    public void dibujar(final GestorEstados ge)
    {
        final BufferStrategy buffer = getBufferStrategy();
        
        if(buffer == null)
        {
            createBufferStrategy(4);
            return;
        }
        
        final Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
        
        DibujoDebug.reiniciarContadorObjetos();
        
        g.setFont(Constantes.FUENTE_BLACKFAT);
        DibujoDebug.dibujarRectanguloRelleno(g, 0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA, Color.BLACK);
        
        if(Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0)
            g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
        
        ge.dibujar(g);
        
        DibujoDebug.dibujarString(g, "FPS: " + GestorPrincipal.getFPS(), 15, 15, Color.WHITE);
        DibujoDebug.dibujarString(g, "APS: " + GestorPrincipal.getAPS(), 15, 25);
        
        DatosDebug.enviarDato("ESCALA X: " + Constantes.FACTOR_ESCALADO_X);
        DatosDebug.enviarDato("ESCALA Y: " + Constantes.FACTOR_ESCALADO_Y);
        DatosDebug.enviarDato("OPF: "+DibujoDebug.getContadorObjetos());
        
        if(GestorControles.teclado.debug)
            DatosDebug.dibujarDatos(g);
        else
            DatosDebug.vaciarDatos();
        
        Toolkit.getDefaultToolkit().sync();
        
        g.dispose();
        
        buffer.show();
    }
    
    // Getters.
    public int getAncho()
    {
        return ancho;
    }
    
    public int getAlto()
    {
        return alto;
    }
    
    public Raton getRaton()
    {
        return raton;
    }
}
