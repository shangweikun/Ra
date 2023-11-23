package com.example._21.demo;

import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.PutParams;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final String sessionId = "6feb7805-260b-61ca-a36c-5d275c1fca90";
    private static final PutParams acquireParams = new PutParams();
    private static final PutParams releaseParams = new PutParams();

    static {
        acquireParams.setAcquireSession(sessionId);
        releaseParams.setReleaseSession(sessionId);
    }


    public static void main(String[] args) {

        ConsulClient client = new ConsulClient("localhost");
        ConsulClient client0 = new ConsulClient("localhost0");

        CyclicBarrier barrier = new CyclicBarrier(2);

        Thread thread = new Thread(() -> {
            while (!client.setKVBinaryValue("key", "locked".getBytes(), acquireParams)
                    .getValue()) ;
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("~");
            client.deleteKVValue("key", acquireParams.toUrlParameters().get(0));
        });

        Thread thread0 = new Thread(() -> {
            while (!client0.setKVBinaryValue("key", "locked".getBytes(), acquireParams)
                    .getValue()) ;
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("0");
            client0.deleteKVValue("key", acquireParams.toUrlParameters().get(0));
        });

        thread.start();
        thread0.start();


    }
}
