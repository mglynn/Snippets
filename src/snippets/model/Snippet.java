package snippets.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public final class Snippet {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty code = new SimpleStringProperty("");

    public Snippet() {

    }

    public Snippet(int id, String name, String code) {
        setId(id);
        setName(name);
        setCode(code);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        if (name.equalsIgnoreCase("")) {
            this.name.set("name");
        } else {
            this.name.set(name);
        }
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        if (code.equalsIgnoreCase("")) {
            this.code.set("code");
        } else {
            this.code.set(code);
        }
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
