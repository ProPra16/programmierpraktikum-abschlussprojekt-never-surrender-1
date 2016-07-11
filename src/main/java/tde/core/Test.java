package tde.core;

import java.io.File;
import java.util.ArrayList;

import tde.file.XMLParser;
import vk.core.api.CompilationUnit;
import vk.core.internal.InternalCompiler;
import vk.core.internal.InternalResult;



public class Test {
	
	InternalCompiler comp;
	InternalResult results = new InternalResult();
	
	/**
	 * 
	 * Bekommt den Dateipfad vom dem Projeckt uebergeben
	 * 
	 * gibt ein CompilationUnit Array der passenden Groeße zurueck
	 * 
	*/
	public static CompilationUnit[] init(String filePath){
		
		File f = new File(filePath);
		File[] files = f.listFiles();
		ArrayList<String> list;
		ArrayList<CompilationUnit> ret = new ArrayList<CompilationUnit>(0);//initialisiert eine ArrayList con CompilationUnit
		CompilationUnit[] gesamt = null;//CompilationUnit Array was am Ende zurueck gegeben werden soll  
		
		int n = files.length;
		
		for(int i = 0; i < n; i++){//durchlaeuft alle datein im angegeben Pfad
		
			list = XMLParser.dataToCode(files[i].getAbsolutePath());
			
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
		
		return gesamt;
	}

	/**
	 * 
	 * @param cUnit bekommt ein CompilationUnit Array, welches es testen soll
	 * @return gibt die Anzahl der fehlgeschlagenen und ignorierten Tests zurueck 
	 */
	public int run(CompilationUnit[] cUnit){
		comp = new InternalCompiler(cUnit);//erstellt einen neuen InternalCompiler mit dem gegebenen Array
		int n;
		
		comp.compileAndRunTests();
		n = results.getNumberOfFailedTests();
		return n + results.getNumberOfIgnoredTests();
	}
}