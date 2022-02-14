/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;

/**
 *
 * @author Remi
 */
public class Graphe {
    private int sommets;
    private int arretes;
    private int[][] matrice = new int [sommets][sommets];
    
    Graphe(int [][] matrice){
        this.matrice = matrice;
        this.sommets = matrice.length;
        this.arretes = 0;
        for(int[] ligne : this.matrice){
            for(int colonne : ligne){
                this.arretes+=colonne;
            }
        } 
    }
    
    public int ordre(){
        return this.sommets;
    }
    
    public int taille(){
        return this.arretes;
    }
    
    public String type(){
        int p = this.pGraphe();
        String check = "";
        if(!(this.estSymetrique())){
            check = "non-";
        }
        if (this.estSimple()){
            return "Ce graphe est simple et "+check+"orienté";
        }
        else if(this.estElementaire()){
            return "Ce graphe est un "+p+"-graphe élémentaire"+check+"orienté";
        }
        return "Ce graphe est un "+p+"graphe"+check+"orienté";
    }
    
    public int pGraphe(){
        int max = 0;
        for (int[] ligne : this.matrice) {
            for (int j = 0; j < this.matrice.length; j++) {
                if (ligne[j] > max) {
                    max = ligne[j];
                }
            }
        }
        return max;
    }
    
    public boolean estElementaire(){
        for(int i = 0; i < this.matrice.length; i++){
            for(int j = 0; j < this.matrice.length; j++){
                if(i == j && matrice[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean estSimple(){
        return this.pGraphe() == 1 && this.estElementaire();
    }
    /**
     * 
     * @param sommet sommet auquel on récupère le ou les degrés (si orienté et non simple)
     * @return un tableau de int correspondant au 
     * degre du sommet, respectivement le degré simple, le demi-degré extérieur puis demi-degré intérieur
     * (si orienté et non simple)
     */
    public int[] degre(int sommet){
        int checkOriente;
        if (this.estSymetrique()){
            checkOriente = 1;
        } 
        else{
            checkOriente = 3;
        }
        int[] degres = new int[checkOriente];
        for(int i = 0; i<this.matrice.length; i++){
            if (this.matrice[sommet][i] != 0){
                degres[0]+=this.matrice[sommet][i];
                if(degres.length > 1){
                    degres[1]+=this.matrice[sommet][i];
                }
                
            }
            if (this.matrice[i][sommet] != 0){
                if(degres.length > 1){
                    degres[0]+=this.matrice[i][sommet];
                    degres[2]+=this.matrice[i][sommet];
                }
            }
        }
        return degres;
    }
    
    public int sommeDegre(){
        return this.arretes*2;
    }
    /**
     * 
     * @param sommet sommet auquel on récupère les successeurs
     * @return un tableau de int correspondant aux sommets successeurs de sommet
     */
    public int[] suivants(int sommet){
        int checkOriente;
        if (this.estSymetrique()){
            checkOriente = 0;
        } 
        else{
            checkOriente = 1;
        }
        int[] suivants = new int[this.degre(sommet)[checkOriente]];
        int ctr = 0;
        for(int i = 0; i < this.matrice.length; i++){
            if(matrice[sommet][i] != 0){
                suivants[ctr] = i;
                ctr+=1;
            }
        }
        return suivants;
    }
    /**
     * 
     * @param sommet sommet auquel on récupère les predecesseurs
     * @return un tableau de int correspondant aux sommets predecesseurs de sommet
     */
    public int[] precedents(int sommet){
        int checkOriente;
        if (this.estSymetrique()){
            checkOriente = 0;
        } 
        else{
            checkOriente = 2;
        }
        int[] precedents = new int[this.degre(sommet)[checkOriente]];
        int ctr = 0;
        for(int i = 0; i < this.matrice.length; i++){
            if(matrice[i][sommet] != 0){
                precedents[ctr] = i;
                ctr+=1;
            }
        }
        return precedents;
    }
    /**
     * 
     * @param sommet1 
     * @param sommet2 
     * @return true si sommet1 est un predecesseur de sommet2, sinon false
     */
    public boolean verifPredecesseur(int sommet1, int sommet2){
        int[] precedents = this.precedents(sommet2);
        for(int i = 0; i < precedents.length; i++){
            if(precedents[i] == sommet1){
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param sommet1 
     * @param sommet2 
     * @return true si sommet1 est un successeur de sommet2, sinon false
     */
    public boolean verifSuccesseur(int sommet1, int sommet2){
        int[] suivants = this.suivants(sommet2);
        for(int i = 0; i < suivants.length; i++){
            if(suivants[i] == sommet1){
                return true;
            }
        }
        return false;
    }

    public boolean estSymetrique(){
        for(int i = 0; i < this.matrice.length; i++){
            for(int j = 0 ; j < this.matrice.length; j++){
                if (this.matrice[i][j] != this.matrice[j][i]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean estComplet(){
        for(int i = 0; i < this.matrice.length; i++){
            for(int j = 0; j < this.matrice.length; j++){
                if(i != j && this.matrice[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    } 
    
    public int nombreClique(){
        if(!(this.estSimple())){
            return 0;
        }
        int maxClique = 0;
        int[] clique = new int[this.ordre()];
        if(this.estSymetrique()){
            for(int i = 0; i < this.ordre(); i++){    
                int []suivants = this.suivants(i);
                for(int j = 0; j < suivants.length; j++){
                    /*if (this.suivants(suivants[j]) < i){
                        
                    }*/
                
                    return nombreClique();
                }
                
            }
        
        }
        else{
            //voir  remarque chap 1 page 13
        }
        
        
        return maxClique;
    }
    @Override
    public String toString(){
        return "Nombre de sommets : "+this.ordre()+"\nNombre d'arc(s)/arrête(s) : "+this.taille()+
                "\nSomme des degrés : "+this.sommeDegre()+"\nType du graphe : "+this.type();
    }
}
