(defproject hotel-nlp "0.2.0-SNAPSHOT"
  :description "NLP tooklit for Clojure with extension points (no wrappers) for most java-based NLP software out there."
  :url "https://github.com/jimpil/annotator-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;:repositories [["bioinformatics-all" "http://bioinformatics.ua.pt/maven/content/groups/public"]]  ;need this for gimli        
  :dependencies [[org.clojure/clojure "1.5.1"]
 		 [org.clojure/data.zip "0.1.1"]
 		 [org.clojure/data.csv "0.1.2"]
 		 [org.clojure/tools.nrepl "0.2.3"]
 		 [org.clojure/math.combinatorics "0.0.4"]
 		 [org.clojure/core.memoize "0.5.6"]
 		 [org.clojure/data.priority-map "0.0.2"]
 		 [org.clojure/data.generators "0.1.0"]
 		 [javax.mail/mail "1.4.3"]
 		 ;[weissjeffm/clojure.prxml "1.3.0-SNAPSHOT"]
                 ;[org.clojure/tools.cli "0.2.2"]
                 ;[org.apache.opennlp/opennlp-tools "1.5.2-incubating"]
                 ;[pt.ua.tm/gimli "1.0.1" :exclusions [[opennlp/tools]]] ;;fetch from the specified repository above and exclude old opennlp version
                 [edu.stanford.nlp/stanford-corenlp "1.3.4"] 
                 [edu.stanford.nlp/stanford-corenlp "1.3.4" :classifier "models"]
                 [org.apache.opennlp/opennlp-maxent "3.0.3"]
                 [experiment/experiment "1.5.3"] ;OPENNLP HACKED
                 [clojure-opennlp "0.3.1" :exclusions [[org.apache.opennlp/opennlp-tools]]]
                 [net.sf.jwordnet/jwnl "1.4_rc3"] ;latest wordnet
                 [uk.ac.gate/gate-core "7.1" :exclusions [[org.springframework/spring-beans]]]
                 [org.apache.lucene/lucene-snowball "3.0.3"]
                 [org.apache.pdfbox/pdfbox "1.8.2"]
                 [org.apache.uima/uimaj-core "2.4.0"]
                 ;[org.apache.uima/uimaj-examples "2.4.0"]
                 [org.apache.uima/Tagger "2.3.1"]
                 [org.apache.uima/WhitespaceTokenizer "2.3.1"]
                 [org.uimafit/uimafit "1.4.0"] ;;stay away from xml descriptors
                 ;[print-foo "0.3.2"]
                 [net.sf.jgrapht/jgrapht "0.8.3"] 
                 [jgraph/jgraph "5.13.0.0"]
                 [org.flatland/ordered "1.5.1"]
                 [instaparse "1.2.8"] [enlive "1.1.4"]
                 [incanter "1.5.1"] 
                 [rhizome "0.1.9"]
                 [prismatic/plumbing "0.1.1"]
                 [seesaw "1.4.3" :exclusions[[org.clojure/clojure]]]
                 ]
  :jvm-opts ^:replace 
            ["-Xmx3g" "-server"  ;;ideally need 3GB for stanford-corenlp
             "-XX:+OptimizeStringConcat" 
             "-XX:-UseCompressedOops" 
             "-XX:+UseStringCache"
             "-XX:+UseConcMarkSweepGC"
             "-XX:+DoEscapeAnalysis"
             ;"-DWNSEARCHDIR=/home/dimitris/WordNet-3.0/dict/" ;;need this for jwnl.jar and opennlp-coref
            ]
  ;:jar-name           ; name of the jar produced by 'lein jar'
  ;:uberjar-name "hotel-nlp.jar" ; same for 'lein uberjar'
  :resource-paths ["resources" "~/gate-7.1-build4485-ALL/lib/*.jar" "~/GIMLI/resources" ]  
  :java-source-paths ["src/java" "src/hotel_nlp/externals"]
  ;:aot :all #_[hotel_nlp.protocols]
  ;:warn-on-reflection true
  ;:main cluja.app.core
  :scm {:name "git"
        :url "https://github.com/jimpil/hotel-nlp"}
  :pom-addition [:developers  [:developer {:id "jimpil"}
                              [:name "Dimitrios Piliouras"]
                              [:url "http://www.cs.man.ac.uk/~piliourd/"]]]
) 
