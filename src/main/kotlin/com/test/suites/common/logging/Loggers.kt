package com.test.suites.common.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Punto unico para obtener loggers SLF4J.
 *
 * SOLID - SRP / DRY: evita repetir `LoggerFactory.getLogger(...)` en cada
 * clase de la suite API o Web.
 */
object Loggers {
    fun <T : Any> of(clazz: Class<T>): Logger = LoggerFactory.getLogger(clazz)
}
