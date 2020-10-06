package com.maya.calidad.doubles.tareaUnitTest;

public class Cuenta{

	static final float ZONA_1 = 1.01f;
	static final float ZONA_2 = 1.02f;
	static final float ZONA_3 = 1.03f;
	
	int balance;
	String holder;
	AlertListener alerts;
	int zona;
	int totalComisiones;
	int totalComisiones2;
	
	public Cuenta(AlertListener alerts) {
		
		this.alerts = alerts;
		this.holder = "Pancho";
		this.balance = 1000;
		this.zona = 1;
		
	}
	
	public Cuenta(String holder, int initialBalance, int zona) {
		
		this.holder = holder;
		this.balance = initialBalance;
		this.zona = zona;

	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public String getHolder() {
		return this.holder;
	}
	
	public int getZona() {
		return this.zona;
	}
	
	public int getTotalComisiones() {
		return this.totalComisiones;
	}
	
	//Metodo utilizado en TestAlmacenamientoTransacciones
	public float getTotalComisionesFake() {
		return this.totalComisiones2;
	}
	
	public float getPorcentaje(int zona) {
		
		if(zona == 1) {
			return 0.01f;
		}else if(zona == 2) {
			return 0.02f;
		}else {
			return 0.03f;
		}

	}
	
	void debit(int balance, int zona) {
		
		if(zona == 1) {
			this.totalComisiones += (balance * Cuenta.ZONA_1) - balance;
			this.balance -= balance * Cuenta.ZONA_1;
		}else if(zona == 2) {
			this.totalComisiones += (balance * Cuenta.ZONA_2) - balance;
			this.balance -= balance * Cuenta.ZONA_2;
		}else {
			this.totalComisiones += (balance * Cuenta.ZONA_3) - balance;
			this.balance -= balance * Cuenta.ZONA_3;
		}
		
		if(this.balance < 100) {
			//System.out.println(this.holder + ", your account balance is below 100");
			this.alerts.sendAlert(this.holder + ", your account balance is below 100");

		}
		
	}
	
	//Metodo utilizado en TestAlmacenamientoTransacciones
	public float debitFake(int balance, int zona) {
		
		float comision = balance * getPorcentaje(zona);
		
		this.totalComisiones += comision;
		this.balance -= (balance - comision);
		
		if(this.balance < 100) {
			//System.out.println(this.holder + ", your account balance is below 100");
			this.alerts.sendAlert(this.holder + ", your account balance is below 100");
		}
		
		return balance;
	}
	
	void credit(int balance, int zona) {
		this.balance += balance;
	}

	void setAlertListener(AlertListener listener) {
		this.alerts = listener;
	}
	
	public float calcularComision(int transaccion, int zona) {
		
		return transaccion * getPorcentaje(zona);
		
	}
	
	@Override
	public String toString() {
		return "Holder: " + getHolder()
				+ "\nZona: " + this.zona
				+ "\nBalance: " + getBalance()
				+ "\nTotal Comisiones Cobradas: " + getTotalComisiones();
	}
	
}
