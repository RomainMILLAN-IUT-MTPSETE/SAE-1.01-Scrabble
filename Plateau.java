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
        String resPlateauAfficher = "   |01 |02 |03 |04 |05 |06 |07 |08 |09 |10 |11 |12 |13 |14 |15 \n";
        //Puis la première lettre
        char colonne = 'A';

        //Maintenant on vas parcourir le tableau de case et afficher les différentes cases.
        for(int i=0; i<g.length; i++){
            //On ajoute la lettre de la ligne
            resPlateauAfficher += " " + colonne + " |";
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

            }else/*Le plateau et vide*/ {
                if(mot.length() >= 2){
                    boolean contientCenter = false;
                    int i =0;
                    //On vérifie si le sens est sur horizontale alors il faut que se soit sur la colonne n°7 pour passer au millieu.
                    if (sens == 'h' && numCol == 7) {
                        //On fait la vérification du passement au millieu.
                        while(contientCenter == false && i < mot.length()-1){
                            if(numLig + i == 7){
                                contientCenter = true;
                            }
                            i++;
                        }

                        //Maintenant on sais que le mot passe bien par le millieu.
                        if(contientCenter == true){
                            /**boolean allLetters = true;
                            int positionMot = 0;
                            int[] chevalet = e.getTabFreq();
                            //On check si toutes les lettres sont dans un MEE
                            while(allLetters == true && positionMot < mot.length()){
                                if(chevalet[Ut.majToIndex(mot.charAt(positionMot))] != 0){
                                    positionMot++;
                                    chevalet[Ut.majToIndex(mot.charAt(positionMot))]--;
                                }else {
                                    allLetters = false;
                                }
                            }

                            if(allLetters == true){
                                resultatPlacementValide = true;
                            }*/
                        }
                    }else if(sens == 'v' && numLig == 7){
                        //On fait la vérification du passement au millieu.
                        while(contientCenter == false && i < mot.length()-1){
                            if(numCol + i == 7){
                                contientCenter = true;
                            }
                            i++;
                        }

                        //Maintenant on sais que le mot passe bien par le millieu.
                        if(contientCenter == true){
                            boolean allLetters = true;
                            int positionMot = 0;
                            //On check si toutes les lettres sont dans un MEE
                            while(allLetters == true && positionMot < mot.length()){
                                if(e.getTabFreq()[Ut.majToIndex(mot.charAt(positionMot))] != 0){
                                    positionMot++;
                                }else {
                                    allLetters = false;
                                }
                            }

                            if(allLetters == true){
                                resultatPlacementValide = true;
                            }
                        }
                    }
                }
            }
        }


        return resultatPlacementValide;
    }

}
