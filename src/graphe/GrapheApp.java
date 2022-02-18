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
        
        int [][] matriceColoration = {
                                    {0,1,0,0,0,1},
                                    {1,0,1,0,0,0},
                                    {0,1,0,1,0,0},
                                    {0,0,1,0,1,0},
                                    {0,0,0,1,0,1},
                                    {1,0,0,0,1,0}
                                                };
        int [][] matriceStabExemple = {
                            {0,1,0,1,0,0},
                            {1,0,1,0,1,0},
                            {0,1,0,1,0,1},
                            {1,0,1,0,0,0},
                            {0,1,0,0,0,1},
                            {0,0,1,0,1,0}
                           };
        int [][] tab1  = {
                            {0,1,1,1,1,0,0,0},
                            {1,0,1,1,0,1,0,0},
                            {1,1,0,0,0,0,0,0},
                            {1,1,0,0,0,0,0,0},
                            {1,0,0,0,0,1,1,1},
                            {0,1,0,0,0,1,1,1},
                            {0,0,0,0,1,1,1,1},
                            {0,0,0,0,1,1,0,1}
                            };
        int [][] cube3d  = {
                            {0,1,1,0,0,0,1,0},
                            {1,0,0,1,0,0,0,1},
                            {1,0,0,1,1,0,0,0},
                            {0,1,1,0,0,1,0,0},
                            {0,0,1,0,0,1,1,0},
                            {0,0,0,1,1,0,0,1},
                            {1,0,0,0,1,0,0,1},
                            {0,1,0,0,1,1,0,0}
                            };
        int [][] tab = {
                           {0,1,1,1,0,0},
                           {1,0,1,1,0,0},
                           {1,1,0,1,1,1},
                           {1,1,1,0,1,0},
                           {0,0,1,1,0,1},
                           {0,0,1,0,1,0}
                          };
        int [][] tab2 = {
                           {0,1,0,0,0,1},
                           {1,0,1,0,0,0},
                           {0,1,0,1,0,0},
                           {0,0,1,0,1,0},
                           {0,0,0,1,0,1},
                           {1,0,0,0,1,0}
                          };
        
        int [][] matriceMultiplication = {
            {0,1,1,0,0},
            {0,0,1,0,1},
            {0,0,0,1,1},
            {0,1,1,0,1},
            {1,1,1,0,0}
        };
        int [][] matriceMultiplication2 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        Matrice matrice = new Matrice(matriceColoration);
        // Matrice matrice2 = new Matrice(matriceMultiplication2);
        Graphe g1 = new Graphe(matrice);
        // Graphe g2 = new Graphe(matrice2);
        // System.out.println(g1.contient(g1));
        // System.out.println(g1.contient(g2));
        // System.out.println(g2.contient(g1));
        // Matrice matrice2 = matrice.selfMultMat();
        // Matrice matrice3 = matrice.powMat(3);
        // matrice.afficher();
        // matrice2.afficher();
        // matrice3.afficher();
        // System.out.println(Graphe.K33.taille());
        // System.out.println(g1.estPlanaire());
        System.out.println(Arrays.toString(Graphe.versComplet(5).encadrementChromatique()));
        
        
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
    
}
