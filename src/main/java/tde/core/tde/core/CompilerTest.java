
import vk.core.internal.InternalCompiler;
import vk.core.internal.InternalResult;

public class CompilerTest  {

	public static int tester(InternalCompiler ic){
		InternalResult ir = new InternalResult();
		ic.compileAndRunTests();
		
		return ir.getNumberOfFailedTests();
	}
	
}
