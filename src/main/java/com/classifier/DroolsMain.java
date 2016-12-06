package com.classifier;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;


public class DroolsMain {

    public static final void main(String[] args) {
        try {
            KnowledgeBase kbase = readKnowledgeBase();
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            
            /* Sapo */
            Animal animal = new Animal();
            animal.setHabitat("terrestre");
            animal.setHabitat("aquatico");
            animal.setRevestimento("pele");
            animal.setLocomocao("anda");
            animal.setLocomocao("rasteja");
            animal.setLocomocao("nada");
            animal.setReproducao("ovuliparo");
            animal.setAlimentacao("carnivoro");
            animal.setRespiracao("cutanea");
            animal.setRespiracao("pulmonar");
            
            ksession.insert(animal);
            ksession.fireAllRules();
            animal.showResults();
            
            /* Ave qualquer */
            animal = new Animal();
            animal.setHabitat("aquatico");
            animal.setRevestimento("penas");
            animal.setLocomocao("anda");
            animal.setLocomocao("nada");
            animal.setRespiracao("pulmonar");
            
            ksession.insert(animal);
            ksession.fireAllRules();
            animal.showResults();
            
            /* Animal confuso 
            animal = new Animal();
            animal.setHabitat("aquatico");
            animal.setReproducao("ovuliparo");
            
            ksession.insert(animal);
            ksession.fireAllRules();
            animal.showResults();
            */
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("Rules.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

}
