package org.lyncc.bazinga.rx.bazinga.msentinel;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNodeBuilder;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.NodeBuilder;

/**
 * @author liguolin
 * @create 2018-12-21 11:03
 **/
public class Env {

    public static final NodeBuilder nodeBuilder = new DefaultNodeBuilder();

    public static final Sph sph = new CtSph();

}
