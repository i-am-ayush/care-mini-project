package populator;

import dao.DatabaseConnector;
import form.Form;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FormPopulator {
    private final static Logger logger = Logger.getLogger(FormPopulator.class);

    public static <T extends Form> T populate(HttpServletRequest request, Class<T> tClass) {

        try {
            return populateForm(request, tClass);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return null;
    }

    public static <T extends Form> T populateForm(HttpServletRequest request, Class<T> tClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] variables = tClass.getDeclaredFields();
        T t = tClass.newInstance();
        System.out.println("okokokokokookk");
        for (Field f : variables) {
            System.out.println("1");
            System.out.println(f.getName());
            Method fieldSetter = null;
            fieldSetter = tClass.getMethod(setterMethodName(f),f.getType());
            System.out.println(fieldSetter.getName());
            fieldSetter.invoke(t,request.getParameter(f.getName()));


        }
        return t;
    }

    private static String setterMethodName(Field f) {
        String name = f.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("2");
        System.out.println(name);
        return "set" + name;
    }
}
