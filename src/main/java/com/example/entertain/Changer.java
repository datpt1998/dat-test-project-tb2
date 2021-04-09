package com.example.entertain;

public class Changer {
    //from MSDN article
    int SPI_SETDESKWALLPAPER = 20;
    int SPIF_UPDATEINIFILE = 0x01;
    int SPIF_SENDWININICHANGE = 0x02;

    public static native int SystemParametersInfo(int uiAction,int uiParam,Object pvParam,int fWinIni);

    static
    {
        System.loadLibrary("user32");
    }

    public int change(String path){
        return SystemParametersInfo(SPI_SETDESKWALLPAPER, 0, path, SPIF_UPDATEINIFILE | SPIF_SENDWININICHANGE);
    }
}
