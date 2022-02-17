/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;
import java.util.Arrays;

import javax.swing.text.TabExpander;
/**
 *
 * @author Rémi
 */
public class Matrice {
    private int colonnes;
    private int lignes;
    public int[][] matrice = new int[lignes][colonnes];
    Matrice(int[][] matrice){
        this.matrice = matrice;
        this.colonnes = matrice[0].length;
        this.lignes = matrice.length;
        int max = this.colonnes;
        for(int i = 0; i < this.lignes; i++){ 
            if(matrice[i].length > max || matrice[i].length < max){
                throw new IllegalArgumentException("Matrice non valide");
            }
        }
    }
    // public static int[][]tabk33 = {{0,0,0,1,1,1},
    //                                 {0,0,0,1,1,1},
    //                                 {0,0,0,1,1,1},
    //                                 {1,1,1,0,0,0},
    //                                 {1,1,1,0,0,0},
    //                                 {1,1,1,0,0,0}};

    // public static Matrice k33Mat = new Matrice(tabk33);
    public int colonnes(){
        return this.colonnes;
    }
    public int lignes(){
        return this.lignes;
    }    
    
    /**
     * 
     *fonction du calcul de produit vectoriel à une position donnée de la matrice résultante.
     * 
     * @param mat
     * @param ligne
     * @param col
     * @return le résultat du produit vectoriel à une position donnée.
     */
    public int multAdd(Matrice mat, int ligne,int col){
        int result = 0;
        for(int i = 0 ; i < this.matrice[ligne].length;i++){
            result += (this.matrice[ligne][i]*mat.matrice[i][col]);// on fait la somme des produits de l'élément i de la ligne de la matrice multipliée par l'élément i de la colone de la matrice multiplicatrice.
        }
        return result;
    }
    /**
     * 
     * @param coeff multiplicateur
     * @return 
     */
    public Matrice multMat(int coeff){
        int[][] matTemp = new int [this.lignes][this.colonnes];
        Matrice matMult = new Matrice(matTemp);
        for (int i= 0; i < matMult.lignes; i ++) {
            for (int j = 0; j< matMult.colonnes; j++) {
                matMult.matrice[i][j] = this.matrice[i][j]*coeff;
            }
        }
        return matMult;
    }
    
    public Matrice multMat(Matrice mat){
        if (this.colonnes() != mat.lignes()){
            return null;
        }
        int[][] matTemp = new int [this.lignes][mat.colonnes];
        Matrice matMult = new Matrice(matTemp);
        for(int i = 0 ; i < matMult.lignes();i++){
            for(int j=  0 ; j< matMult.colonnes();j++){
                matMult.matrice[i][j] = this.multAdd(mat,i,j); //appelle de la méthode de produit vectoriel.
            }
        }
        return matMult;
    }
    public Matrice powMat(int n){
        if (!estCarre()){
            return null;
        }
        int ctr = n;
        Matrice matMult = new Matrice(this.matrice);
        while(ctr != 1){
            matMult = matMult.multMat(this);
            ctr--;
        }
        return matMult;
    }
    public Matrice selfMultMat(){
        if (!estCarre()){
            return null;
        }
        int[][] matTemp = new int [this.lignes][this.colonnes];
        Matrice matMult = new Matrice(matTemp);
        for(int i = 0 ; i < matMult.lignes();i++){
            for(int j=  0 ; j< matMult.colonnes();j++){
                matMult.matrice[i][j] = this.multAdd(this,i,j); //appelle de la méthode de produit vectoriel.
            }
        }
        return matMult;
    }
    public Matrice addMat(Matrice mat){
        if (this.colonnes() != mat.colonnes() && this.lignes() != mat.lignes()){
            return null;
        }
        int[][] matTemp = new int [this.lignes()][this.colonnes()];
        Matrice matSoustrait = new Matrice(matTemp);
        for(int i = 0 ; i < this.lignes();i++){
            for (int j = 0 ; j < this.colonnes();j++){
                matSoustrait.matrice[i][j]= this.matrice[i][j] + mat.matrice[i][j];
            }
        }
        return matSoustrait;
    }
    public Matrice sousMat(Matrice mat){
        if (this.colonnes() != mat.colonnes() && this.lignes() != mat.lignes()){
            return null;
        }
        int[][] matTemp = new int [this.lignes()][this.colonnes()];
        Matrice matSoustrait = new Matrice(matTemp);
        for(int i = 0 ; i < this.lignes();i++){
            for (int j = 0 ; j < this.colonnes();j++){
                matSoustrait.matrice[i][j]= this.matrice[i][j] - mat.matrice[i][j];
            }
        }
        return matSoustrait;
    }
    public void afficher(){
        for(int i = 0; i < this.lignes(); i++){
            System.out.println(Arrays.toString(this.matrice[i]));
        }
        System.out.println();
        
    }
    public boolean estCarre(){
        return this.lignes == this.colonnes;
    }
    
    public boolean estSymetrique(){
        if(!(this.estCarre())){
            return false;
        }
        for(int i = 0; i < this.lignes(); i++){
            for(int j = 0 ; j < this.colonnes(); j++){
                if (this.matrice[i][j] != this.matrice[j][i]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean estVide(){
        for (int i = 0; i < this.lignes(); i++) {
            for (int j = 0; j < this.colonnes(); j++) {
                if (this.matrice[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
