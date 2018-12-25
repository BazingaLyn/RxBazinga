package org.lyncc.bazinga.rx.bazinga.msentinel;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.EntranceNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.StringResourceWrapper;

/**
 * @author liguolin
 * @create 2018-12-25 14:21
 **/
public class Constants {

    public final static String CONTEXT_DEFAULT_NAME = "sentinel_default_context";
    public final static int MAX_SLOT_CHAIN_SIZE = 6000;
    public final static int MAX_CONTEXT_NAME_SIZE = 2000;
    public static volatile boolean ON = true;

    public final static String ROOT_ID = "machine-root";

    /**
     * Response time that exceeds TIME_DROP_VALVE will be calculated as TIME_DROP_VALVE.
     */
    public final static int TIME_DROP_VALVE = 4900;

    public final static DefaultNode ROOT = new EntranceNode(new StringResourceWrapper(ROOT_ID,EntryType.IN),
            Env.nodeBuilder.buildClusterNode());

}
