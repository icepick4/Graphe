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
        if (this.estSimple()){
            return "Ce graphe est simple";
        }
        else if(this.estElementaire()){
            return "Ce graphe est un "+p+"-graphe élémentaire";
        }
        return "Ce graphe est un "+p+"graphe";
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
                if(i != j && this.matrice[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    } 
    
    public String toString(){
        return "Nombre de sommets : "+this.ordre()+"\nNombre d'arc(s)/arrête(s) : "+this.taille()+
                "\nSomme des degrés : "+this.sommeDegre()+"\nType du graphe : "+this.type();
    }
    public Graphe versComplet(){
        int[][] matComp = new int [this.sommets][this.sommets];
        Graphe gComplet = new Graphe(matComp);
        for(int i = 0 ; i < gComplet.matrice.length;i++){
            for(int j = 0 ; j < gComplet.matrice.length ; j++){
                if( i != j){
                    gComplet.matrice[i][j] = 1; // on remplit la matrice de 1 sauf pour la diagonale.
                } 
            }
        }
        return gComplet;
    }
    public Graphe versComplementaire(){
        int[][] matComplem = new int [this.sommets][this.sommets];
        Graphe gComplem = new Graphe(matComplem);
        gComplem = this.sousMat(this.versComplet());
        return gComplem;
    }

    public Graphe sousMat(Graphe graphe){
        if (this.ordre() != graphe.ordre()){
            return null;
        }
        int[][] matTemp = new int [this.sommets][this.sommets];
        Graphe gSoustrait = new Graphe(matTemp);
        for(int i = 0 ; i < this.matrice.length;i++){
            for (int j = 0 ; j < this.matrice.length;j++){
                gSoustrait.matrice[i][j]= this.matrice[i][j] - graphe.matrice[i][j];
            }
        }
        return gSoustrait;
    }
    public Graphe addMat(Graphe graphe){
        if (this.ordre() != graphe.ordre()){
            return null;
        }
        int[][] matTemp = new int [this.sommets][this.sommets];
        Graphe gSoustrait = new Graphe(matTemp);
        for(int i = 0 ; i < this.matrice.length;i++){
            for (int j = 0 ; j < this.matrice.length;j++){
                gSoustrait.matrice[i][j]= this.matrice[i][j] + graphe.matrice[i][j];
            }
        }
        return gSoustrait;
    }
    public Graphe multMat(Graphe graphe){
        if (this.ordre() != graphe.ordre() || this.matrice[0].length != graphe.matrice.length){
            return null;
        }
        int[][] matTemp = new int [this.sommets][graphe.matrice[0].length];
        Graphe gMult = new Graphe(matTemp);
        for(int i = 0 ; i < gMult.matrice.length;i++){
            for(int j=  0 ; j< gMult.matrice[0].length;j++){
                gMult.matrice[i][j] = this.multAdd(graphe,i,j);
            }
        }
        return gMult;
    }
    public Graphe multMat(int coeff){
        int[][] matTemp = new int [this.sommets][this.sommets];
        Graphe gMult = new Graphe(matTemp);
        for(int i = 0 ; i < gMult.matrice.length;i++){
            for(int j=  0 ; j< gMult.matrice[0].length;j++){
                gMult.matrice[i][j] *= coeff;
            }
        }
        return gMult;
        }
    /**
     * 
     *fonction du calcul de produit vectoriel à une position donnée de la matrice résultante.
     * 
     * @param graphe
     * @param ligne
     * @param col
     * @return le résultat du produit vectoriel à une position donnée.
     */
    public int multAdd(Graphe graphe, int ligne,int col){
        int result = 0;
        for(int i = 0 ; i < this.matrice[ligne].length;i++){
            result += (this.matrice[ligne][i]*graphe.matrice[i][col]);
        }
        return result;
    }
}
