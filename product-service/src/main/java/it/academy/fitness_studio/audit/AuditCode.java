package it.academy.fitness_studio.audit;

public enum AuditCode {
    CREATED("Создана запись в журнале продуктов"),
    UPDATE("Обновлена запись в журнале продуктов");
    private String description;
    private AuditCode(String description){
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
