package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversal;

public class LocketteRegister {
    public static void registerLockette() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Lockette") != null) {
            SkUniversal.hookedPlugins.add("Lockette");

            //Conditions
            Skript.registerCondition(CondLocked.class, "%block% is (locked|protected) [by Lockette]");

            //Expressions
            Skript.registerExpression(ExprOwner.class, Player.class, ExpressionType.COMBINED, "[Lockette] owner of %block%", "%block%'s [Lockette] owner");
        }
    }
}
