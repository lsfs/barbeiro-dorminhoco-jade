/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lufes
 */
public class Main {

    public static void main(String[] args) {

        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl(true);
        ContainerController containerController = runtime.createMainContainer(profile);
        AgentController rma = null;
        AgentController barbeiro = null;
        AgentController barbearia = null;

        
        try {
            rma = containerController.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
            barbeiro = containerController.createNewAgent("Barbeiro", "barbeiro.AgenteBarbeiro", new Object[0]);
            barbearia = containerController.createNewAgent("Barbearia", "barbeiro.Barbearia", new Object[0]);
            
            rma.start();
            barbeiro.start();
            barbearia.start();
            

        } catch (StaleProxyException ex) {
            ex.printStackTrace();
        }

    }
}