package com.rjso.cursomc.services;

import java.util.Calendar;
import org.springframework.stereotype.Service;
import com.rjso.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Calendar instante) {
		Calendar cal = (Calendar) instante.clone();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal);
	}

}
