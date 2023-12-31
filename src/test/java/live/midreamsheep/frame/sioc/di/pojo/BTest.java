package live.midreamsheep.frame.sioc.di.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.comment.Comment;
import live.midreamsheep.frame.sioc.di.annotation.basic.comment.Injector;

@Comment
public class BTest {

    @Injector
    private AIter aIter;

    public void sayHello() {
        System.out.println("Hello, I'm B");
        aIter.sayHello();
    }
}
