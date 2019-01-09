package org.lyncc.bazinga.rx.bazinga.drools.test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.lyncc.bazinga.rx.bazinga.drools.domain.Account;
import org.lyncc.bazinga.rx.bazinga.drools.domain.CashFlow;
import org.lyncc.bazinga.rx.bazinga.drools.domain.Customer;
import org.lyncc.bazinga.rx.bazinga.drools.utils.KnowledgeSessionHelper;
import org.lyncc.bazinga.rx.bazinga.drools.utils.OutputDisplay;

/**
 * 什么是规则
 * 条件简单的规则
 * 如何记录规则引擎中发生的事情
 * 什么触发规则执行以及如何与规则引擎交互以进行事实处理。
 */
public class HelloRule {

    static KieContainer kieContainer;

    KieSession sessionStatefull = null;


    @BeforeClass
    public static void beforeClass(){
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }

    @Before
    public void setUp(){
        System.out.println("---------Before-------");
    }

    @After
    public void tearDown(){
        System.out.println("--------After-------");
    }


    @Test
    public void test1(){

        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer,"lesson1-session");
        Account account = new Account();
        sessionStatefull.insert(account);
        sessionStatefull.fireAllRules();

    }


    @Test
    public void testRuleOneFactWithFactAndUsageOfGlobal(){
        sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer,"lesson1-session");
        Customer customer = new Customer();
        OutputDisplay outputDisplay = new OutputDisplay();

        sessionStatefull.setGlobal("showResult",outputDisplay);
        sessionStatefull.insert(customer);

        sessionStatefull.fireAllRules();
    }

    @Test
    public void testRuleOneFactWithFactAndUsageOfGlobalAndCallBack() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "lesson1-session");
        sessionStatefull.addEventListener(new RuleRuntimeEventListener() {
            public void objectInserted(ObjectInsertedEvent event) {
                System.out.println("Object inserted \n"
                        + event.getObject().toString());
            }

            public void objectUpdated(ObjectUpdatedEvent event) {
                System.out.println("Object was updated \n"
                        + "new Content \n" + event.getObject().toString());
            }

            public void objectDeleted(ObjectDeletedEvent event) {
                System.out.println("Object retracted \n"
                        + event.getOldObject().toString());
            }
        });

//        OutputDisplay outputDisplay = new OutputDisplay();
//
//        sessionStatefull.setGlobal("showResult",outputDisplay);

        Customer a = new Customer();
        a.setName("Heron");
        FactHandle handlea = sessionStatefull.insert(a);
        a.setSurname("Nicolas");
        sessionStatefull.update(handlea, a);
//        sessionStatefull.delete(handlea);
        sessionStatefull.fireAllRules();
        System.out.println("So you saw something ;)");
    }

    @Test
    public void testRuleOneFactFireConditionStep1() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "lesson1-session");
        Account a = new Account();
        FactHandle handlea = sessionStatefull.insert(a);
        System.out.println("First fireAllRules");
        sessionStatefull.fireAllRules();
        System.out.println("Second fireAllRules");
        sessionStatefull.fireAllRules();
        System.out.println("Did A rule be fired");
    }

    /**
     * 该规则第二次执行。
     *
     * 以下是在statefull会话上调用FireAllRules方法时发生的情况：
     * drools会查看所有适用的规则并将其列入议程。
     * drools将执行其议程上的规则
     * 一旦触发，该规则将被停用
     * 我们必须告诉drools状态的变化，在部分（lhs）中的一个事实让他重新考虑规则。
     * 状态更改可以是插入，更新或删除（撤消）。
     * 在最后一个例子中，我们告诉drools事实已经更新
     * sessionStatefull.update(handle, a);
     * 因此，考虑到先前插入的事实已经更新，drools重新考虑该规则。
     * 在规则“重新访问你的第一条规则”中，属性没有条件，然后触发规则。
     */
    @Test
    public void testRuleOneFactFireConditionStep2() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "lesson1-session");
        Account a = new Account();
        //insert 表示是插入的是一个事实 返回的是一个句柄
        FactHandle handlea = sessionStatefull.insert(a);
        System.out.println("First fireAllRules");
        sessionStatefull.fireAllRules();
        System.out.println("Updating fact");
        sessionStatefull.update(handlea, a);
        System.out.println("Second fireAllRules");
        sessionStatefull.fireAllRules();
        System.out.println("Did A rule be fired");
    }


    @Test
    public void testRuleOneFactThatInsertObject() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSession(kieContainer, "lesson1-session");

        OutputDisplay display = new OutputDisplay();
        sessionStatefull.setGlobal("showResult", display);

        sessionStatefull.addEventListener(new RuleRuntimeEventListener() {
            public void objectInserted(ObjectInsertedEvent event) {
                System.out.println("Object inserted \n"
                        + event.getObject().toString());
            }

            public void objectUpdated(ObjectUpdatedEvent event) {
                System.out.println("Object was updated \n"
                        + "new Content \n" + event.getObject().toString());
            }

            public void objectDeleted(ObjectDeletedEvent event) {
                System.out.println("Object retracted \n"
                        + event.getOldObject().toString());
            }
        });
        CashFlow a = new CashFlow();
        FactHandle handlea = sessionStatefull.insert(a);
        sessionStatefull.fireAllRules();
    }
}
