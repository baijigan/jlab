package cn.org.rsun;

public class ObjFactory{
    public static <T> T newInstance(String className ){
        try {
            Class proxy = Class.forName("cn.org.rsun.ObjProxy");
            ObjProxy objProxy = (ObjProxy)proxy.newInstance();

            Class impl = Class.forName("cn.org.rsun."+ className+ "Impl");
            Object objImpl = impl.newInstance();

            T o= (T)objProxy.getProxyInstance(objImpl);
            return o;
        } catch (Exception E){  E.printStackTrace(); }
        return null;
    }
}
