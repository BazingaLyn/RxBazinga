package org.lyncc.bazinga.rx.bazinga.algorithm.practise;

import java.util.ArrayList;

public class PractiseMaxHeap<E extends Comparable<E>> {

    public static void main(String[] args) {
        PractiseMaxHeap<Integer> practiseMaxHeap = new PractiseMaxHeap<Integer>();
        practiseMaxHeap.add(21);
        practiseMaxHeap.add(44);
        practiseMaxHeap.add(56);
        practiseMaxHeap.add(14);
        practiseMaxHeap.add(140);

        System.out.println(practiseMaxHeap.extractMax());
        System.out.println(practiseMaxHeap.extractMax());
        System.out.println(practiseMaxHeap.extractMax());
        System.out.println(practiseMaxHeap.extractMax());
        System.out.println(practiseMaxHeap.extractMax());
    }


    private ArrayList<E> datas;

    public PractiseMaxHeap(int capacity){
        datas = new ArrayList<>(capacity);
    }

    public PractiseMaxHeap(){
        datas = new ArrayList<>();
    }

    // 返回堆中的元素个数
    public int size(){
        return datas.size();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return datas.isEmpty();
    }


    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        datas.add(e);
        siftUp(datas.size() - 1);
    }

    private void siftUp(int k){
        while(k > 0 && datas.get(parent(k)).compareTo(datas.get(k)) < 0){
            E temp = datas.get(parent(k));
            datas.set(parent(k),datas.get(k));
            datas.set(k,temp);

            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax(){
        if(datas.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return datas.get(0);
    }

    // 取出堆中最大元素
    public E extractMax(){

        E ret = findMax();

        datas.set(0,datas.get(datas.size() -1));
        datas.remove(datas.size() -1);
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){
        while(leftChild(k) < datas.size()){
            int j = leftChild(k);
            if( j + 1 < datas.size() &&
                    datas.get(j + 1).compareTo(datas.get(j)) > 0 )
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if(datas.get(k).compareTo(datas.get(j)) >= 0 )
                break;

            E temp = datas.get(k);
            datas.set(k,datas.get(j));
            datas.set(j,temp);
            k = j;
        }
    }
}
