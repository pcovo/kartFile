package gympass.kart.file;

import gympass.kart.bean.CorridaBean;
import gympass.kart.bean.LinhaKartBean;
import gympass.kart.bean.LoadFileBean;
import gympass.kart.corrida.AnaliseCorrida;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class LoadFile {

	private CorridaBean corrida;
	
	public boolean loadFile(){
		
		String appConfigPath = "app.properties";
		Properties appProps = new Properties();
		
		try {
			appProps.load(new FileInputStream(appConfigPath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		
	    try (Scanner scanner = new Scanner(new File(appProps.getProperty("fileName")))) {
	    	boolean firstLine = true;
	    	Integer countLines = 0;
			while (scanner.hasNext()){
				if(firstLine){
					corrida = new CorridaBean(scanner.nextLine());
					countLines ++;
					firstLine = false;
				}else{
					countLines ++;
					LoadFileBean linha = null;
					LinhaKartBean linhaLog = null;
					try{
						linha = new LoadFileBean(scanner.nextLine(), countLines);
						linhaLog = new LinhaKartBean(linha);
					}catch(Throwable e){
						linhaLog = new LinhaKartBean(e, linha);
					}
					corrida.addLineListLogLineKart(linhaLog);
				}
			}
			AnaliseCorrida analisar = new AnaliseCorrida(corrida);
			analisar.geraPrintResultadoCorrida();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	    return true;
    }

	public CorridaBean getCorrida() {
		return corrida;
	}
	
}
