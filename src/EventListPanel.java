import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

// Class that manages a panel displaying a list of events
class EventListPanel extends JPanel {
    // List to hold events
    private final ArrayList<Event> events;
    // Panel where event panels will be displayed
    private final JPanel displayPanel;
    // Drop-down to allow sorting of events
    private final JComboBox<String> sortDropDown;
    // Checkboxes to filter events based on their type or status
    private final JCheckBox filterCompleteBox;
    private final JCheckBox filterDeadlineBox;
    private final JCheckBox filterMeetingBox;

    // Constructor to set up the panel layout, sorting, and filtering controls
    public EventListPanel() {
        events = new ArrayList<>(); // Initializing the events list
        setLayout(new BorderLayout()); // Setting the layout for the main panel

        // Panel to hold sorting and filtering controls
        JPanel controlPanel = new JPanel();
        // Panel to display individual event panels
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS)); // Display events in vertical layout

        // ComboBox for selecting sort options
        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date", "Sort by Name (Reverse)", "Sort by Date (Reverse)"});
        // Adding action listener to handle sorting when the user selects a sort option
        sortDropDown.addActionListener(e -> sortEvents());

        // Checkboxes for filtering completed events, deadlines, and meetings
        filterCompleteBox = new JCheckBox("Hide Completed");
        filterDeadlineBox = new JCheckBox("Hide Deadlines");
        filterMeetingBox = new JCheckBox("Hide Meetings");

        // Action listener to trigger display update when filters are applied
        ActionListener filterListener = e -> updateDisplay();

        // Adding filter action listeners
        filterCompleteBox.addActionListener(filterListener);
        filterDeadlineBox.addActionListener(filterListener);
        filterMeetingBox.addActionListener(filterListener);

        // Button to add a new event
        JButton addEventButton = new JButton("Add Event");
        // Action listener to open a modal for adding a new event
        addEventButton.addActionListener(e -> {
            new AddEventModal(EventListPanel.this);
        });

        // Adding the controls (sort drop-down and filter checkboxes) to the control panel
        controlPanel.add(sortDropDown);
        controlPanel.add(filterCompleteBox);
        controlPanel.add(filterDeadlineBox);
        controlPanel.add(filterMeetingBox);
        controlPanel.add(addEventButton);

        // Adding the control panel to the top of the main panel (NORTH) and the display panel to the center
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);
    }

    // Method to add a new event to the list and update the display
    public void addEvent(Event event) {
        events.add(event); // Adding the event to the list
        updateDisplay(); // Updating the display to reflect the new event
    }

    // Method to sort events based on the selected option from the drop-down
    private void sortEvents() {
        String selectedSort = (String) sortDropDown.getSelectedItem(); // Getting the selected sort option
        // Sorting by event name
        if ("Sort by Name".equals(selectedSort)) {
            events.sort(Comparator.comparing(Event::getName));
            // Sorting by event date/time
        } else if ("Sort by Date".equals(selectedSort)) {
            events.sort(Comparator.comparing(Event::getDateTime));
            // Sorting by event name in reverse order
        } else if ("Sort by Name (Reverse)".equals(selectedSort)) {
            events.sort((e1, e2) -> e2.getName().compareTo(e1.getName()));
            // Sorting by event date/time in reverse order
        } else if ("Sort by Date (Reverse)".equals(selectedSort)) {
            events.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        }
        updateDisplay(); // Updating the display to reflect the sorted order
    }

    // Method to update the display panel based on filters and current events
    private void updateDisplay() {
        displayPanel.removeAll(); // Clearing the display panel before updating it

        // Looping through all events and applying filters
        for (Event event : events) {
            boolean isComplete = event instanceof Completable && ((Completable) event).isComplete(); // Checking if the event is complete
            boolean isDeadline = event instanceof Deadline; // Checking if the event is a deadline
            boolean isMeeting = event instanceof Meeting; // Checking if the event is a meeting

            // Skipping the event if it matches the filter conditions
            if (filterCompleteBox.isSelected() && isComplete) {
                continue; // Skip completed events if the filter is checked
            }

            if (filterDeadlineBox.isSelected() && isDeadline) {
                continue; // Skip deadline events if the filter is checked
            }

            if (filterMeetingBox.isSelected() && isMeeting) {
                continue; // Skip meeting events if the filter is checked
            }

            // Adding an EventPanel for each event that passes the filters
            displayPanel.add(new EventPanel(event));
        }

        // Refreshing the display panel after adding the filtered events
        displayPanel.revalidate(); // Revalidating the panel to update the layout
        displayPanel.repaint(); // Repainting the panel to reflect the changes
    }
}