/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Demineur;

/**
 *
 * @author Lucas
 */
public class Plateau {
    private Case[][] cases;
    private int dif;
    private int nbMine;
    private int nblig;
    private int nbcol;
    
    public Plateau(int d, int l, int c){//constructeur avec paramètre qui initialise les attributs de la classe et appelle la méthode intiPlateau()
        this.cases=new Case[l+2][c+2];
        this.nblig=l+2;
        this.nbcol=c+2;
        this.dif=d;
        this.nbMine=((l*c*d)/10);
        initPlateau();
    }
    
    public void melange(){//méthode qui mélange les cases de la grille entre elle pour éviter que toutes les mines se retrouvent côtes à côtes et qui appelle la méthode initCases()
        for(int i=0; i<1000; i++){
            int l1=(int)(Math.random()*(this.nblig-2)+1);
            int c1=(int)(Math.random()*(this.nbcol-2)+1);
            int l2;
            int c2;
            do{
                l2=(int)(Math.random()*(this.nblig-2)+1);
                c2=(int)(Math.random()*(this.nbcol-2)+1);
            }while(l1==l2 && c1==c2);
            
            Case temp=this.cases[l1][c1];          
            this.cases[l1][c1]=this.cases[l2][c2];
            this.cases[l2][c2]=temp;
        }
        initCases();
    }
    
    public void initCases(){//donne la place des cases dans la grille aux cases elles-mêmes et si c'est une case ne contenant pas de mine appelle la méthode getNbMineAutour(Case c)
        for(int i=1; i<this.nblig-1; i++)
            for(int j=1; j<this.nbcol-1;j++){
                    this.cases[i][j].setX(i);
                    this.cases[i][j].setY(j);
                    if(!this.cases[i][j].getMine())
                        this.cases[i][j].setNbMine(getNbMineAutour(this.cases[i][j]));
             
            }
    }
    
    public void initPlateau(){//méthode qui créé les cases du tableau et appelle la méthode melange()
        int cpt=0;
        for(int i=0; i<this.nblig; i++)
            for(int j=0; j<this.nbcol;j++){
                if(i==0 || i==this.nblig-1 ||j==0 || j==this.nbcol-1)//fais en sorte que tout le tour de la grille soit null
                    this.cases[i][j]=null;
                else{
                    if(cpt<this.nbMine)//fais en sorte qu'il y ait le bon nombre de mine
                        this.cases[i][j]=new Mine();
                    else
                        this.cases[i][j]=new Vide();
                    cpt++;
                }
            }
        melange();
    }
    
    public boolean verifCoupValide(String s){//méthode qui vérifie si le coup entré par le joueur est valide ou non 
        boolean valide=false;
        if(s.length()==3){
            String c0=""+s.charAt(0);
            String c1=""+s.charAt(1);
            String c2=""+s.charAt(2);
            int x=Integer.parseInt(c0)+1;
            int y=Integer.parseInt(c2)+1;
            if(x>0 && x<this.nblig-1)
                if(c1.equals(":"))
                    if(y>0 && y<this.nbcol-1)
                        if(!this.cases[x][y].isVisible())
                            valide=true;
        }
        return valide;
    }
    
    
    public String toString(){//méthode qui permet l'affichage de la grille de jeu en prenant en compte l'avancement de la partie
        String s="";
        for(int i=1; i<this.nblig-1;i++){
            for(int j=1; j<this.nbcol-1;j++)
                s+=this.cases[i][j].toString();
            s+="\n";
        }
        return s;
    }
    
    public String toStringTest(){//méthode qui permet l'affichage de la grille de jeu de manière résolue pour le test
        String s="";
        for(int i=1; i<this.nblig-1;i++){
            for(int j=1; j<this.nbcol-1;j++)
                if(this.cases[i][j].getMine())
                    s+="[ m ]";
                else
                    if(this.cases[i][j].getNbMine()==0)
                        s+="[   ]";
                    else
                        s+="[ "+this.cases[i][j].getNbMine()+" ]";
            s+="\n";
        }
        return s;
    }
    
    public int getNbMineAutour(Case c){//méthode qui compte le nombre de mine autour de la case donnée en paramètre
        int cpt=0;
        if(this.cases[c.getX()-1][c.getY()]!=null)
            if(this.cases[c.getX()-1][c.getY()].getMine())
                cpt+=1;
        if(this.cases[c.getX()-1][c.getY()+1]!=null)
            if(this.cases[c.getX()-1][c.getY()+1].getMine())
                cpt+=1;
        if(this.cases[c.getX()][c.getY()+1]!=null)
            if(this.cases[c.getX()][c.getY()+1].getMine())
                cpt+=1;
        if(this.cases[c.getX()+1][c.getY()+1]!=null)
            if(this.cases[c.getX()+1][c.getY()+1].getMine())
                cpt+=1;
        if(this.cases[c.getX()+1][c.getY()]!=null)
            if(this.cases[c.getX()+1][c.getY()].getMine())
                cpt+=1;
        if(this.cases[c.getX()+1][c.getY()-1]!=null)
            if(this.cases[c.getX()+1][c.getY()-1].getMine())
                cpt+=1;
        if(this.cases[c.getX()][c.getY()-1]!=null)
            if(this.cases[c.getX()][c.getY()-1].getMine())
                cpt+=1;
        if(this.cases[c.getX()-1][c.getY()-1]!=null)
            if(this.cases[c.getX()-1][c.getY()-1].getMine())
                cpt+=1;
    return cpt;
    }
        
    public boolean PartieFinie(){//méthode qui vérifie que la partie est terminée ou non en regardant que toutes les mines sont bien marquées par un drapeau ou que toutes les cases de type Vide sont dévoilées
        boolean fini=true;
        int cptDrap=0;
        for(int i=1; i<this.nblig-1; i++)
            for(int j=1; j<this.nbcol-1;j++)
                if(this.cases[i][j].getMine())
                    if(this.cases[i][j].isDrapeau())
                        cptDrap++;
        if(cptDrap!=this.nbMine){
            for(int i=1; i<this.nblig-1; i++)
                for(int j=1; j<this.nbcol-1;j++)
                    if(!this.cases[i][j].getMine() && !this.cases[i][j].isVisible())
                        fini=this.cases[i][j].isVisible();
        }
        return fini;
    }
    
    public void active(Case c){//méthode qui dévoile la case en paramètre et dévoile les autres cases aux alentours avec la méthode active(Case c), seulement si le nombre de mine aux alentours est 0 
        if(!c.isDrapeau()){
            c.setVisible(true);
            if(c.getNbMine()==0){
                if(this.cases[c.getX()-1][c.getY()]!=null)
                    if(!this.cases[c.getX()-1][c.getY()].getMine() && !this.cases[c.getX()-1][c.getY()].isVisible())
                        active(this.cases[c.getX()-1][c.getY()]);
                if(this.cases[c.getX()-1][c.getY()+1]!=null)
                    if(!this.cases[c.getX()-1][c.getY()+1].getMine() && !this.cases[c.getX()-1][c.getY()+1].isVisible())
                        active(this.cases[c.getX()-1][c.getY()+1]);
                if(this.cases[c.getX()][c.getY()+1]!=null)    
                    if(!this.cases[c.getX()][c.getY()+1].getMine() && !this.cases[c.getX()][c.getY()+1].isVisible())
                        active(this.cases[c.getX()][c.getY()+1]);
                if(this.cases[c.getX()+1][c.getY()+1]!=null)    
                    if(!this.cases[c.getX()+1][c.getY()+1].getMine() && !this.cases[c.getX()+1][c.getY()+1].isVisible())
                        active(this.cases[c.getX()+1][c.getY()+1]);
                if(this.cases[c.getX()+1][c.getY()]!=null)    
                    if(!this.cases[c.getX()+1][c.getY()].getMine() && !this.cases[c.getX()+1][c.getY()].isVisible())
                        active(this.cases[c.getX()+1][c.getY()]);
                if(this.cases[c.getX()+1][c.getY()-1]!=null)    
                    if(!this.cases[c.getX()+1][c.getY()-1].getMine() && !this.cases[c.getX()+1][c.getY()-1].isVisible())
                        active(this.cases[c.getX()+1][c.getY()-1]);
                if(this.cases[c.getX()][c.getY()-1]!=null)    
                    if(!this.cases[c.getX()][c.getY()-1].getMine() && !this.cases[c.getX()][c.getY()-1].isVisible())
                        active(this.cases[c.getX()][c.getY()-1]);
                if(this.cases[c.getX()-1][c.getY()-1]!=null)    
                    if(!this.cases[c.getX()-1][c.getY()-1].getMine() && !this.cases[c.getX()-1][c.getY()-1].isVisible())
                        active(this.cases[c.getX()-1][c.getY()-1]);             
            }
        }
    }
    
   public boolean choixDrap(String s){//méthode qui vérifie que le joueur a bien entré 1 ou 2 au moment de répondre au message demandant s'il voulait mettre un drapeau
       if(s.length()==1){
           int x=Integer.parseInt(s.charAt(0)+"");
           return (x==1 || x==2);
       }else
           return false;
   }
    
    public void setFini(boolean b){//méthode qui donne le boolean en paramètre à l'attribut fini de la classe case à toute la grille
        for(int i=1; i<this.nblig-1; i++)
            for(int j=1; j<this.nbcol-1;j++)
                this.cases[i][j].setFini(b);
    }
    
    public int getNblig() {//accesseur en lecture de l'attribut nblig
        return nblig;
    }

    public void setNblig(int nblig) {//accesseur en écriture de l'attribut nblig
        this.nblig = nblig;
    }

    public int getNbcol() {//accesseur en lecture de l'attribut nbcol
        return nbcol;
    }

    public void setNbcol(int nbcol) {//accesseur en écriture de l'attribut nbcol
        this.nbcol = nbcol;
    }

    public int getNbMine() {//accesseur en lecture de l'attribut nbMine
        return nbMine;
    }

    public void setNbMine(int nbMine) {//accesseur en écriture de l'attribut nbMine
        this.nbMine = nbMine;
    }

    public Case[][] getCases() {//accesseur en lecture de l'attribut cases
        return cases;
    }

    public void setCases(Case[][] cases) {//accesseur en écriture de l'attribut cases
        this.cases = cases;
    }

    public int getDif() {//accesseur en lecture de l'attribut dif
        return dif;
    }

    public void setDif(int dif) {//accesseur en écriture de l'attribut dif
        this.dif = dif;
    }
    
    public Case getCase(int l, int c){//accesseur en lecture particulier qui renvoie une case spécifique du tableau 
        return this.cases[l][c];
    }
    
    public void setCase(int l, int c, Case ca){//accesseur en écriture particulier qui modifie une case spécifique du tableau
        this.cases[l][c]=ca;
    }
    

}
