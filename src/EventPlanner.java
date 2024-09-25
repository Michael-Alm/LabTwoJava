import javax.swing.*;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Event Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            EventListPanel eventListPanel = new EventListPanel();
            frame.add(eventListPanel);

            addDefaultEvents(eventListPanel);

            frame.setVisible(true);
        });
    }

    static void addDefaultEvents(EventListPanel panel) {
        LocalDateTime deadlineTime = LocalDateTime.of(2024, 10, 1, 17, 0);
        LocalDateTime meetingStartTime = LocalDateTime.of(2024, 9, 29, 10, 0);
        LocalDateTime meetingEndTime = meetingStartTime.plusHours(1);

        panel.addEvent(new Deadline("Default Deadline", deadlineTime));
        panel.addEvent(new Meeting("Default Meeting", meetingStartTime, meetingEndTime, "Class 339"));
    }

}