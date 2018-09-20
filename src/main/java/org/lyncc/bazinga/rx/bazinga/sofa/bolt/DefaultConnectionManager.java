package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import okhttp3.ConnectionPool;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.exception.RemotingException;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.GlobalSwitch;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.RunStateRecordedFutureTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * @author liguolin
 * @create 2018-07-22 10:42
 **/
public class DefaultConnectionManager implements ConnectionManager, ConnectionHeartbeatManager,Scannable {


    private static final Logger logger = LoggerFactory.getLogger(DefaultConnectionManager.class);


    /**
     * connection pool initialize tasks
     */
    protected ConcurrentHashMap<String, RunStateRecordedFutureTask<ConnectionPool>> connTasks;

    /**
     * heal connection tasks
     */
    protected ConcurrentHashMap<String, FutureTask<Integer>>                        healTasks;


    protected ConnectionSelectStrategy                                              connectionSelectStrategy;


    private GlobalSwitch globalSwitch;

    /**
     * connection factory
     */
    protected ConnectionFactory                                                     connectionFactory;


    /**
     * address parser
     */
    protected RemotingAddressParser                                                 addressParser;


    /**
     * connection event handler
     */
    protected ConnectionEventHandler                                                connectionEventHandler;

    /**
     * connection event listener
     */
    protected ConnectionEventListener                                               connectionEventListener;



    public DefaultConnectionManager() {
        this.connTasks = new ConcurrentHashMap<String, RunStateRecordedFutureTask<ConnectionPool>>();
        this.healTasks = new ConcurrentHashMap<String, FutureTask<Integer>>();
        this.connectionSelectStrategy = new RandomSelectStrategy(globalSwitch);
    }

    /**
     * @param connectionSelectStrategy
     */
    public DefaultConnectionManager(ConnectionSelectStrategy connectionSelectStrategy) {
        this();
        this.connectionSelectStrategy = connectionSelectStrategy;
    }

    /**
     * @param connectionSelectStrategy
     * @param connctionFactory
     */
    public DefaultConnectionManager(ConnectionSelectStrategy connectionSelectStrategy,
                                    ConnectionFactory connctionFactory) {
        this(connectionSelectStrategy);
        this.connectionFactory = connctionFactory;
    }

    /**
     * @param connectionFactory
     * @param addressParser
     * @param connectionEventHandler
     */
    public DefaultConnectionManager(ConnectionFactory connectionFactory,
                                    RemotingAddressParser addressParser,
                                    ConnectionEventHandler connectionEventHandler) {
        this(new RandomSelectStrategy(), connectionFactory);
        this.addressParser = addressParser;
        this.connectionEventHandler = connectionEventHandler;
    }

    /**
     * @param connectionSelectStrategy
     * @param connctionFactory
     * @param connectionEventHandler
     * @param connectionEventListener
     */
    public DefaultConnectionManager(ConnectionSelectStrategy connectionSelectStrategy,
                                    ConnectionFactory connctionFactory,
                                    ConnectionEventHandler connectionEventHandler,
                                    ConnectionEventListener connectionEventListener) {
        this(connectionSelectStrategy, connctionFactory);
        this.connectionEventHandler = connectionEventHandler;
        this.connectionEventListener = connectionEventListener;
    }

    /**
     * @param connectionSelectStrategy
     * @param connctionFactory
     * @param connectionEventHandler
     * @param connectionEventListener
     * @param globalSwitch
     */
    public DefaultConnectionManager(ConnectionSelectStrategy connectionSelectStrategy,
                                    ConnectionFactory connctionFactory,
                                    ConnectionEventHandler connectionEventHandler,
                                    ConnectionEventListener connectionEventListener,
                                    GlobalSwitch globalSwitch) {
        this(connectionSelectStrategy, connctionFactory, connectionEventHandler,
                connectionEventListener);
        this.globalSwitch = globalSwitch;
    }


    @Override
    public void disableHeartbeat(Connection connection) {

    }

    @Override
    public void enableHeartbeat(Connection connection) {

    }

    @Override
    public void init() {

    }

    @Override
    public void add(Connection connection) {

    }

    @Override
    public void add(Connection connection, String poolKey) {

    }

    @Override
    public Connection get(String poolKey) {
        return null;
    }

    @Override
    public List<Connection> getAll(String poolKey) {
        return null;
    }

    @Override
    public Map<String, List<Connection>> getAll() {
        return null;
    }

    @Override
    public void remove(Connection connection) {

    }

    @Override
    public void remove(Connection connection, String poolKey) {

    }

    @Override
    public void remove(String poolKey) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public void check(Connection connection) throws RemotingException {

    }

    @Override
    public int count(String poolKey) {
        return 0;
    }

    @Override
    public Connection getAndCreateIfAbsent(Url url) {
        return null;
    }

    @Override
    public void createConnectionAndHealIfNeed(Url url) {

    }

    @Override
    public Connection create(Url url) throws RemotingException {
        return null;
    }

    @Override
    public Connection create(String address, int connectTimeout) throws RemotingException {
        return null;
    }

    @Override
    public Connection create(String ip, int port, int connectTimeout) throws RemotingException {
        return null;
    }

    @Override
    public void scan() {

    }
}
