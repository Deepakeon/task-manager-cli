package helpers;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class JsonMapper {
    public static ArrayList<Task> stringToTask(String jsonString){
        ArrayList<Task> tasks = new ArrayList<>();
        String[] taskList = jsonString.replace("]", "").replace("[", "").split("},");

        for (String task: taskList){
            if(!task.isEmpty()){
                String json = task.endsWith("}") ? task.replace("}", "") : task;
                tasks.add(Task.fromJson(json));
            }
        }
        return tasks;
    }

    public static String taskToString(List<Task> tasks){
        StringBuilder json = new StringBuilder();

        int idx = 0;
        for (Task task: tasks){
            if(idx >= 1){
                json.append(",");
            }
            json.append(task.serializeToJsonString());
            idx+=1;
        }
        return "[" + json.toString() + "]";
    }
}
