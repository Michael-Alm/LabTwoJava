import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

class EventListPanel extends JPanel {
    private final ArrayList<Event> events;
    private final JPanel displayPanel;
    private final JComboBox<String> sortDropDown;
    private final JCheckBox filterCompleteBox;
    private final JCheckBox filterDeadlineBox;
    private final JCheckBox filterMeetingBox;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        sortDropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Date", "Sort by Name (Reverse)", "Sort by Date (Reverse)"});
        sortDropDown.addActionListener(e -> sortEvents());

        filterCompleteBox = new JCheckBox("Hide Completed");
        filterDeadlineBox = new JCheckBox("Hide Deadlines");
        filterMeetingBox = new JCheckBox("Hide Meetings");

        ActionListener filterListener = e -> updateDisplay();

        filterCompleteBox.addActionListener(filterListener);
        filterDeadlineBox.addActionListener(filterListener);
        filterMeetingBox.addActionListener(filterListener);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            new AddEventModal(EventListPanel.this);
        });

        controlPanel.add(sortDropDown);
        controlPanel.add(filterCompleteBox);
        controlPanel.add(filterDeadlineBox);
        controlPanel.add(filterMeetingBox);
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);
    }

    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    private void sortEvents() {
        String selectedSort = (String) sortDropDown.getSelectedItem();
        if ("Sort by Name".equals(selectedSort)) {
            events.sort(Comparator.comparing(Event::getName));
        } else if ("Sort by Date".equals(selectedSort)) {
            events.sort(Comparator.comparing(Event::getDateTime));
        } else if ("Sort by Name (Reverse)".equals(selectedSort)) {
            events.sort((e1, e2) -> e2.getName().compareTo(e1.getName()));
        } else if ("Sort by Date (Reverse)".equals(selectedSort)) {
            events.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        }
        updateDisplay();
    }

    private void updateDisplay() {
        displayPanel.removeAll();

        for (Event event : events) {
            boolean isComplete = event instanceof Completable && ((Completable) event).isComplete();
            boolean isDeadline = event instanceof Deadline;
            boolean isMeeting = event instanceof Meeting;

            if (filterCompleteBox.isSelected() && isComplete) {
                continue;
            }

            if (filterDeadlineBox.isSelected() && isDeadline) {
                continue;
            }

            if (filterMeetingBox.isSelected() && isMeeting) {
                continue;
            }

            displayPanel.add(new EventPanel(event));
        }

        displayPanel.revalidate();
        displayPanel.repaint();
    }

}
