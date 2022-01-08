public class Plateau {

    /**
     * Gris (rien): 1
     * Bleu clair (lettre double): 2
     * Bleu foncé (lettre triple): 3
     * Rose (mot double): 4
     * Rouge (mot triple): 5
     */
    private Case[][] g = new Case[15][15];

    /**
     * Constructeur default du plateau, à partir de la matrice standard selon les règles.
     */
    public Plateau() {
        int[][] plateau = { { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 } };

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                this.g[i][j] = new Case(plateau[i][j]);
            }
        }
    }

    /**
     * Constructeur de plateau à partir d'une grille passée en paramètre
     */
    public Plateau(Case[][] plateau) {
        this.g = plateau;
    }

    public String toString(){
        //On définit les premières lignes de l'affichage
        String resPlateauAfficher = "   |0  |1  |2  |3  |4  |5  |6  |7  |8  |9  |10 |11 |12 |13 |14 \n";
        //Puis la première lettre
        char colonne = 'A';

        //Maintenant on vas parcourir le tableau de case et afficher les différentes cases.
        for(int i=0; i<g.length; i++){
            //On ajoute la lettre de la ligne
            //Si le numero de la colonne est inferieur à 10
            if(colonne < 10){
                resPlateauAfficher += " " + colonne + " |";
            }else {//Sinon
                resPlateauAfficher += " " + colonne + "|";
            }


            for(int j=0; j<g[0].length; j++){
                //On regarde si la case est recourverte
                if(g[i][j].estRecouverte() == false){
                    if(g[i][j].getCouleur() == 1){
                        resPlateauAfficher += "   |";
                    }else {
                        resPlateauAfficher += " " + g[i][j].getCouleur() + " |";
                    }
                }else {
                    resPlateauAfficher += " " + g[i][j].getLettre() + " |";
                }
            }
            resPlateauAfficher += "\n";
            colonne++;
        }

        //On retourne le plateau
        return resPlateauAfficher;
    }


    /**
     * PR:mot est un mot accepté par CapeloDico, 0 <= numLig <= 14, 0 <= numCol <= 14, sens est un élément de {’h’,’v’} et l’entier maximum prévu pour e est au moins 25
     * R: retourne vrai ssi le placement de mot sur this à partir de la case (numLig, numCol) dans le sens donné par sens à l’aide des jetons de e est valide.
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @param e
     * @return
     */
    public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e){
        boolean resultatPlacementValide = false;

        if(0 <= numLig && numLig <= 14 && 0 <= numCol && numCol <= 14){
            //Si le plateau est non vide
            if(g[7][7].estRecouverte() == true)/*Le plateau n'est pas vide*/{
                if(sens == 'h'){
                    if(numCol + mot.length()-1 <= 14){
                        if(numCol == 0 || g[numLig][numCol-1].estRecouverte() == false){
                            if(numCol + mot.length()-1 == 14 || g[numLig][numCol + mot.length()].estRecouverte() == false){
                                if(checkCase(mot, numLig, numCol, sens) == true){
                                    if(checkChevalet(motWithoutCase(mot, numLig, numCol, sens), e) == true){
                                        if(capeloDico(mot) == true){
                                            resultatPlacementValide = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else if(sens == 'v'){
                    if(numLig + mot.length()-1 <= 14){
                        if(numLig == 0 || g[numLig-1][numCol].estRecouverte() == false){
                            if(numLig + mot.length()-1 == 14 || g[numLig + mot.length()][numCol].estRecouverte() == false){
                                if(checkCase(mot, numLig, numCol, sens) == true){
                                    if(checkChevalet(motWithoutCase(mot, numLig, numCol, sens), e) == true){
                                        if(capeloDico(mot) == true){
                                            resultatPlacementValide = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }else/*Le plateau et vide*/ {
                if(mot.length() >= 2){
                    boolean contientCenter = false;
                    int i =0;
                    //On vérifie si le sens est sur horizontale alors il faut que se soit sur la colonne n°7 pour passer au millieu.
                    if (sens == 'h' && numLig == 7) {
                        //On fait la vérification du passement au millieu.
                        while(contientCenter == false && i < mot.length()-1){
                            if(numCol + i == 7){
                                contientCenter = true;
                            }
                            i++;
                        }

                        //Maintenant on sais que le mot passe bien par le millieu.
                        if(contientCenter == true){
                            if(checkChevalet(mot, e) == true){
                                if(capeloDico(mot) == true){
                                    resultatPlacementValide = true;
                                }
                            }
                        }
                    }else if(sens == 'v' && numCol == 7){
                        //On fait la vérification du passement au millieu.
                        while(contientCenter == false && i < mot.length()-1){
                            if(numLig + i == 7){
                                contientCenter = true;
                            }
                            i++;
                        }

                        //Maintenant on sais que le mot passe bien par le millieu.
                        if(contientCenter == true){
                            if(checkChevalet(mot, e) == true){
                                if(capeloDico(mot) == true){
                                    resultatPlacementValide = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return resultatPlacementValide;
    }

    public boolean capeloDico(String mot){
        boolean resultat = false;
        char reponsePlayer;

        System.out.println(" CapeloDico - Veuilliez indiquez si le mot et valide et si il est en MAJUSCULE (v: valide, n: non-valide): "); reponsePlayer = Ut.saisirCaractere();

        while (reponsePlayer != 'v' && reponsePlayer != 'n'){
            System.out.println(" CapeloDico - Veuilliez indiquez si le mot et valide et si il est en MAJUSCULE (v: valide, n: non-valide): "); reponsePlayer = Ut.saisirCaractere();
        }

        if(reponsePlayer == 'v'){
            resultat = true;
        }

        return resultat;
    }


    /**
     * Note: Fonction qui permet de regarder si toutes les lettre d'un mot sont dans un MEE
     * @param mot
     * @param e
     * @return
     */
    public boolean checkChevalet(String mot, MEE e){
        boolean resultatDansChevalet = true;
        int positionMot = 0;
        int[] chevalet = new int[e.getTabFreq().length];

        for(int i=0; i<e.getTabFreq().length; i++){
            chevalet[i] = e.getTabFreq()[i];
        }

        while (resultatDansChevalet == true && positionMot < mot.length()) {
            // Si la lettre du mot saisi représentée par son indice dans le tabFreq du
            // chevalet à au moins 1 exemplaire
            if (chevalet[Ut.majToIndex(mot.charAt(positionMot))] != 0) {
                chevalet[Ut.majToIndex(mot.charAt(positionMot))]--;
                positionMot++;
            } else {
                resultatDansChevalet = false;
            }
        }


        return resultatDansChevalet;
    }


    /**
     * Notes: Cette fonction permet de regarder si un mot par rapport à une ligne et une colonne et un ses et un MEE, on au moins une case vide une case pleine et dans les cases pleine les lettres sont egaux
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @return
     */
    public boolean checkCase(String mot, int numLig, int numCol, char sens) {
        boolean resultat = false;

        //On check si il y a au moin 1 case non vide
        boolean resultatCheckVide = false;
        if(sens == 'h'){
            for(int i=numCol; i<numCol + mot.length()-1; i++){
                if(g[numLig][i].estRecouverte() == false){
                    resultatCheckVide = true;
                }
            }
        }else if(sens == 'v'){
            for(int j=numLig; j<numLig + mot.length()-1; j++){
                if(g[j][numCol].estRecouverte() == false){
                    resultatCheckVide = true;
                }
            }
        }

        //On check si il y a au moins une case de remplie et on check que la lettre correspondant à la case est bien pareil que la lettre du mot.
        boolean resultatCheckNonVide = false;
        boolean resultatCheckMotCaseLetter = true;
        int indexMot = 0;
        if(sens == 'h'){
            indexMot = 0;
            for(int i=numCol; i<numCol + mot.length()-1; i++){
                int test = numCol+i;
                if(g[numLig][i].estRecouverte() == true){
                    resultatCheckNonVide = true;

                    char letterCase = g[numLig][i].getLettre();
                    char letterMotCase = mot.charAt(indexMot);

                    if(letterCase == letterMotCase){
                        resultatCheckMotCaseLetter = true;
                    }else {
                        resultatCheckMotCaseLetter = false;
                    }
                }

                indexMot++;
            }
        }else if(sens == 'v'){
            indexMot = 0;
            for(int k=numLig; k<numLig + mot.length()-1; k++){
                if(g[k][numCol].estRecouverte() == true){
                    resultatCheckNonVide = true;

                    char letterCase = g[k][numCol].getLettre();
                    char letterMotCase = mot.charAt(indexMot);

                    if(letterCase == letterMotCase){
                        resultatCheckMotCaseLetter = true;
                    }else {
                        resultatCheckMotCaseLetter = false;
                    }
                }
                indexMot++;
            }
        }

        if(resultatCheckVide == true && resultatCheckNonVide == true && resultatCheckMotCaseLetter == true){
            resultat = true;
        }

        return resultat;
    }


    /**
     * Fonction qui permet de connaitre le mot sans les lettres déjà présentent dans le plateau.
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @return
     */
    public String motWithoutCase(String mot, int numLig, int numCol, char sens){
        StringBuilder motWithoutCase = new StringBuilder(mot);

        if(sens == 'h'){
            for(int i=0; i<mot.length(); i++){
                if(g[numLig][numCol + i].estRecouverte() == true){
                    motWithoutCase.deleteCharAt(i);
                }
            }
        }else if(sens == 'v'){
            for(int i=0; i<mot.length(); i++){
                if(g[numLig + i][numCol].estRecouverte() == true){
                    motWithoutCase.deleteCharAt(i);
                }
            }
        }
        String motResultat = motWithoutCase.toString();

        return motResultat;
    }

    /**
     * PR: le placement de mot sur this à partir de la case (numLig, numCol) dans le sens donné par sens est valide
     * R: retourne le nombre de points rapportés par ce placement, le nombre de points de chaque jeton étant donné par le tableau nbPointsJet.
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @param nbPointsJet
     * @return
     */
    public int nbPointsPlacement(String mot, int numLig, int numCol, char sens, int[] nbPointsJet){
        int nbPointsFinal = 0;
        int multiplicateurMot = 1;
        switch (sens) {
            case 'v':
                for (int i = 0; i < mot.length(); i++) {
                    int indexPointsJet = Ut.majToIndex(mot.charAt(i));
                    if(g[numCol][numLig].getCouleur()==4 || g[numCol][numLig].getCouleur()==5){ //Mot compte Double Triple
                        switch (g[numCol][numLig].getCouleur()){
                            case 4:
                                nbPointsFinal += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 2;
                                break;

                            case 5:
                                nbPointsFinal += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 3;
                                break;

                        }

                    }
                    else
                    {
                        nbPointsFinal += nbPointsJet[indexPointsJet] * g[numCol][numLig].getCouleur();} // Je multiplie la valeur
                    // score de la lettre par
                    // le code couleur de la
                    // case sous-jacente.
                    numLig++;
                }
                break;
            case 'h':
                for (int i = 0; i < mot.length(); i++) {
                    int indexPointsJet = Ut.majToIndex(mot.charAt(i));
                    if(g[numCol][numLig].getCouleur()==4 || g[numCol][numLig].getCouleur()==5){
                        switch (g[numCol][numLig].getCouleur()){
                            case 4:
                                nbPointsFinal += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 2;
                                break;

                            case 5:
                                nbPointsFinal += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 3;
                                break;

                        }

                    }
                    else
                    {
                        nbPointsFinal += nbPointsJet[indexPointsJet] * g[numCol][numLig].getCouleur();}
                    numCol++;

                }
                break;
        }
        nbPointsFinal = nbPointsFinal *multiplicateurMot;
        return (nbPointsFinal);
    }


    /**
     * PR: Le placement de mot sur this à partir de la case (numLig, numCol) dans le sens donnée par sens à l'aide des jetons de e est valide.
     * A/R: Effectue ce placement et retour ne nombre de jetons retirées de e.
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @param e
     * @return
     */
    public int place(String mot, int numLig, int numCol, char sens, MEE e){
        int resultatNbJetonRetiree = 0;
        int index = 0;

        //Je regarde le sens.
        if(sens == 'h'){
            //Puis je crée un boucle qui vas check toutes les cases du mot.
            for(int a=numCol; a<numCol+mot.length(); a++){
                //Si c'est possible de retirer.
                if(e.retire(Ut.majToIndex(mot.charAt(index)))){
                    //Alors j'ajoute 1 aux res.
                    resultatNbJetonRetiree ++;
                }
                //Puis je place sur le plateau.
                g[numLig][a].setLettre(mot.charAt(index));
                index++;
            }
        }else if(sens == 'v'){
            //Puis je crée un boucle qui vas check toutes les cases du mot.
            for(int i=numLig; i<numLig+mot.length(); i++){
                //Si c'est possible de retirer.
                if(e.retire(Ut.majToIndex(mot.charAt(index)))){
                    //Alors je rajoute 1 au res.
                    resultatNbJetonRetiree ++;
                }
                //Puis je place sur le plateau.
                g[i][numCol].setLettre(mot.charAt(index));
                index++;
            }
        }

        return resultatNbJetonRetiree;
    }



}
