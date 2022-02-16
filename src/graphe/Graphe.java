/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphe;
import java.util.Arrays;
import java.net.ContentHandler;
import java.util.ArrayList;
/**
 *
 * @author Remi
 */
public class Graphe {
    private int sommets;
    private int arretes;
    public int[][] matrice = new int [sommets][sommets];
    
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
    
    public void coloration(){
        if(!(this.estSymetrique()) || !(this.estSimple())){
            //return "Ce graphe est orienté ou non simple";
        }
        int[] SommetsColores = new int[this.sommets];
        int[][] NonColores = new int[this.sommets][2];
        for(int i = 0; i < this.sommets; i++){
            NonColores[i][0] = this.degre(i)[0];
            NonColores[i][1] = i;
        }
        NonColores = trier(NonColores,NonColores.length);
        int [] SommetsNonColores;
        SommetsNonColores = new int[this.sommets];
        for(int i = 0; i < this.sommets; i++){
            SommetsNonColores[i] = NonColores[i][1];
        }
        System.out.println(NonColores[0][1]);
        System.out.println(NonColores[1][1]);
        System.out.println(NonColores[2][1]);
        int ctr = 1;
        int couleur;
        afficherListe(SommetsNonColores);
        while (listeNonRempli(SommetsColores)) {
            couleur = ctr;
            int debut = 0;
            while(SommetsColores[debut] != 0){
                debut++;
            }
            SommetsColores[debut] = couleur;
            //afficherListe(SommetsNonColores);
            /*for (int i = debut; i < SommetsNonColores.length - 1; i++) {
                SommetsNonColores[i] = SommetsNonColores[i + 1];
            }*/
            //System.out.println("Sommets non Colores : ");
            //afficherListe(SommetsNonColores);
            
            int cpt = 0;
            for (int i : SommetsNonColores) {
                if(SommetsColores[i] == 0){
                    for (int j : SommetsColores) {
                        if(j == couleur && !(valeurVerif(this.suivants(i),j))){//ne prend pas en compte la coloration en direct
                            cpt++;
                            //SommetsNonColores
                        }
                    }
                    if (cpt == nombreOccurences(SommetsColores, couleur)){
                        SommetsColores[i] = couleur; 
                    }
                }
            }
            ctr+=1;
            System.out.println("Sommets Colores : ");
            afficherListe(SommetsColores);
        }
        //afficherListe(SommetsColores);
        
    }
    public int[][] trier( int tab_arg[][], int nb_case )
{
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
    public int[][] supprOccurence(int[][] tab, int valeur){
        for(int i = 0; i < tab.length; i++){
            if(tab[i][1] == valeur){
                
            }
        }
        return tab;
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
        gComplem = this.versComplet().sousMat(this);
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
                gMult.matrice[i][j] = this.multAdd(graphe,i,j); //appelle de la méthode de produit vectoriel.
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
            result += (this.matrice[ligne][i]*graphe.matrice[i][col]);// on fait la somme des produits de l'élément i de la ligne de la matrice multipliée par l'élément i de la colone de la matrice multiplicatrice.
        }
        return result;
    }
    public int nbClique(){
        if (!this.estSimple()){
            System.err.println("[ERROR] - LE GRAPHE N'EST PAS SIMPLE");
            return -1;
        }
        int nbClique= 0;
        int tempNb = 0;
            for (int i=0; i<this.matrice.length;i++) {
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
        for(int j = sommet+1 ; j < this.matrice.length; j++){
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
        System.out.println(clique);
        return clique;
    }
    public int nbStable(){
        if (!this.estSimple()){
            System.err.println("[ERROR] - LE GRAPHE N'EST PAS SIMPLE");
            return -1;
        }
        int nbStable= 0;
        int tempNb = 0;
            for (int i=0; i<this.matrice.length;i++) {
                tempNb = this.stable(i).size();
                if (nbStable < tempNb){
                    nbStable = tempNb;
                }
            }
        return nbStable;
    }
    public ArrayList<Integer> stable(int sommet){
        int[][] matComplem = new int [this.sommets][this.sommets];
        Graphe gComplem = new Graphe(matComplem);
        gComplem = this.versComplementaire();
        ArrayList<Integer> stable = new ArrayList<>();
        stable.add(sommet);
        int valid = 0;     
        for(int j = sommet+1 ; j < gComplem.matrice.length; j++){
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
        int[][] dsatTable = new int[this.sommets][3];
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
        for(int i = 0; i < dTable.length; i++){
            tempDeg = dTable[i][2];
            if(tempDeg > dsatMax){
                dsatMax = tempDeg;
            }
        }
        return dsatMax;
    }
    public boolean listeNonRempli(int[][] tab){
        for(int i = 0; i < tab.length; i++){
            if (tab[i][1] == 0){
                return true;
            }
        }
        return false;
    }
    public void setColor(int sommet, int[][] dtable){
        ArrayList couleursRelies = new ArrayList();
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
        ArrayList comCouleurs = new ArrayList<>();
        boolean actu = false;
        for (int i = 0; i < dtable.length; i++){
            comCouleurs = couleursRelies(i, dtable);
            // System.out.println(i+" : "+comCouleurs);

            if(dtable[i][2]!= -1 && comCouleurs.size()>0){
                dtable[i][2] = comCouleurs.size();
            }
        }
        
            // System.out.println("Actualisation dsat de "+i+" à : "+dtable[i][2]);
    }
    public boolean relies(int sommet1, int sommet2) {
        return (this.verifSuccesseur(sommet1, sommet2) && this.verifSuccesseur(sommet2, sommet1));
    }
    public ArrayList couleursRelies(int sommet,int[][] dtable){
        ArrayList couleurs = new ArrayList<>();
        for(int i = 0; i < dtable.length; i++){
           if(this.relies(sommet, i) && dtable[i][1]!=0 && !(couleurs.contains(dtable[i][1])) ){
                couleurs.add(dtable[i][1]); 
            }
        }
        return couleurs;
    }
    public int dsatNbColoration(int[][] dTable){
        int dsatNb = 0;
        int tempNb;
        for(int i = 0; i < dTable.length; i++){
            tempNb = dTable[i][1];
            if(tempNb > dsatNb){
                dsatNb = tempNb;
            }
        }
        return dsatNb;
    }
}
