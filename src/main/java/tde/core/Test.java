package tde.core;

import java.io.File;
import java.util.ArrayList;

import tde.file.XMLParser;
import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
//import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;
//import vk.core.internal.InternalCompiler;
import vk.core.internal.InternalResult;



public class Test {
	
	JavaStringCompiler comp;
	InternalResult results;
	CompilationUnit[] gesamt;
	//ArrayList<CompilationUnit> ret = new ArrayList<CompilationUnit>(0);
	
	/**
	 * 
	 * Bekommt den Dateipfad vom dem Projeckt uebergeben
	 * 
	 * 
	 * 
	*/
	public void init(TDEDataStore dataStore){
		String filePath = dataStore.getWorkspace() + TDEDataStore.separator + dataStore.getProjectName();

		File f = new File(filePath);
		File[] files = f.listFiles();
		ArrayList<String> list;
		ArrayList<CompilationUnit> ret = new ArrayList<>();//initialisiert eine ArrayList con CompilationUnit
		gesamt = new CompilationUnit[0];//CompilationUnit Array

		for (File file : files) {//durchlaeuft alle datein im angegeben Pfad
			dataStore.setAktivFile(file);
			list = XMLParser.dataToCode(dataStore);

			for (int temp = 1; temp < list.size(); temp++) {

				if (temp == 1) {
					ret.add(new CompilationUnit(list.get(0).replace("Exercise Name :", ""), list.get(temp).replace("Classe : ", ""), false));
				} else {
					ret.add(new CompilationUnit(list.get(0).replace("Exercise Name :", ""), list.get(temp).replace("Test : ", ""), true));
				}
			}

		}

		if(!ret.isEmpty()) gesamt = ret.toArray(gesamt);

	}

	/**
	 * 
	 * 
	 * @return gibt die Anzahl der fehlgeschlagenen und ignorierten Tests zurueck 
	 */
	public int run(){
		
		results = new InternalResult();


		comp = CompilerFactory.getCompiler(gesamt);//erstellt einen neuen InternalCompiler initialisierten Array

		int n;
		
		comp.compileAndRunTests();
		n = results.getNumberOfFailedTests() + results.getNumberOfIgnoredTests();
		return n;
	}
	
	/**
	 * 
	 * @return gibt alle CompileErrors und TestFailures ueber Errors weiter
	 */
	public Errors getErrors(){
		
		Errors err = new Errors();
		
		err.setTestErrors(results.getTestFailures());

		for (CompilationUnit aGesamt : gesamt) {
			err.addCompileError(results.getCompilerErrorsForCompilationUnit(aGesamt));
		}
		
		return err;
	}
}