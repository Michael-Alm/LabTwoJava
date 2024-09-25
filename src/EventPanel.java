import javax.swing.*;
import java.awt.*;
import java.time.Duration;

// Class to display information about a single event in a panel
class EventPanel extends JPanel {
    // Reference to the event being displayed
    private final Event event;
    // Button to mark the event as complete (only for completable events)
    private JButton completeButton;

    // Constructor to set up the panel with event information and actions
    public EventPanel(Event event) {
        this.event = event; // Storing the event reference
        setLayout(new BorderLayout()); // Setting layout for the panel

        // Panel to hold event details in a vertical layout
        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.add(new JLabel("Name: " + event.getName())); // Displaying event name
        infoPanel.add(new JLabel("Date: " + event.getDateTime().toString())); // Displaying event date and time

        // If the event is a Meeting, display additional information
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event; // Casting event to Meeting
            Duration duration = meeting.getDuration(); // Getting the duration of the meeting
            long hours = duration.toHours(); // Extracting hours from the duration
            long minutes = duration.toMinutes() % 60; // Extracting minutes from the duration
            String formattedDuration = hours + " hours and " + minutes + " minutes"; // Formatting the duration

            infoPanel.add(new JLabel("Duration: " + formattedDuration)); // Displaying the formatted duration
            infoPanel.add(new JLabel("Location: " + meeting.getLocation())); // Displaying the location of the meeting
        }

        add(infoPanel, BorderLayout.CENTER); // Adding the info panel to the center of the EventPanel

        // If the event is completable, add a button to mark it as complete
        if (event instanceof Completable) {
            completeButton = new JButton("Complete"); // Creating a button for completion

            // If the event is already completed, disable the button
            if (((Completable) event).isComplete()) {
                completeButton.setEnabled(false); // Disabling the button if already complete
            }

            // Adding an action listener to mark the event as complete when the button is clicked
            completeButton.addActionListener(e -> {
                ((Completable) event).complete(); // Marking the event as complete
                completeButton.setEnabled(false); // Disabling the button after completion
            });

            add(completeButton, BorderLayout.SOUTH); // Adding the complete button to the bottom of the EventPanel
        }
    }
}