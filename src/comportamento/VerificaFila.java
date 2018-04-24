/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comportamento;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Luís 2781
 */
public class VerificaFila extends OneShotBehaviour{

    @Override
    public void action() {
        ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);
                mensagem.addReceiver(new AID("Barbearia", AID.ISLOCALNAME));
                mensagem.setOntology("haClientes");
                mensagem.setContent("Ha clientes?");
                myAgent.send(mensagem);
    }
    
}
