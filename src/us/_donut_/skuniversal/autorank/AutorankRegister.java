package us._donut_.skuniversal.autorank;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.armar.plugins.autorank.api.events.RequirementCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AutorankRegister {
    public static void registerAutorank() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Autorank") != null) {

            //Expressions
            Skript.registerExpression(ExprGlobalTime.class, Number.class, ExpressionType.PROPERTY, "[the] global [Autorank] [play[ ]]time of %player%", "%player%'s global [Autorank] [play[ ]]time");
            Skript.registerExpression(ExprLocalTime.class, Number.class, ExpressionType.PROPERTY, "[the] local [Autorank] [play[ ]]time of %player%", "%player%'s local [Autorank] [play[ ]]time");
            Skript.registerExpression(ExprAllRequirements.class, String.class, ExpressionType.PROPERTY, "[the] [Autorank] (requirements|reqs) of %player%", "%player%'s [Autorank] (requirements|reqs)");
            Skript.registerExpression(ExprCompletedReqs.class, String.class, ExpressionType.PROPERTY, "[the] completed [Autorank] (requirements|reqs) of %player%", "%player%'s completed [Autorank] (requirements|reqs)");
            Skript.registerExpression(ExprFailedReqs.class, String.class, ExpressionType.PROPERTY, "[the] failed [Autorank] (requirements|reqs) of %player%", "%player%'s failed [Autorank] (requirements|reqs)");
            Skript.registerExpression(ExprCurrentPath.class, String.class, ExpressionType.PROPERTY, "[the] [(current|active)] [Autorank] path of %player%", "%player%'s [(current|active)] [Autorank] path");
            Skript.registerExpression(ExprEligiblePaths.class, String.class, ExpressionType.PROPERTY, "[the] eligible [Autorank] paths of %player%", "%player%'s eligible [Autorank] paths");
            Skript.registerExpression(ExprCompletedPaths.class, String.class, ExpressionType.PROPERTY, "[the] (completed|finished) [Autorank] paths of %player%", "%player%'s (completed|finished) [Autorank] paths");

            //Events
            Skript.registerEvent("Autorank Requirement Completion", SimpleEvent.class, RequirementCompleteEvent.class, "[on] [Autorank] requirement complet(e|ion)");
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
}
