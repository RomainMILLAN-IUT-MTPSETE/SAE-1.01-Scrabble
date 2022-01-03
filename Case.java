public class Case {
    private int couleur;
    private char lettre; //0 valeur par défaut

    /**
     * PR: uneCouleur est un entier entre 1 et 5
     * A: constructeur de Case
     */
    public Case(int uneCouleur){
        this.couleur = uneCouleur;
        this.lettre = '0';
    }

    /**
     * R: la couleur de this, un nombre entre 1 et 5
     * @return
     */
    public int getCouleur(){
        return this.couleur;
    }

    /**
     * PR: cette case est recouverte
     * @return
     */
    public char getLettre(){
        return this.lettre;
    }

    /**
     * PR: let est une lettre majuscule
     * @param let
     */
    public void setLettre(char let){
        this.lettre = let;
    }

    /**
     * R: vrai si est seuelemnt si la case est recourverte par une lettre
     */
    public boolean estRecouverte(){
        boolean resultat = false;
        if(this.lettre != '0'){
            resultat = true;
        }

        return resultat;
    }

    public String toString(){
        String resultat = "";
        if(this.estRecouverte() == true){
            resultat = "La case est recouverte par la lettre " + this.getLettre();
        }else {
            resultat = "La case est vide";
        }

        return resultat;
    }
    
}
