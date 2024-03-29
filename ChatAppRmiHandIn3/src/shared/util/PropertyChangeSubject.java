package shared.util;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    void addPropertyListener(PropertyChangeListener listener);
    void addPropertyListener(String eventName, PropertyChangeListener listener);
    void removePropertyListener(PropertyChangeListener listener);
    void removePropertyListener(String eventName, PropertyChangeListener listener);
}
