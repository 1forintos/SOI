package message;

import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message>{

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public String encode(Message msg) throws EncodeException {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		jsonBuilder.add("type", msg.getType());
		for(Entry<String, Object> prop : msg.getProperties().entrySet()) {
			if(prop.getValue() instanceof Integer) {
				jsonBuilder.add(prop.getKey(), (Integer) prop.getValue());
			} else if(prop.getValue() instanceof String) {
				jsonBuilder.add(prop.getKey(), (String) prop.getValue());
			}
			
		}
		
		return jsonBuilder.build().toString();
	}
}
