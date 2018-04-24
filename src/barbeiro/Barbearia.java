/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Luís 2781
 */
public class Barbearia extends Agent{
    
    private boolean dormindo;
    
    protected void setup(){
        
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensagem = myAgent.receive();
                if(mensagem!=null && mensagem.getOntology()!=null){
                    if(mensagem.getOntology().equalsIgnoreCase("haClientes")){
                      
                      ACLMessage resposta = mensagem.createReply();
                      resposta.setContent("resposta");
                      resposta.setOntology("N");
                      myAgent.send(resposta);
                      
                    }
                    
                    if(mensagem.getOntology().equalsIgnoreCase("status")){
                       
                        String status = mensagem.getContent();
                        
                        if(status.equalsIgnoreCase("true")){
                            setDormindo(true);
                            System.out.println(isDormindo());
                        }else{
                            setDormindo(false);
                            System.out.println(isDormindo());
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
    

