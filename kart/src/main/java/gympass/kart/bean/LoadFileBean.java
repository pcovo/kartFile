package gympass.kart.bean;


public class LoadFileBean {

	private String linha;
	private String splitCharacter = ";"; //;
	private Integer numeroDaLinhaNoArquivo;
	
	private String hora;
	private String piloto;
	private String nVolta;
	private String tempoVolta;
	private String velocidadeMediaDaVolta;
	
	public LoadFileBean(String linha, Integer numLinha){
		this.setLinha(linha, numLinha);
	}
	
	public String getLinha() {
		return linha;
	}
	public String getHora() {
		return hora;
	}
	public String getPiloto() {
		return piloto;
	}
	public String getnVolta() {
		return nVolta;
	}
	public String getTempoVolta() {
		return tempoVolta;
	}
	public String getVelocidadeMediaDaVolta() {
		return velocidadeMediaDaVolta;
	}
	private void setLinha(String linha, Integer numLinha) {
		this.linha = linha;
		hora = this.linha.split(splitCharacter)[0];
		piloto = this.linha.split(splitCharacter)[1];
		nVolta = this.linha.split(splitCharacter)[2];
		tempoVolta = this.linha.split(splitCharacter)[3];
		velocidadeMediaDaVolta = this.linha.split(splitCharacter)[4];
		this.numeroDaLinhaNoArquivo = numLinha;
	}
	
	public Integer getNumeroDaLinhaNoArquivo() {
		return numeroDaLinhaNoArquivo;
	}
}
