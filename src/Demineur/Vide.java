/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Demineur;


/**
 *
 * @author Lucas
 */
public class Vide extends Case{

    public Vide(){
        super(0,0,false);
    }

    public String toString(){//méthode qui renvoie sous forme de chaine de caractère le contenu de la case en fonction de la prograssion de la partie
        String s="";
        if(this.visible)
            if(this.nbMine!=0)
                s+="[ "+this.nbMine+" ]";
            else
                s+="[   ]";
        else
            if(this.drapeau)
                s+="[ O ]";
            else
                s+="["+(this.X-1)+":"+(this.Y-1)+"]";
        return s;
    }
    

}
