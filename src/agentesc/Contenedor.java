package agentesc;

import agentes.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contenedor {
    AgentContainer agentContainer;
    public void contenedor(){
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile p = new ProfileImpl(null, 1099, null);
        agentContainer = runtime.createMainContainer(p);
        agregarAgentes();
    }
    public void agregarAgentes(){
        try {
            agentContainer.createNewAgent("ReceptorInfo", Agente1.class.getName(), new Object[]{this,1}).start();
            agentContainer.createNewAgent("Ag3", Agente3.class.getName(), null).start();
            agentContainer.createNewAgent("Ag4", Agente4.class.getName(), null).start();
            agentContainer.createNewAgent("BuscarDatos", Agente2.class.getName(), null).start();
        } catch (StaleProxyException e) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void crearHijos(String alias, Object[] conocimiento) {
        try {
            agentContainer.createNewAgent(alias, Agente1.class.getName(), conocimiento).start();
        } catch (StaleProxyException e) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
