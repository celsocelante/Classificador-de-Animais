package com.classifier;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;


public class DroolsMain {
	static Animal animal;

    public static final void main(String[] args) {
        try {
            KnowledgeBase kbase = readKnowledgeBase();
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            /* Entradas hard-coded */
            
            /* sapo */
            animal = new Animal();
            animal.addHabitat("terrestre");
            animal.addHabitat("aquatico");
            animal.addRevestimento("pele");
            animal.addLocomocao("anda");
            animal.addLocomocao("rasteja");
            animal.addLocomocao("nada");
            animal.addReproducao("ovuliparo");
            animal.addAlimentacao("carnivoro");
            animal.addRespiracao("cutanea");
            animal.addRespiracao("pulmonar");
            
            ksession.insert(animal);
            ksession.fireAllRules();
            animal.showResults();
            /* fim sapo */
            
            /* animal confuso */
            animal = new Animal();
            animal.addHabitat("aquatico");
            animal.addReproducao("ovuliparo");
            
            ksession.insert(animal);
            ksession.fireAllRules();
            animal.showResults();
            /* fim animal confuso */
            
            /* possível ave */
            animal = new Animal();
            animal.addHabitat("aereo");
            animal.addReproducao("oviparo");
            animal.addLocomocao("voa");
            animal.addRevestimento("penas");
            
            ksession.insert(animal);
            ksession.fireAllRules();
            animal.showResults();
            /* fim possivel ave */
            
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
