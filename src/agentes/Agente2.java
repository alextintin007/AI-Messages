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
        int recEnv=0;
        String cont[]= new String[2];
        @Override
        public void action(){
            //todo lo que necesite hacer el agente
            //ANN, AG, Bayes, if else
            System.out.print("Nombre Agente 2: ");
            System.out.println(getName());
            ACLMessage msj = blockingReceive(); //this is blocking it till it recieves a message

            String idC = msj.getConversationId();

            if(idC.equalsIgnoreCase("COD0102")) {
                String temperatura = msj.getContent();
                if (Integer.parseInt(temperatura) > 35) {
                    System.out.println("Prendiendo ventiladores");
                    //...........
                    Mensajes.enviar(ACLMessage.INFORM, "ReceptorInfo", "Ventilador prendido", "COD0201", getAgent());
                    recEnv=1;
                    cont[0]=temperatura;
                }
            }
            else {
                if(idC.equalsIgnoreCase("COD0302")) {
                    String humedadHoja = msj.getContent();
                    if (humedadHoja.equalsIgnoreCase("alta")) {
                        System.out.println("No regar");
                    }
                    else{
                        System.out.println("Regar");
                    }
                    if(recEnv==1){
                        recEnv=2;
                    }

                    cont[1]=humedadHoja;
                    Mensajes.enviar(ACLMessage.INFORM, "Ag3", "Estado de riesgo", "COD0203", getAgent());
                }
            }

            if(recEnv==2){
                Mensajes.enviar(ACLMessage.INFORM, "Ag4", "REVISAR PH", "COD0204", getAgent());
                recEnv=0;
            }
            //System.out.println(msj);
            //System.out.println(msj.getContent());
            //System.out.println(msj.getConversationId());

        }
    }

}
