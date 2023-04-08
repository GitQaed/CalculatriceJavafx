package models;


public class Calculs {
    private float numberA;
    private float numberB;

    private String operateur;

    private float result;

    public Calculs(float numberA, String operateur, float numberB, float result) {
        this.numberA = numberA;
        this.operateur = operateur;
        this.numberB = numberB;
        this.result = result;
    }


    @Override
    public String toString() {
        if (this.operateur.equals("/") && this.numberB == 0) {
            return this.numberA + " " + this.operateur + " " + this.numberB
                    + " = Division par zero impossible";
        }
        return this.numberA + " " + this.operateur + " " + this.numberB
                + " = " + this.result;
    }

}
