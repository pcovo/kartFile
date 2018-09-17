package gympass.kart.corrida;

import gympass.kart.bean.CorridaBean;
import gympass.kart.bean.corredor.CorredorBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AnaliseCorrida {

	/*
	 A partir de um input de um arquivo de log do formato acima, 
	 montar o resultado da corrida com as seguintes informações: 
	 Posição Chegada, Código Piloto, Nome Piloto, Qtde Voltas Completadas e Tempo Total de Prova.
	 */
	
	private CorridaBean corrida;
	private List<CorredorBean> corredores;
	private CorredorBean vencedor;
	
	DecimalFormat df2 = new DecimalFormat("###.#");
	
	private SimpleDateFormat formatarData = new SimpleDateFormat("mm:ss.SSS");
	private SimpleDateFormat formatarDataInicioCorrida = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public AnaliseCorrida(CorridaBean corrida){
		this.corrida = corrida;
		corredores =  this.corrida.getListaCorredores();
		vencedor = corredores.get(0);
	}

	public CorridaBean getCorrida() {
		return corrida;
	}
	
	public void geraPrintResultadoCorrida(){
		/* resultado da corrida com as seguintes informações: 
	 	Posição Chegada, Código Piloto, Nome Piloto, Qtde Voltas Completadas e Tempo Total de Prova. */
		// Tempo Total de Prova
		
		System.out.println("RESULTADO DA CORRIDA");
		System.out.println(" ");
		System.out.println(" Tempo Total de Corrida: "+ formatarData.format(this.corrida.getFimCorrida().getTime() - this.corrida.getInicioCorrida().getTime()));
		System.out.println(" Inicio: "+ formatarDataInicioCorrida.format(this.corrida.getInicioCorrida()));
		System.out.println(" Fim   : "+ formatarDataInicioCorrida.format(this.corrida.getFimCorrida()));
		System.out.println(" ");
		System.out.println("Melhor Volta: "+corrida.getMelhorVolta().getCorredor().getPilotoNome()+" ("+corrida.getMelhorVolta().getCorredor().getPilotoCodigo()+")");
		System.out.println("Nº Volta         - "+ corrida.getMelhorVolta().getnVolta());
		System.out.println("Velocidade Média - "+ corrida.getMelhorVolta().getVelocidadeMediaDaVolta()+" Km/H");
		System.out.println("Finalizou as     - "+ formatarDataInicioCorrida.format(corrida.getMelhorVolta().getHora()));
		System.out.println("Tempo da Volta   - "+ formatarData.format(corrida.getMelhorVolta().getTempoVolta()));
		System.out.println(" ");
		
		/** 
		 *Para Cada Corredor 
		 */
		
		for(CorredorBean c : corredores){
			System.out.println(c.getPosicaoChegada()+"ª Lugar ");
			System.out.println(c.getPilotoNome()+" ("+c.getPilotoCodigo()+")");
			System.out.println("Qtde Voltas Completadas - "+c.getQtdeVoltasCompletadas());
			System.out.println("Iniciou a Prova em - "+formatarDataInicioCorrida.format(c.getInicioCorrida()));
			System.out.println("Finalizou em - "+formatarDataInicioCorrida.format(c.getFimCorrida()));
			System.out.println("Tempo Total de Prova - "+formatarData.format(c.getTempoTotalDeProva()));
			System.out.println("Velocidade Media na Prova - "+df2.format(c.getVelocidadeMedia())+" km/h");
			System.out.println("Distancia Percorrida - "+df2.format(c.getDistanciaPercorrida() / 1000) +" Km");
			
			if(vencedor.getPilotoCodigo().intValue() != c.getPilotoCodigo().intValue()){
				System.out.println("Chegou após o Vencendor por - "+formatarData.format(c.getFimCorrida().getTime() - vencedor.getFimCorrida().getTime()));
			}
			
			//Posição Chegada
			// Código Piloto, Nome Piloto
			// Qtde Voltas Completadas
			// Tempo Total de Prova
			
			/**
			 	Descobrir a melhor volta de cada piloto
				Descobrir a melhor volta da corrida
				Calcular a velocidade média de cada piloto durante toda corrida
				Descobrir quanto tempo cada piloto chegou após o vencedor
			 */
			
			System.out.println("Melhor Volta:");
			System.out.println("Nº Volta         - "+ c.getMelhorVolta().getnVolta());
			System.out.println("Velocidade Média - "+ c.getMelhorVolta().getVelocidadeMediaDaVolta()+" Km/H");
			System.out.println("Finalizou as     - "+ formatarDataInicioCorrida.format(c.getMelhorVolta().getHora()));
			System.out.println("Tempo da Volta   - "+ formatarData.format(c.getMelhorVolta().getTempoVolta()));
			System.out.println(" ");
		}
		
		
	}

	public List<CorredorBean> getCorredores() {
		return corredores;
	}

	public void setCorredores(List<CorredorBean> corredores) {
		this.corredores = corredores;
	}

	public void setCorrida(CorridaBean corrida) {
		this.corrida = corrida;
	}
}