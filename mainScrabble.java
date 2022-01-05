public class mainScrabble {

    public static void main(String[] args){

        //Petit message avant de démarer
        System.out.println("Bienvenue sur 'le jeu de SCRABBLE'\nCe jeu à était développé dans une S.A.E. au cour du B.U.T. Informatique - 1ère année [2021-2022]");

        System.out.print("  - Veuilliez saisir le nombre de joueurs souhaitez (Entre 2 & 4): ");
        int nbJoueurs = Ut.saisirEntier();

        while(nbJoueurs<2 || nbJoueurs>4){//Tant qu'il n'y a pas asser de joueur
            System.out.print("\n\nErreur durant le traitement\n  - Veuilliez saisir le nombre de joueurs souhaitez (Entre 2 & 4): ");
            nbJoueurs=Ut.saisirEntier();
        }


        //On crée la liste des joueurs
        String[] listeJoueurs = new String[nbJoueurs];

        for(int i = 0; i < nbJoueurs; i++){//On fait une boucle pour crée tous les noms de joueur
            Ut.afficher("Nom J" + (i + 1) + " :");
            listeJoueurs[i] = Ut.saisirChaine();
        }

        //On crée le SCRABBLE
        Scrabble leJeuDeScrabble = new Scrabble(listeJoueurs);
        //Et enfin on lance le jeu
        leJeuDeScrabble.partie();
    }

}
