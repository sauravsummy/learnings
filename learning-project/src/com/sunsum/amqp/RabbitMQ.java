package com.sunsum.amqp;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RabbitMQ {

    private final static String  AMQP_URL = "amqp://guest:guest@localhost:5672/";
    public static void createExchange(String exchangeName, BuiltinExchangeType type){
        ConnectionFactory connectionFactory = getConnectionFactory();
        try(Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()){
            channel.exchangeDeclare(exchangeName, type,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ConnectionFactory getConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    public static void createQueue(String qName){
        ConnectionFactory connectionFactory = getConnectionFactory();
        try(Connection connection = connectionFactory.newConnection(AMQP_URL); Channel channel = connection.createChannel()){
            channel.queueDeclare(qName,true,false,false,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void bindQ(String qName, String exchangeName, String routingKey){
        ConnectionFactory connectionFactory = getConnectionFactory();
        try(Connection connection = connectionFactory.newConnection(AMQP_URL); Channel channel = connection.createChannel()){
            channel.queueBind(qName, exchangeName, routingKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void bindToDDL(String qName, String exchangeName, String routingKey){
        ConnectionFactory connectionFactory = getConnectionFactory();
        try(Connection connection = connectionFactory.newConnection(AMQP_URL); Channel channel = connection.createChannel()){
            Map<String,Object> args = new HashMap<>();
            args.put("x-dead-letter-exchange",exchangeName);
            channel.queueBind(qName, exchangeName, routingKey,args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void publish(String exchange, String routingKey){
        ConnectionFactory cf = getConnectionFactory();
        try(Connection connection = cf.newConnection(); Channel ch=connection.createChannel();){
            boolean sendIt = true;
            while (sendIt){
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader bf = new BufferedReader(isr);
                System.out.println("Enter msg to publish");
                String msg = bf.readLine();
                boolean isExit = "exit".equals(msg.trim().toLowerCase());
                if(!isExit){
                    ch.basicPublish(exchange,routingKey,null,msg.getBytes());
                }
                if(isExit){
                    sendIt = false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void consume(String qName){
        ConnectionFactory cf = getConnectionFactory();
        try {
            Connection c = cf.newConnection();
            Channel ch = c.createChannel();
            DeliverCallback deliverCallback = (tag, delivery) -> {
                System.out.println(tag);
                System.out.println(new String(delivery.getBody()));
            };
            ch.basicConsume(qName,true,deliverCallback,tag->{});
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //createExchange("ss-x", BuiltinExchangeType.DIRECT);
        //createQueue("ss-x-q");
        //bindQ("ss-x-q","ss-x","ss-x-rk");
        /*String qName = "ss-x-q";
        String exchange = "ss-x";
        String rk = "ss-x-rk";
        consume(qName);
        publish(exchange,rk);*/
        //createExchange("com.sunsum.exchange.dlx",BuiltinExchangeType.DIRECT);
        createQueue("com.sunsum.q.dlx");
        bindToDDL("com.sunsum.q.dlx","com.sunsum.exchange.dlx","com.sunsum.rk");
    }
}
