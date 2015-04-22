package message;

import java.io.StringReader;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message>{

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public Message decode(String msg) throws DecodeException {
		System.out.println("MSG: " + msg);
		JsonReader reader = Json.createReader(new StringReader(msg));
		JsonObject jsonMsg = reader.readObject();
		reader.close();
		
		Message resultMsg = new Message();
		resultMsg.setType(jsonMsg.getString("type"));
		
		for(Entry<String, JsonValue> property : jsonMsg.entrySet()) {
			if(!property.getKey().equals("type")) {
				resultMsg.addProperty(property.getKey(), property.getValue());
			}
		}
		
		return resultMsg;
	}

	@Override
	public boolean willDecode(String arg0) {
		return true;
	}
}
