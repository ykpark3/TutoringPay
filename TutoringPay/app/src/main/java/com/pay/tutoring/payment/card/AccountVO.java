package com.pay.tutoring.payment.card;

public class AccountVO {


    private String name;
    private String bank;
    private int accountNum;
    private int img;
    private int pay;

    public AccountVO()
    {

    }
    public AccountVO(String name,String bank, int accountNum, int img)
    {
        this.name = name;
        this.bank = bank;
        this.accountNum = accountNum;
        this.img = img;
    }


    public void setName(String name)
    {
        this.name = name;
    }
    public String getName(){ return name;}

    public void setBank(String bank)
    {
        this.bank = bank;
    }
    public String getBank(){ return bank;}

    public void setAccountNum(int accountNum)
    {
        this.accountNum = accountNum;
    }
    public int getAccountNum(){ return accountNum;}

    public void setImg(int img)
    {
        this.img = img;
    }
    public int getImg(){ return img;}

    public void setPay(int pay)
    {
        this.pay = pay;
    }
    public int getPay(){ return pay;}
}
