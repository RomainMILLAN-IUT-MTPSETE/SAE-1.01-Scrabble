public class Joueur {
    private String nom;
    private MEE chevalet;
    private int score;

    /**
     * A: Constructeur de la classe
     * @param unNom
     */
    public Joueur(String unNom){
        this.nom = unNom;
        this.chevalet = new MEE(26);
        this.score = 0;
    }

    /**
     * A: Return le nom du joueur
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * A: Retourne le score du joueur
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * A: Retourne le chevalet du joueur.
     * @return
     */
    public MEE getChevalet() {
        return chevalet;
    }

    /**
     * A: Renvoie en String le nom et le score du joueur
     * @return
     */
    public String toString(){
        return "Joueur: " + this.nom + "\n Score: " + this.score;
    }

    /**
     * A: Ajouter au score actuelle un nombre à passer en paramètre
     * @param nb
     */
    public void ajouteScore(int nb){
        this.score += nb;
    }

    /**
     * PR: nbPointsJet indique le nombre de points rapportés par chaque jeton/lettre.
     * R: Le nombre de points total sur le chevalet de ce joueur.
     * S: Bien relire la classe MEE !
     * @param nbPointsJet
     * @return
     */
    public int nbPointsChevalet(int[] nbPointsJet){
        int nbPointsChevalet = 0;

        //On regarde si le chevalet et vide car si il est vide ca sert à rien de faire un calcul.
        if(this.chevalet.estVide() == true){
            nbPointsChevalet = 0;
        }else {
            nbPointsChevalet = this.chevalet.sommeValeurs(nbPointsJet);
        }

        return nbPointsChevalet;
    }

    /**
     * PR: Les éléments de s sont inférieurs à 26
     * A: Simule la prise de nbJetons jetons par this dans le sac s dans la limite de son contenu.
     * @param s
     * @param nbJetons
     */
    public void prendJetons(MEE s, int nbJetons){
        s.tranfereAleat(this.chevalet, nbJetons);
    }

    /**
     * PR: Les éléments de s sont inférieurs à 26.
     * A: Simule le coup de this: this choisit de passer son tour, d'échanger des jetons ou de placer un mot.
     * R: -1 si this a passé son tour, 1 si son chevalet est vide, et 0 sinon.
     * @param p
     * @param s
     * @param nbPointsJet
     * @return
     */
    public int joue(Plateau p, MEE s, int[] nbPointsJet){
        int resultat = 0;

        //Je clear la console (il y a Ut.clearConsole(), cependant je n'arrive pas a le faire fonctionner).
        for(int m=0; m<500; m++){
            System.out.println(" ");
        }

        System.out.println(p.toString());
        System.out.println("\n\n\n" + chevaletString());

        System.out.println("Votre score actuel: " + this.score);
        System.out.println("\n - A votre tour de joueur " + this.nom + ":\n    S pour passer, E pour échanger, P pour placer");
        char selectionJoueur = Ut.saisirCaractere();
        if(selectionJoueur == 'S'){
            resultat = -1;
        }else if(selectionJoueur == 'E'){
            echangeJetons(s);
            resultat = 0;
        }else if(selectionJoueur == 'P'){
            if(joueMot(p, s, nbPointsJet) == true){
                if(this.chevalet.estVide() == true){
                    resultat = 1;
                }else {
                    resultat = 0;
                }
            }
        }

        return resultat;
    }

    /**
     * PR: Les éléments de s sont inférieurs à 26 et nbPointsJet.length >= 26
     * A: Simule le placement d'un mot de this:
     *   a) le mot, sa position sur le plateau et sa direction, sont saisis au clavier.
     *   b)Vérifier si le mot est valide.
     *   c) Si le coup est valide, le mot est placé sur le plateau.
     * R: Vrai si et seulement si ce coup est valide, c'est-à-dire accepté par CapeloDico et satisfaisant les règles détaillées plus haut.
     * S: Utilise la méthode joueMotAux.
     * @param p
     * @param s
     * @param nbPointsJet
     * @return
     */
    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet){
        boolean resultat = false;

        System.out.println(p.toString());

        System.out.println("\n" + chevaletString());


        System.out.print("Veuilliez saisir le mot à placer: "); String mot = Ut.saisirChaine();
        System.out.print("Veuilliez saisir le numéro de Colonne: "); int numCol = Ut.saisirEntier();
        //EXTENSION START
        System.out.print("Veuilliez saisir la lettre de Ligne: "); char letLig = Ut.saisirCaractere();//Je demande à l'utilisateur de renter la lettre de la ligne.
        int numLig = Ut.majToIndex(letLig);//Puis je convertie la lettre en nombre.
        //EXTENSION STOP
        System.out.print("Veuilliez saisir le sens de direction (v: Vertical et h: Horizontal) : "); char sens = Ut.saisirCaractere();


        while(p.placementValide(mot, numLig, numCol, sens, this.chevalet) == false){
            System.out.println("\n\n - Erreur, placement du mot invalide !");
            System.out.print("Veuilliez saisir le mot à placer: "); mot = Ut.saisirChaine();
            System.out.print("Veuilliez saisir le numéro de Colonne: "); numCol = Ut.saisirEntier();
            //EXTENSION START
            System.out.print("Veuilliez saisir la lettre de Ligne: "); letLig = Ut.saisirCaractere();//Je demande à l'utilisateur de renter la lettre de la ligne.
            numLig = Ut.majToIndex(letLig);//Puis je convertie la lettre en nombre.
            //EXTENSION STOP
            System.out.print("Veuilliez saisir le sens de direction (v: Vertical et h: Horizontal) : "); sens = Ut.saisirCaractere();
        }

        joueMotAux(p, s, nbPointsJet, mot, numLig, numCol, sens);
        resultat = true;


        System.out.println(p.toString());
        System.out.println("Votre score: " + this.score);

        return resultat;
    }

    /**
     * PR: sac peut contenir des entiers de 0 à 25.
     * A:simule l'échange de jetons de ce joueur:
     *   - Saisie de la suite de lettres du chevalet ) échanger en vérifiant que la suite soit correcte.
     *   - Echange de jetons entre le chevalet du joueur et le sac.
     * S: Appelle les méthodes estCorrectPourEchange et echangeJetonsAux
     * @param sac
     */
    public void echangeJetons(MEE sac){
        String motChange = "";
        System.out.println("Veuilliez saisir les lettres que vous voulez changer en MAJUSCULE: "); motChange = Ut.saisirChaine();

        while(estCorrectPourEchange(motChange) == false){
            System.out.println("ERREUR: Vous n'avez pas mit toutes les lettres en MAJUSCULE ou vous n'avez pas ces lettres dans votre chevalet.");
            System.out.println("Veuilliez saisir les lettres que vous voulez changer en MAJUSCULE: "); motChange = Ut.saisirChaine();
        };

        echangeJetonsAux(sac, motChange);
    }

    /**
     * R: Vrai si est seulement si les caractères de mot correcpondent tous à des lettre majuscules et l'ensemble de ces caractères est un sous-esemble de jetons du chevalet de this.
     * @param mot
     * @return
     */
    public boolean estCorrectPourEchange(String mot){
        boolean resultat = true;
        int i=0;

        //Ici on regarde que toutes les lettres soient bien en majuscule.
        while(i<mot.length() && resultat == true){
            if(Ut.estUneMajuscule(mot.charAt(i)) == false){
                resultat = false;
            }
            i++;
        }

        i=0;
        //On regarde si le résultat et toujours bon & si on n'as pas dépaser le mot.
        while(resultat == true && i<mot.length()){
            if(this.chevalet.contientAChar(mot.charAt(i)) == true){
                i++;
            }else {
                resultat = false;
            }
        }


        return resultat;
    }

    /**
     * PR: Sac peut contenir des entiers de 0 à 25 et ensJetons est un ensemble de jetons correct pour l'échange.
     * A: Simule l'échange de jetons de ensJetons avec des jetons du sac tirés aléatoirement.
     * @param sac
     * @param ensJetons
     */
    public void echangeJetonsAux(MEE sac, String ensJetons){
        for(int i=0; i<ensJetons.length(); i++){
            int letterChange = Ut.majToIndex(ensJetons.charAt(i));
            //Je transfere aleatoirement une lettre dans le chevalet
            sac.tranfereAleat(this.chevalet, 1);
            //Ensuite je transfere la lettre du chevalet vers le sac.
            this.chevalet.transfere(sac, letterChange);
        }
        if(this.chevalet.getNbTotEx() < 7){
            this.prendJetons(sac, 7 - this.chevalet.getNbTotEx());
        }
    }

    /**
     * PR: CF. joueMot et le placement de mot à partir de la case (numLig, numCol) dans le sens donnée par sens est valide.
     * A: Simule le placement d'un mot de this.
     * @param p
     * @param s
     * @param nbPointsJet
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     */
    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot, int numLig, int numCol, char sens){
        p.place(mot, numLig, numCol, sens, this.chevalet);

        if(this.chevalet.getNbTotEx() < 7){
            this.prendJetons(s, 7 - this.chevalet.getNbTotEx());
        }

        System.out.println(this.score);
        this.ajouteScore(p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet));
        System.out.println(this.score);

        if(mot.equalsIgnoreCase("SCRABBLE")){
            this.ajouteScore(50);
        }
    }

    /**
     * A/R: Fonction qui met en affichage le chevalet et le retourne.
     * @return
     */
    public String chevaletString(){
        String chevalet = "| ";
        int[] tableauChevalet = this.chevalet.getTabFreq();
        for(int i=0; i<tableauChevalet.length; i++){
            if(tableauChevalet[i] !=0){
                for(int k=0; k<tableauChevalet[i]; k++){
                    chevalet += Ut.indexToMaj(i) + " | ";
                }
            }
        }

        return chevalet;
    }
}
