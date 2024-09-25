import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

// AddEventModal is a custom JDialog for adding events to the event list.
// It supports two types of events: "Deadline" and "Meeting".
class AddEventModal extends JDialog {
    private JTextField endDateField;  // Text field for entering end date (used for Meetings).
    private JTextField locationField; // Text field for entering location (used for Meetings).
    private JLabel endDateLabel;      // Label for the end date field.
    private JLabel locationLabel;     // Label for the location field.

    // Constructor for initializing the modal dialog.
    public AddEventModal(EventListPanel parent) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Add Event", true);  // Create a modal dialog.
        setLayout(new GridLayout(0, 2));  // Use grid layout to arrange components.

        // Fields for entering event details.
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Deadline", "Meeting"});
        endDateField = new JTextField();
        locationField = new JTextField();

        // Initialize fields for Meetings.
        endDateField = new JTextField();  // Field for meeting end date.
        locationField = new JTextField(); // Field for meeting location.

        // Initialize labels for Meetings.
        endDateLabel = new JLabel("End Date (YYYY-MM-DD HH:MM):");
        locationLabel = new JLabel("Location:");

        // Add components to the modal.
        add(new JLabel("Type:"));
        add(typeCombo);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Date (YYYY-MM-DD HH:MM):"));
        add(dateField);

        // Initially hide meeting-specific fields until "Meeting" is selected.
        endDateLabel.setVisible(false);
        endDateField.setVisible(false);
        locationLabel.setVisible(false);
        locationField.setVisible(false);

        // Add meeting-specific fields to the dialog.
        add(endDateLabel);
        add(endDateField);
        add(locationLabel);
        add(locationField);

        // Event listener to toggle meeting fields visibility based on the selected event type.
        typeCombo.addActionListener(e -> {
            boolean isMeeting = Objects.equals(typeCombo.getSelectedItem(), "Meeting");
            toggleMeetingFields(isMeeting);
        });

        // Button to add the event.
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                // Fetch and parse the input data.
                String name = nameField.getText();
                LocalDateTime date = LocalDateTime.parse(dateField.getText().replace(" ", "T")); // Convert input to LocalDateTime.

                // Add event based on the selected type.
                if (Objects.equals(typeCombo.getSelectedItem(), "Deadline")) {
                    parent.addEvent(new Deadline(name, date));
                } else {
                    // Handle meetings by parsing additional fields.
                    LocalDateTime endDate = LocalDateTime.parse(endDateField.getText().replace(" ", "T"));
                    String location = locationField.getText();
                    parent.addEvent(new Meeting(name, date, endDate, location));
                }
                dispose(); // Close dialog after adding.
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.");
            }
        });

        add(addButton);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Toggles visibility
    private void toggleMeetingFields(boolean isVisible) {
        endDateLabel.setVisible(isVisible);
        endDateField.setVisible(isVisible);
        locationLabel.setVisible(isVisible);
        locationField.setVisible(isVisible);
    }
}