package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;


public class Jugador
{
    // Atributos.
    private double posicionX;
    private double posicionY;
    
    private int direccion;
    
    private double velocidad;
    
    private boolean enMovimiento;
    
    private HojaSprites hs;
    
    private BufferedImage imagenActual;
    
    private final int ANCHO_JUGADOR = 16;
    private final int ALTO_JUGADOR = 16;
    
    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
    
    
    private int animacion;
    private int estado;
    
    
    public static int RESISTENCIA_TOTAL = 820;
    public int resistencia;
    private int recuperacion;
    private final int RECUPERACION_MAXIMA = 100;
    private boolean recuperado;
    
    private Mapa mapa;    
    
    // Constructor.
    public Jugador(Mapa mapa)
    {
        posicionX = mapa.getPosicionInicial().getX();
        posicionY = mapa.getPosicionInicial().getY();
        
        direccion = 0;
        
        velocidad = 1;
        
        enMovimiento = false;
        
        hs = new HojaSprites(Constantes.RUTA_PERSONAJE, Constantes.LADO_SPRITE, false);
        
        imagenActual = hs.getSprite(0).getImagen();
        
        animacion = 0;
        estado = 0;
        
        resistencia = 820;
        recuperacion = 0;
        recuperado = true;
        
        this.mapa = mapa;
    }
    
    // Métodos.
    public void actualizar()
    {
        gestionarVelocidadYresistencia();
        cambiarAnimacionYestado();
        enMovimiento = false;
        determinarDireccion();
        animar();
    }
    
    private void gestionarVelocidadYresistencia()
    {
        if(GestorControles.teclado.corriendo && resistencia > 0)
        {
            velocidad = 2;
            recuperado = false;
            recuperacion = 0;
        }
        else
        {
            velocidad = 1;
            if(!recuperado && recuperacion < RECUPERACION_MAXIMA)
                recuperacion++;
            if(recuperacion == RECUPERACION_MAXIMA && resistencia < 820)
                resistencia++;
        }
    }
    
    private void cambiarAnimacionYestado()
    {
        if(animacion < 30)
            animacion++;
        else
            animacion = 0;
        
        if(animacion < 15)
            estado = 1;
        else
            estado = 2;
    }
    
    private void determinarDireccion()
    {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();
        
        if(velocidadX == 0 && velocidadY == 0)
            return;
        if((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0))
            mover(velocidadX, velocidadY);
        else
        {
            // izquierda y arriba
            if(velocidadX == -1 && velocidadY == -1)
            {
                if(GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion())
                    mover(velocidadX, 0);
                else
                    mover(0, velocidadY);
            }
            // izquierda y abajo
            if(velocidadX == -1 && velocidadY == 1)
            {
                if(GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion())
                    mover(velocidadX, 0);
                else
                    mover(0, velocidadY);
            }
            // derecha y arriba
            if(velocidadX == 1 && velocidadY == -1)
            {
                if(GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion())
                    mover(velocidadX, 0);
                else
                    mover(0, velocidadY);
            }
            // derecha y abajo
            if(velocidadX == 1 && velocidadY == 1)
            {
                if(GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion())
                    mover(velocidadX, 0);
                else
                    mover(0, velocidadY);
            }
        }
    }
    
    private int evaluarVelocidadX()
    {
        int velocidadX = 0;
        
        if(GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada())
            velocidadX = -1;
        else if(GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada())
            velocidadX = 1;
        
        return velocidadX;
    }
    
    private int evaluarVelocidadY()
    {
        int velocidadY = 0;
        
        if(GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada())
            velocidadY = -1;
        else if(GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada())
            velocidadY = 1;
        
        return velocidadY;
    }
    
    private void mover(final int velocidadX, final int velocidadY)
    {
        enMovimiento = true;
        
        cambiarDireccion(velocidadX, velocidadY);
        
        if(!fueraMapa(velocidadX, velocidadY))
        {
            if(velocidadX == -1 && !enColisionIzquierda(velocidadX))
                posicionX += velocidadX * velocidad;
                restarResistencia();
            
            if(velocidadX == 1 && !enColisionDerecha(velocidadX))
                posicionX += velocidadX * velocidad;
                restarResistencia();
            
            if(velocidadY == -1 && !enColisionArriba(velocidadY))
                posicionY += velocidadY * velocidad;
                restarResistencia();
                
            if(velocidadY == 1 && !enColisionAbajo(velocidadY))
                posicionY += velocidadY * velocidad;
                restarResistencia();
        }
    }
    
    private void restarResistencia()
    {
        if(GestorControles.teclado.corriendo && resistencia > 0)
                    resistencia--;
    }
    
    private boolean enColisionArriba(int velocidadY)
    {
        for(int r=0; r<mapa.areasColision.size(); r++)
        {
            final Rectangle area = mapa.areasColision.get(r);
            
            int origenX = area.x;
            int origenY = area.y + velocidadY * (int)velocidad + 3 * (int)velocidad;
            
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            
            if(LIMITE_ARRIBA.intersects(areaFutura))
                return true;
        }
        
        return false;
    }
    
    private boolean enColisionAbajo(int velocidadY)
    {
        for(int r=0; r<mapa.areasColision.size(); r++)
        {
            final Rectangle area = mapa.areasColision.get(r);
            
            int origenX = area.x;
            int origenY = area.y + velocidadY * (int)velocidad - 1 * (int)velocidad;
            
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            
            if(LIMITE_ABAJO.intersects(areaFutura))
                return true;
        }
        
        return false;
    }
    
    private boolean enColisionIzquierda(int velocidadX)
    {
        for(int r=0; r<mapa.areasColision.size(); r++)
        {
            final Rectangle area = mapa.areasColision.get(r);
            
            int origenX = area.x + velocidadX * (int)velocidad + 3 * (int)velocidad;
            int origenY = area.y;
            
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            
            if(LIMITE_IZQUIERDA.intersects(areaFutura))
                return true;
        }
        
        return false;
    }
    
    private boolean enColisionDerecha(int velocidadX)
    {
        for(int r=0; r<mapa.areasColision.size(); r++)
        {
            final Rectangle area = mapa.areasColision.get(r);
            
            int origenX = area.x + velocidadX * (int)velocidad - 3 * (int)velocidad;
            int origenY = area.y;
            
            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            
            if(LIMITE_DERECHA.intersects(areaFutura))
                return true;
        }
        
        return false;
    }
    
    private boolean fueraMapa(final int velocidadX, final int velocidadY)
    {
        int posicionFuturaX = (int) posicionX + velocidadX * (int)velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * (int)velocidad;
        
        final Rectangle bordesMapa = mapa.getBordes(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);
        
        final boolean fuera;
        
        if(LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa) || LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa))
            fuera = false;
        else
            fuera = true;
        
        return fuera;
    }
    
    private void cambiarDireccion(final int velocidadX, final int velocidadY)
    {
        if(velocidadX == -1)
        {
            direccion = 3;
        }
        else if(velocidadX == 1)
            direccion = 2;
        
        if(velocidadY == -1)
            direccion = 1;
        else if(velocidadY == 1)
            direccion  = 0;
    }
    
    private void animar()
    {
        if(!enMovimiento)
        {
            estado = 0;
            animacion = 0;
        }
        
        imagenActual = hs.getSprite(direccion, estado).getImagen();
    }
    
    public void dibujar(Graphics g)
    {
        final int centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        final int centroY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        
        DibujoDebug.dibujarImagen(g, imagenActual, centroX, centroY);
    }
    
    public void setPosicionX(double posicionX)
    {
        this.posicionX = posicionX;
    }
    
    public void setPosicionY(double posicionY)
    {
        this.posicionY = posicionY;
    }
    
    public double getPosicionX()
    {
        return posicionX;
    }
    
    public double getPosicionY()
    {
        return posicionY;
    }
    
    public Rectangle get_LIMITE_ARRIBA()
    {
        return LIMITE_ARRIBA;
    }
    
}