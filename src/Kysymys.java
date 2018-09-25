public class Kysymys {

    private int _kertoja;
    private int _kerrottava;

    private int _result;

    public int get_result() {
        return _result;
    }

    public String get_kysymys() {
        return _kysymys;
    }

    private String _kysymys;

    public Kysymys(int kertoja, int kerrottava) {
        _kertoja = kertoja;
        _kerrottava = kerrottava;
        _result = _kertoja * _kerrottava;
        _kysymys = "Paljonko on " + _kertoja + " kertaa " + _kerrottava + "?";
    }

}


