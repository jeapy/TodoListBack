package jpz.application.todo.Service;

import jpz.application.todo.Model.Todo;
import jpz.application.todo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Create a new todo
    public Todo createTodo(Todo todo) {
      //  todo.setCreatedAt(LocalDateTime.now());

        return todoRepository.save(todo);
    }

    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Get todo by ID
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    // Update an existing todo
    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        existingTodo.setTitle(todoDetails.getTitle());
        existingTodo.setDescription(todoDetails.getDescription());
        existingTodo.setCompleted(todoDetails.isCompleted());

        return todoRepository.save(existingTodo);
    }

    // Delete a todo
    public void deleteTodo(Long id) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        todoRepository.delete(existingTodo);
    }

    // Get completed todos
    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompleted(true);
    }

    // Get pending todos
    public List<Todo> getPendingTodos() {
        return todoRepository.findByCompleted(false);
    }

}