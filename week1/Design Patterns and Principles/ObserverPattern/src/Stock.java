import java.util.List;

public interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}
