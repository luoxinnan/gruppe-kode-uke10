


class Matprodusent implements Runnable{

    MatMonitor monitor;

    public Matprodusent(MatMonitor monitor){
        this.monitor = monitor;
    }

    @Override
    public void run(){
        // Lager og setter inn én mat hvert femte sekund så lenge programmet ikke er ferdig
        try{
            while(!monitor.programFerdig()){
            Mat mat = new Mat();
            monitor.settInn(mat);
            System.out.println("Produserer mat til matlistei i monitoren");
            Thread.sleep(5000);
            }
            
        } catch(InterruptedException e){
            System.out.println("Interrupted while making food.");
        }
        

    }
}