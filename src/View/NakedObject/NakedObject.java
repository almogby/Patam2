package View.NakedObject;

import java.util.List;

public interface NakedObject {
	public List<String> getFieldNames();
	public void setFieldNewValue(String fieldName, String newValue);
	public String getFieldValue (String fieldName);
}
