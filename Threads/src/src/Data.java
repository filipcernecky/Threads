package src;

public class Data {
    private String packet;
    private boolean transfer = true; 

    public synchronized void send(String packet) {
        while(!transfer){
            try {
                wait();
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }

        transfer =false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while(transfer){
            try {
                wait();
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }

        transfer = true;

        notifyAll();
        return packet;
    }
}