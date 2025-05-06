package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class InviteCodeService {

	public static final Map<Long, String> inviteCodes = new HashMap<>();

    public String generateInviteCode(String mobile) {
        String code = UUID.randomUUID().toString();
		Long mob=Long.parseLong(mobile);
        if(inviteCodes.containsKey(mob)) {
        	return inviteCodes.get(mob);   //Always return older value if key are Present 
        }
        else {
        	inviteCodes.put(mob, code); // Store the new Key and Value
        	
        }
        return code;      // return the  new generated value
    }

    public Long getKey(String value) {
    	if(inviteCodes.containsValue(value)) {
    		Long key = inviteCodes.keySet()
    								.stream()
    								.filter(e-> inviteCodes.get(e).equals(value))
    								.findAny()
    								 .get();
    		return key;
    	}
    	
//        return inviteCodes.containsKey(code) && !inviteCodes.get(code);
    	
    	return null;
    }

    public void markAsUsed(String code) {
//        inviteCodes.put(code, true);
    	
    }
	
}
