package com.massisframework.massis3.examples.simulator.Configuration;

public class VerticeConfig {
    /* Simulamos los vertices como puertas con tiempo de transporte 0 en principio */
    /* asumimos por ahora que es un grafo bidirecional*/
    private String firstNode;
    private String secondNode;
    private int transportCapacity;

    public VerticeConfig() {
        firstNode = null;
        secondNode = null;
        transportCapacity = 0;
    }

    public VerticeConfig(String firts, String second, int rate) {
        //this();
        firstNode = firts;
        secondNode = second;
        transportCapacity = rate;
    }

    public String getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(String source) {
        this.firstNode = source;
    }

    public String getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(String destination) {
        this.secondNode = destination;
    }

    public int getTransportCapacity() {
        return transportCapacity;
    }

    public void setTransportCapacity(int transportRate) {
        this.transportCapacity = transportRate;
    }
}
