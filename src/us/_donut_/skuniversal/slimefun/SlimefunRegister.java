package us._donut_.skuniversal.slimefun;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.mrCookieSlime.Slimefun.Events.ResearchUnlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us._donut_.skuniversal.SkUniversalEvent;

public class SlimefunRegister {

    public SlimefunRegister() {
        //Conditions
        Skript.registerCondition(CondHasResearch.class, "%offlineplayer% has [unlocked] [the] [Slimefun] research [with id] %integer%");

        //Effects
        Skript.registerEffect(EffCreateItem.class, "create [a] [new] Slimefun item %itemstack% with id %string% in category %string% with recipe %itemstacks% with recipe type %string%");
        Skript.registerEffect(EffCreateResearch.class, "create [a] [new] [Slimefun] research with id %integer% (named|with name) %string% with (cost|level) %integer%");
        Skript.registerEffect(EffAddResearch.class, "add [the] [Slimefun] research [with id] %integer% to [the] [Slimefun] [item] [(named|with name)] %string%");
        Skript.registerEffect(EffUnlockResearch.class, "unlock [the] [Slimefun] research [with id] %integer% for %player%");
        Skript.registerEffect(EffLockResearch.class, "lock [the] [Slimefun] research [with id] %integer% for %player%");

        //Expressions
        Skript.registerExpression(ExprItemNames.class, String.class, ExpressionType.SIMPLE, "[the] [names of] [all] [the] Slimefun items");
        Skript.registerExpression(ExprSlimefunItem.class, ItemStack.class, ExpressionType.COMBINED, "[the] Slimefun item [(named|with name)] %string%");
        Skript.registerExpression(ExprResearchName.class, String.class, ExpressionType.COMBINED, "[the] name of [the] [Slimefun] research [with id] %integer%");
        Skript.registerExpression(ExprResearchCost.class, Number.class, ExpressionType.COMBINED, "[the] (cost|level) of [the] [Slimefun] research [with id] %integer%");
        Skript.registerExpression(ExprAllResearches.class, Number.class, ExpressionType.SIMPLE, "[[the] ids of] [all [of]] [the] [Slimefun] researches");

        //Events
        Skript.registerEvent("Slimefun - Research Unlock", SkUniversalEvent.class, ResearchUnlockEvent.class, "[Slimefun] research unlock")
                .description("Called when a player unlocks a Slimefun research.")
                .examples("on research unlock:", "\tsend \"You unlocked research!\"");
        EventValues.registerEventValue(ResearchUnlockEvent.class, Player.class, new Getter<Player, ResearchUnlockEvent>() {
            public Player get(ResearchUnlockEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ResearchUnlockEvent.class, Number.class, new Getter<Number, ResearchUnlockEvent>() {
            public Number get(ResearchUnlockEvent e) {
                return e.getResearch().getID();
            }
        }, 0);
    }
}
