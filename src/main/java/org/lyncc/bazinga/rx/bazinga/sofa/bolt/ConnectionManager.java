package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.exception.RemotingException;

import java.util.List;
import java.util.Map;

/**
 * @author liguolin
 * @create 2018-07-27 11:12
 **/
public interface ConnectionManager extends Scannable {


    void init();


    void add(Connection connection);


    void add(Connection connection, String poolKey);


    Connection get(String poolKey);


    List<Connection> getAll(String poolKey);


    Map<String, List<Connection>> getAll();


    void remove(Connection connection);


    void remove(Connection connection, String poolKey);


    void remove(String poolKey);


    void removeAll();


    void check(Connection connection) throws RemotingException;


    int count(String poolKey);


    Connection getAndCreateIfAbsent(Url url);



    void createConnectionAndHealIfNeed(Url url);



    Connection create(Url url)throws RemotingException;



    Connection create(String address, int connectTimeout) throws RemotingException;




    Connection create(String ip, int port, int connectTimeout) throws RemotingException;



}
