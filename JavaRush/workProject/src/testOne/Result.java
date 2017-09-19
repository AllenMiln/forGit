package testOne;

import java.util.Date;

/**
 * Created by admin on 19.11.2016.
 */
public class Result
{
    double sum;
    Date dateSum;
    double debt;
    Date dateDebt;
    double pay;
    int dDay;
    double fine;

    public Result(double sum, Date dateSum, double debt, Date dateDebt, double pay, int dDay, double fine)
    {
        this.sum = sum;
        this.dateSum = dateSum;
        this.debt = debt;
        this.dateDebt = dateDebt;
        this.pay = pay;
        this.dDay = dDay;
        this.fine = fine;
    }
}
