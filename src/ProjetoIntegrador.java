/*Desenvolvido por Evandro Augusto de Oliveira Marcolino, Felipe Silva, Guilherme Rocha Wenger e João Paulo Nunes
 * 
 * */


//Bibliotecas utilizadas
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;

public class ProjetoIntegrador {
	// variaveis globais
	static JFrame janela;
	static JFrame frame = new JFrame("Menu");
	static JFrame telaAjuda;
	static ImageIcon fundo = new ImageIcon("cenario.jpg");
	static ImageIcon passaro = new ImageIcon("angry123.png");
	static ImageIcon ninho = new ImageIcon("alvo.png");
	static ImageIcon boom = new ImageIcon("kaboom.png");
	static ImageIcon porco = new ImageIcon("porco.png");
	static ImageIcon imagemTutorial = new ImageIcon("teclado.jpg");
	static ImageIcon fimTutorial = new ImageIcon("fim.jpg");
	static ImageIcon porcosTutorial = new ImageIcon("porcos.jpg");
	static JLabel label;
	static JLabel label1;
	static JLabel rota;
	static JLabel msg;
	static JLabel titulo;
	static JLabel alvo;
	static JLabel explosao;
	static JLabel pork;
	static JLabel tent;
	static JLabel velocimetro;
	static JLabel msgVelocidade;
	static JLabel teclado;
	static JPanel painel;
	static JPanel painelAjuda;
	static JProgressBar barraForca;
	static JProgressBar barraAngulo;
	static int forca;
	static int angulo;
	static int tentativas = 1;
	static int clique = 0;
	static int indiceProximo = 1;
	static boolean definiuForca = false;
	static boolean definiuAngulo = false;
	static boolean consultando = false;
	
	static JLabel labelMenu = new JLabel();
	static ImageIcon fotoMenu = new ImageIcon("imagemMenu.jpg");

	static JButton novoJogo = new JButton("Novo Jogo");
	static JButton sobreJogo = new JButton("Sobre");
	static JButton sairJogo = new JButton("Sair");
	static JButton botaoProximo;
	static JButton botaoAnterior;
	static JButton botaoOkEntendi;
	static JEditorPane ajuda;

	static JLabel escritaMenu = new JLabel("START");

	public static void main(String[] args) {
		telaInicial();
		CriarJanela();
		MostrarJanela();
		TravarTela(janela,800,600);
		MovimentarBarra();

		if (definiuForca) {
			MovimentarBarraAngulo();
			if (definiuAngulo) {
				MovimentarPassaro();
			}
		}
		
	}
	static void criarTelaAjuda() {
		indiceProximo = 1;

		telaAjuda = new JFrame("Tela de Ajuda");
		telaAjuda.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		telaAjuda.setVisible(true);
		TravarTela(telaAjuda, 800, 800);
		
		teclado = new JLabel(imagemTutorial);
		teclado.setLayout(null);
		teclado.setLocation(100, 100);
		teclado.setVisible(false);
		teclado.setSize(500, 600);
		
		painelAjuda = new JPanel();
		painelAjuda.setLayout(null);
		painelAjuda.setVisible(true);
		painelAjuda.setBackground(Color.white);

		botaoProximo = new JButton("Próximo ->");
		botaoProximo.setLayout(null);
		botaoProximo.setVisible(true);
		botaoProximo.setSize(100, 20);
		botaoProximo.setLocation(690, 630);

		botaoAnterior = new JButton("<- Anterior");
		botaoAnterior.setLayout(null);
		botaoAnterior.setVisible(true);
		botaoAnterior.setSize(100, 20);
		botaoAnterior.setLocation(10, 630);

		botaoOkEntendi = new JButton("Ok, Entendi e Sair.");
		botaoOkEntendi.setLayout(null);
		botaoOkEntendi.setVisible(true);
		botaoOkEntendi.setSize(150, 20);
		botaoOkEntendi.setLocation(340, 630);

		ajuda = new JEditorPane();
		ajuda.setEditable(false);
		ajuda.setContentType("text/html");
		ajuda.setSize(800, 300);
		ajuda.setLocation(0, 0);
		telaAjuda.add(painelAjuda);
		painelAjuda.add(ajuda);
		painelAjuda.add(botaoAnterior);
		painelAjuda.add(botaoProximo);
		painelAjuda.add(botaoOkEntendi);
		painelAjuda.add(teclado);
		mudarTextoAjuda();
		chamarAcoesAjuda();
	}

	static void mudarTextoAjuda() {
		if (indiceProximo == 1) {
			botaoAnterior.setEnabled(false);
			botaoProximo.setEnabled(true);
			teclado.setVisible(true);
			teclado.setIcon(porcosTutorial);
			ajuda.setText("<font color=" + "red>" + "<font size =" + "12>" + "<b>Essa não!!!</b></font>"
					+ "<br><br><font color =" + "blue" + "><font size =" + "9>" + "<b>Os porcos invadiram seu ninho enquanto você esteve fora.</b><br></font>"
					+ "<br>" + "<font color =" + "blue"
					+ "><b>Não deixe barato, expulse-os imediatamente!!<br>"
					+ "Eles deixaram um sentinela lá, mas fique tranquilo que te ajudo a resolver essa."
					);

		} else if (indiceProximo == 2) {
			botaoAnterior.setEnabled(true);
			teclado.setVisible(true);
			teclado.setIcon(imagemTutorial);
			ajuda.setText("<font color=" + "red>" + "<font size =" + "12>" + "<b>Primeiro Passo</b></font>"
					+ "<br><br><font color =" + "blue" + "><font size =" + "9>" + "<b>Vamos precisar de força</b><br></font>"
					+ "<br>" + "<font color =" + "blue"
					+ "><b>Cara, aquele sentinela não é de nada, mas precisamos de força para ir do canhão ate o ninho, faça o seguinte. Assim que você entrar no cenário vai "
					+ "ver uma barra de força"
					+ "se movimentando, observe-a e<br><font color ="
					+ "blue" + ">" + " <br>quando achar que a barra estiver em um bom nível de força, pressione a barra de espaço</font> <font color =" + "red" + "><br><br>"
					);
		} else if (indiceProximo == 3) {
			botaoProximo.setEnabled(true);
			teclado.setIcon(imagemTutorial);
			ajuda.setText("<font color=" + "red>" + "<font size =" + "12>" + "<b>Segundo Passo</b></font>"
					+ "<br><br><font color =" + "blue" + "><font size =" + "9>" + "<b>Vamos escolher o ângulo perfeito!</b><br></font>"
					+ "<br>" + "<font color =" + "blue"
					+ "><b>Após edcolher a força do lançamento, será necessário escolher o melhor ângulo. Assim pressionar barra de espaço, a primeira barra de força vai parar e a "
					+ "segunda irá começar a se movimentar."
					+ "Observe-a e<font color ="
					+ "blue" + ">" + " <br>quando achar que o ângulo está perfeito pressione novamente a barra de espaço e aguarde para ver</font> <font color =" + "blue" + ">"
					+ "se seu pássaro conseguirá salvar o ninho.<br>"
					+ "Ele só precisa atingir qualquer região do ninho para expulsar o sentinela."
					);

		} else if (indiceProximo == 4) {
			
			botaoProximo.setEnabled(false);
			teclado.setVisible(true);
			teclado.setIcon(fimTutorial);
			ajuda.setText("<font color=" + "red>" + "<font size =" + "12>" + "<b>Agora é só ver o resultado</b></font>"
					+ "<br><br><font color =" + "blue" + "><font size =" + "9>" + "<b>Vamos festejar! Ou talvez chorar.</b><br></font>"
					+ "" + "<font color =" + "blue"
					+ "><b>Mas tenho certeza que com essas dicas você conseguirá salvar o ninho sem maiores problemas!! <br>"
					+ "<br>"
					+ "Equipe de desenvolvimento<font color ="
					+ "black" + ">" + "<br> <br>Evandro Augusto;<br>Felipe Silva;<br>Guilherme Wenger<br>João Paulo Nunes.<br>"
					+ "versão 1.1");
		} 
	}

	static void chamarAcoesAjuda() {
		botaoProximo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				indiceProximo++;
				mudarTextoAjuda();
			}
		});

		botaoAnterior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				indiceProximo--;
				mudarTextoAjuda();
			}
		});

		botaoOkEntendi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaAjuda.dispose();
				if(!consultando){
					frame.setVisible(false);
					janela.setVisible(true);
					if (definiuForca) {
						MovimentarBarraAngulo();
						if (definiuAngulo) {
							MovimentarPassaro();
						}
					}
				}else{
					consultando = false;
					frame.setVisible(true);
				}
			}
		});
	}

	//procedimentos que criam o menu
	static void telaInicial() {

		componentesMenu();

		frame.setSize(797, 574);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);

		frame.add(labelMenu);
		labelMenu.setIcon(fotoMenu);
		labelMenu.setVisible(true);
		labelMenu.setSize(797, 574);

	}

	static void componentesMenu() {

		labelMenu.add(escritaMenu);
		escritaMenu.setBounds(345, 30, 200, 50);
		escritaMenu.setVisible(true);
		escritaMenu.setFont(new Font("Tahoma", Font.BOLD, 35));
		escritaMenu.setForeground(Color.RED);

		labelMenu.add(novoJogo);
		novoJogo.setVisible(true);
		novoJogo.setBounds(300, 120, 200, 50);
		novoJogo.setFont(new Font("Tahoma", Font.BOLD, 15));
		novoJogo.setForeground(Color.BLUE);
		novoJogo.setBorder(null);

		labelMenu.add(sobreJogo);
		sobreJogo.setVisible(true);
		sobreJogo.setBounds(300, 250, 200, 50);
		sobreJogo.setFont(new Font("Tahoma", Font.BOLD, 15));
		sobreJogo.setForeground(Color.LIGHT_GRAY);
		sobreJogo.setBorder(null);

		labelMenu.add(sairJogo);
		sairJogo.setVisible(true);
		sairJogo.setBounds(300, 420, 200, 50);
		sairJogo.setFont(new Font("Tahoma", Font.BOLD, 15));
		sairJogo.setForeground(Color.BLACK);
		sairJogo.setBorder(null);
		acaoNovoJogo();
		acaoSobreJogo();
		acaoSairJogo();

	}

	static void acaoNovoJogo() {

		novoJogo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(tentativas>1){
					frame.setVisible(false);
					janela.setVisible(true);
					if (definiuForca) {
						MovimentarBarraAngulo();
						if (definiuAngulo) {
							MovimentarPassaro();
						}
					}
				}else{
					criarTelaAjuda();
					tentativas++;
				}
			}
				
		});

	}

	static void acaoSobreJogo() {

		sobreJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				consultando = true;
				criarTelaAjuda();
			}
		});

	}

	static void acaoSairJogo() {

		sairJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	// cria o frame
	static void CriarJanela() {
		janela = new JFrame("Projeto Integrador");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// adciona os labels e exibe a janela
	static void MostrarJanela() {
		IniciarComponentes();
		janela.add(label);// adciona imagem ao como plano de fundo
		label.add(label1);
		label.add(barraForca);
		label.add(msg);
		label.add(tent);
		label.add(titulo);
		label.add(pork);
		label.add(alvo);		
		label.add(explosao);
		label.add(velocimetro);
		label.add(msgVelocidade);
		label.add(barraAngulo);
		janela.getContentPane().setBackground(Color.BLACK);
		janela.setVisible(false);
	}

	/*
	 * Função que cria os objetos do jogo
	 */
	static void IniciarComponentes() {
		
		msgVelocidade = new JLabel("Velocidade do Pássaro");
		msgVelocidade.setBounds(450, 0, 260, 30);
		msgVelocidade.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 20));
		msgVelocidade.setVisible(true);
		
		velocimetro = new JLabel("0,0 m/s");
		velocimetro.setBounds(700, 0, 200, 30);
		velocimetro.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 20));
		velocimetro.setVisible(true);
		
		tent = new JLabel("Tentativas:");
		tent.setBounds(0, 0, 100, 30);
		tent.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 15));
		tent.setVisible(true);
		
		// cria o cenario do jogo
		label = new JLabel(fundo);
		label.setLayout(null);
		label1 = new JLabel(passaro);
		label1.setBounds(78, 340, 65, 65);
		//cria o inimigo
		pork = new JLabel(porco);
		pork.setBounds(590, 315, 200, 100);
		// cria a mensagem acima da barra de força
		titulo = new JLabel("Força do lançamento");
		titulo.setBounds(10, 30, 250, 30);
		titulo.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 10));
		titulo.setVisible(true);
		// cria a mensagem embaixo da barra de força
		msg = new JLabel("Ângulo do lançamento:");
		msg.setBounds(10, 65, 250, 30);
		msg.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 10));
		msg.setVisible(true);
		// criar alvo
		alvo = new JLabel(ninho);
		alvo.setLayout(null);
		alvo.setLocation(590, 290);
		alvo.setVisible(true);
		alvo.setSize(200, 300);
		// criar a barra de progresso
		barraForca = new JProgressBar();
		barraForca.setMinimum(0); // valor inicial da barra
		barraForca.setMaximum(100);// valor final da barra
		barraForca.setLocation(10, 50);
		barraForca.setSize(200, 15);// largura e altura
		barraForca.setStringPainted(true); // exibir os numeros com a
											// porcentagem na
											// barra
		barraForca.setBackground(Color.YELLOW);// cor de fundo da barra de força
		barraForca.setForeground(Color.blue); // cor do progresso da barra de
												// força
		barraForca.setIndeterminate(false); // tipo de progresso
		barraForca.setVisible(true); // deixa a barra visivel

		barraAngulo = new JProgressBar();
		barraAngulo.setMinimum(0); // valor inicial da barra
		barraAngulo.setMaximum(90);// valor final da barra
		barraAngulo.setLocation(10, 85);
		barraAngulo.setSize(200, 15);// largura e altura
		barraAngulo.setStringPainted(true); // exibir os numeros com a
											// porcentagem na
		// barra
		barraAngulo.setBackground(Color.YELLOW);// cor de fundo da barra de
												// força
		barraAngulo.setForeground(Color.blue); // cor do progresso da barra de
												// força
		barraAngulo.setIndeterminate(false); // tipo de progresso
		barraAngulo.setVisible(true);
		// criar a explosão
		explosao = new JLabel(boom);
		explosao.setBounds(70, 250, 200, 200);
		explosao.setVisible(false);

	}

	// faz o movimento da barra de força
	static void MovimentarBarra() {
		for (int i = 0; i <= 100; i++) {
			barraForca.setValue(i);
			try {
				Thread.sleep(15);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			ChamarAcoes();
			if (definiuForca) {
				clique++;
				break;
			}
			if (i == 100) {
				for (i = 100; i >= 0; i--) {
					barraForca.setValue(i);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					ChamarAcoes();
					if (definiuForca) {
						clique++;
						break;
					}
				}
			}
		}
	}

	static void MovimentarBarraAngulo() {
		for (int i = 0; i <= 90; i++) {
			barraAngulo.setValue(i);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			ChamarAcoes();
			if (definiuAngulo) {
				break;
			}
			if (i == 90) {
				for (i = 90; i >= 0; i--) {
					barraAngulo.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e1) {

						e1.printStackTrace();
					}
					ChamarAcoes();
					if (definiuAngulo) {
						break;
					}
				}
			}
		}
	}

	static void MovimentarPassaro() {

		int x1 = 77;
		double x = 0;
		double anguloRad = angulo * (Math.PI / 180);
		double t = 1;
		double cos = (Math.cos(anguloRad));
		double v = forca;
		int y1 = 160;
		double y = 160;
		double sen = (Math.sin(anguloRad));
		double gravidade = 9.8;
		double vel = 0;
		boolean executar = true;
		int controta = 0;
		explosao.setVisible(true);// o label explosão é exibido na tela por
									// alguns decimos de segundo
		
		while (executar == true) {
			// calculo do movimento
			x = (x1 + (v * cos * t));
			y = (y1 + (v * sen * t) - ((gravidade * (t * t)) / 2));
			t = t + 0.01;
			// movimentar o personagem
			label1.setLocation((int) x, (((int) y - 500) * (-1)));
			// desenhar rota
			if (controta % 15 == 0) {// usuado para desenhar a rota a cada 15
										// posições
				JLabel rota = new JLabel("  ●  ");

				rota.setBounds((int) x, (((int) y - 500) * (-1)) + 5, 30, 30);
				/*
				 * define a fonte utilizada no label, se utiliza negrito italico
				 * etc. e o tamanho da fonte
				 */
				rota.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 10));
				rota.setForeground(Color.black);
				rota.setVisible(true);
				label.add(rota);

			}

			if (controta % 95 == 0) {
				vel = calcularVelocidadeInstantanea(cos, sen, t, v);

				DecimalFormat dc = new DecimalFormat("#0.00");
				String mostrar = "m/s";
				velocimetro.setText(dc.format(vel) + mostrar);
				forca = (int) vel;
			}
			controta++;
			if (t >= 1.10) {
				explosao.setVisible(false);
			}

			if (x >= 800) {// executa até o passaro sair da tela pela direita
				velocimetro.setText("0,0 m/s");
				JOptionPane.showMessageDialog(null, "Poxa não foi dessa vez! :C \n Mas você pode tentar novamente");
				definiuAngulo = false;
				definiuForca = false;
				clique = 0;
				janela.dispose();
				frame.setVisible(true);
				break;
			} else if ((((int) x >= 570 && (int) x <= 790) && (int) y <= 140)) {// executa até o passáro entrar na área do alvo
				pork.setLocation(450, 410);
				label1.setLocation(650, 360);
				JOptionPane.showMessageDialog(null, "Meus parabéns!!! Você livrou seu ninho dos porcos.");
				definiuAngulo = false;
				definiuForca = false;
				janela.dispose();
				clique = 0;
				frame.setVisible(true);
				break;

			} else if ((((int) x >= 77 && (int) x <= 130) && (int) y <= 145)) {// caso o passaro não tenha força para sair da pilha de caixas onde está o canhão
				velocimetro.setText("0,0 m/s");
				JOptionPane.showMessageDialog(null, "Poxa não foi dessa vez! :C \n Mas você pode tentar novamente");
				definiuAngulo = false;
				definiuForca = false;
				clique = 0;
				janela.dispose();
				frame.setVisible(true);
				break;
			}

			if ((int) y <= 42.0 && t > 0) {// executa quando o pássaro bater no
											// chão
				v = v * 0.6;
				x1 = (int) x;
				y1 = 42;
				y = 42;
				t = 1;
				
				if(velocimetro.getText().equals("9,90m/s")){
					velocimetro.setText("0,0 m/s");
					JOptionPane.showMessageDialog(null, "Poxa não foi dessa vez! :C \n Mas você pode tentar novamente");
					definiuAngulo = false;
					definiuForca = false;
					janela.dispose();
					frame.setVisible(true);
					break;
				}

			}
		
			int velocidade = CalcularForca();
			try {
				Thread.sleep(velocidade);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}		
		CriarJanela();
		MostrarJanela();
		TravarTela(janela,800,600);
		MovimentarBarra();
		
		if (definiuForca) {
			MovimentarBarraAngulo();
			if (definiuAngulo) {
				MovimentarPassaro();
			}
		}
		
	}
	

	static double calcularVelocidadeX(double v, double cos) {
		double velocidade = v * cos;
		return velocidade;
	}

	static double calCularVelocidadeY(double v, double sen, double t) {
		double velocidade = (v * sen) - (9.8 * t);

		return velocidade;
	}

	static double calcularVelocidadeInstantanea(double c, double s, double t, double v0) {
		double vx = calcularVelocidadeX(v0, c);
		double vy = calCularVelocidadeY(v0, s, t);
		double vel = Math.sqrt((vx * vx) + (vy * vy));
		return vel;
	}

	// configura a tecla de que comanda o lançamento do personagem
	static void ChamarAcoes() {
		janela.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (!definiuForca) {
						definiuForca = true;
						forca = barraForca.getValue();
					} else if (clique > 0) {
						definiuAngulo = true;
						int a = barraAngulo.getValue();
						angulo = (int)(a*100)/90;
						if(angulo>90){
							angulo = 90;
						}
					}

				}
			}
		});
		janela.getKeyListeners();// captura qual tecla foi pressionada
		
		janela.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// sem implementação necessária
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// sem implementação necessária
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// sem implementação necessária
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// sem implementação necessária
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(true);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// sem implementação necessária
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// sem implementação necessária
				
			}
		});
	}

	/* Definir o delay conforme a força */
	static int CalcularForca() {
		int frc = 0;
		if (forca >= 90 && forca <= 100) {
			frc = 5;
		} else if (forca >= 80 && forca < 90) {
			frc = 6;
		} else if (forca >= 70 && forca < 80) {
			frc = 7;
		} else if (forca >= 60 && forca < 70) {
			frc = 8;
		} else if (forca >= 50 && forca < 60) {
			frc = 9;
		} else if (forca >= 40 && forca < 50) {
			frc = 10;
		} else if (forca >= 30 && forca < 40) {
			frc = 11;
		} else if (forca >= 20 && forca < 30) {
			frc = 12;
		} else if (forca >= 10 && forca < 20) {
			frc = 13;
		} else if (forca >= 0 && forca < 10) {
			frc = 14;
		}

		return frc;
	}
	// faz o movimento do personagem

	static void TravarTela(JFrame j, int w, int h) {
		/**/
		Insets in = Toolkit.getDefaultToolkit().getScreenInsets(janela.getGraphicsConfiguration());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		/* recupera a largura e altura do monitor */
		int width = d.width - (in.left + in.top);
		int height = d.height - (in.top + in.bottom);
		/* Define largura e altura da tela */
		int largura = w;
		int altura = h;
		/* Centraliza a tela no monitor */
		j.setLocation(((width - largura) / 2), ((height - altura) / 2));
		/* Cria a tela com o tamanho definido */
		j.setSize(largura, altura);
		/* define que o tamanho da tela não poderá ser alterado pelo usuário */
		j.setResizable(false);
		j.addComponentListener(/**/

		new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				j.setEnabled(true);
				// janela.setEnabled(true);
			}
		}/**/);
	}

}
