package contenidoSerializado;

import java.io.Serializable;

public class Sensores implements Serializable{
    private String riego;
    private int temperatura;

    public Sensores(String riego, int temperatura) {
        this.riego = riego;
        this.temperatura = temperatura;
    }
    public Sensores(){}

    public String getRiego() {
        return riego;
    }

    public void setRiego(String riego) {
        this.riego = riego;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
}
