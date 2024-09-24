import java.time.Duration;
import java.time.LocalDateTime;

// Meeting class represents a meeting event with a start and end time and a location.
// It can be marked as complete.
public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime; // End time of the meeting
    private String location; // Location of the meeting
    private boolean complete; // Flag indicating whether the meeting is complete

    // Constructor to initialize the meeting with its name, start time, end time, and location.
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
        this.complete = false; // Default state is incomplete
    }

    // Marks the meeting as complete.
    public void complete() {
        this.complete = true;
    }

    // Returns whether the meeting is complete.
    public boolean isComplete() {
        return complete;
    }

    // Returns the end time of the meeting.
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // Sets a new end time for the meeting.
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    // Returns the duration of the meeting as a Duration object.
    public Duration getDuration() {
        // Basic implementation of duration calculation between start and end times.
        return Duration.between(getDateTime(), endDateTime);
    }

    // Returns the location of the meeting.
    public String getLocation() {
        return location; // Return the location of the meeting
    }

    // Sets a new location for the meeting.
    public void setLocation(String location) {
        this.location = location;
    }

    // Returns the name of the meeting.
    public String getName() {
        return super.getName();
    }
}
