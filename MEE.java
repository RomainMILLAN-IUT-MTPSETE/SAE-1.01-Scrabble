import java.util.Random;

public class MEE {
    //PR => Pré-requis
    //A => Action
    private int[] tabFreq;
    private int nbTotEx;

    /**
     * PR: max >= 0
     * A:  Crée un multi-ensemble vide dont les éléments seront inférieurs à max
     * @param max
     */
    public MEE(int max){
        this.tabFreq = new int[max]; //On initialise le tableau 'tabFreq' et 'nbToEx' à 0
        this.nbTotEx = 0;
    }

    /**
     * PR: Les éléments de tab sont positifs ou nuls.
     * A: Crée un multi-ensemble dont le tableau de fréquences est une copie de tab.
     * @param tab
     */
    public MEE(int[] tab){

        this.tabFreq = tab; //On copie tab dans l'élément courant
        this.nbTotEx = 0;
        for (int i = 0; i<this.tabFreq.length; i++){//On calcule nbTotEx de tabFreq
            this.nbTotEx = this.nbTotEx + this.tabFreq[i];
        }
    }

    /***
     * PR: Nothing
     * A: Copie d'une autre MEE
     * @param e
     */
    public MEE(MEE e){

        this.tabFreq = e.tabFreq;//On copie les éléments de e dans this
        this.nbTotEx = e.nbTotEx;
    }

    /**
     * PR: Nothing
     * A: Getter of the this.nbToEx
     * @return
     */
    public int getNbTotEx() {
        return this.nbTotEx;
    }


    /**
     * PR: Nothing
     * A: Le résultat est vrai si est seulement si l'ensemble est vide
     * @return
     */
    public boolean estVide(){

        return this.nbTotEx==0; //On vérifie si nbTotEx est à 0 : si il l'est cela signifie que tabFreq est vide (remplit de 0) sinon il n'est pas vide
    }

    /**
     * PR: i > 0 et i < tabFreq.length
     * A: Ajoute un exemplaire de i à this
     * @param i
     */
    public void ajoute(int i){

        this.tabFreq[i] = this.tabFreq[i]+1;//On ajoute 1 exemplaire de i à tabFreq et on incrémente le nombre total d'exemplaire de 1
        this.nbTotEx++;
    }

    /**
     * PR: i > 0 et i < tabFreq.length
     * A: retire un exemplaire de i dans this s'il en existe et retourne vrai si est seulement si cette action a pu être effectuée
     * @param i
     * @return
     */
    public boolean retire(int i){

        boolean retireFonctionne = false;//On initialize la variable 'retireFonctionne' pour savoir si nous avons retirer un exemplaire ou non
        if(this.tabFreq[i] > 0){//Si il existe un exemplaire de i dans this alors on décrémente l'élément et nbTotEx et on change la valeur de retireFonctionne pour true
            this.tabFreq[i] = this.tabFreq[i] - 1;
            this.nbTotEx--;
            retireFonctionne = true;
        }

        return retireFonctionne;
    }

    /**
     * PR: this est non vide.
     * A: Retire de this un exemplaire choisi aléatoirement et le retourne.
     * @return
     */
    public int retireAleat(){
        int indice = Ut.randomMinMax(0, this.tabFreq.length-1);//Choisi un nombre aléatoire entre 0 et la longueur du tableau -1
        int res = this.tabFreq[indice];

        if(this.retire(indice) == false ){//Si retire ne fonctionne pas alors on relance la fonction
            this.retireAleat();
        }else {
            return res;
        }
    }

    /**
     * PR: 0 <= i && i < tabFreq.length
     * A:Tranfère un exemplaire de i de this vers e s'il en existe, et retourne vrai ssi cette action a pu être effectué.
     * @param e
     * @param i
     * @return
     */
    public boolean transfere(MEE e, int i){
        boolean resultat = false;//On initialize la variable 'resultat', qui est un boolean.

        if(this.retire(i) == true){//On verifie si il est possible de transferer l'élément et on effectue l'action si possible.
            this.retire(i);
            e.ajoute(i);
            resultat = true;
        }

        return resultat;
    }

    /**
     * PR: k >= 0
     * A: transfère k exemplaires choisis aléatoirement de this vers e dans la limite du contenu de this.
     * @param e
     * @param k
     */
    public int tranfereAleat(MEE e, int k){

        if(this.nbTotEx >= k){//Si la variable k
            int res = k;
            while(k>0){
                int indice = Ut.randomMinMax(0, this.tabFreq.length-1);//On lance un random nombre pour avoir l'indice.
                if(this.tabFreq[indice] > 0){
                    this.transfere(e, indice);
                }
                k--;
            }
        }else {
            k = nbTotEx;
            this.tranfereAleat(e, k);
        }
        return res;
    }

    /**
     * PR: tabFreq.length <= v.length
     * A: Retourne la somme des valeurs des exemplaires des éléments de this, la valeur d'un exemplaire d'un élément i de this étant égale a v[i]
     * @param v
     * @return
     */
    public int sommeValeurs(int[] v){
        //On initialize le résulat
        int resultat = 0;

        //On fait une boucle pour parcourir tous le trableau this
        for(int i=0; i<this.tabFreq.length; i++){
            //On ajoute au résultat le nombre d'exemplaire * valeur des points
            resultat += this.tabFreq[i] * v[i];
        }

        //Retoune le résultat
        return resultat;
    }

}
