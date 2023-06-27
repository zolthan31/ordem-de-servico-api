package io.com.zolthan31.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
	
	ABERTO    (0, "ABERTO"),
	ANDAMENTO (1, "ANDAMENTO"),
	ENCERRADO (2, "ENCERRADO");
	
	private Integer code;
	private String title;

	public static Status toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválida!!" + code);
	}	
	

}
