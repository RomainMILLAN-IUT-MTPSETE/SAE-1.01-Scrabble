import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Scrabble {
    private Joueur[] joueurs;
    private int numJoueurs; //Joueur courant (entre 0 et joueur.length-1);
    private Plateau plateau;
    private MEE sac;
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};

    public Scrabble(String[] listeJoueurs){
                      //A.B.C.D.E. F.G.H.I.J.K.L.M.N.O.P.Q.R.S.T.U.V.W.X.Y.Z
        int[] sacTab = {9,2,2,3,15,2,2,2,8,1,1,5,3,6,6,2,1,6,6,6,6,2,1,1,1,1};
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
        //EXTENSION START
        String[][] dico = new String[26][50000];//crée un tableau contenant les mot valide chaque ligne tu tableau correspondant à une lettre
        int i = 0;
        File file = new File("dicoReference.txt");//permet d'importer le fichier texte en le mettant dans une variable de type File
        BufferedReader bufferedReader = null;

        try{
            FileReader fileReader = new FileReader(file);//lance la lecture du fichier
            bufferedReader = new BufferedReader(fileReader);//Lit le fichier ligne par ligne plutôt que charactère par charactère
            String line;

            while((line = bufferedReader.readLine())!=null){
               dico[Ut.majToIndex(line.charAt(0))][i] = line;//met la ligne dans la case de dico
               i++;
            }
        } catch (FileNotFoundException e){//permet d'éviter que le programme ne se lance pas si le fichier n'existe pas
            System.err.printf("Le fichier %s n'a pas été trouvé", file.toString());
        } catch (IOException e){//permet d'éviter que le programme ne se lance pas si le fichier n'a pas de contenu
            System.err.printf("Impossible de lire le contenu du fichier " + file.toString());
        }
        try{
            bufferedReader.close();//arrête la lecture du fichier
        } catch (IOException e){//permet d'éviter que le programme ne se lance pas si il est impossible de fermer le fichier
            System.err.println("Impossible de fermer le fichier " + file.toString());
        } catch (NullPointerException e){//permet d'éviter l'erreur NullPointerException dans le cas ou le fichier n'est pas lu et que bufferedReader reste à null
            System.err.println("Impossible d'ouvrir le fichier " + file.toString());
        }
        //EXTENSION STOP
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

        System.out.println(afficheVainqueur());
    }

    /**
     * A/R: Fonction qui retourne en String, les résultats des joueurs du scrabble.
     * @return
     */
    public String afficheVainqueur(){
        String resultatVainqueurs = "";
        int idJoueursVainqueur = 0;
        int[] vainqueursJoueurs = new int[joueurs.length];

        //On fait une boucle qui vas regarder le score de tous les joueur en commencant à 1 car on se dit que le 0 et le premier vainqueur.
        for(int i = 1; i<joueurs.length; i++) {
            if (joueurs[idJoueursVainqueur].getScore() == joueurs[i].getScore()) {
                vainqueursJoueurs[i] = i;
            }else if(joueurs[i].getScore() > joueurs[idJoueursVainqueur].getScore()){
                idJoueursVainqueur = i;
            }
        }

        if(idJoueursVainqueur == vainqueursJoueurs[1] && vainqueursJoueurs[1] != 0) {
            resultatVainqueurs += "Ex-aequo: " + joueurs[idJoueursVainqueur].toString() + " et ";
            for(int i=1; i<vainqueursJoueurs.length; i++) {
                if(vainqueursJoueurs[i] != 0 && joueurs[vainqueursJoueurs[i]].getScore() == joueurs[idJoueursVainqueur].getScore()) {
                    resultatVainqueurs += joueurs[vainqueursJoueurs[i]].getNom() + ", ";
                }
            }
        }else {
            resultatVainqueurs += "\n\nLe vainqueur est: " + joueurs[idJoueursVainqueur].getNom();
        }




        return resultatVainqueurs;
    }
}
