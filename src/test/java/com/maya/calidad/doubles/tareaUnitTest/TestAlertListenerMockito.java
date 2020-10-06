package com.maya.calidad.doubles.tareaUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class TestAlertListenerMockito {

	private AlertListener alertMock;
	private Cuenta miCuenta;
	private AlertListener alert;
	
	@Before
	public void setUp() {
		
		miCuenta = new Cuenta(alert);
		
		//Creamos mock
		alertMock = mock(AlertListener.class);
		
	}
	
	@Test
	public void testAlertListener() {
				
		miCuenta.debit(950, miCuenta.getZona());
		
		verify(alertMock).sendAlert(miCuenta.getHolder() + ", your account balance is below 100");
		
		
				
	}

}
