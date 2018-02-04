package us._donut_.skuniversal.autorank;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.armar.plugins.autorank.api.events.RequirementCompleteEvent;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class AutorankRegister {

    public AutorankRegister() {
        //Expressions
        Skript.registerExpression(ExprGlobalTime.class, Number.class, ExpressionType.COMBINED, "[the] global [Autorank] [play[ ]]time of %player%", "%player%'s global [Autorank] [play[ ]]time");
        Skript.registerExpression(ExprLocalTime.class, Number.class, ExpressionType.COMBINED, "[the] local [Autorank] [play[ ]]time of %player%", "%player%'s local [Autorank] [play[ ]]time");
        Skript.registerExpression(ExprAllRequirements.class, String.class, ExpressionType.COMBINED, "[the] [Autorank] (requirements|reqs) of %player%", "%player%'s [Autorank] (requirements|reqs)");
        Skript.registerExpression(ExprCompletedReqs.class, String.class, ExpressionType.COMBINED, "[the] completed [Autorank] (requirements|reqs) of %player%", "%player%'s completed [Autorank] (requirements|reqs)");
        Skript.registerExpression(ExprFailedReqs.class, String.class, ExpressionType.COMBINED, "[the] failed [Autorank] (requirements|reqs) of %player%", "%player%'s failed [Autorank] (requirements|reqs)");
        Skript.registerExpression(ExprCurrentPath.class, String.class, ExpressionType.COMBINED, "[the] [(current|active)] [Autorank] path of %player%", "%player%'s [(current|active)] [Autorank] path");
        Skript.registerExpression(ExprEligiblePaths.class, String.class, ExpressionType.COMBINED, "[the] eligible [Autorank] paths of %player%", "%player%'s eligible [Autorank] paths");
        Skript.registerExpression(ExprCompletedPaths.class, String.class, ExpressionType.COMBINED, "[the] (completed|finished) [Autorank] paths of %player%", "%player%'s (completed|finished) [Autorank] paths");

        //Events
        Skript.registerEvent("Autorank - Requirement Completion", SkUniversalEvent.class, RequirementCompleteEvent.class, "[Autorank] requirement complet(e|ion)")
				.description("Called when a player completes a requirement.")
				.examples("on requirement complete:", "broadcast \"%player% has completed %event-string% requirement!\"");
        EventValues.registerEventValue(RequirementCompleteEvent.class, Player.class, new Getter<Player, RequirementCompleteEvent>() {
            public Player get(RequirementCompleteEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(RequirementCompleteEvent.class, String.class, new Getter<String, RequirementCompleteEvent>() {
            public String get(RequirementCompleteEvent e) {
                return e.getRequirement().getDescription();
            }
        }, 0);
    }
}
