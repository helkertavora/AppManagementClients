package  br.com.unifor.AppManagementClients.email;

public class Envelope {
	
	private String destinatario;
	private String copiar;
	private String titulo;
	private String message;
	
	public String getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getCopiar() {
		return copiar;
	}
	
	public void setCopiar(String copiar) {
		this.copiar = copiar;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	
	
}
