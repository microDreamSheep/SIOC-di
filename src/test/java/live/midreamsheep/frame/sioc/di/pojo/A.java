package live.midreamsheep.frame.sioc.di.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.comment.Comment;

public class A implements AIter{
    @Override
    public void sayHello() {
        System.out.println("Hello, I'm A");
    }
}
