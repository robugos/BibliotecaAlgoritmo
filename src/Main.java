import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	
		//Instanciar 2 arvores uma pra livro outra pra usuario
    	ArvoreVermelhaPreta rbt1 = new ArvoreVermelhaPreta();
    	ArvoreVermelhaPreta rbt2 = new ArvoreVermelhaPreta();
    	
    	int idLivro = 0;
    	int idUsuario = 0;
    	
		Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n1 - CADASTRAR LIVRO\n"
                    + "2 - CADASTRAR USUARIO\n"
                    + "3 - DESCADASTRAR LIVRO\n"
                    + "4 - DESCADASTRAR USUARIO\n"
                    + "5 - PROCURAR LIVRO\n"
                    + "6 - EMPRESTAR LIVRO\n" 
                    + "7 - DEVOLVER LIVRO\n"
                    + "8 - IMPRIMIR ARVORE DE LIVRO\n"
                    + "9 - IMPRIMIR ARVORE DE USUARIO\n"
                    + "10 - HISTÓRICO POR USUARIO\n"
                    + "11 - HISTORICO POR LIVRO\n"); //x                 
            
            System.out.println("DIGITE SUA OPCAO: ");
            String choice = scan.next();
//            int escolha = scan.nextInt();
            int escolha = 0;
            try{
            	escolha = Integer.parseInt(choice);
            }catch(Exception e){
            	escolha = -1;
            };
            		
            
            String sentence ="aed";
           
            switch (escolha) {
                case 1:
                	System.out.println("DIGITE O TITULO DO LIVRO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String titulo = sentence;
                        if (titulo.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        idLivro = idLivro + 1;
                    	Livro l1 = new Livro(idLivro,titulo,true,false);
                    	ArvoreVermelhaPreta.Node noLivro = rbt1.new Node(l1);
                    	rbt1.insert(noLivro);
                		System.out.println("\nDIGITE O TITULO DO LIVRO:");                    	
                    }
                    rbt1.printTreeLivro(rbt1.getRoot());
                    break;
                    
                case 2:
                	System.out.println("DIGITE O NOME DO USUARIO:");
                	sentence = scan.nextLine().toUpperCase();

                	while (!sentence.equals("SAIR")) {
                	
                    	sentence = scan.nextLine().toUpperCase();
                        String nome = sentence;
                        if (nome.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        idUsuario = idUsuario + 1;
                        Usuario u1 = new Usuario(idUsuario,nome,0,true); //inicia usuario com 0 livros locados e ativo true
                        
                    	ArvoreVermelhaPreta.Node noUsuario = rbt2.new Node(u1);
                    	rbt2.insert(noUsuario);
                		System.out.println("\nDIGITE O NOME DO USUARIO:");
                    }
                	rbt2.printTreeUsuario(rbt2.getRoot());
                    break;
                    
                case 3: 
                	System.out.println("DIGITE O NOME DO LIVRO A SER DELETADO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String titulo = sentence;
                        if (titulo.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        
                        try{
                        	ArvoreVermelhaPreta.Node encontrado;
                        	encontrado = rbt1.procurarLivro(titulo,rbt1.getRoot());
                        	rbt1.delete(encontrado);
                        	System.out.println("LIVRO DELETADO");
                        	
                        }catch(Exception e){
                        	System.out.println("LIVRO NAO ENCONTRADO");
                        }
                        
                		System.out.println("\nDIGITE O NOME DO LIVRO A SER DELETADO:");                    	
                    }
                    rbt1.printTreeLivro(rbt1.getRoot());
                    break;

                case 4:
                	System.out.println("DIGITE O NOME DO USUARIO A SER DELETADO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String nome = sentence;
                        if (nome.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        
                        try{
                        	ArvoreVermelhaPreta.Node encontrado;
                        	encontrado = rbt2.procurarUsuario(nome,rbt2.getRoot());
                        	rbt2.delete(encontrado);
                        	System.out.println("USUARIO DELETADO");
                        	
                        }catch(Exception e){
                        	System.out.println("USUARIO NAO ENCONTRADO");
                        }
                        
                		System.out.println("\nDIGITE O NOME DO USUARIO A SER DELETADO:");                    	
                    }
                    rbt2.printTreeUsuario(rbt2.getRoot());
                    break;
                case 5:
                	System.out.println("DIGITE O NOME DO LIVRO A SER PROCURADO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String titulo = sentence;
                        if (titulo.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        
                        try{
                        	ArvoreVermelhaPreta.Node encontrado;
                        	encontrado = rbt1.procurarLivro(titulo,rbt1.getRoot());
                        	System.out.println("LIVRO: "+encontrado.livro.getTitulo()+"\nDISPONIBILIDADE: " + ((encontrado.livro.getDisponibilidade()==true)?"DISPONIVEL":"INDISPONIVEL")+"\nRESERVA: "+((encontrado.livro.getReserva()==true)?"RESERVADO":"DISPONIVEL"));
                        	
                        	
                        }catch(Exception e){
                        	System.out.println("LIVRO NAO ENCONTRADO");
                        } 
                		System.out.println("\nDIGITE O NOME DO LIVRO A SER PROCURADO:");                    	
                    }
                    rbt1.printTreeLivro(rbt1.getRoot());
                    break;
                    
                // O usuário pode solicitar empréstimo do livro; 
                case 6:               	
                	Boolean flagUsuario = false;
                	Boolean flagLivro = false;
                	
                	ArvoreVermelhaPreta.Node encontradoUsuario = null; //inicia como null
                	ArvoreVermelhaPreta.Node encontradoLivro = null; //inicia como null
                	
                	System.out.println("DIGITE O NOME DO USUARIO PARA EMPRESTIMO:");
                	String fake = scan.nextLine().toUpperCase(); //so para controle
                	String setenceUsuario = scan.nextLine().toUpperCase(); //essa eh verdadeira
                	
                	if (setenceUsuario.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                	
                	System.out.println("DIGITE O TITULO DO LIVRO PARA EMPRESTIMO:");
                	String sentenceLivro = scan.nextLine().toUpperCase();
                	
                	if (sentenceLivro.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                	
                	while(!setenceUsuario.equals("SAIR") || !sentenceLivro.equals("SAIR")){
                		
                    	try{
                    		//ArvoreVermelhaPreta.Node encontradoUsuario;
                        	encontradoUsuario = rbt2.procurarUsuario(setenceUsuario,rbt2.getRoot());
                        	System.out.println("\nUSUARIO: "+encontradoUsuario.usuario.getNome());
                        	flagUsuario = true;
	
                    	}catch(Exception e){
                    		System.out.println("USUARIO NAO ENCONTRADO");
                    	}
                    	
                    	try{               		
                        	//ArvoreVermelhaPreta.Node encontradoLivro;
                        	encontradoLivro = rbt1.procurarLivro(sentenceLivro, rbt1.getRoot());
                            //System.out.println("LIVRO: "+encontradoLivro.livro.getTitulo());
                            flagLivro = true;

                    	}catch(Exception e){
                    		System.out.println("LIVRO NAO ENCONTRADO");
                    	}
                    	
                    	//agora sim pode locar livro
                    	if(flagLivro==true && flagUsuario==true){
                    		if (encontradoUsuario.usuario.getAtivo() != true || encontradoUsuario.usuario.getNumeroEmprestimos()==3){ //usuario em pendencia(quando nao devolve livro) ou atingio numero de emprestimos
                        		System.out.println("USUARIO COM PENDENCIA OU ATINGIU MAXIMO DE EMPRESTIMO");
                        	}
                    		//aqui tem q ter se o usuario ja locou livro
                    		else if(encontradoUsuario.usuario.getNumeroEmprestimos()<3 && encontradoLivro.livro.getDisponibilidade()==true && encontradoLivro.livro.getReserva()==false){
                        		
                    			if (encontradoUsuario.usuario.getId()!= encontradoLivro.livro.getUsuario()){
                    				System.out.println("LIVRO EMPRESTADO: "+encontradoLivro.livro.getTitulo()+"\n");   
                            		encontradoUsuario.usuario.setNumeroEmprestimos(encontradoUsuario.usuario.getNumeroEmprestimos()+1); //incrementa em 1
                                	encontradoLivro.livro.setDisponibilidade(false);
                                	encontradoLivro.livro.setUsuario(encontradoUsuario.usuario.getId());
                                	
                                	//adicionar na  de registro e da lista emprestimos atuais
                                	encontradoUsuario.usuario.addRegistro(encontradoLivro.livro.getId());
                                	encontradoUsuario.usuario.addEmprestimo(encontradoLivro.livro.getId());
                                	encontradoLivro.livro.addEmprestimo(encontradoUsuario.usuario.getId());
                    			}
                    			else{
                    				System.out.println("LIVRO INDISPONIVEL");
                    			}                    			
                        	}
                    		else{
                    			System.out.println("LIVRO INDISPONIVEL");
                    		}
                    		
                    	}
                    	
                    	System.out.println("\nDIGITE O NOME DO USUARIO PARA EMPRESTIMO:");
                    	setenceUsuario = scan.nextLine().toUpperCase(); //essa eh verdadeira
                    	if (setenceUsuario.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                    	
                    	System.out.println("\nDIGITE O TITULO DO LIVRO PARA EMPRESTIMO:");
                		sentenceLivro = scan.nextLine().toUpperCase();
                		if (sentenceLivro.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo	
                		
                		//resetar flag
                		flagUsuario = false;
                    	flagLivro = false;                		               		
                	}
                	break;
                	
                case 7: 
                	System.out.println("DIGITE O TITULO DO LIVRO PARA DEVOLUCAO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String titulo = sentence;
                        if (titulo.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        
                        try{
                        	ArvoreVermelhaPreta.Node encontrado;
                        	encontrado = rbt1.procurarLivro(titulo,rbt1.getRoot());
                        	//System.out.println("Livro com titulo: "+encontrado.livro.getTitulo()+" Disponibilidade: " + encontrado.livro.getDisponibilidade()+" Reserva: "+encontrado.livro.getReserva());
                        	
                        	if(encontrado.livro.getUsuario()!=0){
                        		encontrado.livro.setDisponibilidade(true);//livro disponivel                    	
                            	ArvoreVermelhaPreta.Node usuarioComOLivro = rbt2.procurarUsuario(encontrado.livro.getUsuario(),rbt2.getRoot());
                            	usuarioComOLivro.usuario.remEmprestimo(encontrado.livro.getId()); //removi dos livros emprestados                         	
                            	usuarioComOLivro.usuario.setNumeroEmprestimos(usuarioComOLivro.usuario.getNumeroEmprestimos()-1); //diminur livros emprestados
                            	encontrado.livro.setUsuario(0);
                            	System.out.println("LIVRO DEVOLVIDO");
                        	}
                        	else{
                        		System.out.println("LIVRO JA ESTA NO ACERVO");
                        	}
                        	                      	
                        	
                        }catch(Exception e){
                        	System.out.println("LIVRO NAO ENCONTRATO");
                        } 
                		System.out.println("\nDIGITE O TITULO DO LIVRO PARA DEVOLUCAO:");                    	
                    }
                    rbt1.printTreeLivro(rbt1.getRoot());
                    
                			                	
                	break;
                	
                case 8:
                	rbt1.mostrarArvore(rbt1.getRoot());
                	break;
                	
                case 9:
                	rbt2.mostrarArvore(rbt2.getRoot());
                	break;
                	
                //mostrar registro de livros por usuario
                case 10:
                	System.out.println("DIGITE O NOME DO USUARIO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String nome = sentence;
                        if (nome.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        
                        try{
                        	ArvoreVermelhaPreta.Node encontrado;
                        	encontrado = rbt2.procurarUsuario(nome,rbt2.getRoot());                        	
                        	                        	
                        	ArvoreVermelhaPreta.Node buscaNo;
                        	
                        	
                        	if (!encontrado.usuario.getLivrosEmprestados().isEmpty()){
                        		//Registro de todos os livros atuais do usuario
                        		System.out.println("\nLIVROS EMPRESTADOS:");
                            	for(int key = 0 ; key <encontrado.usuario.getLivrosEmprestados().size();key++){
                            		buscaNo = rbt1.procurarLivro(encontrado.usuario.getLivrosEmprestados().get(key),rbt1.getRoot());
                            		System.out.println(buscaNo.livro.getTitulo());
                            	}
                        	}
                        	else{
                        		System.out.println("USUARIO SEM LOCACAO");
                        	}
                        	                       	
                        	if(!encontrado.usuario.getRegistroLivros().isEmpty()){
                        		//Registro de todos os livros q usuario ja locou
                        		System.out.println("\nHISTORICO DE EMPRESTIMOS:");                        		
                            	for(int key = 0 ; key <encontrado.usuario.getRegistroLivros().size();key++){
                            		buscaNo = rbt1.procurarLivro(encontrado.usuario.getRegistroLivros().get(key),rbt1.getRoot());
                            		System.out.println(buscaNo.livro.getTitulo());    	
                            	}
                        	}
                        	else{
                        		System.out.println("USUARIO SEM HISTORICO DE EMPRESTIMOS");
                        	}                        	        	
                        	
                        }catch(Exception e){
                        	System.out.println("USUARIO INEXISTENTE");
                        }
                        
                		System.out.println("\nDIGITE O NOME DO USUARIO:");                    	
                    }
                    break;  
                    
                  //mostrar registro de livros por usuario
                case 11:
                	System.out.println("DIGITE O NOME DO LIVRO:");
                	sentence = scan.nextLine().toUpperCase();
                	
                    while (!sentence.equals("SAIR")) {
                    	sentence = scan.nextLine().toUpperCase();
                        String titulo = sentence;
                        if (titulo.equals("SAIR")) break; //para nao iserir sair como se fosse um titilo
                        
                        try{
                        	ArvoreVermelhaPreta.Node encontrado;
                        	encontrado = rbt1.procurarLivro(titulo,rbt1.getRoot());                        	
                        	                        	
                        	ArvoreVermelhaPreta.Node buscaNo;
                        	
                        	
                        	if (!encontrado.livro.getRegistroUsuarios().isEmpty()){
                        		//Registro de todos os usuarios que locaram 
                        		System.out.println("USUARIOS QUE REALIZARAM EMPRESTIMO DO LIVRO:");
                            	for(int key = 0 ; key <encontrado.livro.getRegistroUsuarios().size();key++){
                            		buscaNo = rbt2.procurarUsuario(encontrado.livro.getRegistroUsuarios().get(key),rbt2.getRoot());
                            		System.out.println(buscaNo.usuario.getNome());
                            	}
                        	}
                        	else{
                        		System.out.println("LIVRO SEM HISTORICO DE EMPRESTIMOS");
                        	}
                        	                       	          	        	
                        	
                        }catch(Exception e){
                        	System.out.println("LIVRO SEM HISTORICO DE EMPRESTIMOS");
                        }
                        
                		System.out.println("DIGITE O TITULO DO LIVRO:");                    	
                    }
                    break;                	
                    
                default:
                	System.out.println("OPCAO INVALIDA");
            }
        }
	}	
}

