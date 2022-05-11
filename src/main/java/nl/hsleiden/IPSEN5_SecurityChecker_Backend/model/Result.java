package nl.hsleiden.IPSEN5_SecurityChecker_Backend.model;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private long resultId;
    private String date;
    private String url;
    @Column(name = "scans_results")
    private int scansDone;
    @Column(name = "is_Finished")
    private boolean isFinished;

    public Result(long resultId, String date, String url, int scansDone, boolean isFinished) {
        this.resultId = resultId;
        this.date = date;
        this.url = url;
        this.scansDone = scansDone;
        this.isFinished = isFinished;
    }

    public Result(String date, String url, int scansDone, boolean isFinished) {
        this.date = date;
        this.url = url;
        this.scansDone = scansDone;
        this.isFinished = isFinished;
    }

    public Result() {
    }
    //    Setters And Getters

    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScansDone() {
        return scansDone;
    }

    public void setScansDone(int scansDone) {
        this.scansDone = scansDone;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

   }
