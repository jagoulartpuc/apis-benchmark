package assetbrains.coreapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("assetbrains.coreapi.repository")
class CoreApiApplication

fun main(args: Array<String>) {
	runApplication<CoreApiApplication>(*args)
}
