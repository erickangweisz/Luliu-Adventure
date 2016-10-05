package principal.sprites;

import java.awt.image.BufferedImage;


public class Sprite 
{
    // Atributos.
    private final BufferedImage imagen;
    
    private final int ancho;
    private final int alto;
    
    // Constructor.
    public Sprite(BufferedImage imagen)
    {
        this.imagen = imagen;
        
        ancho = imagen.getWidth();
        alto = imagen.getHeight();
    }
    
    // Getters.
    public BufferedImage getImagen()
    {
        return imagen;
    }
    
    public int getAncho()
    {
        return ancho;
    }
    
    public int getAlto()
    {
        return alto;
    }
}
