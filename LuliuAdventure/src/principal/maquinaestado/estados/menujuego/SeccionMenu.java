package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;


public abstract class SeccionMenu 
{
    // Atributos.
    protected final String nombreSeccion;
    protected final Rectangle etiquetaMenu;
    
    // Constructor.
    public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu)
    {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;
    }
    
    // Métodos.
    public abstract void actualizar();
    
    public abstract void dibujar(final Graphics g, final SuperficieDibujo sd);
    
    public void dibujarEtiquetaInactiva(final Graphics g)
    {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 10, etiquetaMenu.y + 12, Color.black);
    }
    
    public void dibujarEtiquetaActiva(final Graphics g)
    {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        
        final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 5, etiquetaMenu.height);   
        DibujoDebug.dibujarRectanguloRelleno(g, marcaActiva, new Color(255, 193, 245));
        
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 10, etiquetaMenu.y + 12, Color.black);
    }
    
    public void dibujarEtiquetaInactivaResaltada(final Graphics g)
    {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        
        DibujoDebug.dibujarRectanguloRelleno(g, new Rectangle(etiquetaMenu.x + etiquetaMenu.width - 10, etiquetaMenu.y + 5, 5, etiquetaMenu.height - 10), new Color (0x2a2a2a));
        
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 10, etiquetaMenu.y + 12, Color.PINK);
    }
    
    public void dibujarEtiquetaActivaResaltada(final Graphics g)
    {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        
        final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 5, etiquetaMenu.height);
        DibujoDebug.dibujarRectanguloRelleno(g, marcaActiva, new Color(255, 193, 245));
        
        DibujoDebug.dibujarRectanguloRelleno(g, new Rectangle(etiquetaMenu.x + etiquetaMenu.width - 10, etiquetaMenu.y + 5, 5, etiquetaMenu.height - 10), new Color (0x2a2a2a));
        
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 10, etiquetaMenu.y + 12, Color.PINK);
    }
    
    // Getters.
    public String getNombreSeccion()
    {
        return nombreSeccion;
    }
    
    public Rectangle getEtiquetaMenu()
    {
        return etiquetaMenu;
    }
    
    public Rectangle getEtiquetaMenuEscalada()
    {
        final Rectangle etiquetaEscalada = new Rectangle((int)(etiquetaMenu.x * Constantes.FACTOR_ESCALADO_X), (int)(etiquetaMenu.y * Constantes.FACTOR_ESCALADO_Y), (int)(etiquetaMenu.width * Constantes.FACTOR_ESCALADO_X), (int)(etiquetaMenu.height * Constantes.FACTOR_ESCALADO_Y));
        return etiquetaEscalada;
    }
}
