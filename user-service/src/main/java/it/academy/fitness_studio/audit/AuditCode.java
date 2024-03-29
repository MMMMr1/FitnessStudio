package it.academy.fitness_studio.audit;

public enum AuditCode {
    CREATED("Создана запись в журнале пользователей"),
    UPDATE("Обновлена запись в журнале пользователей");
    private String description;
    private AuditCode(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
