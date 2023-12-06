package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart <T extends Food>{

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        final boolean[] proteins = {false};
        final boolean[] fats = {false};
        final boolean[] carbohydrates = {false};

        foodstuffs.stream().forEach(food -> {
            if (!proteins[0] && food.getProteins())
                proteins[0] = true;
            else
            if (!fats[0] && food.getFats())
                fats[0] = true;
            else
            if (!carbohydrates[0] && food.getCarbohydrates())
                carbohydrates[0] = true;
//            if (proteins[0] && fats[0] && carbohydrates[0]) // не придумал как это изменить. работает без данного условия
//                break;
        });

        if (proteins[0] && fats[0] && carbohydrates[0])
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        market.getThings(Food.class).stream().forEach(thing -> {
            if (!proteins[0] && thing.getProteins())
            {
                proteins[0] = true;
                foodstuffs.add((T)thing);
            }
            else if (!fats[0] && thing.getFats())
            {
                fats[0] = true;
                foodstuffs.add((T)thing);
            }
            else if (!carbohydrates[0] && thing.getCarbohydrates())
            {
                carbohydrates[0] = true;
                foodstuffs.add((T)thing);
            }
//            if (proteins[0] && fats[0] && carbohydrates[0]) // не придумал как это изменить. работает без данного условия
//                break;
        });

        String result = (proteins[0] && fats[0] && carbohydrates[0]) ? "Корзина сбалансирована по БЖУ." : "Невозможно сбалансировать корзину по БЖУ.";
        System.out.println(result);

    }

}
