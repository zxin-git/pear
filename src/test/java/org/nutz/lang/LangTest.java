package org.nutz.lang;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import com.zxin.mvc.auth.data.User;

public class LangTest {
	
	public static void main(String[] args) {
		User[] users = new User[3];
		users[0] = new User();
		users[0].setUserName("zx");
		users[1] = new User();
		users[1].setUserName("zx1");
		users[2] = new User();
		users[2].setUserName("zx2");
		
//		HashMap<String,User> map = Lang.array2map(HashMap.class, users, "userName");
//		String[] ss = (String[]) Lang.array2array(users, String.class);
		Map<String, Object> map = Lang.map("{a:10, b:'ABC', c:true}");
		
		System.out.println(Json.toJson(map));
	}
}
