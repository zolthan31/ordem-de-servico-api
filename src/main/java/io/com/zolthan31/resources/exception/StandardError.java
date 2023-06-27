package io.com.zolthan31.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String error;
	
	public StandardError() {
		super();		
	}
	
	
	

}
