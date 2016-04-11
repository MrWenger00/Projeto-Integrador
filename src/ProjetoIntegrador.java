/*Desenvolvido por Evandro Augusto de Oliveira Marcolino, Felipe Silva, Guilherme Rocha Wenger e João Paulo Nunes*/

//Bibliotecas utilizadas
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ProjetoIntegrador {
 	//variaveis globais
    static JFrame       janela;
    static ImageIcon    fundo   = new ImageIcon("cenario.jpg");
    static ImageIcon    passaro = new ImageIcon("angry123.png");
    static ImageIcon    ninho   = new ImageIcon("alvo.png");
    static ImageIcon    boom   = new ImageIcon("kaboom.png");
    static JLabel       label;
    static JLabel       label1;
    static JLabel       rota;
    static JLabel       msg;
    static JLabel       titulo;
    static JLabel       alvo;
    static JLabel       explosao;
    static JPanel       painel;
    static JProgressBar barra;	    
    static int forca;
    static boolean passou = false;
    	   	    
	public static void main(String[] args){
		
		CriarJanela();
		MostrarJanela();
		TravarTela();			
		MovimentarBarra();
		
		if(passou == true){
			MovimentarPassaro();
		}
	}
	//cria o frame			
	static void CriarJanela() {
	    janela = new JFrame("Projeto Integrador");
	    janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		    
	}
	
	//adciona os labels e exibe a janela
	static void MostrarJanela(){			        	        
		IniciarComponentes();
        janela.add(label);//adciona imagem ao como plano de fundo
        label.add(label1);  
        label.add(barra);
        label.add(msg);
        label.add(titulo);
        label.add(alvo);
        label.add(explosao);
        janela.getContentPane().setBackground(Color.BLACK); 
        janela.setVisible(true);
	}
	/*Função que cria os objetos do jogo 
	 * */
	static void IniciarComponentes(){
		//cria o cenario do jogo
		label = new JLabel(fundo);
		label.setLayout(null);
		label1 = new JLabel(passaro);
		label1.setBounds(78, 340, 65,65 );
		//cria a mensagem acima da barra de força
		titulo =new JLabel("Força do lançamento");
		titulo.setBounds(10,30,250,30);
		titulo.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 10));
		titulo.setVisible(true);
		//cria a mensagem embaixo da barra de força
		msg = new JLabel("Pressione a barra de espaço para lançar");
		msg.setBounds(10,55,250,30);
		msg.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 10));
		msg.setVisible(true);
		//criar alvo
		alvo = new JLabel(ninho); 
		alvo.setLayout(null);
		alvo.setLocation(590,290);
		alvo.setVisible(true);
		alvo.setSize(200,300);
		//criar a barra de progresso
		barra = new JProgressBar();
		barra.setMinimum(0); //valor inicial da barra
		barra.setMaximum(100);// valor final da barra			
		barra.setLocation(10,50);
		barra.setSize(200,15);//largura e altura
		barra.setStringPainted(true);     //exibir os numeros com a porcentagem na barra
		barra.setBackground(Color.YELLOW);//cor de fundo da barra de força
		barra.setForeground(Color.blue); // cor do progresso da barra de força
		barra.setIndeterminate(false); // tipo de progresso
		barra.setVisible(true);			//deixa a barra visivel
		//criar a explosão
		explosao = new JLabel(boom);
		explosao.setBounds(70, 250, 200, 200);
		explosao.setVisible(false);
		
		
	}
	//faz o movimento da barra de força
	static void MovimentarBarra(){
		for(int i = 0; i <= 100; i++){
            barra.setValue(i);
            try{
				Thread.sleep(15);//delay de 1/10 de segundo
			}catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            ChamarAcoes();
            if(passou){
            	break;
            }
            if(i == 100){
            	for(i = 100; i >= 0; i--){
            		barra.setValue(i);
    	            try{
    					Thread.sleep(15);
    				}catch (InterruptedException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    	            ChamarAcoes();
    	            if(passou){
    	            	break;
    	            }
            	}
            }
        }
	}
	
	static void MovimentarPassaro(){			
		
		int x1 = 77;
		double x =0;
		double angulo = 30*(Math.PI /180);
		double t =1;
		double cos = (Math.cos(angulo));
		double v = forca;
		int y1 = 160;
		double y =160;
		double sen = (Math.sin(angulo));
		double gravidade = 9.8; 
		boolean executar = true;
		int controta = 0;
		explosao.setVisible(true);
		while(executar==true){				
			//calculo do movimento
			x= (x1+(v*cos*t));
			y= (y1+(v*sen*t)-((gravidade*(t*t))/2));
			t = t+0.01;
			//movimentar o personagem
			label1.setLocation((int)x,(((int)y-500)*(-1)));
			
			//desenhar rota	
			if(controta % 15 ==0){// usuado para desenhar a rota a cada 15 posições
				JLabel rota = new JLabel("  ●  ");
				rota.setBounds((int)x, (((int)y-500)*(-1))+5, 30, 30);
				/*define a fonte utilizada no label, se utiliza negrito italico etc. e o tamanho da fonte*/
				rota.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 10));
				rota.setForeground(Color.black);
				rota.setVisible(true);
				label.add(rota);					
				
			}				
			controta++;
			if(t>=1.10){
				explosao.setVisible(false);
			}
			
			if(x>=800){//executa até o passaro sair da tela pela direita
				break;
			}else if((((int)x>=590 && (int)x<=790) && (int)y<=140)){//executa até o passáro entrar na área do alvo
				break;
				
			}else if((((int)x>=77 && (int)x<=130) && (int)y<=145)){//caso o passaro não tenha força para sair da pilha de caixas onde está o canhão 
				break;
			}
			
			if((int)y<=42.0 && t>0){//executa quando o pássaro bate no chão
				v = v *0.6;
				x1=(int) x;
				y1=42;
				y=42;
				t=1;
									
			}
			
			int velocidade = CalcularForca();
			try{
				Thread.sleep(velocidade);
			}catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
		}						
	}
	
	//configura a tecla de que comanda o lançamento do personagem
	static void ChamarAcoes(){
		janela.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub					
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE/*codigo da barra de espaço*/){						
					passou = true;
					forca= barra.getValue();											
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE){						
					passou = true;
					forca= barra.getValue();
					
				}					
			}				
		});	
		janela.getKeyListeners();//captura qual tecla foi pressionada
	}
	/*Definir o delay conforme a força */
	static int CalcularForca(){
		int frc = 0;
		if(forca >= 90 && forca <=100){
			frc = 5;
		}else if(forca >=80  && forca <90){
			frc = 6;
		}else if(forca >=70  && forca <80){
			frc = 7;
		}else if(forca >=60  && forca <70){
			frc = 8;
		}else if(forca >=50  && forca <60){
			frc = 9;
		}else if(forca >=40  && forca <50){
			frc = 10;
		}else if(forca >=30  && forca <40){
			frc = 11;
		}else if(forca >=20  && forca <30){
			frc = 12;
		}else if(forca >=10  && forca <20){
			frc = 13;
		}else if(forca >=0  && forca <10){
			frc = 14;
		}
		
		return frc;
	}
	//faz o movimento do personagem

	static void TravarTela(){
		/**/
		Insets in = Toolkit.getDefaultToolkit().getScreenInsets(janela.getGraphicsConfiguration());
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        /*recupera a largura  e altura do monitor*/
        int width = d.width - (in.left + in.top);      
        int height = d.height - (in.top + in.bottom); 
        /*Define largura e altura da tela*/
        int largura = 800;
        int altura = 600;
        /*Centraliza a tela no monitor*/
        janela.setLocation(((width-largura)/2), ((height-altura)/2));
        /*Cria a tela com o tamanho definido*/
        janela.setSize(largura, altura);
        /*define que o tamanho da tela não poderá ser alterado pelo usuário*/
        janela.setResizable(false);
        janela.addComponentListener(/**/
        	
        	new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
            	janela.setEnabled(true);
            	//janela.setEnabled(true);
            }
        }/**/);	
	}

}
