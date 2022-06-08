package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import org.json.JSONObject;

public class ScanCategory {
    private String title;
    private String path;
    private boolean loading;
    private int grade;
    private JSONObject result;

    public ScanCategory() {
    }

    public ScanCategory(String title, String path, boolean loading, int grade, JSONObject result) {
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

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
