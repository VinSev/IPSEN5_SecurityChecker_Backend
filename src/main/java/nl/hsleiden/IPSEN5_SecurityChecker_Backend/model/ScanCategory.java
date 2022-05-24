package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

public class ScanCategory {
    public String title;
    public String path;
    public Boolean loading;
    public int grade;
    public String result;

    public ScanCategory() {
    }

    public ScanCategory(String title, String path, Boolean loading, int grade, String result) {
        this.title = title;
        this.path = path;
        this.loading = loading;
        this.grade = grade;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getLoading() {
        return loading;
    }

    public void setLoading(Boolean loading) {
        this.loading = loading;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
