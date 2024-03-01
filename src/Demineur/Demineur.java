/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Demineur;

/**
 *
 * @author Lucas
 */
public class Demineur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int  l, c, d, coupl, coupc, nbmine, cpt=1, cptDrap, drapeau=0;
        boolean perdu=false, fini=false, drapDispo=false;
        String drap="";
        do{
            System.out.println("Entrez la largeur de votre grille");
            l=Lire.i();
        } while(l<=4);
        do{
            System.out.println("Entrez la longueur de votre grille");
            c=Lire.i();
        }while (c<=4);
        do{
            System.out.println("Entrez une difficulté entre 1 et 9");
            d=Lire.i();
        }while(d<1 || d>9);
        Plateau p=new Plateau(d, l, c);
        cptDrap=p.getNbMine();
        nbmine=p.getNbMine();
        String coup="";
        System.out.println("Vous devez trouver "+nbmine+" mines.");
        System.out.println(p.toString());     
        System.out.println();     
        System.out.println(p.toStringTest());     
        do{
            do{
                System.out.println("Veuillez jouer pour le coup "+cpt);
                System.out.print("Choix d'une case sous le format <ligne:colonne> : ");
                coup=Lire.S();                
            }while(!p.verifCoupValide(coup));
            coupl=getLig(coup)+1;
            coupc=getCol(coup)+1;
            if(!p.getCase(coupl, coupc).isDrapeau()){
                do{
                    do{
                        System.out.println("Voulez Vous mettre un drapeau ? (1)OUI  (2)NON");
                        drap=Lire.S();
                    }while(!p.choixDrap(drap));
                    drapeau=Integer.parseInt(drap);
                    if(drapeau==1)
                        if(cptDrap>0){
                            cptDrap--;
                            drapDispo=true;
                        }else{
                            System.out.println("Il n'y a plus de drapeau, le coup à été annulé");
                            drapDispo=false;
                            cpt--;
                        }
                }while(drapeau!=1 && drapeau!=2);
                if(drapeau==1){
                    if(drapDispo)
                        p.getCase(coupl, coupc).setDrapeau(true);
                }else{
                    perdu=p.getCase(coupl, coupc).getMine();
                    p.active(p.getCase(coupl, coupc));
                }
            }else{
                p.getCase(coupl, coupc).setDrapeau(false);
                cptDrap++;
                
            }
            if(perdu){
                System.out.println("Vous avez perdu !");
                fini=true;
                p.setFini(fini);
                System.out.println(p.toString());
            }else{
                fini=p.PartieFinie();
                p.setFini(fini);
                if(fini){
                    System.out.println("Vous avez gagné en "+cpt+" coups.");
                    System.out.println(p.toString());
                }else{
                    System.out.println(p.toString());
                    System.out.println();     
                    System.out.println(p.toStringTest());  
                }
            }
            cpt++;
        }while(!fini);
        
            
    }
    
    public static int getCol(String s){
        return Integer.parseInt(s.charAt(2)+"");
    }
    public static int getLig(String s){
        return Integer.parseInt(s.charAt(0)+"");
    }
}
