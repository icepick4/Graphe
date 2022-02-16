/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graphe;
import java.util.Arrays;

/**
 *
 * @author Remi
 */
public class GrapheApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int [][] matrice = {
                           {0,1,1,1,0,0},
                           {1,0,1,1,0,0},
                           {1,1,0,1,1,1},
                           {1,1,1,0,1,0},
                           {0,0,1,1,0,1},
                           {0,0,1,0,1,0}
                          };
        int [][] matriceStabExemple = {
                            {0,1,0,1,0,0},
                            {1,0,1,0,1,0},
                            {0,1,0,1,0,1},
                            {1,0,1,0,0,0},
                            {0,1,0,0,0,1},
                            {0,0,1,0,1,0}
                           };
        int [][] matriceDsatExemple = {
                            {0,1,1,1,1,0,0,0},
                            {1,0,1,1,0,1,0,0},
                            {1,1,0,0,0,0,0,0},
                            {1,1,0,0,0,0,0,0},
                            {1,0,0,0,0,0,1,1},
                            {0,1,0,0,0,0,1,1},
                            {0,0,0,0,1,1,0,1},
                            {0,0,0,0,1,1,1,0},
                            };
        int [][] matrice1 = {
                            {0,0,1},
                            {0,0,1},
                            {1,1,0}
                            };
        int [][] matrice2 = {
                           {0,1,0,0,0,1},
                           {1,0,1,0,0,0},
                           {0,1,0,1,0,0},
                           {0,0,1,0,1,0},
                           {0,0,0,1,0,1},
                           {1,0,0,0,1,0}
                          };
        int [][] matriceColoration = {
            {0,1,1,0},
            {1,0,1,0},
            {1,1,0,1},
            {0,0,1,0}
        };
        Graphe graphe1 = new Graphe(matrice2);
        graphe1.WelshPowell();
        // System.out.println(Arrays.deepToString(graphe1.matrice));
        // System.out.println(graphe1.estComplet());
        // System.out.println(Arrays.deepToString(graphe1.versComplet().matrice));
        // System.out.println(graphe1.versComplet().estComplet());
        // System.out.println(Arrays.deepToString(graphe1.versComplementaire().matrice));
        // System.out.println(graphe1.versComplet().estComplet());
        // System.out.print(graphe1.degMax());
        //System.out.println(Arrays.deepToString(graphe1.dsat()));
        //System.out.println(graphe1.dsatNbColoration(graphe1.dsat()));
        /*afficherListe(graphe1.degre(2));
        afficherListe(graphe1.suivants(0));
        afficherListe(graphe1.precedents(0));
        afficherListeDegres(graphe1);
        System.out.println(graphe1); */
        // afficherListe(graphe1.suivants(0));
        // afficherListe(graphe1.precedents(0));
        // System.out.println(graphe1);
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
    public static void afficherListeDegres(Graphe graphe){
        System.out.print("              Sommet : ");
        for(int i = 0; i < graphe.ordre(); i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i = 0; i < 3; i++){
            switch (i) {
                case 0 -> {
                    System.out.print("               Degré : ");
                    for(int j = 0; j < graphe.ordre(); j++){
                        System.out.print(graphe.degre(j)[0]+" ");
                    }
                    System.out.println();
                }
                case 1 -> {
                    System.out.print("Demi-degré extérieur : ");
                    for(int j = 0; j < graphe.ordre(); j++){
                        System.out.print(graphe.degre(j)[1]+" ");
                    }
                    System.out.println();
                }
                default -> {
                        System.out.print("Demi-degré intérieur : ");
                        for(int j = 0; j < graphe.ordre(); j++){
                                System.out.print(graphe.degre(j)[2]+" ");
                                }
                        System.out.println();
                }
            } 
        }
    }
}
