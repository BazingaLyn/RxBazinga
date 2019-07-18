package org.lyncc.bazinga.rx.bazinga.filesave.internal;

/**
 *
 * 定期扫描所有的文件,对重复的 key 进行合并.
 * 即: 将所有的老数据文件扫描一遍并生产新的数据文件,对同一个 key 的多个操作只保留最新的一个的原则进行删除.
 *    每次合并后,新生成的数据文件就不再有冗余数据了.
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:27
 */
public class Compaction {

    public void compact() {


    }

}
