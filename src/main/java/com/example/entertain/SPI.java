package com.example.entertain;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import java.util.HashMap;

public interface SPI extends StdCallLibrary {

    SPI INSTANCE = (SPI) Native.load("user32", SPI.class, new HashMap<String, Object>() {
        {
            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
        }
    });

    boolean SystemParametersInfo(
            int uiAction,
            int uiParam,
            String pvParam,
            int fWinIni
    );
}
