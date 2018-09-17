package gympass.kart.bean;

import gympass.kart.bean.corredor.CorredorBean;
import gympass.kart.bean.corredor.Volta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorridaBean {

	private String cabecalho;
	private List <LinhaKartBean> listLogLineKart = new ArrayList <LinhaKartBean>();
	private Volta melhorVolta;
	private Date inicioCorrida = null;
	private Date fimCorrida = null;
	private List<CorredorBean> corredores;
	

	public CorridaBean(String cabecalho){
		this.cabecalho = cabecalho;
	}
	
	public List<CorredorBean> getListaCorredores(){
		this.parseListLogLineKartToListCorredores();
		return this.corredores;
	}
	
	public void addLineListLogLineKart(LinhaKartBean linha){
		this.calculoMelhorVolta(new Volta(new CorredorBean(linha), linha));
		this.getListLogLineKart().add(linha);
	}
	
	private List<LinhaKartBean> getListLogLineKart() {
		return listLogLineKart;
	}
	
	public String getCabecalho() {
		return cabecalho;
	}
	
	private void parseListLogLineKartToListCorredores(){
		Map<Integer, CorredorBean> mapCorredores = new HashMap<Integer, CorredorBean>();
		for(LinhaKartBean linha : this.getListLogLineKart()){
			
			CorredorBean c = mapCorredores.get(linha.getPilotoCodigo());
			if(c != null){
				c.addVolta(linha);
				c.setFimCorrida(linha.getHora());
			}else{
				c = new CorredorBean(linha);
				mapCorredores.put(c.getPilotoCodigo(), c);
				if(linha.getnVolta().intValue() > c.getVoltas().size())
					c.setFimCorrida(linha.getHora());
			}
			
			if(linha.getnVolta().intValue() == 1){
				Integer milisec = new Integer((new SimpleDateFormat("SSS")).format(linha.getTempoVolta()));
				Integer sec = new Integer((new SimpleDateFormat("ss")).format(linha.getTempoVolta()));
				Integer min = new Integer((new SimpleDateFormat("mm")).format(linha.getTempoVolta()));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(linha.getHora());
				calendar.add(Calendar.MILLISECOND, -milisec);
				calendar.add(Calendar.SECOND, -sec);
				calendar.add(Calendar.MINUTE, -min);
				
				Date inicioCorrida = calendar.getTime();
				
				c.setInicioCorrida(inicioCorrida);
				
				if(this.inicioCorrida == null || inicioCorrida.getTime() < this.inicioCorrida.getTime())
					this.inicioCorrida = inicioCorrida;
			}
			
			if(fimCorrida == null || linha.getHora().getTime() > fimCorrida.getTime())
				fimCorrida = linha.getHora();
			
		}
		
		corredores =  new ArrayList<CorredorBean>( mapCorredores.values());	
		corredores.sort(new Comparator<CorredorBean>() {
		    @Override
		    public int compare(CorredorBean m1, CorredorBean m2) {
		        if(m1.getFimCorrida().getTime() == m2.getFimCorrida().getTime()){
		            return 0;
		        }
		        return m1.getFimCorrida().getTime() > m2.getFimCorrida().getTime() ? 1 : -1;
		     }
		});
		
		Comparator<CorredorBean> comparator = (o1, o2) -> (new Integer(o2.getVoltas().size())).compareTo(o1.getVoltas().size());
		comparator.reversed();
		corredores.sort(comparator);
		
		int colocacao = 0;
		for(CorredorBean c : corredores){
			colocacao ++;
			c.setPosicaoChegada(colocacao);
		}
	}

	public Date getInicioCorrida() {
		return inicioCorrida;
	}

	public Date getFimCorrida() {
		return fimCorrida;
	}
	
	private void calculoMelhorVolta(Volta volta) {
		if(melhorVolta == null || volta.getTempoVolta().getTime() < melhorVolta.getTempoVolta().getTime()){
			melhorVolta = volta;
		}
	}

	public Volta getMelhorVolta() {
		return melhorVolta;
	}
	
}
