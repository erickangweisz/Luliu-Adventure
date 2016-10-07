package principal.mapas;

import java.awt.Rectangle;
import principal.sprites.Sprite;


public class Tile 
{
    // Atributos.
    private final Sprite sprite;
    private final int id;
    private boolean solido;
    
    // Constructores.
    public Tile(final Sprite sprite, final int id)
    {
        this.sprite = sprite;
        this.id = id;
        solido = false;
    }
    
    public Tile(final Sprite sprite, final int id, final boolean solido)
    {
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }
    
    // Getters.
    public Sprite getSprite()
    {
        return sprite;
    }
    
    public int getId()
    {
        return id;
    }
    
    // Setters.
    public void setSolido(final boolean solido)
    {
        this.solido = solido;
    }
    
    // Métodos.
    public Rectangle obtenerLimites(final int x, final int y)
    {
        return new Rectangle(x, y, sprite.getAncho(), sprite.getAlto());
    }
            
}
