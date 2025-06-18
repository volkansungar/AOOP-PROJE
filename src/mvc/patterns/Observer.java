package mvc.patterns;

/*
 * Observers of the observer design pattern are pages in this implementation
 * Any change in the models(subject) will be updated to the observers
 * therefore the page will be refreshed with new information
 */
public interface Observer {
    void update();
}
