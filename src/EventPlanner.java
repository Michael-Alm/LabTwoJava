import javax.swing.*;
import java.time.LocalDateTime;

// Main class for the Event Planner application
public class EventPlanner {
    // Main method to run the application
    public static void main(String[] args) {
        // Running the GUI creation on the Event Dispatch Thread (best practice in Swing)
        SwingUtilities.invokeLater(() -> {
            // Creating the main application window (JFrame)
            JFrame frame = new JFrame("Event Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensuring the application closes when the window is closed
            frame.setSize(800, 600); // Setting the size of the main window

            // Creating an EventListPanel to display the list of events
            EventListPanel eventListPanel = new EventListPanel();
            frame.add(eventListPanel); // Adding the EventListPanel to the JFrame

            // Adding some default events to the panel
            addDefaultEvents(eventListPanel);

            // Making the frame visible to the user
            frame.setVisible(true);
        });
    }

    // Method to add default events to the EventListPanel
    static void addDefaultEvents(EventListPanel panel) {
        // Creating a LocalDateTime for a deadline event (October 1st, 2024 at 5:00 PM)
        LocalDateTime deadlineTime = LocalDateTime.of(2024, 10, 1, 17, 0);
        // Creating LocalDateTime objects for a meeting event (September 29th, 2024 from 10:00 AM to 11:00 AM)
        LocalDateTime meetingStartTime = LocalDateTime.of(2024, 9, 29, 10, 0);
        LocalDateTime meetingEndTime = meetingStartTime.plusHours(1); // Setting the end time to one hour after the start

        // Adding a default Deadline event to the panel
        panel.addEvent(new Deadline("Default Deadline", deadlineTime));
        // Adding a default Meeting event to the panel
        panel.addEvent(new Meeting("Default Meeting", meetingStartTime, meetingEndTime, "Class 339"));
    }
}