import java.time.LocalDateTime;

// Abstract class representing a general event with a name and date-time.
public abstract class Event implements Comparable<Event> {
    private String name; // The name of the event
    private LocalDateTime dateTime; // The date and time of the event

    // Constructor to initialize the event with its name and date-time.
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // Returns the name of the event.
    public String getName() {
        return name;
    }

    // Sets the name of the event.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the date-time of the event.
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Sets the date-time of the event.
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Compares the date-time of two events to allow sorting.
    // This method may require more testing for edge cases.
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
