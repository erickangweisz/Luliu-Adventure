package principal.control;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;


public class Raton extends MouseAdapter
{
    // Atributos.
    private final Cursor cursor;
    private Point posicion;
    private boolean click;
    
    // Constructor.
    public Raton(final SuperficieDibujo sd)
    {
        Toolkit configuracion = Toolkit.getDefaultToolkit();
        
        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_ICONO_RATON);
        
        Constantes.LADO_CURSOR = icono.getWidth();
        
        Point punta = new Point(0, 0);
        
        this.cursor = configuracion.createCustomCursor(icono, punta, "Cursor1");
        
        posicion = new Point();
        actualizarPosicion(sd);
        
        click = false;
    }
    
    // Getters.
    public Cursor getCursor()
    {
        return this.cursor;
    }
    
    public boolean getClick()
    {
        return click;
    }
    
    // Setters.
    public void reiniciarClick()
    {
        if(click)
            click = false;
    }
    
    // Métodos.
    public void actualizar(final SuperficieDibujo sd)
    {
        actualizarPosicion(sd);
    }
    
    public void dibujar(Graphics g)
    {
        DatosDebug.enviarDato("RX: " + posicion.getX());
        DatosDebug.enviarDato("RY: " + posicion.getY());
    }
    
    private void actualizarPosicion(final SuperficieDibujo sd)
    {
        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        
        SwingUtilities.convertPointFromScreen(posicion, sd);
        
        posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(!click)
            click = true;
    }
    
    // Getters.
    public Point getPuntoPosicion()
    {
        return posicion;
    }
    
    public Rectangle getRectanguloPosicion()
    {
        final Rectangle area = new Rectangle(posicion.x, posicion.y, 1, 1);
        return area;
    }
}
