package com.SpringBoot.Application.Service;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.Application.Model.Todo;



@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private int todoCount = 0;

    
    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public Todo retrieveTodo(int id) {
        for(Todo todo:todos)
        {
        	if(todo.getId()==id)
        	{
        		return todo;
        	}
        }
        return null;
    }
    
    public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
    
    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}