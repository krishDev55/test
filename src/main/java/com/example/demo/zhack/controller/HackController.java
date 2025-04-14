package com.example.demo.zhack.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.zhack.Vo.Course;
import com.example.demo.zhack.Vo.Lecture;
import com.example.demo.zhack.Vo.User;

import okhttp3.internal.connection.Exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/v1/hac")
@CrossOrigin()
public class HackController {
	RestTemplate restTemplate= new RestTemplate();
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getAllCourse")
	public List<Course> getAllCourse() {
		String url="https://newdeploysuccessmarathi.onrender.com/api/getAllCourses";
		@SuppressWarnings("unchecked")
		Map<String, Object> obj= restTemplate.getForObject(url, Map.class);
		
		return (List<Course>)obj.get("course");
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getlecturesByCourseId/{id}")
	public Map<String, Object> getMethodName(@PathVariable String id,@RequestHeader String token) throws ClientAbortException {
		Map<String, Object> map= new HashMap<>();
		
		String url="https://newdeploysuccessmarathi.onrender.com/api/getlecturesByCourseId/"+id;        
		HttpHeaders headers= new HttpHeaders();
		headers.set("token",token);
		HttpEntity<String> entity= new HttpEntity<>(headers);
		ResponseEntity<Map> ex = null;
		
		try {
			 ex= restTemplate.exchange(url,
					HttpMethod.GET,
				    entity, 
				    Map.class);
	HttpStatusCode sc = ex.getStatusCode(); 
					if(sc.is4xxClientError()) {
						String msgError = (String)ex.getBody().get("message");
						map.put("message", msgError);
						return map;
					}
					
		} catch (Exception e) {
//			
			map.put("message","No lectures found for this course" );
//			map.put("status", ex.getStatusCode());
			return map;
		}
				
		Map<String,Object> body = ex.getBody();
		String massage =(String) body.get("message");
		map.put("message", massage);
		map.put("lectures", (List<Lecture>)body.get("lectures"));
				
		return map;
	
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	public Map<String,Object> login(@RequestBody User user) {
	System.out.println("UserName "+user.getEmail() + " Password : "+user.getPassword());
				String url="https://newdeploysuccessmarathi.onrender.com/api/user/login";
				ResponseEntity<Map> map=null;
				try {
					 map= restTemplate.postForEntity(url,  user, Map.class);
					
				} catch (Exception e) {
					System.out.println(user.getEmail()+" this emailNot p:  "+user.getPassword() );
				}
				String message = (String)map.getBody().get("message");

			@SuppressWarnings("unchecked")
			Map<String, User> list	=(Map<String,User>)map.getBody().get("user");
			System.out.println(list);
		return map.getBody();
	}
	
	
	// this method is Not Working Proparly
	@PostMapping("/register")
	private  Map<String,Object> register(@RequestBody User user) {
	
				String url="https://newdeploysuccessmarathi.onrender.com/api/user/login";	
				ResponseEntity<Map> map = restTemplate.postForEntity(url,  user, Map.class);
				String message = (String)map.getBody().get("message");

			@SuppressWarnings("unchecked")
			Map<String, User> list	=(Map<String,User>)map.getBody().get("user");
			System.out.println(list);
		return map.getBody();
	}
	
	
	
	
}
