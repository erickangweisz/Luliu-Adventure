package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.GeneradorTooltip;


public class MenuInventario extends SeccionMenu
{
    // Atributos.
    private int limitePeso = 100;
    private int pesoActual = 20;
    
    private final int margenGeneral = 8;
    
    private final Rectangle barraPeso;

    public MenuInventario(String nombreSeccion, Rectangle etiquetaMenu, EstructuraMenu em) 
    {
        super(nombreSeccion, etiquetaMenu);
        
        int anchoBarra = 100;
        int altoBarra = 8;
        
        barraPeso = new Rectangle(Constantes.ANCHO_JUEGO - anchoBarra - margenGeneral, em.BANNER_SUPERIOR.height + margenGeneral, anchoBarra, altoBarra);
    }

    @Override
    public void actualizar() 
    {
        
    }

    @Override
    public void dibujar(Graphics g, SuperficieDibujo sd) 
    {
        dibujarLimitePeso(g);
        if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(barraPeso)))
            GeneradorTooltip.dibujarTooltip(g, sd, "Peso: " + pesoActual + "/" + limitePeso);
    }
    
    private void dibujarLimitePeso(final Graphics g)
    {
        final Rectangle contenidoBarra = new Rectangle(barraPeso.x + 1, barraPeso.y + 1, barraPeso.width / (limitePeso / pesoActual), barraPeso.height - 2);
        
        DibujoDebug.dibujarString(g, "Peso", barraPeso.x - 40, barraPeso.y + 7, Color.black);
        DibujoDebug.dibujarRectanguloRelleno(g, barraPeso, Color.GRAY);
        DibujoDebug.dibujarRectanguloRelleno(g, contenidoBarra, Constantes.COLOR_ROSA);
    }
    
    
    
}
