package testOne;

import java.util.Date;

/**
 * Created by admin on 18.11.2016.
 */
public class Plan
{
    Date date;    //дата платежа по плану
    double price; //сумма платежа по плану
    double fine;  //сумма пени

    public Plan(Date date, double price)
    {
        this.date = date;
        this.price = price;
    }
}
