package it.academy.fitness_studio.audit;

public enum AuditCode {
    GET("Предоставление отчета по конкретному событию"),
    GETAll("Предоставление общего отчета") ;
    private String description;
    private AuditCode(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
