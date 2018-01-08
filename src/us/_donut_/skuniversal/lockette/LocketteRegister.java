package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.entity.Player;

public class LocketteRegister {
    public static void registerLockette() {
        //Conditions
        Skript.registerCondition(CondLocked.class, "[the] %block% is (locked|protected) [by Lockette]");

        //Expressions
        Skript.registerExpression(ExprOwner.class, Player.class, ExpressionType.COMBINED, "[the] [Lockette] owner of [the] %block%", "[the] %block%'s [Lockette] owner");
    }
}
