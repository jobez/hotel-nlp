package hotel_nlp.externals;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase; 
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.UimaContext;
import org.uimafit.descriptor.ConfigurationParameter;
import clojure.lang.RT;
import clojure.lang.IFn;
import clojure.lang.AFn;
import clojure.lang.ISeq;

public class UIMAProxy extends JCasAnnotator_ImplBase{

  //private static IFn requireFn = RT.var("clojure.core", "require").fn(); //
  private static IFn nsResolveFn = RT.var("clojure.core", "ns-resolve").fn();
  private static IFn symbolFn    = RT.var("clojure.core", "symbol").fn();

  private UimaContext context;

  public static final String PARAM_NS = "ns-parameter";
  public static final String PARAM_ANNFN = "annfn-parameter";
  public static final String PARAM_EXTFN = "extfn-parameter";

  @ConfigurationParameter(name = PARAM_NS) //the namespace string
  private String ns;

  @ConfigurationParameter(name = PARAM_EXTFN) //the input-extractor-fn string
  private String extfn;

  @ConfigurationParameter(name = PARAM_ANNFN) //the annotator-fn string
  private String annfn;

 
  @Override
  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    this.context = context;
  }    


  @Override 
  public void process(JCas aJCas) throws AnalysisEngineProcessException{
      
      try{
         call(ns, extfn, annfn, context, aJCas);
      }
      catch (Exception e){
           throw new AnalysisEngineProcessException(e);
        }
        
  }

   private void call (String strns, String strextfn, String strannfn, UimaContext context, JCas jcas){
   
   IFn extfn = nsResolveFn.invoke(symbolFn.invoke(strns), 
                                  symbolFn.invoke(strextfn));
   IFn annfn = nsResolveFn.invoke(symbolFn.invoke(strns), 
                                  symbolFn.invoke(strannfn));

   Object trueInput = extfn.invoke(jcas, context); //extractor should be a function that accepts at least a JCas object - context can be null
   annfn.invoke(trueInput); //we've now sepearted our component completely from UIMA

  }

}
