package View.NakedObject;

import java.util.List;

public class ServerConfig implements NakedObject{
	
	private String IP;
	private String Port;
	
	public ServerConfig() {
		this.IP = "localhost";
		this.Port="6400";
	}

	@Override
	public List<String> getFieldNames() {
		return null;
	}

	@Override
	public void setFieldNewValue(String fieldName, String newValue) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public String getFieldValue(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

}
