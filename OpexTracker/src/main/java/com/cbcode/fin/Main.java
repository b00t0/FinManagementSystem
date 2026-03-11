package com.cbcode.fin;

import com.cbcode.fin.config.AppConfig;
import com.cbcode.fin.console.ConsoleMenu;



public class Main {
    public static void main(String[] args) {


        AppConfig config = new AppConfig();

        ConsoleMenu menu = config.consoleMenu();

        menu.start();

    }
}