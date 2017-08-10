import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private int id;
    private String nome;
    private int numeroEmprestimos;
	private Boolean ativo;
	private List<Integer> registroLivros = new ArrayList<Integer>();
	private List<Integer> livrosEmprestados = new ArrayList<Integer>();
    
	
	public Usuario(int id,String nome,int numeroEmprestimos,Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.numeroEmprestimos = numeroEmprestimos;
        this.ativo = ativo;
    }
    
    public Usuario(int id){
 	   this.id = id;
    }
    
    public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getNumeroEmprestimos() {
		return numeroEmprestimos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNumeroEmprestimos(int numeroEmprestimos) {
		this.numeroEmprestimos = numeroEmprestimos;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<Integer> getRegistroLivros() {
		return registroLivros;
	}

	public void setRegistroLivros(List<Integer> registroLivros) {
		this.registroLivros = registroLivros;
	}

	public void addRegistro(int keyLivro){
		this.registroLivros.add(keyLivro);
	}
	
	
	public List<Integer> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(List<Integer> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}
	
	public void addEmprestimo(int keyLivro){
		this.livrosEmprestados.add(keyLivro);
	}
	
	public void remEmprestimo(int keyLivro){
		this.livrosEmprestados.remove(this.livrosEmprestados.indexOf(keyLivro));
	}
	
}
