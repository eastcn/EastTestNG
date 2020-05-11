package dataProvider;

import org.testng.annotations.DataProvider;

public class HefengDemoDataprovider {
    /**
     * 被指定的类必须为static方法
     */
    @DataProvider(name = "DemoDataProvider")
    static public Object[][] DemoDataProvider() {
        return new Object[][]{{"1", "first"}, {"2", "second"}};
    }

}
