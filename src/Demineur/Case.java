/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Demineur;



/**
 *
 * @author Lucas
 */
public abstract class Case{
    protected boolean mine;
    protected boolean visible;
    protected boolean drapeau;
    protected boolean fini;
    protected int X;
    protected int Y;
    protected int nbMine;
   
    public boolean isDrapeau() {
        return this.drapeau;
    }

    public void setDrapeau(boolean drapeau) {
        this.drapeau = drapeau;
    }

    public int getNbMine() {
        return nbMine;
    }

    public void setNbMine(int nbMine) {
        this.nbMine = nbMine;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    
    public boolean getMine(){
        return this.mine;
    }
    public void setMine(boolean b){
        this.mine=b;
    }
    
    public Case(int x, int y, boolean b){
        this.X=x;
        this.Y=y;
        this.mine=b;
        this.visible=false;
        this.drapeau=false;
        this.fini=false;
    }

    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }


    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    
    public abstract String toString();
}
