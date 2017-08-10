
public class ArvoreVermelhaPreta {

	//bit de cores do no
    private final int RED = 0;
    private final int BLACK = 1;
    
    public class Node {
    	
    	//no pode ter livro ou usuario
    	Livro livro;
    	Usuario usuario;
    	
        int key = -1;
        int cor = BLACK;
        Node esquerda = nil;
        Node direita = nil;
        Node pai = nil;
        
        //construtores um para cada tipo
        Node(int key) {
            this.key = key;
        } 
        
        Node(Usuario usuario){
        	this.usuario = usuario;
        	key = usuario.getId();
        }
        
        Node(Livro livro){
        	this.livro = livro;
        	key = livro.getId();
        }
    }

    //criar nodo nil (folhas)
    private final Node nil = new Node(-1); 
    
    //na inicializacao
    private Node root = nil;
    
    //retornar raiz
    public Node getRoot(){
    	return root;
    }

    //no + pai
    public void mostrarArvore(Node node) {
        if (node == nil) {
            return;
        }
        mostrarArvore(node.esquerda);
        System.out.print("\n"+((node.cor==RED)?"COR: RED":"COR: BLACK")+"\nKEY: "+node.key+"\nPAI: "+node.pai.key+"\n");
        mostrarArvore(node.direita);
    }
    
    // key + nome do livro
    public void printTreeLivro(Node node) {
        if (node == nil) {
            return;
        }
        printTreeLivro(node.esquerda);
        System.out.print("\n"+((node.cor==RED)?"COR: RED ":"COR: BLACK")+"\nKEY: "+node.key+"\nTITULO DO LIVRO: "+node.livro.getTitulo()+"\n");
        printTreeLivro(node.direita);
    }
    
    // key + nome do usuario
    public void printTreeUsuario(Node node) {
        if (node == nil) {
            return;
        }
        printTreeUsuario(node.esquerda);
        System.out.print("\n"+((node.cor==RED)?"COR: RED ":"COR: BLACK")+"\nKEY: "+node.key+"\nNOME DO USUARIO: "+node.usuario.getNome()+"\n");       
        printTreeUsuario(node.direita);
    }

    //necessario para o delete
    public Node findNode(Node findNode, Node node) {
        if (root == nil) {
            return null;
        }
        if (findNode.key < node.key) {
            if (node.esquerda != nil) {
                return findNode(findNode, node.esquerda);
            }
        } 
        else if (findNode.key > node.key) {
            if (node.direita != nil) {
                return findNode(findNode, node.direita);
            }
        } 
        else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }
    
    //procura e retorna no com terminado titulo
    public Node procurarLivro(String nome, Node no){ //passar a string do nome do livro e o no raiz
        if(no != nil){ //equanto nao chegar na raiz
            if(no.livro.getTitulo().equals(nome)){ //se achou com esse titulo retornar o no
               return no;               
            }             
            else { //se nao faz chamadas recursivas da funcao ate achar primeiro no filho esquerdo depois filho direito
            	Node noEncontrado = procurarLivro(nome, no.esquerda);           
                if(noEncontrado == null) {
                    noEncontrado = procurarLivro(nome, no.direita);
                }                               
                return noEncontrado;
             }
        }         
        else { //cai aqui se nao achar no com esse titilo de livro        	
            return null;
        }
    }  
    
  //procura e retorna no com terminado titulo
    public Node procurarLivro(int key, Node no){ //passar a string do nome do livro e o no raiz
        if(no != nil){ //equanto nao chegar na raiz
            if(no.livro.getId() == key){ //se achou com esse titulo retornar o no
               return no;               
            }             
            else { //se nao faz chamadas recursivas da funcao ate achar primeiro no filho esquerdo depois filho direito
            	Node noEncontrado = procurarLivro(key, no.esquerda);           
                if(noEncontrado == null) {
                    noEncontrado = procurarLivro(key, no.direita);
                }                               
                return noEncontrado;
             }
        }         
        else { //cai aqui se nao achar no com esse titilo de livro        	
            return null;
        }
    }  
    
    
    //procura e retorna no com terminado usuario
    public Node procurarUsuario(String nome, Node no){ //passar a string do nome do livro e o no raiz
        if(no != nil){
            if(no.usuario.getNome().equals(nome)){ //se achou com esse titulo retornar o no
               return no;
            } else { //se nao faz chamadas recursivas da funcao ate achar primeiro no filho esquerdo depois filho direito
            	Node noEncontrado = procurarUsuario(nome, no.esquerda);
                if(noEncontrado == null) {
                    noEncontrado = procurarUsuario(nome, no.direita);
                }
                return noEncontrado;
             }
        } else { //cai aqui se nao achar no com esse titilo de livro
            return null;
        }
    }
    
  //procura e retorna no com terminado usuario
    public Node procurarUsuario(int key, Node no){ //passar a string do nome do livro e o no raiz
        if(no != nil){
            if(no.usuario.getId() == key){ //se achou com esse titulo retornar o no
               return no;
            } else { //se nao faz chamadas recursivas da funcao ate achar primeiro no filho esquerdo depois filho direito
            	Node noEncontrado = procurarUsuario(key, no.esquerda);
                if(noEncontrado == null) {
                    noEncontrado = procurarUsuario(key, no.direita);
                }
                return noEncontrado;
             }
        } else { //cai aqui se nao achar no com esse titilo de livro
            return null;
        }
    }  
    
    
    //insercao
    public void insert(Node node) {
        Node temp = root;
        if (root == nil) {
            root = node;
            node.cor = BLACK;
            node.pai = nil;
        } else {
            node.cor = RED;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.esquerda == nil) {
                        temp.esquerda = node;
                        node.pai = temp;
                        break;
                    } else {
                        temp = temp.esquerda;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.direita == nil) {
                        temp.direita = node;
                        node.pai = temp;
                        break;
                    } else {
                        temp = temp.direita;
                    }
                }
            }
            fixTree(node); // primeiro recolorir segundo rotacionar
        }
    }

    //Todos os nos inseridos deverao passar aqui
    private void fixTree(Node node) {
        while (node.pai.cor == RED) {
            Node uncle = nil;
            if (node.pai == node.pai.pai.esquerda) {
                uncle = node.pai.pai.direita;

                if (uncle != nil && uncle.cor == RED) {
                    node.pai.cor = BLACK;
                    uncle.cor = BLACK;
                    node.pai.pai.cor = RED;
                    node = node.pai.pai;
                    continue;
                } 
                if (node == node.pai.direita) {
                    //rotacao dupla necessaria
                    node = node.pai;
                    rotateLeft(node);
                } 
                node.pai.cor = BLACK;
                node.pai.pai.cor = RED;                
                rotateRight(node.pai.pai);
            } else {
                uncle = node.pai.pai.esquerda;
                 if (uncle != nil && uncle.cor == RED) {
                    node.pai.cor = BLACK;
                    uncle.cor = BLACK;
                    node.pai.pai.cor = RED;
                    node = node.pai.pai;
                    continue;
                }
                if (node == node.pai.esquerda) {                    
                    node = node.pai;
                    rotateRight(node);
                }
                node.pai.cor = BLACK;
                node.pai.pai.cor = RED;                
                rotateLeft(node.pai.pai);
            }
        }
        root.cor = BLACK;
    }

    //rotacao a esquerda
    void rotateLeft(Node node) {
        if (node.pai != nil) {
            if (node == node.pai.esquerda) {
                node.pai.esquerda = node.direita;
            } else {
                node.pai.direita = node.direita;
            }
            node.direita.pai = node.pai;
            node.pai = node.direita;
            if (node.direita.esquerda != nil) {
                node.direita.esquerda.pai = node;
            }
            node.direita = node.direita.esquerda;
            node.pai.esquerda = node;
        } else {//precisa rotacionar a raiz
            Node right = root.direita;
            root.direita = right.esquerda;
            right.esquerda.pai = root;
            root.pai = right;
            right.esquerda = root;
            right.pai = nil;
            root = right;
        }
    }

    //rotacao a direita
    void rotateRight(Node node) {
        if (node.pai != nil) {
            if (node == node.pai.esquerda) {
                node.pai.esquerda = node.esquerda;
            } else {
                node.pai.direita = node.esquerda;
            }

            node.esquerda.pai = node.pai;
            node.pai = node.esquerda;
            if (node.esquerda.direita != nil) {
                node.esquerda.direita.pai = node;
            }
            node.esquerda = node.esquerda.direita;
            node.pai.direita = node;
        } else {//Need to rotate root
            Node left = root.esquerda;
            root.esquerda = root.esquerda.direita;
            left.direita.pai = root;
            root.pai = left;
            left.direita = root;
            left.pai = nil;
            root = left;
        }
    }

    
    //delete 
    
    void transplant(Node target, Node with){ 
          if(target.pai == nil){
              root = with;
          }else if(target == target.pai.esquerda){
              target.pai.esquerda = with;
          }else
              target.pai.direita = with;
          with.pai = target.pai;
    }
    
    boolean delete(Node z){
        if((z = findNode(z, root))==null)return false;
        Node x;
        Node y = z; 
        int y_original_color = y.cor;
        
        if(z.esquerda == nil){
            x = z.direita;  
            transplant(z, z.direita);  
        }else if(z.direita == nil){
            x = z.esquerda;
            transplant(z, z.esquerda); 
        }else{
            y = treeMinimum(z.direita);
            y_original_color = y.cor;
            x = y.direita;
            if(y.pai == z)
                x.pai = y;
            else{
                transplant(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }
            transplant(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor; 
        }
        if(y_original_color==BLACK)
            deleteFixup(x);  
        return true;
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.cor == BLACK){ 
            if(x == x.pai.esquerda){
                Node w = x.pai.direita;
                if(w.cor == RED){
                    w.cor = BLACK;
                    x.pai.cor = RED;
                    rotateLeft(x.pai);
                    w = x.pai.direita;
                }
                if(w.esquerda.cor == BLACK && w.direita.cor == BLACK){
                    w.cor = RED;
                    x = x.pai;
                    continue;
                }
                else if(w.direita.cor == BLACK){
                    w.esquerda.cor = BLACK;
                    w.cor = RED;
                    rotateRight(w);
                    w = x.pai.direita;
                }
                if(w.direita.cor == RED){
                    w.cor = x.pai.cor;
                    x.pai.cor = BLACK;
                    w.direita.cor = BLACK;
                    rotateLeft(x.pai);
                    x = root;
                }
            }else{
                Node w = x.pai.esquerda;
                if(w.cor == RED){
                    w.cor = BLACK;
                    x.pai.cor = RED;
                    rotateRight(x.pai);
                    w = x.pai.esquerda;
                }
                if(w.direita.cor == BLACK && w.esquerda.cor == BLACK){
                    w.cor = RED;
                    x = x.pai;
                    continue;
                }
                else if(w.esquerda.cor == BLACK){
                    w.direita.cor = BLACK;
                    w.cor = RED;
                    rotateLeft(w);
                    w = x.pai.esquerda;
                }
                if(w.esquerda.cor == RED){
                    w.cor = x.pai.cor;
                    x.pai.cor = BLACK;
                    w.esquerda.cor = BLACK;
                    rotateRight(x.pai);
                    x = root;
                }
            }
        }
        x.cor = BLACK; 
    }
    
    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.esquerda!=nil){
            subTreeRoot = subTreeRoot.esquerda;
        }
        return subTreeRoot;
    }
    
    public static void main(String[] args) {
    	
    	
    	
    }    
}