package tde.core;

import java.io.File;
import java.util.ArrayList;

import tde.file.XMLParser;
import vk.core.api.CompilationUnit;
import vk.core.internal.InternalCompiler;
import vk.core.internal.InternalResult;



public class Test {
	
	InternalCompiler comp;
	InternalResult results;
	CompilationUnit[] gesamt;
	
	/**
	 * 
	 * Bekommt den Dateipfad vom dem Projeckt uebergeben
	 * 
	 * 
	 * 
	*/
	public void init(String filePath){
		
		File f = new File(filePath);
		File[] files = f.listFiles();
		ArrayList<String> list;
		ArrayList<CompilationUnit> ret = new ArrayList<CompilationUnit>(0);//initialisiert eine ArrayList con CompilationUnit
		gesamt = new CompilationUnit[0];//CompilationUnit Array  

		String project = f.getName();
		String name = "";

		int n = files.length;
		
		for(int i = 0; i < n; i++){//durchlaeuft alle datein im angegeben Pfad
			
			name = files[i].getName();
			name = name.replace(".xml", "");

			list = XMLParser.dataToCode(project, name);//files[i].getAbsolutePath());
			
			for(int temp = 1; temp < list.size(); temp++){

				if(temp == 1){
					ret.add(new CompilationUnit(list.get(0),list.get(temp), false));
				}
				else{
					ret.add(new CompilationUnit(list.get(0),list.get(temp), true));
				}	
			}
		}
		
		gesamt = ret.toArray(gesamt);
	}

	/**
	 * 
	 * 
	 * @return gibt die Anzahl der fehlgeschlagenen und ignorierten Tests zurueck 
	 */
	public int run(){
		
		results = new InternalResult();
		comp = new InternalCompiler(gesamt);//erstellt einen neuen InternalCompiler initialisierten Array
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
		
		for(int i = 0; i < gesamt.length; i++){
			err.addCompileError(results.getCompilerErrorsForCompilationUnit(gesamt[i]));
		}
		
		return err;
	}
}