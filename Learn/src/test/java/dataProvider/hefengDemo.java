package dataProvider;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class hefengDemo {


    /**
     * 方法1： 基础使用步骤
     * <p>
     * 1. 使用@DataProvider(name = "basic") 创建一个返回二维数组的数据提供方法
     * 2. 使用@Test(dataProvider="basic") 和dataProvider进行绑定
     */
    @DataProvider(name = "DemoDataProviderBasic")
    public Object[][] demoDataProviderBasic() {
        return new Object[][]{{"1", "first"}, {"2", "second"}};
    }

    @Test(dataProvider = "DemoDataProviderBasic")
    public void testDemoBasic(String p1, String p2) {
        System.out.println(p1 + p2);
    }

    /**
     * 方法2： 指定在不同类中的dataProvider
     * <p>
     * dataProvider, 指定dataProvider注解时指定的name
     * dataProviderClass, 指定在特定类中查找provider, 如果不指定则在当前Test注解所在的类下查找
     */
    @Test(description = "HefengDemoDataProvider", dataProvider = "DemoDataProvider", dataProviderClass = HefengDemoDataprovider.class)
    public void testDemo(String p1, String p2) {
        System.out.println(p1 + ":" + p2);
    }


    /**
     * 方法3： 使用Method method 获取调用方法的属性，进行有针对的返回
     *
     * @param method 获取调用该dataProvider的方法的属性，如name,返回类型等
     *               通过method可以对不同的方法进行有针对的返回
     */
    @DataProvider(name = "DemoDataProviderMethod")
    public Object[][] demoDataProvider(Method method) {
        if (method.getName().equals("testDemoMethod1")) {
            return new Object[][]{{"1", "first"}, {"2", "second"}};
        } else {
            return new Object[][]{{"3", "three"}, {"4", "four"}};
        }
    }

    @Test(dataProvider = "DemoDataProviderMethod")
    public void testDemoMethod1(String p1, String p2) {
        System.out.println(p1 + p2);
    }

    @Test(dataProvider = "DemoDataProviderMethod")
    public void testDemoMethod2(String p1, String p2) {
        System.out.println(p1 + p2);
    }

    @DataProvider(name = "DemoDataProviderITestContext")
    public Object[][] demoDataProviderITestContext(ITestContext iTestContext) {
        String[] group = iTestContext.getIncludedGroups();
        String groupName = "";
        for (String s : group) {
            if ("demo1".equals(s)) {
                groupName = "demo1";
            } else if ("demo2".equals(s)) {
                groupName = "demo2";
            } else if ("demo3".equals(s)) {
                groupName = "demo3";
            }
        }
        if ("demo1".equals(groupName)) {
            return new Object[][]{{"1", "first"}, {"2", "second"}};
        } else if ("demo2".equals(groupName)) {
            return new Object[][]{{"3", "first"}, {"4", "second"}};
        } else if ("demo3".equals(groupName)) {
            return new Object[][]{{"5", "first"}, {"6", "second"}};
        }else {
            return new Object[][]{{"10", "first"}, {"20", "second"}};
        }
    }

    @Test(dataProvider = "DemoDataProviderITestContext", groups = {"demo1"})
    public void testDemoITestContext1(String p1, String p2) {
        System.out.println(p1 + p2);
    }

    @Test(dataProvider = "DemoDataProviderITestContext", groups = {"demo2"})
    public void testDemoITestContext2(String p1, String p2) {
        System.out.println(p1 + p2);
    }

}
