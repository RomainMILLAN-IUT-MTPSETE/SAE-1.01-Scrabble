public class Test {
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
    public static void main(String[] args){
        Plateau jusTest = new Plateau();
        MEE chevalet = new MEE(26);
        System.out.println(jusTest.toString());

        chevalet.ajoute(9);
        chevalet.ajoute(20);
        chevalet.ajoute(18);
        chevalet.ajoute(19);
        chevalet.ajoute(0);
        chevalet.ajoute(19);
        chevalet.ajoute(4);
        chevalet.ajoute(18);
        chevalet.ajoute(19);

        System.out.println(jusTest.placementValide("JustATest", 6, 7, 'h', chevalet));
    }

}