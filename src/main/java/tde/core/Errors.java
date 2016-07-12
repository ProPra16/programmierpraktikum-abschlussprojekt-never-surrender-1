package tde.core;

import vk.core.api.CompileError;
import vk.core.api.TestFailure;

public class Errors {
	
	java.util.Collection<TestFailure> testErrors;
	java.util.Collection<CompileError> compileErrors;
	
	/**
	 * 
	 * @param bekommt eine TestFailure Collection
	 * testErrors wird mit dem uebergebenen Parameter beladen
	 */
	public void setTestErrors(java.util.Collection<TestFailure> terr){
		testErrors = terr;
	}
	
	/**
	 * 
	 * @param bekommt eine CompileError Collection 
	 * compileErrors wird mit dem uebergebenen Parameter beladen
	 */
	public void setCompileErrors(java.util.Collection<CompileError> cerr){
		compileErrors = cerr;
	}
	
	/**
	 * 
	 * @param bekommt eine CompileError Collection
	 * der uebergebenen Parameter wird an compileErrors dran gehangen
	 */
	public void addCompileError(java.util.Collection<CompileError> cerr){
		compileErrors.addAll(cerr);
	}
	
	/**
	 * 
	 * @return gibt testErrors zurueck
	 */
	public java.util.Collection<TestFailure> getTestErrors(){
		return testErrors;
	}
	
	/**
	 * 
	 * @return gibt compileErrors zurueck
	 */
	public java.util.Collection<CompileError> getCompileErrors(){
		return compileErrors;
	}
}
