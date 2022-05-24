package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

public class ScanCategory {
    private String title;
    private String path;
    private boolean loading;
    private int grade;
    private Object result;

    public ScanCategory() {
    }

    public ScanCategory(String title, String path, boolean loading, int grade, Object result) {
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

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
