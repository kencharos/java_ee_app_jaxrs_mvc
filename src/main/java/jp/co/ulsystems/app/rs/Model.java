package jp.co.ulsystems.app.rs;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class Model extends HashMap<String, Object>{

    private Object form;
    
    private Map<String, String> errors = new HashMap<>();

    public Object getForm() {
        return form;
    }

    public void setForm(Object form) {
        this.form = form;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    public boolean hasErrors(String key) {
        return errors.containsKey(key);
    }
    
}
