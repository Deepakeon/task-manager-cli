package model;

public class Task {

    private int id;
    private String description;
    private TaskStatus status;
    private long createdAt;
    private long updatedAt;

    public Task(long createdAt, String description, int id, TaskStatus status, long updatedAt) {
        this.createdAt = createdAt;
        this.description = description;
        this.id = id;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public static Task fromJson(String jsonString){
            String task = jsonString.replace("{", "").replace("}", "").replace("\"", "");
            String[] taskDetails = task.split(",");

            int id = Integer.parseInt(taskDetails[0].split(":")[1].trim());
            String description = taskDetails[1].split(":")[1].trim();
            TaskStatus status = TaskStatus.valueOf(taskDetails[2].split(":")[1].trim());
            long createdAt = Long.parseLong(taskDetails[3].split(":")[1]);
            long updatedAt = Long.parseLong(taskDetails[4].split(":")[1]);
            return new Task(createdAt, description, id, status, updatedAt);
    }

    public  String serializeToJsonString() {
        return "{" +
                    "\"id\":\"" + id + "\"," +
                    "\"description\":\"" + description + "\"," +
                    "\"status\":\"" + status + "\"," +
                    "\"createdAt\":\"" + createdAt + "\"," +
                    "\"updatedAt\":\"" + updatedAt + "\""+
                "}";
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
