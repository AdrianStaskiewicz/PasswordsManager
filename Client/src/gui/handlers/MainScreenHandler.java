package gui.handlers;

public class MainScreenHandler {
    public Double progressValue;
    public String progressInfo;

    public MainScreenHandler(){
        this.progressValue=0.0;
        this.progressInfo="";
    }

    public void loadSettings() {
        while(progressValue<100) {
            //LADUJE DANE
            this.progressValue += 0.0001;
            this.progressInfo = "Ladowanie domyslnych ustawien aplikacji..." + progressValue;
            break;
        }
    }

    public double getProgressValue(){
        return progressValue;
    }

    public String getProgressInfo(){
        return progressInfo;
    }

}
