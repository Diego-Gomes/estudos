import java.util.Calendar;
import java.util.GregorianCalendar;

public class Principal {

	public static void main(String[] args) {
		
		
		Calendar dataNacimento = new GregorianCalendar(1928, 12, 12);
		Funcionario funcionario = new Funcionario("Diego", 10, dataNacimento );
		System.out.println(funcionario);
		
		
		
	}
}
