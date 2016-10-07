package principal.maquinaestado.estados.juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;
import principal.interfaz_usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego
{
    // Atributos.
    Mapa mapa;
    Jugador jugador;
    BufferedImage logo;
    MenuInferior menuInferior;
    
    // Constructor.
    public GestorJuego()
    {
        iniciarMapa(Constantes.RUTA_MAPA);
        iniciarJugador();
        menuInferior = new MenuInferior(jugador);
        logo = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_ICONO_VENTANA);
    }
    
    // Métodos.
    private void recargarJuego()
    {
        final String ruta = "/mapas/" + mapa.getSiguienteMapa();
        
        iniciarMapa(ruta);
        iniciarJugador();
    }
    
    private void iniciarMapa(final String ruta)
    {
        mapa = new Mapa(ruta);
    }
    
    private void iniciarJugador()
    {
        jugador = new Jugador(mapa);
    }

    @Override
    public void actualizar() 
    {
        if(jugador.get_LIMITE_ARRIBA().intersects(mapa.getZonaSalida()))
        {
            recargarJuego();
        }
        
        jugador.actualizar();
        mapa.actualizar((int)jugador.getPosicionX(), (int)jugador.getPosicionY()); 
    }

    @Override
    public void dibujar(Graphics g) 
    {
        mapa.dibujar(g, (int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        jugador.dibujar(g);
        menuInferior.dibujar(g, jugador);
        
        DibujoDebug.dibujarImagen(g, logo, Constantes.ANCHO_JUEGO - logo.getWidth() - 5, 0 + 5);
        
        //DibujoDebug.dibujarRectanguloRelleno(g, mapa.getZonaSalida().x, mapa.getZonaSalida().y, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE, Color.MAGENTA);
        
        DatosDebug.enviarDato("X = " + jugador.getPosicionX());
        DatosDebug.enviarDato("Y = " + jugador.getPosicionY());
        DatosDebug.enviarDato("Siguiente mapa: " + mapa.getSiguienteMapa());
        DatosDebug.enviarDato("Coordenadas salida X = " + mapa.getPuntoSalida().getX() + " Y = " + mapa.getPuntoSalida().getY());
    }
    
}
