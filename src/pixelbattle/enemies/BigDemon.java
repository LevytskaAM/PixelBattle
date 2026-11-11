package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Fighter;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.core.Ultimate;
import pixelbattle.engine.Sprite;

/** Великий Демон — могутній бос із вогняними здібностями. */
public class BigDemon extends Enemy {

    public BigDemon() {
        super("Великий Демон", 150, new Sprite("big_demon_idle_anim", 4, 4));

        // Звичайна атака «Кготь темряви»
        addSkill(new Skill("Кготь темряви", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(10, 16));
            return user.getName() + " атакує пазурами " + target.getName() + ": " + dmg + " шкоди";
        }));

        // Вміння «Пекло» (перезарядка 2): завдає сильної магічної шкоди
        addSkill(new Skill("Пекло", Target.ENEMY, 2, (user, target) -> {
            int dmg = target.takeDamage(random(18, 26));
            return user.getName() + " спалює " + target.getName() + " вогнем пекла на " + dmg + " шкоди!";
        }));

        // Ультимейт «Апокаліпсис» (анонімний клас): завдає шкоди всім героям та підпалює їх
        setUltimate(new Ultimate() {
            @Override public String getName() { return "Апокаліпсис"; }
            @Override public String getDescription() { return "Обрушує демонічний вогненний шторм на всіх героїв"; }

            @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int totalDmg = 0;
                for (Fighter hero : enemies) { // Для ворога масив enemies — це герої гравця
                    if (hero.isAlive()) {
                        int dmg = Fighter.random(15, 25);
                        hero.takeDamage(dmg);
                        totalDmg += dmg;
                    }
                }
                return user.getName() + " активує Апокаліпсис! Усі герої отримують загалом " + totalDmg + " шкоди!";
            }
        });
    }

    // Пасивка «Демонічний щит»: зменшує всю вхідну шкоду на 3
    @Override
    protected int modifyIncomingDamage(int damage) {
        return Math.max(0, damage - 3);
    }
}