/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Luís 2781
 */
public class AgenteCliente extends Agent {

    protected void setup() {

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                Object[] dados = getArguments();
                int delay = Integer.parseInt(dados[0].toString());
                verificaBarbeiroAcordado(delay);
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage mensagem = myAgent.receive();
                if (mensagem != null && mensagem.getOntology() != null) {

                    if (mensagem.getOntology().equalsIgnoreCase("respostaStatusBarbeiro")) {
                        if (mensagem.getContent().equalsIgnoreCase("true")) {
                            System.out.println(getLocalName() + ": O barbeiro está dormindo. Vou acordá-lo.");
                            ACLMessage acordaBarbeiro = new ACLMessage(ACLMessage.INFORM);
                            acordaBarbeiro.addReceiver(new AID("Barbeiro", AID.ISLOCALNAME));
                            acordaBarbeiro.setOntology("clienteTeAcordou");
                            acordaBarbeiro.setContent("clienteTeAcordou");
                            myAgent.send(acordaBarbeiro);

                        } else {
                            System.out.println(getLocalName() + ": O barbeiro está acordado e ocupado.");
                            ACLMessage verificaEspacoFila = new ACLMessage(ACLMessage.REQUEST);
                            verificaEspacoFila.setOntology("verificaEspacoFila");
                            verificaEspacoFila.setContent("verificaEspacoFila");
                            verificaEspacoFila.addReceiver(new AID("Barbearia", AID.ISLOCALNAME));
                            myAgent.send(verificaEspacoFila);

                        }

                    } else if (mensagem.getOntology().equalsIgnoreCase("corteFinalizado")) {
                        System.out.println(getLocalName() + ": Finalizei meu corte.");
                        myAgent.doDelete();
                    } else if (mensagem.getOntology().equalsIgnoreCase("naoTemEspaco")) {
                        
                        System.out.println(getLocalName() + ": Não há espaço na fila. Vou embora");
                        myAgent.doDelete();

                    }

                }
            }
        });

    }

    public void verificaBarbeiroAcordado(int delay) {

        addBehaviour(new WakerBehaviour(this, delay) {
            @Override
            protected void onWake() {
                ACLMessage verificaStatusBarbeiro = new ACLMessage(ACLMessage.QUERY_IF);
                verificaStatusBarbeiro.setOntology("clientePerguntaStatus");
                verificaStatusBarbeiro.setContent("clientePerguntaStatus");
                verificaStatusBarbeiro.addReceiver(new AID("Barbearia", AID.ISLOCALNAME));
                myAgent.send(verificaStatusBarbeiro);
            }

        });
    }

}
