public class Kysymys {

    private int kertoja;
    private int kerrottava;

    private int oikeaVastaus;
    private String kysymys;

    public Kysymys() {
        this(1, 1);
    }

    public Kysymys(int kertoja, int kerrottava) {
        this.kertoja = kertoja;
        this.kerrottava = kerrottava;
        oikeaVastaus = this.kertoja * this.kerrottava;
        kysymys = "Paljonko on " + this.kertoja + " kertaa " + this.kerrottava + "?";
    }

    public int getOikeaVastaus() {
        return oikeaVastaus;
    }

    public String getKysymys() {
        return kysymys;
    }

}


