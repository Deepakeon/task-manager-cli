package validation;

import exceptions.ValidationException;
import model.Task;

public class TaskValidator {
    public static void validateDescription(String description){
        StringBuilder errors = new StringBuilder();

        if(description == null || description.trim().isEmpty()){
            errors.append("Description cannot be empty").append("\n");
        }else if(description.length() > 200){
            errors.append("Description cannot be more than 200 characters long").append("\n");
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors.toString());
        }
    }

    public static void validate(Task task){
        StringBuilder errors = new StringBuilder();

        try{
            validateDescription(task.getDescription());
        }catch(ValidationException ve){
            errors.append(ve.getMessage());
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors.toString());
        }

    }
}
