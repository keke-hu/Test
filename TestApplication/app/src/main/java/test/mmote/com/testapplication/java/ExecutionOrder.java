package test.mmote.com.testapplication.java;

/**
 * Created by KeKe on 2017/10/16.
 */

public class ExecutionOrder {

    AttributeOrder attributeOrder = new AttributeOrder();

    /**
     * 静态代码块
     */
    static {
        System.out.println("执行静态代码块...");
    }

    /**
     * 构造代码块
     */ {
        System.out.println("执行构造代码块...");
    }


    public ExecutionOrder() {
        System.out.println("执行无参构造函数...");
    }

    public ExecutionOrder(String id) {
        System.out.println("执行有参构造函数...");
    }
}
