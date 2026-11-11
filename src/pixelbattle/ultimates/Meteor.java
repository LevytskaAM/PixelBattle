package pixelbattle.ultimates;

/*
 * TODO 8: ультимейт «Метеорит» — ЗВИЧАЙНИЙ (іменований) клас:
 *
 *   public class Meteor implements Ultimate { ... }
 *
 *   - getName()        → "Метеорит"
 *   - getDescription() → короткий опис
 *   - activate(...)    → кожному живому ворогу Fighter.random(20, 30) шкоди;
 *                        поверніть рядок для журналу (наприклад, загальну шкоду).
 *
 *   Потрібні імпорти: pixelbattle.core.Fighter, pixelbattle.core.Ultimate.
 *   Зверніть увагу: Meteor НЕ є бійцем, тому random(...) тут викликається
 *   через ім'я класу: Fighter.random(20, 30).
 *
 *   Потім підключіть ультимейт у конструкторі Mage: setUltimate(new Meteor());
 */
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Meteor implements Ultimate {

    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Завдає від 20 до 30 шкоди всім живим ворогам";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int totalDamage = 0;
        for (Fighter enemy : enemies) {
            if (enemy.isAlive()) {
                int dmg = Fighter.random(20, 30);
                enemy.takeDamage(dmg);
                totalDamage += dmg;
            }
        }
        return user.getName() + " прикликає Метеорит, завдаючи загалом " + totalDamage + " шкоди ворогам!";
    }
}
