package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

/** Гоблін — підступний і швидкий. */
public class Goblin extends Enemy {

    public Goblin() {
        super("Гоблін", 70, new Sprite("goblin_idle_anim", 4, 6));

        addSkill(new Skill("Удар кинджалом", Target.ENEMY, 0, (user, target) ->
                user.getName() + " б'є кинджалом " + target.getName() + ": "
                        + target.takeDamage(random(8, 12)) + " шкоди"));

        // БОНУС: вміння «Бомба» (перезарядка 3) — 15..22 шкоди та оглушення на 1 хід.
        addSkill(new Skill("Бомба", Target.ENEMY, 3, (user, target) -> {
            int dmg = target.takeDamage(random(15, 22));
            target.stun(1);
            return user.getName() + " кидає Бомбу в " + target.getName() + ": " + dmg + " шкоди та оглушення!";
        }));
    }
}
