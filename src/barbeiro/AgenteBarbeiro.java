/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro;

import comportamento.VerificaFila;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Luís 2781
 */
public class AgenteBarbeiro extends Agent {

    private boolean dormindo = false;

    protected void setup() {

        System.out.println("Eu sou o " + getLocalName());

        addBehaviour(new VerificaFila());

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage mensagem = myAgent.receive();

                if (mensagem != null && mensagem.getOntology() != null) {
                    
                    if (mensagem.getOntology().equalsIgnoreCase("naoTemClientes")) {
                        System.out.println(getLocalName() + ": não há clientes. Vou dormir...");
                        dormindo = true;
                        ACLMessage informaStatus = mensagem.createReply();
                        informaStatus.setOntology("barbeiroInformaStatus");
                        informaStatus.setContent("true");
                        myAgent.send(informaStatus);
                        myAgent.doWait();

                    }else if(mensagem.getOntology().equalsIgnoreCase("temClientes")){
                        
                        String proximoDaFila = mensagem.getContent();
                        cortaCabelo(proximoDaFila);
                        
                        
                        
                    
                    }
                    
                    else if (mensagem.getOntology().equalsIgnoreCase("clienteTeAcordou")) {

                        myAgent.doWake();
                        System.out.println(getLocalName() + ": fui acordado");
                        dormindo = false;
                        ACLMessage informaStatus = new ACLMessage(ACLMessage.INFORM);
                        informaStatus.addReceiver(new AID("Barbearia",AID.ISLOCALNAME));
                        informaStatus.setOntology("barbeiroInformaStatus");
                        informaStatus.setContent("false");
                        myAgent.send(informaStatus);
                        
                        cortaCabelo(mensagem.getSender().getLocalName());

                    }

                }
            }
        });
    }

    protected void cortaCabelo(String cliente) {

        System.out.println(getLocalName()+": Cortando cabelo do " + cliente);

        addBehaviour(new WakerBehaviour(this, 5000) {
            @Override
            protected void onWake() {
                ACLMessage informaCorteFinalizado = new ACLMessage(ACLMessage.INFORM);
                informaCorteFinalizado.addReceiver(new AID(cliente, AID.ISLOCALNAME));
                informaCorteFinalizado.setOntology("corteFinalizado");
                System.out.println(getLocalName()+": O corte do "+cliente+ " foi finalizado");
                myAgent.send(informaCorteFinalizado);
                addBehaviour(new VerificaFila());
            }

        });
        
         

    }

}
