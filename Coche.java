import java.io.Serializable;

public class Coche implements Serializable {
	
	public Coche(String matricula, String marca) {
		this.matricula = matricula;
		this.marca = marca;
	}
	private String matricula;
	private String marca;
	
	public String toString() {
		return matricula+"@"+marca;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMatricula(String matricula) {
		this.matricula= matricula;
		
	}
	public void setMarca(String marca) {
		this.marca= marca;
		
	}
}
