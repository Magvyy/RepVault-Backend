package com.codecool.repvault

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["com.codecool.repvault"])
@EnableJpaRepositories(basePackages = ["com.codecool.repvault.infrastructure.repositories"])
object RepVaultApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        runApplication<RepVaultApplication>(*args)
        //SpringApplication.run(RepVaultApplication::class.java, *args)
    }
}