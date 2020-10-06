package com.maya.calidad.doubles.tareaUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

public class TestAlmacenamientoTransacciones {

	private Cuenta miCuenta;
	private Cuenta cuentaMock;
	private HashMap<Integer,Float> comisiones = new HashMap<Integer,Float>();
	private int id = 1;
	
	@Before
	public void setUp() {
		
		miCuenta = new Cuenta("Eloy",1000,1);
		cuentaMock = mock(Cuenta.class);
		
		//Creamos comportamiento que almacene transacciones en un HashMap
		when(cuentaMock.debitFake(anyInt(), anyInt())).thenAnswer(new Answer<Float>(){
			public Float answer(InvocationOnMock invocation) throws Throwable {
				int retiro = (Integer) invocation.getArguments()[0];
				int zona = (Integer) invocation.getArguments()[1];
				
				comisiones.put(id, retiro * miCuenta.getPorcentaje(zona));
				id++;
				
				float balance = miCuenta.getBalance() - retiro - (retiro * miCuenta.getPorcentaje(zona));
				
				return balance;
			}
		}
		);
		
		//Creamos comportamiento que suma todas las transacciones realizadas por una cuenta
		when(cuentaMock.getTotalComisionesFake()).thenAnswer(new Answer<Float>(){
			public Float answer(InvocationOnMock invocation) throws Throwable {
				
				float suma = 0;
				
				for(Float comision:comisiones.values()) {
					suma += comision;
				}
				
				return suma;
			}
		}
		);
		
	}
	
	@Test
	public void test() {
		
		//Ejercitamos mocks de cuentaMock para ver su comportamiento
		cuentaMock.debitFake(200, miCuenta.getZona());
		System.out.println("Total comisiones: " + cuentaMock.getTotalComisionesFake());
		System.out.println("HashMap size: " + comisiones.size());
		cuentaMock.debitFake(500, miCuenta.getZona());
		System.out.println("Total comisiones: " + cuentaMock.getTotalComisionesFake());
		System.out.println("HashMap size: " + comisiones.size());
		cuentaMock.debitFake(100, miCuenta.getZona());
		System.out.println("Total comisiones: " + cuentaMock.getTotalComisionesFake());
		System.out.println("HashMap size: " + comisiones.size());
		
		//Ejercitamos metodos de clase original
		miCuenta.debitFake(200, miCuenta.getZona());
		System.out.println("Total comisiones: " + miCuenta.getTotalComisiones());
		miCuenta.debitFake(500, miCuenta.getZona());
		System.out.println("Total comisiones: " + miCuenta.getTotalComisiones());
		miCuenta.debitFake(100, miCuenta.getZona());
		System.out.println("Total comisiones: " + miCuenta.getTotalComisiones());
		
		//Resultado esperado de transacciones 200, 500 y 100 en zona1 (comision = 0.01) totalComisiones = 8
		float expectedResult = 8;
		
		float result = cuentaMock.getTotalComisionesFake();
		
		assertThat(result,is(expectedResult));
		
		
	}

}
