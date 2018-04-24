/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comportamento;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Pesquisa 01
 */
public class TesteFila {

    public static void main(String[] args) {
        Queue fila = new LinkedList();

        fila.add("Casa");
        fila.add("casamento");
        
        for (Object valor : fila) {
            System.out.println(fila.toString());
        }

       System.out.println(fila.poll());
       System.out.println(fila.size());
       
        //System.out.println(fila.poll());
         System.out.println(fila.size());
         
         for (Object valor : fila) {
            System.out.println(fila.toString());
        }

    }

}
