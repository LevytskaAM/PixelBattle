package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Ultimate;
import pixelbattle.core.Fighter;

/** Лицар — міцний воїн ближнього бою. */
public class Knight extends Hero {

    public Knight() {
        super("Лицар", 110, new Sprite("knight_m_idle_anim", 4, 4));

        // ПРИКЛАД: звичайна атака, ефект записаний ЛЯМБДОЮ
        addSkill(new Skill("Удар мечем", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(12, 18));
            return user.getName() + " рубає " + target.getName() + ": " + dmg + " шкоди";
        }));

        // TODO 1: вміння «Щит» (Target.SELF, перезарядка 2):
        //   лицар стає в захист — user.setGuarding(true).
        addSkill(new Skill("Щит", Target.SELF, 2, (user, target) -> {
            user.setGuarding(true);
            return user.getName() + " підіймає щит і готується до захисту!";
        }));

        // TODO 7: ультимейт «Небесний молот» — АНОНІМНИЙ КЛАС, що реалізує Ultimate:
        //   кожному ЖИВОМУ ворогу 15 шкоди та оглушення на 1 хід.
        //   (не забудьте import pixelbattle.core.Ultimate; та import pixelbattle.core.Fighter;)
        setUltimate(new Ultimate() {
            @Override public String getName() { return "Небесний молот"; }
            @Override public String getDescription() { return "Завдає 15 шкоди всім ворогам та оглушує їх на 1 хід"; }
            @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                for (Fighter e : enemies) {
                    if (e.isAlive()) {
                        e.takeDamage(15);
                        e.stun(1);
                    }
                }
                return user.getName() + " викликає Небесний молот, оглушуючи ворогів!";
            }
        });
    }

    // TODO 4: пасивка «Важка броня» — перевизначте modifyIncomingDamage:
    //   лицар отримує на 4 шкоди менше (але не менше 0).
    @Override
    protected int modifyIncomingDamage(int damage) {
        int reducedDamage = damage - 4;
        return Math.max(0, reducedDamage);
    }
}
