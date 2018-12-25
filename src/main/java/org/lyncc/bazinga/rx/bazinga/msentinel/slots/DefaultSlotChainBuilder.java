package org.lyncc.bazinga.rx.bazinga.msentinel.slots;

import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.DefaultProcessorSlotChain;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotChain;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.SlotChainBuilder;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.authority.AuthoritySlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.cluster.ClusterBuilderSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.degrade.DegradeSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.flow.FlowSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.logger.LogSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.nodeseletor.NodeSelectorSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic.StatisticSlot;
import org.lyncc.bazinga.rx.bazinga.msentinel.slots.system.SystemSlot;

/**
 * @author liguolin
 * @create 2018-12-25 14:54
 **/
public class DefaultSlotChainBuilder implements SlotChainBuilder {


    @Override
    public ProcessorSlotChain build() {

        ProcessorSlotChain chain = new DefaultProcessorSlotChain();
        chain.addLast(new NodeSelectorSlot());
        chain.addLast(new ClusterBuilderSlot());
        chain.addLast(new LogSlot());
        chain.addLast(new StatisticSlot());
        chain.addLast(new SystemSlot());
        chain.addLast(new AuthoritySlot());
        chain.addLast(new FlowSlot());
        chain.addLast(new DegradeSlot());

        return chain;
    }
}
