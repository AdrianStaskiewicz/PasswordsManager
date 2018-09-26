package gui.handlers;

import gui.controllers.LoadScreenController;

public class LoadScreenHandler {
    public Double progressValue;
    public String progressInfo;

    public LoadScreenHandler(){
        this.progressValue=0.0;
        this.progressInfo="";
    }

    public void loadSettings() {
        while(progressValue<=100) {

            /*Thread t1 = new Thread(){
                public void run(){
                    progressValue += 1;//pseudoladowanie
                }
            };
            Thread t2 = new Thread(){
                public void run{
                    //setowanie do widoku
                }
            }*/
            //LADUJE DANE
            this.progressValue += 1;
            this.progressInfo = "Ladowanie domyslnych ustawien aplikacji..." + progressValue;
        }
    }

    public double getProgressValue(){
        return progressValue;
    }

    public String getProgressInfo(){
        return progressInfo;
    }

//    public void setPbProgressBar(Double progressValue) {
//        LoadScreenController loadScreenController = this.get;
//        loadScreenController.setPbProgressBar(progressValue);
//    }
//
//    public void setProgressInfo(String progressInfo) {
//        this.progressInfo = progressInfo;
//    }
}
