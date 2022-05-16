package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jdk.jshell.MethodSnippet;

public class Agente2 extends Agent {
    @Override
    protected void setup() {                //crea el primer hilo en la creacion del agente
        addBehaviour(new Comportamiento());
    }
    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action(){
            //todo lo que necesite hacer el agente
            //ANN, AG, Bayes, if else
            System.out.println(getName());
            ACLMessage msj = blockingReceive(); //this is blocking it till it recieves a message
            //System.out.println(msj);
            //System.out.println(msj.getContent());
            //System.out.println(msj.getConversationId());
            String temperatura = msj.getContent();
            if (Integer.parseInt(temperatura)>35){
                System.out.println("Prendiendo ventiladores");
            }
            Mensajes.enviar(ACLMessage.INFORM, "ReceptorInfo", "Ventilador prendido", "COD0201", getAgent());
        }
    }

}
