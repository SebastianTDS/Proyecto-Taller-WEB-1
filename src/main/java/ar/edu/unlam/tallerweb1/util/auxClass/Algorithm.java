package ar.edu.unlam.tallerweb1.util.auxClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import java.time.Duration;;

public class Algorithm {

	private final static int SEGUNDOS_EN_MINUTO = 60;
	private final static int MINUTOS_EN_HORA = 60;
	private final static int HORAS_EN_DIA = 24;
	private final static int DIAS_EN_SEMANA = 7;
	private final static int SEMANAS_EN_MES = 4;
	private final static int MESES_EN_ANIO = 12;

	public static String getTiempoTranscurrido(LocalDateTime desde) {
		Period diferenciaDias = Period.between(desde.toLocalDate(), LocalDate.now());
		Duration diferenciaSegundos = Duration.between(desde, LocalDateTime.now());

		if (diferenciaSegundos.getSeconds() >= SEGUNDOS_EN_MINUTO * MINUTOS_EN_HORA * HORAS_EN_DIA) {
			if (diferenciaDias.getDays() < DIAS_EN_SEMANA)
				return "Hace " + diferenciaDias.getDays() + " día/s.";
			else if (diferenciaDias.getDays() < DIAS_EN_SEMANA * SEMANAS_EN_MES)
				return "Hace " + diferenciaDias.getDays() / DIAS_EN_SEMANA + " semana/s.";
			else if (diferenciaDias.getDays() < DIAS_EN_SEMANA * SEMANAS_EN_MES * MESES_EN_ANIO)
				return "Hace " + diferenciaDias.getMonths() + " mes/es.";
			else
				return "Hace " + diferenciaDias.getYears() + " año/s.";

		} else {
			if (diferenciaSegundos.getSeconds() < SEGUNDOS_EN_MINUTO)
				return "Hace " + diferenciaSegundos.getSeconds() + " segundo/s.";
			else if (diferenciaSegundos.getSeconds() < SEGUNDOS_EN_MINUTO * MINUTOS_EN_HORA)
				return "Hace " + diferenciaSegundos.getSeconds() / SEGUNDOS_EN_MINUTO + " minuto/s.";
			else
				return "Hace " + diferenciaSegundos.getSeconds() / (SEGUNDOS_EN_MINUTO * MINUTOS_EN_HORA) + " hora/s.";
		}
	}

}
