package org.lyncc.bazinga.rx.bazinga.drools.domain;

public class Account {

    private long accountno;
    private double balance;

    public Account(long accountno, double balance) {
        this.accountno = accountno;
        this.balance = balance;
    }

    public Account(){

    }

    public long getAccountno() {
        return accountno;
    }

    public void setAccountno(long accountno) {
        this.accountno = accountno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuffer buff = new StringBuffer();
        buff.append("-----Account-----)\n");
        buff.append("Account no " + this.accountno + "\n");
        buff.append("Balance " + this.balance + "\n");
        buff.append("-----End Account-)");
        return buff.toString();
    }
}
