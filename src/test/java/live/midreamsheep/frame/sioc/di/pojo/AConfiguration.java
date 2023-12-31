package live.midreamsheep.frame.sioc.di.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.configurarion.Bean;
import live.midreamsheep.frame.sioc.di.annotation.basic.configurarion.Configuration;

@Configuration
public class AConfiguration {

    @Bean()
    public AIter a() {
        return new A();
    }
}
