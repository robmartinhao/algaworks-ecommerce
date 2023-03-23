package com.algaworks.ecommerce.concorrencia;

import org.junit.Test;

public class ThreadTest {

    public static void log(Object obj, Object... args) {
        System.out.println(
                String.format("[LOG " + System.currentTimeMillis() + "] " + obj, args)
        );
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void entenderThreads() {
        Runnable runnable1 = () -> {
            log("Runnable 01 vai esperar 3 segundos.");
            esperar(3);
            log("Runnable 01 concluído");
        };

        Runnable runnable2 = () -> {
            log("Runnable 02 vai esperar 1 segundos.");
            esperar(1);
            log("Runnable 02 concluído");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
        log("Encerrando método de teste");
    }
}
