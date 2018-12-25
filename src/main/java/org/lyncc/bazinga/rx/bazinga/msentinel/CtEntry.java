package org.lyncc.bazinga.rx.bazinga.msentinel;

import org.lyncc.bazinga.rx.bazinga.msentinel.context.Context;
import org.lyncc.bazinga.rx.bazinga.msentinel.context.NullContext;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotChain;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

/**
 * @author liguolin
 * @create 2018-12-25 14:01
 **/
public class CtEntry extends Entry {

    protected Entry parent = null;

    protected Entry child = null;

    protected ProcessorSlotChain<Object> chain;
    protected Context context;


    public CtEntry(ResourceWrapper resourceWrapper, ProcessorSlotChain<Object> chain,Context context) {
        super(resourceWrapper);
        this.chain = chain;
        this.context = context;

        setUpEntryFor(context);
    }

    private void setUpEntryFor(Context context) {

        if(context instanceof NullContext){
            return;
        }
        this.parent = context.getCurEntry();
        if(null != null){
            ((CtEntry)parent).child = this;
        }
        context.setCurEntry(this);
    }

    @Override
    public Node getLastNode() {
        return parent == null ? null : parent.getCurNode();
    }

    @Override
    protected void exit(int count, Object... args) {
        trueExit(count, args);
    }

    @Override
    protected Entry trueExit(int count, Object... args) {
        exitForContext(context,count,args);
        return parent;
    }


    protected void exitForContext(Context context, int count, Object... args) {

    }
}
