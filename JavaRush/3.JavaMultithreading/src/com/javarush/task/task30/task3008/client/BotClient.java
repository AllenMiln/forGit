package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        //19.1
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        //19.2
        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String name = message.split(":")[0].trim();
                String command = message.split(":")[1].trim();

                SimpleDateFormat simpleDateFormat;
                Date date = Calendar.getInstance().getTime();
                switch (command) {
                    case "дата":
                        simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "день":
                        simpleDateFormat = new SimpleDateFormat("d");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "месяц":
                        simpleDateFormat = new SimpleDateFormat("MMMM");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "год":
                        simpleDateFormat = new SimpleDateFormat("YYYY");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "время":
                        simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "час":
                        simpleDateFormat = new SimpleDateFormat("H");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "минуты":
                        simpleDateFormat = new SimpleDateFormat("m");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                    case "секунды":
                        simpleDateFormat = new SimpleDateFormat("s");
                        sendTextMessage(String.format("Информация для %s: %s", name, simpleDateFormat.format(date)));
                        break;
                }

            }
        }
    }

    @Override
    protected String getUserName() {

        return "date_bot_" +  (int) (Math.random()*100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
