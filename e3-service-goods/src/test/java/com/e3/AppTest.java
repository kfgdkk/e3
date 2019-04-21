package com.e3;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    /*@Test
    public void testQueueProducer()throws Exception{
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.177.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queque");
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("hello activemq,this is my first MQ");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
    @Test
    public void testQueueConsumer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://192.168.177.128:61616"
        );
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queque");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = null;
                    text= textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待键盘输入
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
    @Test
    public void testTopicProducer() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.177.128:61616");//oracle1521默认开发端口
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("hello activemq,this is my first topic啦啦啦啦");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
    @Test
    public void testTopicConsumer()throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.177.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = null;
                    text=textMessage.getText();
                    System.out.println(text);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("topic的消费者02");
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
    @Test
    public void testTopicConsumer3()throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.177.128:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = null;
                    text=textMessage.getText();
                    System.out.println(text);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("topic的消费者03");
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }*/
}
