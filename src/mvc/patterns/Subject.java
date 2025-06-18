package mvc.patterns;

/*
 * Subject of the observer pattern is the observed object
 * Schedule and reservation lists are subjects
 * so any change on them will be updated to their assigned observers (pages)
 */

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
