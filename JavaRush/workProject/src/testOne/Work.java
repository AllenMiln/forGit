package testOne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 18.11.2016.
 */
public class Work
{

    public static void main(String[] args) throws ParseException
    {
        //Формат даты.
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        //Заполняем массивы данными для теста.
        ArrayList<Plan> plans = new ArrayList<>();
        ArrayList<Fact> facts = new ArrayList<>();

        //Обьявляем четыре платежа по плану.
        Plan planOne = new Plan(sdf.parse("15.07.2016"),935695.35);
        Plan planTwo = new Plan(sdf.parse("01.10.2016"),3118984.48);
        Plan planThree = new Plan(sdf.parse("01.12.2016"),2183289.14);
        //Plan planFour = new Plan(sdf.parse("01.11.2016"),2000000);

        plans.add(planOne);
        plans.add(planTwo);
        plans.add(planThree);
        //plans.add(planFour);

        //Обьявляем платежи по факту.
        Fact factOne = new Fact(sdf.parse("01.07.2016"),100000);
        Fact factTwo = new Fact(sdf.parse("10.08.2016"),100000);

        facts.add(factOne);
        facts.add(factTwo);
        //расчёт
        Calculation(plans, facts);

    }

    static public void Calculation(ArrayList<Plan> plansCal, ArrayList<Fact> factsCal) {
        //Количество платежей (план)
        int nMax = plansCal.size();
        //Количество платежей (факт)
        int iMax = factsCal.size();
        //Для пропуска блока кода.
        boolean asTrue = true;
        //Процент пени в день.
        double percent = 0.001;
        //Сегодняшняя дата.
        Date today = new Date();
        //Формат даты.
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        //Массив для логирования.
        ArrayList<String> strings = new ArrayList<>();
        String s;

        ArrayList<Result> logList = new ArrayList<>();

        int i = 0;
        //1
        for (int n = 0;n < nMax - 1; n++) //Смотрим, входил ли платёж (план) в интервал.
        {
            //ПЕРЕМЕННЫЕ ДЛЯ ЛОГИРОВАНИЯ.
            double payPlan = plansCal.get(n).price;
            Date datePlan = plansCal.get(n).date;
            //2
            if (today.after(plansCal.get(n).date))   //Сравниваем две даты: наступил ли платёж (план).
            {
                //3
                while (i < iMax)  // Смотрим есть ли платежи (факт)
                {
                    //4
                    if ((factsCal.get(i).date).after(plansCal.get(n).date))  //Сравниваем две даты: платеж (факт) после  платежа (план).
                    {
                        //количество дней между датами: от факта до плана.
                        int dDate = (int)((factsCal.get(i).date.getTime() - plansCal.get(n).date.getTime())/(24 * 60 * 60 * 1000));

                        //6 расчёт пени: от даты платежа (факт), до даты платежа (план).
                        double fine = (dDate)* percent * plansCal.get(n).price;
                        plansCal.get(n).fine += (dDate)* percent * plansCal.get(n).price;
                        //7 Смена даты: теперь считать датой платежа (факт) - дату платежа (план).
                        plansCal.get(n).date = factsCal.get(i).date;

                        //ЛОГИРОВАНИЕ
                        logList.add(new Result(payPlan,datePlan,plansCal.get(n).price,factsCal.get(i).date,factsCal.get(i).price,dDate,fine));

                    }
                    else
                    {
                        //количество дней между датами: от факта до плана.
                        int dDate = (int)((factsCal.get(i).date.getTime() - plansCal.get(n).date.getTime())/(24 * 60 * 60 * 1000));

                        //ЛОГИРОВАНИЕ
                        logList.add(new Result(payPlan,datePlan,plansCal.get(n).price,factsCal.get(i).date,factsCal.get(i).price,0,0));
                    }
                    //8 Сумма платежа (факт) больше суммы платежа (план).
                    if(plansCal.get(n).price > factsCal.get(i).price)
                    {
                        //9 Платёж (план) покрыт частично. Смена суммы: вычитаем из суммы (плана) сумму (факт).
                        plansCal.get(n).price -= factsCal.get(i).price;
                        //11 Берём следующий платёж (факт).
                        i++;
                    }
                    else
                    {
                        //10 Платёж (план) покрыт полностью. Смена суммы: вычитаем из суммы (факт) сумму (план).
                        // Пропускается блок 5. Выход из цикла 3.
                        factsCal.get(i).price -= plansCal.get(n).price;

                        //если Платёж (факт) израсходован полностью, берём слудующий.
                        if (factsCal.get(i).price == 0) {
                            i++;
                        }

                        asTrue = false;
                        break;
                    }
                }

                if (asTrue)
                {
                    //Вычитаем количество дней между датами: от "сегодня" до даты платежа (план).
                    int dDate = (int) ((today.getTime() - plansCal.get(n).date.getTime()) / (24 * 60 * 60 * 1000));
                    //5 расчёт пени: от даты платежа (план), до "сегодня".
                    double fine = (dDate) * percent * plansCal.get(n).price;
                    plansCal.get(n).fine += fine;

                    //ЛОГИРОВАНИЕ
                    logList.add(new Result(payPlan,datePlan,plansCal.get(n).price,today,0,dDate,fine));
                }

                asTrue = true;
            }
            else {
                //2 Выход из цикла 1.
                break; //выход из цикла while (n < nMax - 1)
            }
        }

        //Вывод для теста
        for (Plan plan: plansCal) {System.out.println("Пени по платежу:" + plan.fine);}
        for (Result result: logList) {
            System.out.println(result.sum + " Дата(должен)="+ sdf.format(result.dateSum) + " ... Должен = " + result.debt +
                    " Дата(оплатил)"+ sdf.format(result.dateDebt) + ", Оплатил = "+ result.pay +
                    ", Кол-во дней = " + result.dDay +   ", Пени = " + result.fine);
        }
    }
}
















