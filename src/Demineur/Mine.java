/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Demineur;

/**
 *
 * @author Lucas
 */
public class Mine extends Case{
    
    public Mine(){
        super(0,0,true);
    }
    
    public String toString(){//méthode qui renvoie sous forme de chaine de caractère le contenu de la case en fonction de la prograssion de la partie
        String s="";
        if(this.visible)
            s+="[ * ]";
        else
            if(!this.fini){
                if(this.drapeau)
                    s+="[ O ]";
                else
                    s+="["+(this.X-1)+":"+(this.Y-1)+"]";
            }else
                s+="[ m ]";
        return s;
    }
}
