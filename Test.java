public class Test {
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
    public static void main(String[] args){
        Plateau jusTest = new Plateau();
                   //A.B.C.D.E.F.G.H.I.J.K.L.M.N.O.P.Q.R.S.T.U.V.W.X.Y.Z
        int[] tab = {3,2,0,0,2,4,1,1,2,5,0,1,2,1,1,3,8,1,3,4,2,4,0,0,0,0};
        MEE chevalet = new MEE(tab);
        System.out.println(jusTest.toString());

        System.out.println(jusTest.placementValide("ALIBIBE", 7, 9, 'v', chevalet));
    }

}