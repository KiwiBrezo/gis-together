package si.um.feri.gisgeodataservice.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment


@Configuration
@PropertySource("classpath:application.properties")
class AppProperties(@Autowired private var env: Environment) {

    fun getAccountServiceUrl(): String? {
        return env.getProperty("account.service.url")
    }

}