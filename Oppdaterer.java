

class Oppdaterer implements Runnable{

    private Loeve[] loeveliste;
    private MatMonitor monitor;

    public Oppdaterer(Loeve[] loeveliste, MatMonitor monitor){
        this.loeveliste = loeveliste;
        this.monitor = monitor;
    }

    @Override
    public void run(){
        while (!monitor.programFerdig()){
            System.out.println("Oppdaterer oppdaterer.");
            // Oppdaterer alle løvene
            for (Loeve loeve : loeveliste){
                loeve.oppdater();
            }
            // venter i ett sekund
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
        System.out.println("Oppdaterer er ferdig");
    }
}