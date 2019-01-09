package org.lyncc.bazinga.rx.bazinga.msentinel.slots.authority;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.AbstractRule;

public class AuthorityRule extends AbstractRule {


    @Override
    public boolean passCheck(Context context, DefaultNode node, int count, Object... args) {
        return false;
    }
}
