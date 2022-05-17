package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;

public class Mensajes {
    public static void enviar(int tipo, String recpetor, String contenidoMSJ, String codigoConversacionID, Agent emisor){
        ACLMessage acl = new ACLMessage(tipo);

        AID aid = new AID();                                //agent id
        aid.setLocalName(recpetor);                         //pasamos al agente que envia

        acl.addReceiver(aid);                               //agente que va a recibir
        acl.setSender(emisor.getAID());                     //el que envia
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        acl.setContent(contenidoMSJ);
        acl.setConversationId(codigoConversacionID);        //canal donde conversan los agentes

        emisor.send(acl);                                   //agente que va a enviar
    }
    public static void enviarS(int tipo, String recpetor, Serializable object, String codigoConversacionID, Agent emisor) {
        ACLMessage acl = new ACLMessage(tipo);

        AID aid = new AID();                             //agent id
        aid.setLocalName(recpetor);                      //pasamos al agente que envia

        acl.addReceiver(aid);                           //agente que va a recibir
        acl.setSender(emisor.getAID());                 //el que envia
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
        try {
            acl.setContentObject(object);               //enviamos un objeto
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        acl.setConversationId(codigoConversacionID);    //canal donde conversan los agentes
        emisor.send(acl);                               //agente que va a enviar
    }
}
