package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

public class SecurityAlert {
    private int grade;
    private String title;
    private boolean passed;
    private String description;

    public SecurityAlert(int grade, String title, boolean passed, String description) {
        this.grade = grade;
        this.title = title;
        this.passed = passed;
        this.description = description;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
