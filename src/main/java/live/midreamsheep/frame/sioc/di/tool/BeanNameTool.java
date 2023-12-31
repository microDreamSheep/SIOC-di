package live.midreamsheep.frame.sioc.di.tool;

public class BeanNameTool {
    public static String getBeanName(String name,Class<?> aClass){
        if (!name.isEmpty()){
            return name;
        }
        //如果name为空，就使用类名首字母小写
        name = aClass.getName();
        name = name.substring(0,1).toLowerCase() + name.substring(1);
        return name;
    }
}
