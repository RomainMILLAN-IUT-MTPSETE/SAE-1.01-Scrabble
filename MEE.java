public class MEE {
    //PR => Pré-requis
    //A => Action
    private int[] tabFreq = new int[26];
    private int nbTotEx = 0;
    private int max = 0;

    /**
     * PR: max >= 0
     * A:  Crée un multi-ensemble vide dont les éléments seront inférieurs à max
     * @param max
     */
    public MEE(int max){
        this.max = max;
        for(int i=0; i<this.tabFreq.length; i++) {
            this.tabFreq[i] = 0;
        }

        //On remet à la variable 'nbTotEx' à 0
        this.nbTotEx = 0;
        //On fait une boucle pour définir le nombre total d'exemplaire
        for(int i=0; i<this.tabFreq.length; i++){
            //Si l'élément dans le tableau 'tabFreq' est suppérieur à 0 alors :
            if(this.tabFreq[i] > 0){
                //On ajoute à la variable le nombre d'élément.
                this.nbTotEx+=tabFreq[i];
            }
        }
    }

    /**
     * PR: Les éléments de tab sont positifs ou nuls.
     * A: Crée un multi-ensemble dont le tableau de fréquences est une copie de tab.
     * @param tab
     */
    public MEE(int[] tab){
        boolean intinTabPositifOrNull = true;
        //Check all elements un the parameter tab.
        for(int i=0; i<tab.length; i++){
            //If the element in tab[i] is less than 0
            if(tab[i] < 0){
                //Si intinTabPositifOrNull est à true alors
                if(intinTabPositifOrNull == true){
                    //On passe la variable boolean à faux
                    intinTabPositifOrNull = false;
                }
            }
        }

        //Si la variable initialisé avant est true
        if(intinTabPositifOrNull == true){
            //On fait une boucle pour copier tous les elements de 'tab' dans 'tabFreq'
            for(int i=0; i<tab.length; i++){
                //On copie les element de tab dans tabFreq
                this.tabFreq[i] = tab[i];
            }
        //Sinon
        }else {
            //On affiche une erreur
            System.out.println("Error: Un element dans le tableau passer en parametre est inferieur a 0");
        }

        //On remet à la variable 'nbTotEx' à 0
        this.nbTotEx = 0;
        //On fait une boucle pour définir le nombre total d'exemplaire
        for(int i=0; i<this.tabFreq.length; i++){
            //Si l'élément dans le tableau 'tabFreq' est suppérieur à 0 alors :
            if(this.tabFreq[i] > 0){
                //On ajoute à la variable le nombre d'élément.
                this.nbTotEx+=tabFreq[i];
            }
        }
    }

    /***
     * PR: Nothin
     * A: Copie d'une autre MEE
     * @param e
     */
    public MEE(MEE e){
        //On copie les éléments de e dans this
        this.tabFreq = e.tabFreq;
        this.nbTotEx = e.nbTotEx;

        //On remet à la variable 'nbTotEx' à 0
        this.nbTotEx = 0;
        //On fait une boucle pour définir le nombre total d'exemplaire
        for(int i=0; i<this.tabFreq.length; i++){
            //Si l'élément dans le tableau 'tabFreq' est suppérieur à 0 alors :
            if(this.tabFreq[i] > 0){
                //On ajoute à la variable le nombre d'élément.
                this.nbTotEx+=tabFreq[i];
            }
        }
    }


    /**
     * PR: Nothing
     * A: Le résultat est vrai si est seulement si l'ensemble est vide
     * @return
     */
    public boolean estVide(){
        //On initializa la variable 'estVide', sur vrai
        boolean estVide = true;

        //On fait une boucle pour connaitre si l'element est vide
        for(int i=0; i<this.tabFreq.length; i++){
            //On regarde si le nombre dans le tableaux est supérieur à 0
            if(this.tabFreq[i] > 0){
                //Alors on passe la variable 'estVide' à false;
                estVide = false;
            }
        }
        return estVide;
    }

    /**
     * PR: i > 0 et i < tabFreq.length
     * A: Ajoute un exemplaire de i à this
     * @param i
     */
    public void ajoute(int i){
        //On véréfie le pré-requis de la méthode
        if(i > 0 && i < this.tabFreq.length){
            //Si le chiffre est bon alors on ajoute dans 'tabFreq' 1 exemplaire
            this.tabFreq[i] = this.tabFreq[i]+1;
        }
    }

    /**
     * PR: i > 0 et i < tabFreq.length
     * A: retire un exemplaire de i dans this s'il en existe et retourne vrai si est seulement si cette action a pu être effectuée
     * @param i
     * @return
     */
    public boolean retire(int i){
        //On initialize la variable 'retireFonctionne' pour savoir si nous avons réussi à retirer l'exemplaire
        boolean retireFonctionne = false;
        //Si il existe un exemplaire dans this alors
        if(this.tabFreq[i] > 0){
            this.tabFreq[i] = this.tabFreq[i] - 1;
            retireFonctionne = true;
        }

        return retireFonctionne;
    }


}
