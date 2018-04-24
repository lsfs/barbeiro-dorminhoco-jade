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
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Luís 2781
 */
public class AgenteBarbeiro extends Agent {

    private boolean dormindo = false;
    Barbearia barbearia = new Barbearia();

    protected void setup() {

        System.out.println("Eu sou o " + getLocalName());

        addBehaviour(new VerificaFila());

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage haClientes = myAgent.receive();
                if (haClientes != null && haClientes.getOntology() != null) {
                    if (haClientes.getOntology().equalsIgnoreCase("N")) {
                        System.out.println("Não há clientes. Vou dormir");
                        dormindo = true;
                        ACLMessage informaStatus = haClientes.createReply();
                        informaStatus.setOntology("status");
                        informaStatus.setContent("true");
                        myAgent.send(informaStatus);

                    } else {

                    }

                }
            }
        });
    }

}
