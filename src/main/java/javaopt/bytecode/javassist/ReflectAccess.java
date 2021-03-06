package javaopt.bytecode.javassist;
import java.lang.reflect.Method;

/**
 * Simple example of program using reflection. This just increments an
 * <code>int</code> bean property using reflection method calls.
 */

public class ReflectAccess {
    public static void main(String[] args) throws Exception {
        String value = "value1";
        // create property name
        char lead = value.charAt(0);
        String pname = Character.toUpperCase(lead) + value.substring(1);

        // look up the get and set methods
        Method gmeth = HolderBean.class.getDeclaredMethod("get" + pname,
                new Class[0]);
        Method smeth = HolderBean.class.getDeclaredMethod("set" + pname,
                new Class[] { int.class });

        // increment value using reflection
        HolderBean bean = new HolderBean();
        Object start = gmeth.invoke(bean, null);
        int incr = ((Integer) start).intValue() + 1;
        smeth.invoke(bean, new Object[] { new Integer(incr) });

        // print the ending values
        System.out.println("Result values " + bean.getValue1() + ", "
                + bean.getValue2());
    }
}
