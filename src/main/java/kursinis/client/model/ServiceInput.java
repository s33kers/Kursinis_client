package kursinis.client.model;

import kursinis.client.SolverType;

public class ServiceInput {

    private String rules;

    private String facts;

    private String goal;

    private SolverType type;

    public ServiceInput() {}

    public ServiceInput(String rules, String facts, String goal, SolverType type) {
        this.rules = rules;
        this.facts = facts;
        this.goal = goal;
        this.type = type;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getFacts() {
        return facts;
    }

    public void setFacts(String facts) {
        this.facts = facts;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public SolverType getType() {
        return type;
    }

    public void setType(SolverType type) {
        this.type = type;
    }
}
