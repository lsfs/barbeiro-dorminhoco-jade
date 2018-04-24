/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Luís 2781
 */
public class AgenteBarbearia extends Agent {

    private boolean dormindo;
    Queue fila = new LinkedList();
    final int cadeiras = 5;

    protected void setup() {

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensagem = myAgent.receive();
                if (mensagem != null && mensagem.getOntology() != null) {

                    if (mensagem.getOntology().equalsIgnoreCase("barbeiroVerificaFila")) {

                        ACLMessage resposta = mensagem.createReply();
                       

                        if (fila.isEmpty()) {
                            resposta.setOntology("naoTemClientes");
                            resposta.setContent("resposta");

                        } else {
                            resposta.setOntology("temClientes");
                            String proximoDaFila = (String) fila.peek();
                            fila.poll();
                            
                            resposta.setContent(proximoDaFila);
                                              
                            
                        }
                        myAgent.send(resposta);
                        
                        
                    } else if (mensagem.getOntology().equalsIgnoreCase("barbeiroInformaStatus")) {

                        String status = mensagem.getContent();
                        if (status.equalsIgnoreCase("true")) {
                            setDormindo(true);

                        } else {
                            setDormindo(false);

                        }

                    } else if (mensagem.getOntology().equalsIgnoreCase("clientePerguntaStatus")) {

                        ACLMessage statusBarbeiro = mensagem.createReply();
                        statusBarbeiro.setOntology("respostaStatusBarbeiro");

                        if (isDormindo()) {
                            statusBarbeiro.setContent("true");
                        } else {
                            statusBarbeiro.setContent("false");
                        }
                        myAgent.send(statusBarbeiro);

                    } else if (mensagem.getOntology().equalsIgnoreCase("verificaEspacoFila")) {

                        if (fila.size() < 5) {
                            
                            fila.add(mensagem.getSender().getLocalName());
                            System.out.println(getLocalName() + ": O cliente " + mensagem.getSender().getLocalName() + " entrou na fila" + fila.size());
                            
                            
                        } else if(fila.size()>=5) {
                           ACLMessage naoHaEspacoFila = mensagem.createReply();
                           naoHaEspacoFila.setContent("naoTemEspaco");
                           naoHaEspacoFila.setOntology("naoTemEspaco");
                           myAgent.send(naoHaEspacoFila);
                        }

                    }

                }
            }
        });

    }

    /**
     * @return the dormindo
     */
    public boolean isDormindo() {
        return dormindo;
    }

    /**
     * @param dormindo the dormindo to set
     */
    public void setDormindo(boolean dormindo) {
        this.dormindo = dormindo;
    }

}
