package gympass.kart.bean.corredor;

import gympass.kart.bean.LinhaKartBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CorredorBean {
	
	private Integer pilotoCodigo;
	private Date inicioCorrida = null;
	private Date fimCorrida = null;
	private String pilotoNome;
	private List<Volta> voltas = new ArrayList<Volta>();
	private Volta melhorVolta;
	private Double distanciaPercorrida = 0.0;
	private Double velocidadeMedia = 0.0;
	
	private Integer posicaoChegada;
	
	
	/*
	 Posição Chegada
	 Código Piloto
	 Nome Piloto
	 Qtde Voltas Completadas -> getQtdeVoltasCompletadas()
	 Tempo Total de Prova -> getTempoTotalDeProva()
	 */
	
	public CorredorBean(LinhaKartBean linha){
		this.pilotoCodigo = linha.getPilotoCodigo();
		this.pilotoNome = linha.getPilotoNome();
		adicionarVolta(linha);
	}

	public void addVolta(LinhaKartBean linha){
		adicionarVolta(linha);
	}
	
	public Integer getPilotoCodigo() {
		return pilotoCodigo;
	}
	public void setPilotoCodigo(Integer pilotoCodigo) {
		this.pilotoCodigo = pilotoCodigo;
	}
	public String getPilotoNome() {
		return pilotoNome;
	}
	public void setPilotoNome(String pilotoNome) {
		this.pilotoNome = pilotoNome;
	}
	
	public List<Volta> getVoltas() {
		Comparator<Volta> comparator = (o1, o2) -> (new Integer(o2.getnVolta())).compareTo(o1.getnVolta());
		voltas.sort(comparator);
		return voltas;
	}
	
	public void setVoltas(List<Volta> voltas) {
		this.voltas = voltas;
	}

	//Qtde Voltas Completadas
	public Integer getQtdeVoltasCompletadas(){
		return this.getVoltas().size();
	}
	
	public Date getTempoTotalDeProva(){
		/*Date tempoTotalDaProva = null;
		try {
			tempoTotalDaProva = (new SimpleDateFormat("HH:mm:ss.SSS")).parse("00:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for(Volta volta : this.voltas){
			tempoTotalDaProva = new Date(tempoTotalDaProva.getTime() + volta.getTempoVolta().getTime());
		}*/
		return new Date(this.getFimCorrida().getTime() - this.getInicioCorrida().getTime());
	}

	public Integer getPosicaoChegada() {
		return posicaoChegada;
	}

	public void setPosicaoChegada(Integer posicaoChegada) {
		this.posicaoChegada = posicaoChegada;
	}

	public Date getInicioCorrida() {
		return inicioCorrida;
	}

	public void setInicioCorrida(Date inicioCorrida) {
		this.inicioCorrida = inicioCorrida;
	}

	public Date getFimCorrida() {
		/*if (this.pilotoCodigo.intValue() == 11)
			return this.voltas.get(0).getHora();
		else*/
			return fimCorrida;
			
	}

	public void setFimCorrida(Date fimCorrida) {
		this.fimCorrida = fimCorrida;
	}

	public Volta getMelhorVolta() {
		return melhorVolta;
	}

	public void setMelhorVolta(Volta melhorVolta) {
		this.melhorVolta = melhorVolta;
	}
	
	private void adicionarVolta(LinhaKartBean linha) {
		Volta volta = new Volta(this, linha);
		voltas.add(volta);
		distanciaPercorrida = Double.sum(distanciaPercorrida, volta.getDistanciaPercorrida());
		calculoMelhorVolta(volta);
	}

	private void calculoMelhorVolta(Volta volta) {
		if(melhorVolta == null || volta.getTempoVolta().getTime() < melhorVolta.getTempoVolta().getTime()){
			melhorVolta = volta;
		}
	}

	public Double getVelocidadeMedia() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date (this.fimCorrida.getTime() - this.inicioCorrida.getTime()));
		int seconds = ((c.get(Calendar.MILLISECOND)/1000)+
				c.get(Calendar.SECOND) +
                c.get(Calendar.MINUTE) * 60);
		this.velocidadeMedia = distanciaPercorrida / seconds * Volta.fatorDeConversao;
		return velocidadeMedia;
	}

	public Double getDistanciaPercorrida() {
		return distanciaPercorrida;
	}
	
}
