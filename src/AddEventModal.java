import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

class AddEventModal extends JDialog {
    private JTextField endDateField;
    private JTextField locationField;
    private JLabel endDateLabel;
    private JLabel locationLabel;

    public AddEventModal(EventListPanel parent) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Add Event", true);
        setLayout(new GridLayout(0, 2));

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Deadline", "Meeting"});
        endDateField = new JTextField();
        locationField = new JTextField();

        endDateLabel = new JLabel("End Date (YYYY-MM-DD HH:MM):");
        locationLabel = new JLabel("Location:");

        add(new JLabel("Type:"));
        add(typeCombo);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Date (YYYY-MM-DD HH:MM):"));
        add(dateField);


        endDateLabel.setVisible(false);
        endDateField.setVisible(false);
        locationLabel.setVisible(false);
        locationField.setVisible(false);

        add(endDateLabel);
        add(endDateField);
        add(locationLabel);
        add(locationField);

        typeCombo.addActionListener(e -> {
            boolean isMeeting = Objects.equals(typeCombo.getSelectedItem(), "Meeting");
            toggleMeetingFields(isMeeting);
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                LocalDateTime date = LocalDateTime.parse(dateField.getText().replace(" ", "T"));
                if (Objects.equals(typeCombo.getSelectedItem(), "Deadline")) {
                    parent.addEvent(new Deadline(name, date));
                } else {
                    LocalDateTime endDate = LocalDateTime.parse(endDateField.getText().replace(" ", "T"));
                    String location = locationField.getText();
                    parent.addEvent(new Meeting(name, date, endDate, location));
                }
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.");
            }
        });

        add(addButton);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void toggleMeetingFields(boolean isVisible) {
        endDateLabel.setVisible(isVisible);
        endDateField.setVisible(isVisible);
        locationLabel.setVisible(isVisible);
        locationField.setVisible(isVisible);
    }
}
