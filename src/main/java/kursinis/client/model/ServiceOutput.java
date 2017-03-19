package kursinis.client.model;

public class ServiceOutput {

    private String result;
    private String resultPath;
    private String solverWorkflow;
    private boolean error;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public String getSolverWorkflow() {
        return solverWorkflow;
    }

    public void setSolverWorkflow(String solverWorkflow) {
        this.solverWorkflow = solverWorkflow;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
