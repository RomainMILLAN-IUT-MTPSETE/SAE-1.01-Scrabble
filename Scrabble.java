public class Scrabble {
    private Joueur[] joueurs;
    private int numJoueurs; //Joueur courant (entre 0 et joueur.length-1);
    private Plateau plateau;
    private MEE sac;
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};

    public Scrabble(String[] listeJoueurs){
                   //A.B.C.D.E.F.G.H.I.J.K.L.M.N.O.P.Q.R.S.T.U.V.W.X.Y.Z
        int[] sacTab = {5,2,6,2,3,4,1,4,2,3,4,1,2,1,1,7,8,1,5,4,2,4,1,4,4,3};
        this.plateau = new Plateau();
        this.sac = new MEE(sacTab);

        //A REVOIR
        //On initialize le joueurs car sinon il y a une erreur.
        this.joueurs = new Joueur[listeJoueurs.length];
        //Puis on remplis la liste.
        for(int i=0; i<listeJoueurs.length; i++){
            this.joueurs[i] = new Joueur(listeJoueurs[i]);
        }
    }

    /**
     * A:
     *   1) La distribution initiale des jetons aux joueurs.
     *   2) Des itérations sur les différents tours de jeu jusqu'à la fin de la partie.
     *   3) Le calcul des scores finaux.
     *   4) L'affichage du ou des gagnants.
     */
    public void partie(){
        boolean arretPartie = false;
        int passePartie = 0;

        //1) La distribution initiale des jetons aux joueurs.
        for(int i=0; i<joueurs.length; i++){
            while(joueurs[i].getChevalet().getNbTotEx() < 7){
                joueurs[i].prendJetons(this.sac, 1);

            }
        }

        //On détermine qui commence:
        this.numJoueurs = Ut.randomMinMax(0, joueurs.length-1);

        //Si on a pas arreter la partie alors:
        while(arretPartie == false){
            int actionJouer = joueurs[this.numJoueurs].joue(this.plateau, this.sac, this.nbPointsJeton);

            //Le joueur passe son tour
            if(actionJouer == -1){
                passePartie++;

                //Si tous les joueurs on passer leur tour.
                if(passePartie == joueurs.length){
                    //J'arrete la partie
                    arretPartie = true;

                    //Et je retire les points de leurs chevalet à leurs score.
                    for(int k=0; k<joueurs.length; k++){
                        int lastPoints = joueurs[k].nbPointsChevalet(nbPointsJeton);
                        joueurs[k].ajouteScore(-(lastPoints));
                    }
                }
                //Le joueur n'as plus de jetons dans son chevalet.
            }else if(actionJouer == 1){
                arretPartie = true;
                int pointsRestantAutreChevalet = 0;

                //On calcule le nombre de points restant sur le chevalet des autres joueurs
                for(int i=0; i<joueurs.length; i++){
                    //On rajoute pour connaitre le nombre total de points sur le chevalet des autres joueurs.
                    pointsRestantAutreChevalet += joueurs[i].nbPointsChevalet(nbPointsJeton);
                    //On supprime le nombre de point du chevalet du joueur à son score total.
                    joueurs[i].ajouteScore(-(joueurs[i].nbPointsChevalet(nbPointsJeton)));
                }
                joueurs[this.numJoueurs].ajouteScore(pointsRestantAutreChevalet);
            }

            if(this.numJoueurs == joueurs.length-1 && arretPartie == false){
                this.numJoueurs = 0;
                passePartie = 0;
            }else {
                if(arretPartie == false){
                    this.numJoueurs ++;
                }
            }
        }

        System.out.println();
    }

    public String afficheVainqueur(){
        int[] vainqueursJoueurs = new int[joueurs.length];
        int joueurVainqueur = 0;
        String resultat = "";

        for(int i=1; i<joueurs.length; i++){
            if(joueurs[i].getScore() > joueurs[joueurVainqueur].getScore()){
                joueurVainqueur = i;
            }
        }


    }
}