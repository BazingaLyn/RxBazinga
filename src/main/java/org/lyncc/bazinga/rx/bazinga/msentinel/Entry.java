package org.lyncc.bazinga.rx.bazinga.msentinel;


import com.alibaba.csp.sentinel.util.TimeUtil;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.Node;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ResourceWrapper;

/**
 * @author liguolin
 * @create 2018-12-21 10:46
 **/
public abstract class Entry {

    private static final Object[] OBJECTS0 = new Object[0];

    private long createTime;
    private Node curNode;

    private Node originNode;
    private Throwable error;

    protected ResourceWrapper resourceWrapper;

    public Entry(ResourceWrapper resourceWrapper) {
        this.resourceWrapper = resourceWrapper;
        this.createTime = TimeUtil.currentTimeMillis();
    }

    public ResourceWrapper getResourceWrapper() {
        return resourceWrapper;
    }


    public void exit(){
        exit(1,OBJECTS0);
    }

    public void exit(int count){
        exit(count,OBJECTS0);
    }

    protected abstract void exit(int count, Object... args);


    protected abstract Entry trueExit(int count,Object... args);

    public abstract Node getLastNode();


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Node getCurNode() {
        return curNode;
    }

    public void setCurNode(Node curNode) {
        this.curNode = curNode;
    }

    public Node getOriginNode() {
        return originNode;
    }

    public void setOriginNode(Node originNode) {
        this.originNode = originNode;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public void setResourceWrapper(ResourceWrapper resourceWrapper) {
        this.resourceWrapper = resourceWrapper;
    }
}
