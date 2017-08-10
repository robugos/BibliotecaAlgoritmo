import java.util.ArrayList;
import java.util.List;

public class Livro {
   
   private int id;
   private String titulo;
   private Boolean disponibilidade;
   private Boolean reserva;
   private int usuario = 0; // valor 0 é sem usuario  
   private List<Integer> registroUsuarios = new ArrayList<Integer>();
    
   public Livro(int id, String titulo, Boolean disponibilidade,Boolean reserva) {
       this.id = id;
       this.titulo = titulo;
       this.disponibilidade = disponibilidade;
       this.reserva = reserva;
   }
   public Livro(int id){
	   this.id = id;
   }
   
   public Livro(){
	   
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public String getTitulo() {
       return titulo;
   }

   public void setTitulo(String titulo) {
       this.titulo = titulo;
   }

  
   public Boolean getDisponibilidade() {
       return disponibilidade;
   }

   public void setDisponibilidade(Boolean disponibilidade) {
       this.disponibilidade = disponibilidade;
   }   
   
   public Boolean getReserva() {
       return reserva;
   }

   public void setReserva(Boolean reserva) {
       this.reserva = reserva;
   }
   
   public int getUsuario() {
	   return usuario;
   }
   
   public void setUsuario(int usuario) {
	   this.usuario = usuario;
   }
   
   public List<Integer> getRegistroUsuarios() {
		return registroUsuarios;
   }
   
   public void setRegistroUsuarios(List<Integer> registroUsuarios) {
		this.registroUsuarios = registroUsuarios;
   }
   
   public void addEmprestimo(int keyUsuario){
		this.registroUsuarios.add(keyUsuario);
	} 
}
