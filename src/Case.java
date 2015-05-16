
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Case extends JLabel{
    private int largeur =140;
    private int haut=140;
    //private ImageIcon cacher = new ImageIcon(getClass().getResource("Icon/def.jpg"));
    private ImageIcon cacher = new ImageIcon(getClass().getResource("Icone/def.png"));
    private ImageIcon drapeau;
     private ImageIcon dernier_drapeau;
    private String ndrapeau="";
    private boolean congelado=false;
    
         public Case( String name ){
        super();
        Dimension d = new Dimension(largeur,haut);
        setName(name);
        setSize( d );
        setPreferredSize( d );
        setText("");                
        setIcon(cacher );
        setVisible(true);        
        setOpaque(true);
        setCursor(new Cursor( Cursor.HAND_CURSOR ));
    }
    
    public void showdrapeau(){
        setIcon( drapeau );
    }
    
    
    public void cacherdrapeau(){
        if( !congelado ){
            setIcon( cacher );
        }
    }
    
    public void congelarImagen(boolean value){
        this.congelado=value;
    }
    
   
    public boolean isCongelado(){
        return this.congelado;
    }
    
    public void setdrapeau( String name ){
        this.ndrapeau = name;
        if( !name.equals("") ){            
           // drapeau = new ImageIcon(getClass().getResource("Icon/"+name+".jpg"));  
             drapeau = new ImageIcon(getClass().getResource("Icone/"+name+".png"));
        }        
    }
    public String getNamedrapeau(){
        return ndrapeau;
    }
    public void setdrapeaux( String name){
      this.ndrapeau = name;
        if( !name.equals("") ){            
          // dernier_drapeau = new ImageIcon(getClass().getResource("Icon/S.jpg")); 
           dernier_drapeau = new ImageIcon(getClass().getResource("Icone/S.jpg")); 
           setIcon( dernier_drapeau );
        }
             
                
    } 
    
}   

