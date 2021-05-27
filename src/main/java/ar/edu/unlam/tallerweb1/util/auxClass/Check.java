package ar.edu.unlam.tallerweb1.util.auxClass;

public class Check {
	
	public static Boolean empty(String str) {
		return str == null || str.isEmpty() || str.isBlank();
	}
	
	public static Boolean isInRange(Integer valor, Integer min, Integer max) {
		return valor != null && valor <= max && valor >= min;
	}
}