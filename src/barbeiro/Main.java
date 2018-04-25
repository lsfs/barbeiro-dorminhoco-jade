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
        AgentController cliente1 = null;
        AgentController cliente2 = null;
        AgentController cliente3 = null;
        AgentController cliente4 = null;
        AgentController cliente5 = null;
        AgentController cliente6 = null;
        AgentController cliente7 = null;
        AgentController cliente8 = null;
        AgentController cliente9 = null;

        Object[] delay1 = {2000};
        Object[] delay2 = {3000};
        Object[] delay3 = {3000};
        Object[] delay4 = {3000};
        Object[] delay5 = {4000};
        Object[] delay6 = {5000};
        Object[] delay7 = {5000};
        Object[] delay8 = {5000};
        Object[] delay9={13000};

        try {
            rma = containerController.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
            barbeiro = containerController.createNewAgent("Barbeiro", "barbeiro.AgenteBarbeiro", new Object[0]);
            barbearia = containerController.createNewAgent("Barbearia", "barbeiro.AgenteBarbearia", new Object[0]);
            cliente1 = containerController.createNewAgent("Cliente-1", "barbeiro.AgenteCliente", delay1);
            cliente2 = containerController.createNewAgent("Cliente-2", "barbeiro.AgenteCliente", delay2);
            cliente3 = containerController.createNewAgent("Cliente-3", "barbeiro.AgenteCliente", delay3);
            cliente4 = containerController.createNewAgent("Cliente-4", "barbeiro.AgenteCliente", delay4);
            cliente5 = containerController.createNewAgent("Cliente-5", "barbeiro.AgenteCliente", delay5);
            cliente6 = containerController.createNewAgent("Cliente-6", "barbeiro.AgenteCliente", delay6);
            cliente7 = containerController.createNewAgent("Cliente-7", "barbeiro.AgenteCliente", delay7);
            cliente8 = containerController.createNewAgent("Cliente-8", "barbeiro.AgenteCliente", delay8);
            cliente9 = containerController.createNewAgent("Cliente-9", "barbeiro.AgenteCliente", delay9);
            
            
            
            rma.start();
            barbeiro.start();
            barbearia.start();
            cliente1.start();
            cliente2.start();
            cliente3.start();
            cliente4.start();
            cliente5.start();
            cliente6.start();
            cliente7.start();
            cliente8.start();
            cliente9.start();

        } catch (StaleProxyException ex) {
            ex.printStackTrace();
        }

    }
}
