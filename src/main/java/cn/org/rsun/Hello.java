package cn.org.rsun;

public interface Hello
{
    public String getName();
    public void setName(String name);
    public String getAge();
    public void setAge(String age);
    public void helloWorld();
    public void helloWorld(String str);
    public void getTelephone();
    
    @HelloTag(value= "from tag")
    public void out(String value);
}
