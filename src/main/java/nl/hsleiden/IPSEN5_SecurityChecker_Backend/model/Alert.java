package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

public class Alert {
    private String score;
    private String titel;
    private boolean geslaagd;

    public Alert(String score, String titel, boolean geslaagd) {
        this.score = score;
        this.titel = titel;
        this.geslaagd = geslaagd;
    }

}
