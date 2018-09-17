package gympass.kart.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LinhaKartBean {

	private LoadFileBean linha;
	private String erro;
	
	private Date hora;
	private Integer pilotoCodigo;
	private String pilotoNome;
	private Integer nVolta;
	private Date tempoVolta;
	private Double velocidadeMediaDaVolta;
	private Integer numeroDaLinhaNoArquivo;
	
	private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss.SSS");
	private SimpleDateFormat formatoTempoVolta = new SimpleDateFormat("mm:ss.SSS");
	
	public LinhaKartBean(LoadFileBean linha) throws ParseException{
		this.setLinha(linha);
	}
	
	public LinhaKartBean(Throwable e, LoadFileBean linha){
		erro = e.getCause() != null ? e.getMessage() +" - "+e.getCause().toString() : e.getMessage();
		this.linha = linha;
		this.numeroDaLinhaNoArquivo = linha.getNumeroDaLinhaNoArquivo();
	}
	
	public Date getHora() {
		return hora;
	}
	public Integer getPilotoCodigo() {
		return pilotoCodigo;
	}
	public String getPilotoNome() {
		return pilotoNome;
	}
	public Integer getnVolta() {
		return nVolta;
	}
	public Date getTempoVolta() {
		return tempoVolta;
	}
	public Double getVelocidadeMediaDaVolta() {
		return velocidadeMediaDaVolta;
	}
	public LoadFileBean getLinha() {
		return linha;
	}
	
	private void setLinha(LoadFileBean linha) throws ParseException {
		this.linha = linha;
		
		try {
			this.hora = formatoHora.parse(linha.getHora());
			this.pilotoCodigo = new Integer(linha.getPiloto().split("–")[0]);
			this.pilotoNome = linha.getPiloto().split("–")[1];
			this.nVolta = new Integer(linha.getnVolta());
			this.tempoVolta = formatoTempoVolta.parse(linha.getTempoVolta());
			this.velocidadeMediaDaVolta = Double.valueOf(linha.getVelocidadeMediaDaVolta().replace(",", "."));
			this.numeroDaLinhaNoArquivo = linha.getNumeroDaLinhaNoArquivo();
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	public Integer getNumeroDaLinhaNoArquivo() {
		return numeroDaLinhaNoArquivo;
	}

	public String getErro() {
		return erro;
	}
}
