package gympass.kart;

import gympass.kart.file.LoadFile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for Gympass.
 */
public class GympassTest{
	
	@BeforeClass
	public static void beforeClass() {
		//usado para escrever código que queremos executar antes de todos os casos de teste
		/*
		 * Carregar um properties, ajustar uma conexão de banco, etc.
		 */
	}

	@Before
	public void before() {
		//executado antes de cada caso de teste
		/*
		 * ajustes especificos de arquivos, mudança de valores de entradas para testar diferentes comportamentos, etc.
		 */
	}

	@Test
	public void tratamentoDoArquivoTest() {
		LoadFile execute = new LoadFile();
		Boolean executouComSucesso = execute.loadFile();
		assert(executouComSucesso);
	}

	@After
	public void after() {
		//executado após cada caso de teste
		/*
		 * limpeza de dados, relatorio do teste em especifico, etc.
		 */
	}

	@AfterClass
	public static void afterClass() {
		//usado para escrever código que queremos executar após todos os casos de teste
		/*
		 * finalização do conjunto de teste, finalização de conexão, relatorio final do conjunto testado, etc.
		 */
	}
}
