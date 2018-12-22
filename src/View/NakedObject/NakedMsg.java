package View.NakedObject;

import java.util.ArrayList;
import java.util.List;

public class NakedMsg implements NakedObject {
	
	private List<String> msgList = new ArrayList<String>();
	
	public NakedMsg(String fieldName) {
		this.msgList.add(fieldName);
	}
	
	public void addNakedMag(String fieldName) {
		this.msgList.add(fieldName);
	}

	@Override
	public List<String> getFieldNames() {
		return this.msgList;
	}

	@Override
	public void setFieldNewValue(String fieldName, String newValue) {
		for (int i=0;i<this.msgList.size();i++) {
			if (msgList.get(i).equals(fieldName))
				msgList.set(i, newValue);
		}
	}

	@Override
	public String getFieldValue(String fieldName) {
		for (int i=0;i<this.msgList.size();i++) {
			if (msgList.get(i).equals(fieldName))
				return msgList.get(i);
		}
		return null;
	}
	
	//test
}
