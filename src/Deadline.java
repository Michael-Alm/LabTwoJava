import java.time.LocalDateTime;

// Deadline class represents an event with a specific deadline that can be marked as complete.
public class Deadline extends Event implements Completable {
    // Flag indicating whether the deadline is complete or not.
    private boolean complete;

    // Constructor initializes the deadline event with a name and deadline date.
    // Initially, the event is incomplete.
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.complete = false;
    }

    // Marks the deadline as complete.
    public void complete() {
        this.complete = true;
    }

    // Returns the completion status of the deadline.
    public boolean isComplete() {
        return complete;
    }

    // Returns the name of the deadline.
    public String getName() {
        return super.getName();
    }
}
