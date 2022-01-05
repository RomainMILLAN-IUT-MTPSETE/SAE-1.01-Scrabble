public class Test {
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
    public static void main(String[] args){
        Plateau jusTest = new Plateau();
                   //A.B.C.D.E.F.G.H.I.J.K.L.M.N.O.P.Q.R.S.T.U.V.W.X.Y.Z
        int[] tab = {3,2,1,0,2,4,1,1,2,5,0,1,2,1,1,3,8,1,3,4,2,4,0,0,0,0};
        /*MEE chevalet = new MEE(tab);

        Joueur j1 = new Joueur("Steve");
        Joueur j2 = new Joueur("Claire");

        System.out.println("Résultat: " + j1.joue(jusTest, chevalet, nbPointsJeton));*/
        String[] joueurs = {"Steve", "Claire"};
        Scrabble jeu1 = new Scrabble(joueurs);

        jeu1.partie();

    }

}