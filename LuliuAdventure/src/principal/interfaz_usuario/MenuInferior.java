package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoDebug;


public class MenuInferior 
{
    // Atributos.
    private Rectangle areaInventario;
    private Rectangle bordeAreaInventario;
    
    private Color rosaApagado;
    private Color rojoClaro;
    private Color rojoOscuro;
    private Color azulTurquesa;
    private Color azulTurquesaOscuro;
    private Color verdeClaro;
    private Color verdeOscuro;
    private Color violetaClaro;
    private Color violetaOscuro;
    
    private Jugador jugador;
            
    // Constructor.
    public MenuInferior(final Jugador jugador)
    {
        int altoMenu = 64;
        areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
        bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);
        
        rosaApagado = new Color(227, 129, 204);
        rojoClaro = new Color(255, 0, 0);
        rojoOscuro = new Color(150, 0 , 0);
        azulTurquesa = new Color(44, 183, 222);
        azulTurquesaOscuro = new Color(14, 101, 125);
        verdeClaro = new Color(0, 255, 98);
        verdeOscuro = new Color(26, 122, 63);
        violetaClaro = new Color(107, 61, 245);
        violetaOscuro = new Color(28, 12, 77);
    }
    
    // Métodos.
    public void dibujar(final Graphics g, final Jugador jugador)
    {
        dibujarAreaInventario(g);
        dibujarBarraVitalidad(g);
        dibujarBarraPoder(g);
        dibujarBarraResistencia(g, jugador.resistencia);
        dibujarBarraExperiencia(g, 77);
        dibujarRanurasObjetos(g);
    }
    
    private void dibujarAreaInventario(final Graphics g)
    {
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, rosaApagado);
        DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario, Color.WHITE);
    }
    
    private void dibujarBarraVitalidad(final Graphics g)
    {
        final int medidaVertical = 4;
        final int anchoTotal = 100;
        
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 2, anchoTotal, medidaVertical, rojoClaro);
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 3, anchoTotal, medidaVertical, rojoOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "VIT", areaInventario.x + 10, areaInventario.y + medidaVertical * 4);
        DibujoDebug.dibujarString(g, "820", anchoTotal + 50, areaInventario.y + medidaVertical * 4);
    }
    
    private void dibujarBarraPoder(final Graphics g)
    {
        final int medidaVertical = 4;
        final int anchoTotal = 100;
        
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 5, anchoTotal, medidaVertical, azulTurquesa);
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 6, anchoTotal, medidaVertical, azulTurquesaOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "POW", areaInventario.x + 10, areaInventario.y + medidaVertical * 7);
        DibujoDebug.dibujarString(g, "1020", anchoTotal + 50, areaInventario.y + medidaVertical * 7);
    }
    
    private void dibujarBarraResistencia(final Graphics g, final int resistencia)
    {
        final int medidaVertical = 4;
        final int anchoTotal = 100;
        final int ancho = anchoTotal * resistencia / Jugador.RESISTENCIA_TOTAL;
        
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 8, ancho, medidaVertical, verdeClaro);
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 9, ancho, medidaVertical, verdeOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "RST", areaInventario.x + 10, areaInventario.y + medidaVertical * 10);
        DibujoDebug.dibujarString(g, "" + resistencia, anchoTotal + 50, areaInventario.y + medidaVertical * 10);
    }
    
    private void dibujarBarraExperiencia(Graphics g, final int experiencia)
    {
        final int medidaVertical = 4;
        final int anchoTotal = 77;
        final int ancho = anchoTotal * experiencia / anchoTotal;
        
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 11, anchoTotal, medidaVertical, violetaClaro);
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 45, areaInventario.y + medidaVertical * 12, anchoTotal, medidaVertical, violetaOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "EXP", areaInventario.x + 10, areaInventario.y + medidaVertical * 13);
        DibujoDebug.dibujarString(g, experiencia + "%", anchoTotal + 50, areaInventario.y + medidaVertical * 13);
    }
    
    private void dibujarRanurasObjetos(final Graphics g)
    {
        final int anchoRanura = 42;
        final int numeroRanuras = 12;
        final int espaciadoRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espaciadoRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
        final int anchoRanuraYespacio = anchoRanura + espaciadoRanuras;
        
        g.setColor(Color.white);
        System.out.println(xInicial);
        
        for(int i=0; i < numeroRanuras; i++)
        {
            int xActual = xInicial + anchoRanuraYespacio * i;
            
            Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);
            DibujoDebug.dibujarRectanguloRelleno(g, ranura, Color.BLACK);
            DibujoDebug.dibujarString(g, "" + i, xActual + 16, areaInventario.y + 58, Color.WHITE);
        }
    }
}
