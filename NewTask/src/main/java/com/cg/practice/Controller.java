package com.cg.practice;

import io.undertow.attribute.RequestMethodAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/hello-world-service")
public class Controller {
	//ArrayList<HelloWorldPojo> msgs = new ArrayList<HelloWorldPojo>();
	HashMap<Integer,HelloWorldPojo> hm=new HashMap<Integer,HelloWorldPojo>();
	
	@RequestMapping(value="/hello",method = RequestMethod.GET)
    public ResponseEntity<?>  retrival() throws EmptyException{
		
		if(hm.isEmpty()){
		
			 String error="no such data found";
			throw new EmptyException(error);
		}
		
        return new ResponseEntity<Collection<HelloWorldPojo>>(hm.values(),HttpStatus.OK);
        
	}

	
	@RequestMapping(value="/hello",method = RequestMethod.POST)
    public ResponseEntity<?>  greeting(@RequestBody HelloWorldPojo pojo ) {
		
		//HelloWorldPojo pojo= new HelloWorldPojo(id, msg);
	
		hm.put(pojo.getId(),pojo);
		
		return new ResponseEntity<String>("Stored Succesfully", HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/Hello/hello{id}")
	public HelloWorldPojo particular(@PathVariable int id){
		
		
		
		return hm.get(id);
	}*/
	
	@RequestMapping(value="/hello/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable(value="id") int id, @RequestBody HelloWorldPojo pojo) throws EmptyException{
		
		if(id!=pojo.getId()){
			 String error="no such data found";
				throw new EmptyException(error);
		}else if(hm.get(id)==null){
			 String error="no such data found";
				throw new EmptyException(error);
		}else{
			hm.replace(pojo.getId(), pojo);
			return new ResponseEntity<HelloWorldPojo>(pojo,HttpStatus.OK);
			}
	}
	
	@RequestMapping(value="/hello/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable(value="id") int id, @RequestBody HelloWorldPojo pojo) throws EmptyException{
		
		if(id!=pojo.getId()){
			String error="no such data found";
			throw new EmptyException(error);
		}else if(hm.get(id)==null){
			String error="no such data found";
			throw new EmptyException(error);
		}else{
			hm.remove(pojo.getId());
			return new ResponseEntity<HelloWorldPojo>(pojo,HttpStatus.OK);
			}
	}
	
	
        
}
