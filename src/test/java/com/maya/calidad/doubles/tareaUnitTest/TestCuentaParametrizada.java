package com.maya.calidad.doubles.tareaUnitTest;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class TestCuentaParametrizada {
	
	@Parameters
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"Eloy",1000,1,500,495},{"Adriana",300,2,100,198},{"Juana",500,3,100,397}
		});
	}
	
	private String holder;
	private int initBalance;
	private int zone;
	private int retiro;
	private int expected;
	
	//Constructor de prueba
	public TestCuentaParametrizada(String holder, int initBalance, int zone, int retiro, int expected) {
		this.holder = holder;
		this.initBalance = initBalance;
		this.zone = zone;
		this.retiro = retiro;
		this.expected = expected;
	}
	

	@Test
	public void ZonasTest() {
		
		//Creamos cuenta a probar
		Cuenta cuenta = new Cuenta(holder, initBalance, zone);
		
		//Ejercitamos
		cuenta.debit(retiro, zone);
		
		//Resultado del ejercicio
		int result = cuenta.getBalance();
		
		System.out.println(cuenta + "\n");
		
		//Verificamos resultados
		Assert.assertEquals(expected,result,0);
		
	}

}
