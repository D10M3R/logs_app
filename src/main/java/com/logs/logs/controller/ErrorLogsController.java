package com.logs.logs.controller;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorLogsController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLogsController.class);
    private final Random random = new Random();

    @GetMapping("/dividir")
    public ResponseEntity<String> dividir(@RequestParam(required = false, defaultValue = "10") int divisor) {
        int dividendo = random.nextInt(100);

        try {
            int resultado = dividendo / divisor;
            logger.info("División realizada: {}/{}={}", dividendo, divisor, resultado);
            return ResponseEntity.ok(String.format("Resultado: %d / %d = %d", dividendo, divisor, resultado));
        } catch (ArithmeticException e) {
            logger.error("Error al realizar la división: {}/{}", dividendo, divisor, e);
            return ResponseEntity.badRequest().body("Error: División por cero. Revisa los parámetros.");
        }
    }

    @GetMapping("/accesoIndice")
    public String accesoIndice(@RequestParam(defaultValue = "5") int index) {
        int[] array = { 1, 2, 3, 4, 5 };

        try {
            int valor = array[index];
            logger.info("Acceso al índice realizado: array[{}]={}", index, valor);
            return String.format("Valor en el índice %d es %d", index, valor);

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Error al acceder al indice: array[{}]", index, e);
            return "Error: Índice fuera de rango. Revisa los parámetros.";
        }
    }

    @GetMapping("/convertirNumero")
    public String convertirNumero(@RequestParam String input) {
        try {
            int numero = Integer.parseInt(input);
            logger.info("Conversion realizada: input='{}', numero={}", input, numero);
            return String.format("Número convertido correctamente: %d", numero);

        } catch (NumberFormatException e) {
            logger.error("Error al convertir el input a numero: '{}'", input, e);
            return "Error: No se pudo convertir el input a un número. Revisa los parámetros.";
        }
    }

    @GetMapping("/modulo")
    public String modulo(@RequestParam(defaultValue = "10") int divisor) {
        int dividendo = random.nextInt(100);

        try {
            int resultado = dividendo % divisor; // Podría generar una excepción si divisor es cero
            logger.info("Operacion modulo realizada: {} % {} = {}", dividendo, divisor, resultado);
            return String.format("Resultado: %d %% %d = %d", dividendo, divisor, resultado);

        } catch (ArithmeticException e) {
            logger.error("Error al realizar la operacion modulo: {} % {}", dividendo, divisor, e);
            return "Error: Operación módulo con divisor cero. Revisa los parámetros.";
        }
    }
}
