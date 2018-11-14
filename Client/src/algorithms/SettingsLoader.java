package algorithms;

public class SettingsLoader extends Thread{

    @Override
    public void run(){
//        message="Test 1";
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        message="Test 2";
    }
}
