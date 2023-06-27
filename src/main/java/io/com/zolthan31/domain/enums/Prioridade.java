package io.com.zolthan31.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prioridade {
	
	BAIXA (0, "BAIXA"),
	MEDIA (1, "MEDIA"),
	ALTA  (2, "ALTA");
	
	private Integer code;
	private String title;

	public static Prioridade toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida!!" + code);
	}
	
	
}
