import javax.swing.*;
import java.awt.*;
import java.time.Duration;

class EventPanel extends JPanel {
    private final Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.add(new JLabel("Name: " + event.getName()));
        infoPanel.add(new JLabel("Date: " + event.getDateTime().toString()));

        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            Duration duration = meeting.getDuration();
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            String formattedDuration = hours + " hours and " + minutes + " minutes";

            infoPanel.add(new JLabel("Duration: " + formattedDuration));
            infoPanel.add(new JLabel("Location: " + meeting.getLocation()));
        }

        add(infoPanel, BorderLayout.CENTER);

        if (event instanceof Completable) {
            completeButton = new JButton("Complete");

            if (((Completable) event).isComplete()) {
                completeButton.setEnabled(false);
            }

            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeButton.setEnabled(false);
            });
            add(completeButton, BorderLayout.SOUTH);
        }
    }
}
