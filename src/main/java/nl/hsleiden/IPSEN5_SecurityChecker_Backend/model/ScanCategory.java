package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

public class ScanCategory {
    public String title;
    public String path;
    public Boolean loading;
    public int grade;
    public String result;

    public ScanCategory(String title, String path, Boolean loading, int grade, String result) {
        this.title = title;
        this.path = path;
        this.loading = loading;
        this.grade = grade;
        this.result = result;
    }
}
