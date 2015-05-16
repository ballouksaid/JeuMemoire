
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;


public class Tableau extends JPanel {

    private String[] groupe = {"1","2","3","5","6","9","8","4"};
    
    private int range =4;
    private int collone = 4;
    private int largeur_case=140;
     
    
    public boolean play = false;
    
    int c=0;
    Case c1;
    Case c2;
    int aciertos=0;
     public int s(){
         
         return aciertos;
     }
   
    public Tableau(){
        super();
                setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout( new java.awt.GridLayout(range, collone) );        
        Dimension d= new Dimension( (largeur_case*collone),(largeur_case*range)  );        
        setSize(d);
        setPreferredSize(d);
            
        int count=0;
        for(int i=1;i<=(range*collone);i++){
            Case p = new Case( String.valueOf(i) );            
            p.setdrapeau(groupe[count] );
            count++;
            count = (count>=groupe.length)? 0:count++;
            p.showdrapeau();
            p.addMouseListener( new juegoMouseListener() );    
            this.add( p );
        }        
        setVisible(true);        
    }
    
    public void comencerjeu(){

        aciertos=0;
        play=true;
        Component[] componentes = this.getComponents();         
       
        for( int i=0; i< componentes.length ;i++){
            ((Case)componentes[i]).congelarImagen(false);
           ((Case)componentes[i]).cacherdrapeau();            
            ((Case)componentes[i]).setdrapeau( "" );
        }
       
        for( int i=0; i< componentes.length ;i++){
            int n = (int) (Math.random()*(groupe.length));        
            if( !existe(groupe[n]) ){               
                ((Case)componentes[i]).setdrapeau( groupe[n] );
            }else{
                i--;
            }
        }
        
    }
    
    private boolean existe( String drapeau ){  
        int count=0;
        Component[] componentes = this.getComponents(); 
        for( int i=0; i<componentes.length;i++ ) {
            if( componentes[i] instanceof Case ) {
                if( ((Case)componentes[i]).getNamedrapeau().equals( drapeau ) ) {
                    count++;
                }
            }
        }        
        return (count==2)? true:false;   
    }
    
    class juegoMouseListener implements MouseListener{        
        
    
        public void mouseClicked(MouseEvent e) {         
         
            if( play ){
                c++;            
                if( c==1 ){ 
                    c1=((Case) e.getSource()); 
                    if( !c1.isCongelado() ){
                        c1.showdrapeau();     
                          
                    }else{
                      c=0;   
                    }                
                }else if( c==2 && !c1.getName().equals( ((Case) e.getSource()).getName() ) ){
                    c2=((Case) e.getSource()); 
                    if( !c2.isCongelado() ){
                        c2.showdrapeau();     
                            
                     comparison a = new comparison( c1, c2 );
                        a.execute();
                 
                    }
                    c=0;
                }else{ 
                    c=0; 
                }
            }
            
            else{
                        if( aciertos == 8 ){
              
                
                }
            }
            
            
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e){}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
        
    }
   
    class comparison extends SwingWorker<Void, Void>{
   
        private Case casilla1;
        private Case casilla2;
        
        public comparison(Case value1, Case value2){
            this.casilla1= value1;
            this.casilla2= value2;
        }
        
        @Override
        protected Void doInBackground() throws Exception {
            
            Thread.sleep( 1000 );                
            if( casilla1.getNamedrapeau().equals( casilla2.getNamedrapeau() ) ){
                    casilla1.setdrapeaux(casilla1.getNamedrapeau());
                casilla2.setdrapeaux(casilla1.getNamedrapeau());
                casilla1.congelarImagen(true);
                casilla2.congelarImagen(true);
            
                aciertos++;
                if( aciertos == 8 ){
            
                    JOptionPane.showMessageDialog(null,"vous etes un gagnant!  ");
                }
            }            
            else{
                casilla1.cacherdrapeau();
                casilla2.cacherdrapeau();
                    
            }
            return null;
        }
    
    }
   
    
}


