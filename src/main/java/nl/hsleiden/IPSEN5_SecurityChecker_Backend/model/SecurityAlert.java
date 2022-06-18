package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

public class SecurityAlert {
    private int score;
    private String titel;
    private boolean geslaagd;

    public SecurityAlert(int score, String titel, boolean geslaagd) {
        this.score = score;
        this.titel = titel;
        this.geslaagd = geslaagd;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public boolean isGeslaagd() {
        return geslaagd;
    }

    public void setGeslaagd(boolean geslaagd) {
        this.geslaagd = geslaagd;
    }
}
