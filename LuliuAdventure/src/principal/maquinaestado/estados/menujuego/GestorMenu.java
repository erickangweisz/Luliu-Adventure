package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.EstadoJuego;


public class GestorMenu implements EstadoJuego
{
    // Atributos.
    private final SuperficieDibujo sd;
    
    private final EstructuraMenu estructuraMenu;
    
    private final SeccionMenu[] secciones;
    
    private SeccionMenu seccionActual;
    
    // Constructor.
    public GestorMenu(final SuperficieDibujo sd)
    {
        this.sd = sd;
        
        estructuraMenu = new EstructuraMenu();
        
        secciones = new SeccionMenu[2];
        
        final Rectangle etiquetaInventario = new Rectangle(estructuraMenu.BANNER_LATERAL.x + estructuraMenu.MARGEN_HORIZONTAL_ETIQUETAS, 
                estructuraMenu.BANNER_LATERAL.y + estructuraMenu.MARGEN_VERTICAL_ETIQUETAS, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);
        
        secciones[0] = new MenuInventario("Inventario", etiquetaInventario, estructuraMenu);
        
        final Rectangle etiquetaEquipo = new Rectangle(estructuraMenu.BANNER_LATERAL.x + estructuraMenu.MARGEN_HORIZONTAL_ETIQUETAS,
                etiquetaInventario.y + etiquetaInventario.height + estructuraMenu.MARGEN_VERTICAL_ETIQUETAS, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);
        
        secciones[1] = new MenuEquipo("Equipo", etiquetaEquipo);
        
        seccionActual = secciones[0];
    }
    
    // Métodos.
    @Override
    public void actualizar() 
    {
        for(int i=0; i<secciones.length; i++)
        {
            if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().intersects(secciones[i].getEtiquetaMenuEscalada()))
                seccionActual = secciones[i];
        }
        
        sd.getRaton().reiniciarClick();
    }

    @Override
    public void dibujar(final Graphics g) 
    {
        estructuraMenu.dibujar(g);
        
        for(int i=0; i<secciones.length; i++)
        {
            if(seccionActual == secciones[i])
            {
                if(sd.getRaton().getRectanguloPosicion().intersects(secciones[i].getEtiquetaMenuEscalada()))
                    secciones[i].dibujarEtiquetaActivaResaltada(g);
                else
                    secciones[i].dibujarEtiquetaActiva(g);
            }
            else
            {
                if(sd.getRaton().getRectanguloPosicion().intersects(secciones[i].getEtiquetaMenuEscalada()))
                    secciones[i].dibujarEtiquetaInactivaResaltada(g);
                else
                    secciones[i].dibujarEtiquetaInactiva(g);
            }
        }
        
        seccionActual.dibujar(g, sd);
    }
    
}
