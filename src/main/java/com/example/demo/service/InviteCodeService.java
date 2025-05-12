package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ReferCodeSave;
import com.example.demo.repository.ReferCodeSaveRepo;

@Service
public class InviteCodeService {

	@Autowired ReferCodeSaveRepo referCodeRepo;

	public String generateInviteCode(String mobile) {
        String code = UUID.randomUUID().toString();
		Long mob=Long.parseLong(mobile);
		String code1 = getRCodeByMobile(mob);
		
        if(code1!=null) {
        	return code1;   //Always return older value if key are Present 
        }
        else {
        	saveReferCode(new ReferCodeSave(mob, code)); // Store the new Key and Value
        }
        return code;      // return the  new generated value
    }

    public Long getKey(String value) {
    	return referCodeRepo.findReferCodeSaveByReferCode(value);
    	}
 

    public void markAsUsed(String code) {
//        inviteCodes.put(code, true);
    	
    }
	
    public ReferCodeSave getReferCodeByMobile(Long mobile) {
    	return referCodeRepo.findById(mobile).get();
    }
    
    public String getRCodeByMobile(Long mobile) {
    	try {
    		return referCodeRepo.findById(mobile).get().getReferCode();
		} catch (Exception e) {
			return null;
		}
    	
    }
    
    public ReferCodeSave saveReferCode(ReferCodeSave referCodeSave) {
    	return referCodeRepo.save(referCodeSave);
    }
    
}
