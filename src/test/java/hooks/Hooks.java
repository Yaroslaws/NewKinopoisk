package hooks;


import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    @Before
    public void prepareData() {
        //подготовить данные
    }

    @After
    public void clearData() {
        //очистить данные
    }

}
