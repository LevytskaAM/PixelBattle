package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

/** Скелет — нежить із можливістю воскреснути. */
public class Skeleton extends Enemy {
    private boolean revivesAvailable = true; // Прапорець для пасивки «Воскресіння»

    public Skeleton() {
        super("Скелет", 50, new Sprite("skelet_idle_anim", 4, 6));

        // Звичайна атака «Кістяний удар»: 8–12 шкоди
        addSkill(new Skill("Кістяний удар", Target.ENEMY, 0, (user, target) ->
                user.getName() + " б'є кістками " + target.getName() + ": "
                        + target.takeDamage(random(8, 12)) + " шкоди"));
    }

    // Пасивка «Воскресіння»: перший фатальний удар залишає 1 HP
    @Override
    public int takeDamage(int damage) {
        int actualDamage = super.takeDamage(damage);
        // Якщо HP впало до 0 або нижче, але воскресіння ще доступне
        if (getHp() <= 0 && revivesAvailable) {
            revivesAvailable = false;
            // Відновлюємо HP до 1 (heal повертає скільки додано)
            heal(1);
        }
        return actualDamage;
    }
}