 package principal.control;

 
public class Tecla 
{
    // Atributos.
    private boolean pulsada = false;
    private long ultimaPulsacion = System.nanoTime();
    
    // Métodos.
    public void teclaPulsada()
    {
        pulsada = true;
        ultimaPulsacion = System.nanoTime();
    }
    
    public void teclaLiberada()
    {
        pulsada = false;
    }
    
    // Getters.
    public boolean estaPulsada()
    {
        return pulsada;
    }
    
    public long getUltimaPulsacion()
    {
        return ultimaPulsacion;
    }
}
