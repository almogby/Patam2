package View.NakedObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ServerConfig implements NakedObject{
	
	private Map<String, String> serverFields = new HashMap<>();
	
	
	public ServerConfig() {
		serverFields.put("Server", "localhost");
		serverFields.put("Port", "6400");
	}

	@Override
	public List<String> getFieldNames() {
		return new ArrayList<>(serverFields.keySet());
	}

	@Override
	public void setFieldNewValue(String fieldName, String newValue) {
		serverFields.put(fieldName, newValue);
		System.out.println("New FieldName:"+fieldName+" "+"New FieldValue:"+newValue);
		
	}

	@Override
	public String getFieldValue(String fieldName) {
		return serverFields.get(fieldName);
	}
	
	public String getIP() {
        return serverFields.get("Server");
    }

    public String getPort() {
        return serverFields.get("Port");
    }

}
