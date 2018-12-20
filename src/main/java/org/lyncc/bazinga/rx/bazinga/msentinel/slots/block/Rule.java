package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;

public interface Rule {


    boolean passCheck(Context context, DefaultNode node, int count, Object... args);

}
