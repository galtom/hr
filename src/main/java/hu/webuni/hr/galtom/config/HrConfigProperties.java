package hu.webuni.hr.galtom.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="hr")
@Component
public class HrConfigProperties {
	
	private Salary salary = new Salary();
	
	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public static class Salary {
		private List<PayRaise> payraise = new ArrayList<PayRaise>();

		public List<PayRaise> getPayRaise() {
			return payraise;
		}

		public void setPayRaise(List<PayRaise> payraise) {
			this.payraise = payraise;
		}
		
	}
	
	public static class PayRaise {
		private float years;
		private int percent;
		
		public float getYears() {
			return years;
		}
		
		public void setYears(float years) {
			this.years = years;
		}
		
		public int getPercent() {
			return percent;
		}
		
		public void setPercent(int percent) {
			this.percent = percent;
		}
	}
}
