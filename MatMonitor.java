
import java.util.ArrayList;
import java.util.concurrent.locks.*;


class MatMonitor{
    Lock laas = new ReentrantLock();
    Condition ikkeTom = laas.newCondition();
    private ArrayList<Mat> matliste = new ArrayList<>();
    private boolean ferdig = false;

    public void settInn(Mat mat){
        laas.lock();
        try{
            matliste.add(mat);
            ikkeTom.signalAll();
        } finally{laas.unlock();}
    }

    public Mat taUt(){
        laas.lock();
        try{
            while(matliste.isEmpty()){
                ikkeTom.await();
            }
            return matliste.remove(0);
        } catch (InterruptedException e ){
            System.out.println("Interrupted when taUt mat");
            return null;
        }
        finally{
            laas.unlock();
             }
        
    }

    public boolean programFerdig(){
        return ferdig;
    }

    public void settFerdig(){
        ferdig = true;
    }
}