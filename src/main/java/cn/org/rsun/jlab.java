package cn.org.rsun;
import java.io.IOException;
import java.util.*;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.rmi.registry.LocateRegistry;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import java.lang.ref.SoftReference;
import java.net.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class jlab {
    public static RefObject refOjbect;
    public static AutoObject autoObject;

    public static void main(String[] args){
        //testReference();
        //testFinalize();
        //testJmx();
        //testMemory();
        //testAnnotation( );
        testLoader();
        /*
        testThread( );
        testString( );
        */

        while(true){
            try{ Thread.sleep( 2000 );}catch (Exception e){}
        }
    }

    public static void testFinalize( ){
        for(int i=0; i<10000000; i++) {
            Thread thread =new Thread(new Runnable() {
                public void run() {
                    jlab lab = allocFinalize();
                    lab = null;
                    freeFinalize();
                }
            });
            thread.start();
            thread= null;
            System.gc();
        }
    }

    public static jlab allocFinalize( ){
        jlab lab = new jlab();
        refOjbect= new RefObject( lab );
        return lab;
    }

    public static void freeFinalize( ){
        refOjbect.free();
        refOjbect= null;

        System.gc();
        try{ Thread.sleep(500);}catch (Exception E){}
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("RefOjbect 内存释放成功()");
    }

    public static void testReference( ){
        RefObject ref= new RefObject( new jlab() );

        SoftReference<RefObject> ex = new SoftReference<RefObject>( ref );
        System.out.println("Soft refrence :: " + ex.get());
    }

    public static void testMemory( ){
        List<Object> list= new ArrayList<Object>();
        for(int i=0; i<1000000; i++){
            for(int j=0; j<1; j++) {
                list.add(new longOjbectXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx());
            }
        }
    }

    public static void testJmx( ){
        try {
            // 本地连接模式 jconsole.exe
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName helloName = new ObjectName("jmxBean:name=hello");
            server.registerMBean(new HelloImpl(), helloName);

            // 网络连接模式
            LocateRegistry.createRegistry(9999);
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            jcs.start();

        }catch (Exception E){}
    }

    public static void testAnnotation( ){
        // 手工创建模式
        try{
            ObjProxy helloProxy= new ObjProxy();
            Hello hello= (Hello)helloProxy.getProxyInstance( new HelloImpl() );
            hello.out("");
        }catch (Exception E){ }

        // 类工厂模式
        Hello hello= ObjFactory.newInstance("Hello");
        hello.out("");
    }

    public static void testLoader( ) {
        String packageName= "cn.org.rsun";
        Set<Class<?>> cls= ObjLoader.scanPackage();

        Iterator<Class<?>> it= cls.iterator();
        while(it.hasNext()){
            Class<?> obj= it.next();
            try {
                autoObject = (AutoObject) obj.newInstance();
                break;
            }catch (Exception E){}
        }

        autoObject.out();
    }

    public static void testThread( ){
        for(int i=0; i<100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while(true){
                        try{ Thread.sleep( 2000 );}catch (Exception e){}
                    }
                }
            }).start();
        }
    }

    public static void testString( ){
        // String、StringBuffer、StringBuilder

        String str= "";
        for(int i=0; i<1000000; i++){
            for(int j=0; j<1000000; j++) {
                //str = str + "hello xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + i;
                //str = "";
            }
        }

    }

}
