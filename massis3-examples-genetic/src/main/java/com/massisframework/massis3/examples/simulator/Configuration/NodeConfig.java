package com.massisframework.massis3.examples.simulator.Configuration;

public class NodeConfig {
    /*Simulamos los nodos como estancias, bien sean salas o pasillos. Todas tienen un tiempo para de trasnporte. Asumimos que el tiemp ode trasnporte es el mismo para todas las salidas*/
    private String nodeName;
    private int capacity;
    private int timeToCrossTheNode;
    private boolean blockCapacity;
    private boolean blockTime;


    public NodeConfig() {
        nodeName = null;
        capacity = 0;
        timeToCrossTheNode = 0;
        blockCapacity = false;
        blockTime = false;
    }

    public NodeConfig(String nd, int cap, int time) {
        nodeName = nd;
        capacity = cap;
        timeToCrossTheNode = time;
        blockCapacity = false;
        blockTime = false;
    }

    public boolean isBlockCapacity() {
        return blockCapacity;
    }

    public void setBlockCapacity(boolean blockCapacity) {
        this.blockCapacity = blockCapacity;
    }

    public boolean isBlockTime() {
        return blockTime;
    }

    public void setBlockTime(boolean blockTime) {
        this.blockTime = blockTime;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int queueSize) {
        this.capacity = queueSize;
    }

    public int getTimeToCrossTheNode() {
        return timeToCrossTheNode;
    }

    public void setTimeToCrossTheNode(int timeToCrossTheNode) {
        this.timeToCrossTheNode = timeToCrossTheNode;
    }
}
