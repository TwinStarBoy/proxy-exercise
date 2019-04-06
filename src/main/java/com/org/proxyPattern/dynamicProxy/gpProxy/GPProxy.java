package com.org.proxyPattern.dynamicProxy.gpProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GPProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader gpClassLoader , Class<?>[] interfaces,GPInvocationHandler h){

        try {
            String src = generateSrc(interfaces);
            String filePath = GPProxy.class.getResource("").getPath();
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manage.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null,manage,null,null,null,iterable);
            task.call();
            manage.close();


            Class proxyClass = gpClassLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            return c.newInstance(h);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?>[] interfaces){
        StringBuffer sb = new StringBuffer();
        sb.append("package com.org.proxyPattern.dynamicProxy.gpProxy;" + ln);
        sb.append("import com.org.proxyPattern.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
            sb.append("GPInvocationHandler h;" + ln);
            sb.append("public $Proxy0 (GPInvocationHandler h){" + ln);
                sb.append("this.h = h");
            sb.append("}" + ln);

            for (Method m : interfaces[0].getMethods()){
                Class<?>[] params = m.getParameterTypes();

                StringBuffer paramNames = new StringBuffer();
                StringBuffer paramValues = new StringBuffer();
                StringBuffer paramClasses = new StringBuffer();

                for (int i=0;i<params.length;i++){
                    Class clazz = params[i];
                    String type = clazz.getName();
                    String paramName = toLowFirstCase(type);
                    paramNames.append(type + " " + paramName);
                    paramValues.append(paramName);
                    paramClasses.append(clazz.getName() + ".class");

                    if (i >0 && i<params.length -1){
                        paramNames.append(",");
                        paramValues.append(",");
                        paramClasses.append(",");
                    }
                }

                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + "){" + ln);
                    sb.append("try {" + ln);
                        sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() +"});" + ln);
                        sb.append((hasReturn(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m, new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);
                    sb.append("}catch(Error _ex) { }" + ln);
                    sb.append("catch(Throwable e){" + ln);
                        sb.append("throw new UndeclaredThrowableException(e);" + ln);
                    sb.append("}");
                    sb.append(getReturnEmptyCode(m.getReturnType()));
                sb.append("}" + ln);
            }
        sb.append("}" + ln);
        return sb.toString();
    }

    private static Map<Class ,Class> mappings = new HashMap<Class,Class>();

    static {
        mappings.put(int.class , Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if (mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == Void.class){
            return "";
        }else {
            return "return null";
        }
    }

    private static String getCaseCode(String code , Class<?> returnClass){
        if (mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code ;
    }

    public static String toLowFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.copyValueOf(chars);
    }

    public static boolean hasReturn(Class<?> clazz){
        return clazz != Void.class;
    }
}
