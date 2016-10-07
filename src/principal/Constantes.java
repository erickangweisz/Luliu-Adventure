package principal;

import java.awt.Color;
import java.awt.Font;
import principal.herramientas.CargadorRecursos;
// cambio para ejercicio dpl A2.

public class Constantes 
{
    // Atributos estáticos.
    public static final int LADO_SPRITE = 32;
    public static int LADO_CURSOR = 0;
    
    public static int ANCHO_JUEGO = 850; // 560 x3 // 840 x2 // 1440 hd
    public static int ALTO_JUEGO = 480; // 350 x3 // 525 x2 // 900 hd
    
    public static int ANCHO_PANTALLA_COMPLETA = 1366; // 1680
    public static int ALTO_PANTALLA_COMPLETA = 768; // 1050
    
    // 16:10
    
    public static double FACTOR_ESCALADO_X = (double)ANCHO_PANTALLA_COMPLETA / (double)ANCHO_JUEGO;
    public static double FACTOR_ESCALADO_Y = (double)ALTO_PANTALLA_COMPLETA / (double)ALTO_JUEGO;
    
    public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
    public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;
    
    public final static String RUTA_MAPA = "/mapas/2.map";
    public final static String RUTA_ICONO_RATON = "/imagenes/iconos/iconoCursor.png";
    public final static String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/1.png";
    public final static String RUTA_ICONO_VENTANA = "/imagenes/iconos/logotipo.png";
    public final static String RUTA_LOGOTIPO = "/imagenes/iconos/logotipo.png";
    public final static String RUTA_LOGO_VENTANA = "/imagenes/iconos/iconoVentana.png";
    
    public final static Font FUENTE_BLACKFAT = CargadorRecursos.cargarFuente("/fuentes/blackfat_demo.ttf");
    
    public final static Color COLOR_ROSA = new Color(227, 129, 204);
}
