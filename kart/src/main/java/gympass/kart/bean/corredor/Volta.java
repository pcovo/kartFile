package gympass.kart.bean.corredor;

import gympass.kart.bean.LinhaKartBean;

import java.util.Calendar;
import java.util.Date;

public class Volta {
	
	private CorredorBean corredor;
	private Date hora;
	private Integer nVolta;
	private Date tempoVolta;
	private Double velocidadeMediaDaVolta;
	
	public static final Double fatorDeConversao = 3.6;
	
	public Volta(CorredorBean corredor, LinhaKartBean linha){
		this.corredor = corredor;
		this.hora = linha.getHora();
		this.nVolta = linha.getnVolta();
		this.tempoVolta = linha.getTempoVolta();
		this.velocidadeMediaDaVolta = linha.getVelocidadeMediaDaVolta();
	}
	
	public CorredorBean getCorredor() {
		return corredor;
	}
	public void setCorredor(CorredorBean corredor) {
		this.corredor = corredor;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public Integer getnVolta() {
		return nVolta;
	}
	public void setnVolta(Integer nVolta) {
		this.nVolta = nVolta;
	}
	public Date getTempoVolta() {
		return tempoVolta;
	}
	public void setTempoVolta(Date tempoVolta) {
		this.tempoVolta = tempoVolta;
	}
	public Double getVelocidadeMediaDaVolta() {
		return velocidadeMediaDaVolta;
	}
	public void setVelocidadeMediaDaVolta(Double velocidadeMediaDaVolta) {
		this.velocidadeMediaDaVolta = velocidadeMediaDaVolta;
	}
	
	public Double getDistanciaPercorrida(){
		Calendar c = Calendar.getInstance();
		c.setTime(this.tempoVolta);
		int seconds = ((c.get(Calendar.MILLISECOND)/1000)+
				c.get(Calendar.SECOND) +
                c.get(Calendar.MINUTE) * 60);
		
		return (this.velocidadeMediaDaVolta / fatorDeConversao) * seconds;
	}
}
