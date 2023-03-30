
import java.util.concurrent.CountDownLatch;

class Main{
    public static void main(String[] args){

        // Oppretter monitoren (som inneholder Mat-beholder)
        MatMonitor monitor = new MatMonitor();

        // Opprett løvenavn
        String[] navneliste = {"Mufasa", "Scar", "Simba", "Nala"};
        int antallLoever = navneliste.length;

        // CountDownLatch
        CountDownLatch bariere = new CountDownLatch(antallLoever);

        // Oppretter trådene    //  UFERDIG //
        Loeve[] loeveliste = new Loeve[antallLoever];
        Thread[] loeveTraader = new Thread[antallLoever];
        for (int i = 0; i < antallLoever; i++){
            loeveliste[i] = new Loeve(navneliste[i], monitor, bariere);
            loeveTraader[i] = new Thread(loeveliste[i]);
            loeveTraader[i].start();
        }

        Thread mattraad = new Thread(new Matprodusent(monitor));
        mattraad.start();

        Thread oppdaterTraad = new Thread(new Oppdaterer(loeveliste, monitor));
        oppdaterTraad.start();

        // // Venter på at du skal trykke enter for å avslutte programmet
        // System.out.println("Trykk enter for å avslutte programmet");
        // Scanner scanner = new Scanner(System.in);
        // String inp = scanner.nextLine();
        // monitor.settFerdig();
        try{
          bariere.await();  
        } catch(InterruptedException e){System.out.println("Interrupted when bariere await");}
        
        System.out.println("Alle loverene er doede");
        monitor.settFerdig();
    }
}