package com.devlpjruan.statisticswinning.program;

import com.devlpjruan.statisticswinning.entities.Panel;
import com.devlpjruan.statisticswinning.entities.Person;

public class Application {

    public static void main(String[] args) {
    	Person player= new Person(0, "10000.0", 0, 0);
    	Panel painel = new Panel(player);
    }
}
