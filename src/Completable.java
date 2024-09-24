// Interface representing completable objects.
public interface Completable {
    // Marks the object as complete.
    void complete();

    // Checks if the object is complete.
    boolean isComplete();
}
