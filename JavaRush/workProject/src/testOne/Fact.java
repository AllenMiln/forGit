package testOne;

import java.util.Date;

/**
 * Created by admin on 18.11.2016.
 */
public class Fact
{
    Date date;    //дата платежа по факту
    double price; //сумма платежа по факту


    Fact(Date date, double price){
        this.date = date;
        this.price = price;
    }

}
