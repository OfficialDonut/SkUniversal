package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.entity.Player;

public class LocketteRegister {
    public static void registerLockette() {
        //Conditions
        Skript.registerCondition(CondLocked.class, "%block% is (locked|protected) [by Lockette]");

        //Expressions
        Skript.registerExpression(ExprOwner.class, Player.class, ExpressionType.COMBINED, "[Lockette] owner of %block%", "%block%'s [Lockette] owner");
    }
}
