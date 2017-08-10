package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LocketteRegister {
    public static void registerLockette() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Lockette") != null) {

            //Conditions
            Skript.registerCondition(CondLocked.class, "%block% is (locked|protected) [by Lockette]");
            Skript.registerCondition(CondOwner.class, "%player% is [the] [Lockette] owner of %block%", "%block%'s [Lockette] owner is %player%");

            //Expressions
            Skript.registerExpression(ExprOwner.class, Player.class, ExpressionType.PROPERTY, "[Lockette] owner of %block%", "%block%'s [Lockette] owner");
        }
    }
}
