/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.TabExpander;
/**
 *
 * @author Remi
 */
public class Graphe{
    public static final int[][] tabk33 = {{0,0,0,1,1,1},
                                          {0,0,0,1,1,1},
                                          {0,0,0,1,1,1},
                                          {1,1,1,0,0,0},
                                          {1,1,1,0,0,0},
                                          {1,1,1,0,0,0}};
    public static final Matrice k33Mat = new Matrice(tabk33);

    private final int sommets;
    private int aretes;
    public Matrice mat;
    
    Graphe(Matrice matrice){
        this.mat = matrice;
        this.sommets = matrice.lignes();
        this.aretes = 0;
        for(int[] ligne : this.mat.matrice){
            for(int colonne : ligne){
                this.aretes+=colonne;
            }
        }
        if(this.mat.estSymetrique()){
            this.aretes /= 2;
        }
        
        if(matrice.lignes() != matrice.colonnes()){
            throw new IllegalArgumentException("Graphe non valide : colonnes!=lignes");
        }
    }
    
    public static final Graphe K33 = new Graphe(k33Mat);
    
    public int ordre(){
        return this.sommets;
    }
    
    public int taille(){
        return this.aretes;
    }
    
    public String type(){
        int p = this.pGraphe();
        String check = "";
        if(!(this.mat.estSymetrique())){
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
        for (int[] ligne : this.mat.matrice) {
            for (int j = 0; j < this.ordre(); j++) {
                if (ligne[j] > max) {
                    max = ligne[j];
                }
            }
        }
        return max;
    }
    
    public boolean estElementaire(){
        for(int i = 0; i < this.ordre(); i++){
            for(int j = 0; j < this.ordre(); j++){
                if(i == j && this.mat.matrice[i][j] == 1){
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
        if (this.mat.estSymetrique()){
            checkOriente = 1;
        } 
        else{
            checkOriente = 3;
        }
        int[] degres = new int[checkOriente];
        for(int i = 0; i<this.ordre(); i++){
            if (this.mat.matrice[sommet][i] != 0){
                degres[0]+=this.mat.matrice[sommet][i];
                if(degres.length > 1){
                    degres[1]+=this.mat.matrice[sommet][i];
                }
                
            }
            if (this.mat.matrice[i][sommet] != 0){
                if(degres.length > 1){
                    degres[0]+=this.mat.matrice[i][sommet];
                    degres[2]+=this.mat.matrice[i][sommet];
                }
            }
        }
        return degres;
    }
    
    public int sommeDegre(){
        return this.aretes*2;
    }
    /**
     * 
     * @param sommet sommet auquel on récupère les successeurs
     * @return un tableau de int correspondant aux sommets successeurs de sommet
     */
    public int[] suivants(int sommet){
        int checkOriente;
        if (this.mat.estSymetrique()){
            checkOriente = 0;
        } 
        else{
            checkOriente = 1;
        }
        int[] suivants = new int[this.degre(sommet)[checkOriente]];
        int ctr = 0;
        for(int i = 0; i < this.ordre(); i++){
            if(this.mat.matrice[sommet][i] != 0){
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
        if (this.mat.estSymetrique()){
            checkOriente = 0;
        } 
        else{
            checkOriente = 2;
        }
        int[] precedents = new int[this.degre(sommet)[checkOriente]];
        int ctr = 0;
        for(int i = 0; i < this.ordre(); i++){
            if(this.mat.matrice[i][sommet] != 0){
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

    
    
    public boolean estComplet(){
        for(int i = 0; i < this.ordre(); i++){
            for(int j = 0; j < this.ordre(); j++){
                if(i != j && this.mat.matrice[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    } 

    @Override
    public String toString(){
        return "Nombre de sommets : "+this.ordre()+"\nNombre d'arc(s)/arrête(s) : "+this.taille()+
                "\nSomme des degrés : "+this.sommeDegre()+"\nType du graphe : "+this.type();
    }
    
    public int[] welshPowell(){
        if(!(this.mat.estSymetrique()) || !(this.estSimple())){
            //return "Ce graphe est orienté ou non simple";
        }
        int[] SommetsColores = new int[this.sommets];
        int[] Sommets = initWelshPowell();
        int ctr = 1;
        int couleur;
        while (listeNonRempli(SommetsColores)) {
            couleur = ctr;     
            for (int i : Sommets) {
                if(SommetsColores[i] == 0){
                    ArrayList <Integer> tab;
                    tab = this.couleursRelies(i, SommetsColores);
                    if(!(tab.contains(couleur))){ 
                        SommetsColores[i] = couleur;
                    } 
                }
            }
            ctr+=1;
        }
        int max = 0;
        for(int i = 0; i < SommetsColores.length; i++){
            if(max < SommetsColores[i]){
                max = SommetsColores[i];
            }
        }
        return SommetsColores;
    }
    public int maxWelshPowell(){
        int [] SommetsColores = this.welshPowell();
        int max = 0;
        for(int i = 0; i < SommetsColores.length; i++){
            if(max < SommetsColores[i]){
                max = SommetsColores[i];
            }
        }
        return max;
    }
    public int[] initWelshPowell(){
        int[][] NonColores = new int[this.sommets][2];
        for(int i = 0; i < this.sommets; i++){
            NonColores[i][0] = this.degre(i)[0];
            NonColores[i][1] = i;
        }
        NonColores = trier(NonColores,NonColores.length);
        int [] Sommets;
        Sommets = new int[this.sommets];
        for(int i = 0; i < this.sommets; i++){
            Sommets[i] = NonColores[i][1];
        }
        return Sommets;
    }
    public int[][] trier( int tab_arg[][], int nb_case ){
        int i, j; 
        int[] tmp;

        for(i=0; i<=nb_case-2; i++) /* nombre de remontée des bulles */
        {
            for(j=0; j<nb_case-1-i; j++) /* les cases dans ]nb_case-1-i;nb_case-1] sont triées */
            {
                if(tab_arg[j][0] < tab_arg[j+1][0])
                {
                    tmp = tab_arg[j+1];
                    tab_arg[j+1] = tab_arg[j];
                    tab_arg[j] = tmp;
                }
            }
        }
        return tab_arg;
    }
    public static void afficherListe(int[] tab){
        System.out.print("[");
        for(int i = 0; i < tab.length; i++){
            System.out.print(tab[i]);
            if (i + 1 != tab.length){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public boolean listeNonRempli(int[] tab){
        for(int i = 0; i < tab.length; i++){
            if (tab[i] == 0){
                return true;
            }
        }
        return false;
    }
    public int nombreOccurences(int[] tab, int valeur){
        int ctr = 0;
        for(int i = 0;i < tab.length; i++){
            if(tab[i] == valeur){
                ctr+=1;
            }
        }
        return ctr;
    }
    public boolean valeurVerif (int[] tab,int valeur){
        for(int i = 0; i < tab.length; i++){
            if(tab[i] == valeur){
                return true;
            }
        }
        return false;
    }
    public Graphe versComplet(){
        Matrice matComp = new Matrice(new int [this.sommets][this.sommets]);
        Graphe gComplet = new Graphe(matComp);
        for(int i = 0 ; i < gComplet.ordre();i++){
            for(int j = 0 ; j < gComplet.ordre() ; j++){
                if( i != j){
                    gComplet.mat.matrice[i][j] = 1; // on remplit la matrice de 1 sauf pour la diagonale.
                } 
            }
        }
        return gComplet;
    }
    public static Graphe versComplet(int coeff){
        Matrice matComp = new Matrice(new int [coeff][coeff]);
        Graphe gComplet = new Graphe(matComp);
        for(int i = 0 ; i < gComplet.ordre();i++){
            for(int j = 0 ; j < gComplet.ordre() ; j++){
                if( i != j){
                    gComplet.mat.matrice[i][j] = 1; // on remplit la matrice de 1 sauf pour la diagonale.
                } 
            }
        }
        return gComplet;
    }

    public Graphe versComplementaire(){
        Graphe gComplem = new Graphe(this.versComplet().mat.sousMat(this.mat));
        return gComplem;
    }

    public int nbClique(){
        if (!this.estSimple()){
            // System.err.println("[ERROR] - LE GRAPHE N'EST PAS SIMPLE");
            return -1;
        }
        int nbClique= 0;
        int tempNb;
            for (int i=0; i<this.ordre();i++) {
                tempNb = this.clique(i).size();
                if (nbClique < tempNb){
                    nbClique = tempNb;
                }
            }
        return nbClique;
    }
    public ArrayList<Integer> clique(int sommet){
        ArrayList<Integer> clique = new ArrayList<>();
        clique.add(sommet);
        int valid = 0;     
        for(int j = sommet+1 ; j < this.ordre(); j++){
            for(int i = 0; i < clique.size(); i++){
                if (this.verifSuccesseur(clique.get(i), j) && this.verifSuccesseur(j, clique.get(i)) && !(clique.contains(j))){
                    valid++;
                }
            }
            if(valid == clique.size()){
                clique.add(j);
            }
            valid = 0;
        }
        // System.out.println(clique);
        return clique;
    }
    public int nbStable(){
        if (!this.estSimple()){
            System.err.println("[ERROR] - LE GRAPHE N'EST PAS SIMPLE");
            return -1;
        }
        int nbStable= 0;
        int tempNb;
            for (int i=0; i<this.ordre();i++) {
                tempNb = this.stable(i).size();
                if (nbStable < tempNb){
                    nbStable = tempNb;
                }
            }
        return nbStable;
    }
    public ArrayList<Integer> stable(int sommet){
        Graphe gComplem;
        gComplem = this.versComplementaire();
        ArrayList<Integer> stable = new ArrayList<>();
        stable.add(sommet);
        int valid = 0;     
        for(int j = sommet+1 ; j < gComplem.ordre(); j++){
            for(int i = 0; i < stable.size(); i++){
                if (gComplem.verifSuccesseur(stable.get(i), j) && gComplem.verifSuccesseur(j, stable.get(i)) && !(stable.contains(j))){
                    valid++;
                }
            }
            if(valid == stable.size()){
                stable.add(j);
            }
            valid = 0;
        }
        System.out.println(stable);
        return stable;
    }
    public int[][] dsat(){
        int[][] dsatTable;
        dsatTable = this.initDsat();
        // System.out.println(Arrays.deepToString(dsatTable));
        int dsatMax = dsatMax(dsatTable);
        while(listeNonRempli(dsatTable)){
            for(int i=0; i<dsatTable.length; i++){
                if (dsatTable[i][2]==dsatMax && dsatTable[i][1]==0){
                    setColor(i,dsatTable);
                    // System.out.println("couleur set : "+Arrays.deepToString(dsatTable));
                    actuDsat(i, dsatTable);
                    // System.out.println("dsat actu : "+Arrays.deepToString(dsatTable));
                    dsatMax=dsatMax(dsatTable);
                    break;
                }
            }
        }
        // System.out.println("DsatProcess ended successfully");
        return dsatTable;
    }
    public int[][] initDsat(){
        int[][] dsatTable = new int[this.sommets][3];
        for(int i=0; i<dsatTable.length; i++){
            dsatTable[i][0] = i;
            dsatTable[i][1] = 0;
            dsatTable[i][2] = this.degre(i)[0];
        }
        return dsatTable;
    }
    public int degMax(){
        int degMax = 0;
        int tempDeg;
        for(int i = 0; i < this.sommets; i++){
            tempDeg = this.degre(i)[0];
            if(tempDeg > degMax){
                degMax = tempDeg;
            }
        }
        return degMax;
    }
    public int dsatMax(int[][] dTable){
        int dsatMax = 0;
        int tempDeg;
        for (int[] dTable1 : dTable) {
            tempDeg = dTable1[2];
            if(tempDeg > dsatMax){
                dsatMax = tempDeg;
            }
        }
        return dsatMax;
    }
    public boolean listeNonRempli(int[][] tab){
        for (int[] tab1 : tab) {
            if (tab1[1] == 0) {
                return true;
            }
        }
        return false;
    }
    public void setColor(int sommet, int[][] dtable){
        ArrayList couleursRelies;
        couleursRelies = couleursRelies(sommet, dtable);
        for(int couleur = 1; couleur < dtable.length; couleur++){
            if(!(couleursRelies.contains(couleur))){
                dtable[sommet][1]=couleur;
                break;
            }
        }
        
        // System.out.println("couleur set successfull");
    }
    public void actuDsat(int sommet,int[][] dtable){
        dtable[sommet][2]= -1;
        ArrayList comCouleurs;
        for (int i = 0; i < dtable.length; i++){
            comCouleurs = couleursRelies(i, dtable);
            // System.out.println(i+" : "+comCouleurs);

            if(dtable[i][2]!= -1 && !comCouleurs.isEmpty()){
                dtable[i][2] = comCouleurs.size();
            }
        }
        
            // System.out.println("Actualisation dsat de "+i+" à : "+dtable[i][2]);
    }
    public boolean relies(int sommet1, int sommet2) {
        return (this.verifSuccesseur(sommet1, sommet2) && this.verifSuccesseur(sommet2, sommet1));
    }
    public ArrayList<Integer> couleursRelies(int sommet,int[][] dtable){
        ArrayList<Integer> couleurs = new ArrayList<>();
        for(int i = 0; i < dtable.length; i++){
           if(this.relies(sommet, i) && dtable[i][1]!=0 && !(couleurs.contains(dtable[i][1])) ){
                couleurs.add(dtable[i][1]); 
            }
        }
        return couleurs;
    }
    public ArrayList<Integer> couleursRelies(int sommet,int[] dtable){
        ArrayList<Integer> couleurs = new ArrayList<>();
        for(int i = 0; i < dtable.length; i++){
           if(this.relies(sommet, i) && dtable[i]!=0 && !(couleurs.contains(dtable[i])) ){
                couleurs.add(dtable[i]); 
            }
        }
        return couleurs;
    }
    public int maxDsat(){
        int dsatNb = 0;
        int tempNb;
        int[][] dTable = this.dsat();
        for (int[] i : dTable) {
            tempNb = i[1];
            if(tempNb > dsatNb){
                dsatNb = tempNb;
            }
        }
        return dsatNb;
    }    
    public void afficher(){
        for(int i = 0; i < this.mat.lignes(); i++){
            System.out.println(Arrays.toString(this.mat.matrice[i]));
        }
        System.out.println();
        
    }
    public boolean contient(Graphe gTested){
        if ((gTested.estComplet() && this.nbClique() == gTested.ordre()) || (gTested == this)){
            return true;
        }
        // int ctr = 0;
        if(this.ordre()>=gTested.ordre()){
            // System.out.println("this sup to gTested");
            for (int i = 0; i < this.ordre()-gTested.ordre()+1;i++){
                for(int j = 0; j < this.ordre()-gTested.ordre()+1;j++){
                    // ctr++;
                    // System.out.println("ctr :"+ ctr);
                    // Graphe gExt = this.extractGraphe(i, j ,gTested.ordre());
                    // gExt.mat.afficher();
                    // System.out.println(Arrays.deepToString(gExt.mat.matrice)+" -> "+Arrays.deepToString(gTested.mat.matrice));
                    if(Arrays.deepEquals(this.extractGraphe(i, j ,gTested.ordre()).mat.matrice,gTested.mat.matrice)){
                        return true;
                    } 
                }
            }
        }
        
        return false;
    }
    public Graphe extractGraphe(int posDepX,int posDepY,int size){
        int[][] tabExtract = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0;j < size; j++){
                tabExtract[i][j] = this.mat.matrice[i+posDepX][j+posDepY];
            }
        }
        Matrice matExtract = new Matrice(tabExtract);
        Graphe gExtracted = new Graphe(matExtract);
        // gExtracted.mat.afficher();
        return gExtracted;
    }
    public boolean estPlanaire(){
        return ((this.aretes > 3*this.sommets - 6) && !(this.contient(Graphe.versComplet(5))) && !(this.contient(Graphe.K33)));
    }
}
