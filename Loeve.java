import java.util.concurrent.CountDownLatch;

class Loeve implements Runnable{

    private String navn;
    private double metthet;
    private boolean lever;
    private MatMonitor monitor;
    private CountDownLatch bariere;

    final double METT_GRENSE = 0.5;

    public Loeve(String navn, MatMonitor monitor, CountDownLatch bariere){
        this.navn = navn;
        metthet = 1.0;
        lever = true;
        this.monitor = monitor;
    }

    @Override
    public void run(){
        try{
         // Loeven spiser mat én gang i sekundet så lenge den lever og ikke er mett (og det finnes mat);
         while(lever){
            if(!erMett()) {
                spis(monitor.taUt());
                Thread.sleep(1000);
            }
         } 
         bariere.countDown();  
         
        } catch(InterruptedException e){
            System.out.println("Interrupted loeve");
        }


    }

    public void oppdater(){
        if (!lever) return;

        metthet -= 0.1;
        if (metthet <= 0){
            lever = false;
            System.out.println(navn + " sultet ihjel");
        }
    }

    public boolean erMett(){
        if (metthet > METT_GRENSE) return true;
        return false;
    }

    public void spis(Mat mat){
        if (lever && mat != null){
            metthet = 1.0;
            System.out.println(navn + " spiste mat");
        }
    }


}