package cn.org.rsun;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ObjProxy implements InvocationHandler {
    private Object obj;
    private Object getObj(){ return obj; }
    private void setObj(Object obj){ this.obj= obj; }

    public Object getProxyInstance(Object object){
        this.obj= object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws  Throwable{
        if(method.isAnnotationPresent(HelloTag.class)){
            HelloTag hello = method.getAnnotation(HelloTag.class);
            Method[] helloMethods = hello.getClass().getDeclaredMethods();

            // 反射机制获取参数名是jdk8之后才实现的, 还需要开启必须手动在javac编译时开启-parameters
            Parameter[] parameters = method.getParameters();
            for (Parameter param : parameters) {
                for(Method md: helloMethods){
                    if(param.getName().equals(md.getName())){
                        System.out.println("find");
                    }
                }
            }

            args[0]= hello.value();
            return method.invoke(obj, args);
        }
        else return method.invoke(obj, args);
    }
}
