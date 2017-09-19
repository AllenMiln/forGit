package allenmilngroup.game;

import allenmilngroup.game.sprites.Barrel;
import allenmilngroup.game.sprites.Mouse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class BarrelManager {
    //Список бочек.
    private ArrayList<Barrel> barrelArrayList;

    //Когда создали бочку.
    private long lastDropTime;

    public BarrelManager() {
        //Создание массива бочек
        barrelArrayList = new ArrayList<Barrel>();

        //Когда создали бочку.
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnBarrel() {
        Barrel barrel = new Barrel();
        barrelArrayList.add(barrel);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void update(float dt) {

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000 - MathUtils.random(0, 250000000)) { spawnBarrel(); }

        Iterator<Barrel> iterator = barrelArrayList.iterator();
        while (iterator.hasNext()) {
            Barrel barrel = iterator.next();
            barrel.update(dt);
            if (barrel.getPosition().y < -100) {
                barrel.dispose();
                iterator.remove();
            }
        }

        checkAllCollisions();
    }

    /**
     * Проверка на пересечение всех бочек
     */
    private void checkAllCollisions() {
        for (int i = 0, size = barrelArrayList.size(); i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                barrelArrayList.get(i).collision(barrelArrayList.get(j));
            }
        }
    }

    /**
     * Проверка пересечений всех бочек с мышкой
     */
    public void checkMouseCollisions(Mouse mouse) {
        for (Barrel barrel : barrelArrayList) {
            barrel.mouseCollision(mouse);
        }
    }

    public ArrayList<Barrel> getBarrelArrayList() {
        return barrelArrayList;
    }

    public void dispose() {
        for (Barrel barrel : barrelArrayList) {
            barrel.dispose();
        }
    }
}
